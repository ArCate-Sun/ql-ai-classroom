package cc.qianlang.aiclassroom.proxy.zai.v4.asr;

import cc.qianlang.aiclassroom.proxy.zai.v4.asr.request.TranscriptionRequest;
import cc.qianlang.aiclassroom.proxy.zai.v4.asr.response.TranscriptionResponse;
import cc.qianlang.aiclassroom.proxy.zai.v4.asr.response.TranscriptionStreamChunk;
import cc.qianlang.web.common.core.util.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;

/**
 * 基于 Z.AI 语音转文本 API 的客户端实现。
 * <p>
 * 使用 Spring WebFlux {@link WebClient} 调用 Z.AI 语音转文本接口，
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/audio/transcriptions}。
 * <p>
 * 所需配置项：
 * <ul>
 *   <li>{@code app.zai.api-key} — Z.AI API 密钥（必填）</li>
 *   <li>{@code app.zai.base-url} — API 基础地址，默认 {@code https://open.bigmodel.cn/api}</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
public class ZaiAsrClient {

	private final WebClient webClient;

	/**
	 * 构造 Z.AI 语音转文本客户端。
	 *
	 * @param baseUrl API 基础地址，如 {@code https://open.bigmodel.cn/api}
	 * @param apiKey  Z.AI API 密钥
	 */
	public ZaiAsrClient(String baseUrl, String apiKey) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.defaultHeader("Authorization", "Bearer " + apiKey)
				.build();
	}

	/**
	 * 发起同步语音转文本请求。
	 *
	 * @param request 语音转文本请求参数，{@link TranscriptionRequest#getFile()} 与
	 *                {@link TranscriptionRequest#getFileBase64()} 至少提供其中一个
	 * @return Z.AI API 返回的转录响应
	 * @throws IllegalArgumentException 请求参数不合法
	 * @throws RuntimeException         API 请求失败或响应为空
	 */
	public TranscriptionResponse transcribe(TranscriptionRequest request) {

		// 构建 multipart/form-data 请求体
		MultipartBodyBuilder bodyBuilder = this.buildMultipartBody(request);

		// 发起 POST 请求并阻塞等待响应
		TranscriptionResponse response = this.webClient.post()
				.uri("/audio/transcriptions")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(bodyBuilder.build()))
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("Z.AI ASR API 请求失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToMono(String.class)
				.map(body -> {
					try {
						return ObjectMapperFactory.objectifyOrThrow(body, TranscriptionResponse.class);
					} catch (JsonProcessingException e) {
						throw new RuntimeException("解析 Z.AI ASR 响应失败: " + body, e);
					}
				})
				.block();

		// 校验响应有效性
		if (response == null) {
			throw new RuntimeException("Z.AI ASR API 返回的响应为空");
		}

		return response;
	}

	/**
	 * 发起流式语音转文本请求。
	 *
	 * @param request 语音转文本请求参数，{@link TranscriptionRequest#getFile()} 与
	 *                {@link TranscriptionRequest#getFileBase64()} 至少提供其中一个
	 * @return 响应流，每个元素为一个数据块
	 * @throws IllegalArgumentException 请求参数不合法
	 */
	public Flux<TranscriptionStreamChunk> transcribeStream(TranscriptionRequest request) {

		// 构建 multipart/form-data 请求体
		MultipartBodyBuilder bodyBuilder = this.buildMultipartBody(request);

		// 发起 POST 请求，接收 SSE 流式响应
		return this.webClient.post()
				.uri("/audio/transcriptions")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.body(BodyInserters.fromMultipartData(bodyBuilder.build()))
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("Z.AI ASR API 请求流失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
				})
				// 过滤掉空数据和结束标记 [DONE]
				.filter(sse -> sse.data() != null && !"[DONE]".equals(sse.data().trim()))
				// 解析每个 SSE 数据块为 TranscriptionStreamChunk
				.flatMap(sse -> {
					try {
						String data = sse.data();
						if (data == null) return Mono.empty();
						TranscriptionStreamChunk chunk = ObjectMapperFactory.objectifyOrThrow(data, TranscriptionStreamChunk.class);
						return Mono.just(chunk);
					} catch (JsonProcessingException e) {
						return Mono.error(new RuntimeException("解析 Z.AI ASR 响应流数据块失败: " + sse.data(), e));
					}
				});
	}

	/**
	 * 将 {@link TranscriptionRequest} 的公共字段填充到 multipart 请求体构建器中。
	 * <p>
	 * 注意：{@code stream} 字段由各调用方法按需单独填充。
	 *
	 * @param request 语音转文本请求参数
	 * @return 填充好公共字段的 {@link MultipartBodyBuilder}
	 * @throws IllegalArgumentException {@link TranscriptionRequest#getFile()} 与
	 *                                  {@link TranscriptionRequest#getFileBase64()} 均未提供
	 */
	private MultipartBodyBuilder buildMultipartBody(TranscriptionRequest request) {

		MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();

		bodyBuilder.part("model", request.getModel());

		// 音频来源，file 与 fileBase64 二选一，同时存在时 file 优先
		if (request.getFile() != null) {
			bodyBuilder.part("file", request.getFile());
		}
		if (request.getFileBase64() != null) {
			bodyBuilder.part("file_base64", request.getFileBase64());
		}

		if (request.getPrompt() != null) {
			bodyBuilder.part("prompt", request.getPrompt());
		}
		if (request.getHotwords() != null) {
			// hotwords 为 JSON 数组字符串
			String hotwordsJson = ObjectMapperFactory.jsonify(request.getHotwords());
			if (hotwordsJson != null) bodyBuilder.part("hotwords", hotwordsJson);
		}
		if (request.getStream() != null) {
			bodyBuilder.part("stream", request.getStream());
		}
		if (request.getRequestId() != null) {
			bodyBuilder.part("request_id", request.getRequestId());
		}
		if (request.getUserId() != null) {
			bodyBuilder.part("user_id", request.getUserId());
		}

		return bodyBuilder;
	}

	public static void main(String[] args) {

		ZaiAsrClient client = new ZaiAsrClient(
				"https://open.bigmodel.cn/api/paas/v4",
				"..."
		);

		TranscriptionRequest request = TranscriptionRequest.builder()
				.model("glm-asr-2512")
				.file(new FileSystemResource(new File("/path/to/audio.wav")))
				.build();

		// 同步调用
		TranscriptionResponse response = client.transcribe(request);
		System.out.println("转录结果: " + response.getText());

		// 流式调用
		request = TranscriptionRequest.builder()
				.model("glm-asr-2512")
				.file(new FileSystemResource(new File("/path/to/audio.wav")))
				.stream(true)
				.build();
		client.transcribeStream(request).subscribe(
				chunk -> System.out.print(chunk.getDelta()),
				error -> System.err.println("请求失败: " + error.getMessage()),
				() -> System.out.println("\n转录完成")
		);

		// 等待异步操作完成
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
