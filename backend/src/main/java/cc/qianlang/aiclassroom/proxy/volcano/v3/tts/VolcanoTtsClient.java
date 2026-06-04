package cc.qianlang.aiclassroom.proxy.volcano.v3.tts;

import cc.qianlang.aiclassroom.common.AudioPlayer;
import cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request.TtsAudioFormat;
import cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request.TtsAudioParameters;
import cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request.TtsRequest;
import cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request.TtsSpeaker;
import cc.qianlang.aiclassroom.proxy.volcano.v3.tts.response.*;
import cc.qianlang.web.common.core.util.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * 基于火山引擎文本转语音 API 的客户端实现（SSE 单向流式）。
 * <p>
 * 使用 Spring WebFlux {@link WebClient} 调用火山引擎 TTS SSE 接口，
 * 接口地址：{@code POST https://openspeech.bytedance.com/api/v3/tts/unidirectional/sse}。
 * <p>
 * 所需配置项：
 * <ul>
 *   <li>{@code app.volcano.api-key} — 火山引擎 API Key（新版控制台，必填）</li>
 *   <li>{@code app.volcano.resource-id} — 资源 ID，用于指定模型版本及计费方式，
 *       如 {@code "seed-tts-2.0"}（必填）</li>
 *   <li>{@code app.volcano.base-url} — API 基础地址，
 *       默认 {@code https://openspeech.bytedance.com/api/v3}</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
public class VolcanoTtsClient {

	private final WebClient webClient;

	/**
	 * 构造火山引擎 TTS 客户端（新版控制台鉴权）。
	 *
	 * @param baseUrl API 基础地址，如 {@code https://openspeech.bytedance.com/api/v3}
	 * @param apiKey  火山引擎 API Key
	 */
	public VolcanoTtsClient(String baseUrl, String apiKey) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.defaultHeader("X-Api-Key", apiKey)
				.build();
	}

	/**
	 * 发起 SSE 流式文本转语音请求，逐块返回类型化响应数据块。
	 * <p>
	 * 每个数据块为 {@link TtsChunk} 的子类型，依据 SSE {@code event} 字段区分：
	 * <ul>
	 *   <li>{@link TtsResponseChunk}（event=352）：{@code audioData} 含 Base64 编码的音频片段</li>
	 *   <li>{@link TtsSentenceEndChunk}（event=351）：{@code sentence} 含句子时间戳</li>
	 *   <li>{@link TtsSessionFinishChunk}（event=152）：{@code code=20000000}，合成结束标志</li>
	 *   <li>{@link TtsSessionCancelChunk}（event=151）：会话取消</li>
	 *   <li>{@link TtsSessionFailedChunk}（event=153）：会话失败</li>
	 * </ul>
	 *
	 * @param request 文本转语音请求参数
	 * @return 响应流，每个元素为一个类型化数据块
	 * @throws IllegalArgumentException 请求参数序列化失败
	 */
	public Flux<TtsChunk> streamSynthesize(TtsRequest request) {

		// 将请求对象序列化为 JSON 字符串
		String payload = ObjectMapperFactory.jsonify(request);
		if (payload == null) {
			throw new IllegalArgumentException("序列化请求参数失败: 结果为 null");
		}

		System.out.println(payload);
		// 发起 POST 请求，接收 SSE 流式响应
		return this.webClient.post()
				.uri("/tts/unidirectional/sse")
				.headers(headers -> {
					headers.add("X-Api-Resource-Id", request.getResourceId());
					if (request.getRequestId() != null) {
						headers.add("X-Api-Request-Id", request.getRequestId());
					}
					if (request.getRequireUsageTokensReturn() != null) {
						headers.add(
								"X-Control-Require-Usage-Tokens-Return",
								request.getRequireUsageTokensReturn().getCode()
						);
					}
				})
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("火山引擎 TTS API 请求失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
				})
				// 过滤掉空数据帧
				.filter(sse -> sse.data() != null && !sse.data().isBlank())
				// 解析 SSE 数据块并按事件类型转换为对应的 TtsChunk 子类型
				.flatMap(sse -> {
					try {
						String data = sse.data();
						if (data == null) return Mono.empty();
						TtsEventType eventType = TtsEventType.fromCode(sse.event());
						TtsChunk chunk = switch (eventType) {
							case TTS_RESPONSE -> ObjectMapperFactory.objectifyOrThrow(data, TtsResponseChunk.class);
							case TTS_SENTENCE_END ->
									ObjectMapperFactory.objectifyOrThrow(data, TtsSentenceEndChunk.class);
							case SESSION_FINISH ->
									ObjectMapperFactory.objectifyOrThrow(data, TtsSessionFinishChunk.class);
							case SESSION_CANCEL ->
									ObjectMapperFactory.objectifyOrThrow(data, TtsSessionCancelChunk.class);
							case SESSION_FAILED -> {
								ObjectMapperFactory.objectifyOrThrow(data, TtsSessionFailedChunk.class);
								throw new RuntimeException("火山引擎 TTS 会话失败: " + data);
							}
							default -> throw new RuntimeException("未知的火山引擎 TTS 事件: " + sse.event());
						};
						return Mono.just(chunk);
					} catch (JsonProcessingException e) {
						return Mono.error(new RuntimeException("解析火山引擎 TTS 响应流数据块失败. event: " + sse.event() + ", data: " + sse.data(), e));
					}
				});
	}

	public static void main(String[] args) throws Exception {

		VolcanoTtsClient client = new VolcanoTtsClient(
				"https://openspeech.bytedance.com/api/v3",
				"..."
		);

		TtsRequest request = TtsRequest.builder()
				.resourceId("seed-tts-2.0")
				.reqParams(TtsRequest.Parameters.builder()
						.text("你好！")
						.speaker(TtsSpeaker.VIVI_2_0)
						.audioParams(TtsAudioParameters.builder()
								.format(TtsAudioFormat.PCM)
								.sampleRate(24000)
								.build())
						.build())
				.build();

		// 流式调用，逐块接收 Base64 编码的音频数据并拼接
		ByteArrayOutputStream streamOutput = new ByteArrayOutputStream();
		client.streamSynthesize(request)
				.doOnNext(chunk -> {
					if (!(chunk instanceof TtsResponseChunk audioChunk)) return;
					byte[] chunkBytes = Base64.getDecoder().decode(audioChunk.getData());
					streamOutput.writeBytes(chunkBytes);
					System.out.println("收到音频块，长度: " + chunkBytes.length + " 字节");
				})
				.doOnError(error -> System.err.println("请求失败: " + error.getMessage()))
				.doOnComplete(() -> System.out.println("转换完成"))
				.blockLast();

		AudioPlayer.playPcm(streamOutput.toByteArray());
	}

}
