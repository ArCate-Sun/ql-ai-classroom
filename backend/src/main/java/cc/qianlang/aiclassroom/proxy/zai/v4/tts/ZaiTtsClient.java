package cc.qianlang.aiclassroom.proxy.zai.v4.tts;

import cc.qianlang.aiclassroom.common.AudioPlayer;
import cc.qianlang.aiclassroom.proxy.zai.v4.tts.request.SpeechRequest;
import cc.qianlang.aiclassroom.proxy.zai.v4.tts.request.TtsResponseFormat;
import cc.qianlang.aiclassroom.proxy.zai.v4.tts.request.TtsVoice;
import cc.qianlang.aiclassroom.proxy.zai.v4.tts.response.SpeechStreamChunk;
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
 * 基于 Z.AI 文本转语音 API 的客户端实现。
 * <p>
 * 使用 Spring WebFlux {@link WebClient} 调用 Z.AI 文本转语音接口，
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/audio/speech}。
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
public class ZaiTtsClient {

	private final WebClient webClient;

	/**
	 * 构造 Z.AI 文本转语音客户端。
	 *
	 * @param baseUrl API 基础地址，如 {@code https://open.bigmodel.cn/api}
	 * @param apiKey  Z.AI API 密钥
	 */
	public ZaiTtsClient(String baseUrl, String apiKey) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.defaultHeader("Authorization", "Bearer " + apiKey)
				.build();
	}

	/**
	 * 发起同步文本转语音请求，返回完整音频二进制数据。
	 * <p>
	 * 采样率建议设置为 24000。
	 *
	 * @param request 文本转语音请求参数（{@code stream} 应为 {@code false} 或不填）
	 * @return 音频二进制数据，格式由 {@code responseFormat} 决定，默认为 PCM
	 * @throws IllegalArgumentException 请求参数序列化失败
	 * @throws RuntimeException         API 请求失败或响应为空
	 */
	public byte[] synthesize(SpeechRequest request) {

		// 将请求对象序列化为 JSON 字符串
		String payload = ObjectMapperFactory.jsonify(request);
		if (payload == null) {
			throw new IllegalArgumentException("序列化请求参数失败: 结果为 null");
		}

		// 发起 POST 请求并阻塞等待响应
		byte[] audioData = this.webClient.post()
				.uri("/audio/speech")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("Z.AI TTS API 请求失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToMono(byte[].class)
				.block();

		// 校验响应有效性
		if (audioData == null || audioData.length == 0) {
			throw new RuntimeException("Z.AI TTS API 返回的音频数据为空");
		}

		return audioData;
	}

	/**
	 * 发起流式文本转语音请求，逐块返回解析后的响应数据块。
	 * <p>
	 * 音频内容位于每个数据块的 {@code choices[0].delta.content}，编码格式由请求体中的
	 * {@code encodeFormat} 决定，默认为 Base64。流式生成时 {@code responseFormat} 仅支持 PCM。
	 *
	 * @param request 文本转语音请求参数（{@code stream} 应为 {@code true}）
	 * @return 响应流，每个元素为一个数据块
	 * @throws IllegalArgumentException 请求参数序列化失败
	 */
	public Flux<SpeechStreamChunk> synthesizeStream(SpeechRequest request) {

		// 将请求对象序列化为 JSON 字符串
		String payload = ObjectMapperFactory.jsonify(request);
		if (payload == null) {
			throw new IllegalArgumentException("序列化请求参数失败: 结果为 null");
		}

		// 发起 POST 请求，接收 SSE 流式响应
		return this.webClient.post()
				.uri("/audio/speech")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("Z.AI TTS API 请求流失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
				})
				// 过滤掉空数据和结束标记 [DONE]
				.filter(sse -> sse.data() != null && !"[DONE]".equals(sse.data().trim()))
				// 解析每个 SSE 数据块为 SpeechStreamChunk
				.flatMap(sse -> {
					try {
						String data = sse.data();
						if (data == null) return Mono.empty();
						SpeechStreamChunk chunk = ObjectMapperFactory.objectifyOrThrow(data, SpeechStreamChunk.class);
						return Mono.just(chunk);
					} catch (JsonProcessingException e) {
						return Mono.error(new RuntimeException("解析 Z.AI TTS 响应流数据块失败: " + sse.data(), e));
					}
				});
	}

	public static void main(String[] args) throws Exception {

		ZaiTtsClient client = new ZaiTtsClient(
				"https://open.bigmodel.cn/api/paas/v4",
				"..."
		);

		SpeechRequest request = SpeechRequest.builder()
				.model("glm-tts")
				.input("你好，今天天气怎么样。")
				.voice(TtsVoice.TONGTONG)
				.responseFormat(TtsResponseFormat.WAV)
				.watermarkEnabled(false)
				.build();

		// 同步调用，获取完整音频数据并播放
		byte[] audioData = client.synthesize(request);
		System.out.println("音频大小: " + audioData.length + " 字节");
		AudioPlayer.playWav(audioData);

		// 流式调用，逐块接收编码音频
		SpeechRequest streamRequest = SpeechRequest.builder()
				.model("glm-tts")
				.input("你好，今天天气怎么样。")
				.voice(TtsVoice.TONGTONG)
				.watermarkEnabled(false)
				.stream(true)
				.build();
		// 逐块提取 choices[0].delta.content，解码 Base64 后拼接
		ByteArrayOutputStream streamOutput = new ByteArrayOutputStream();
		client.synthesizeStream(streamRequest)
				.doOnNext(chunk -> {
					if (chunk.getChoices() == null || chunk.getChoices().isEmpty()) return;
					SpeechStreamChunk.Choice.Delta delta = chunk.getChoices().getFirst().getDelta();
					if (delta == null || delta.getContent() == null) return;
					byte[] chunkBytes = Base64.getDecoder().decode(delta.getContent());
					streamOutput.writeBytes(chunkBytes);
					System.out.println("收到音频块，长度: " + chunkBytes.length + " 字节");
				})
				.doOnError(error -> System.err.println("请求失败: " + error.getMessage()))
				.doOnComplete(() -> System.out.println("转换完成"))
				.blockLast();

		AudioPlayer.playPcm(streamOutput.toByteArray());
	}

}
