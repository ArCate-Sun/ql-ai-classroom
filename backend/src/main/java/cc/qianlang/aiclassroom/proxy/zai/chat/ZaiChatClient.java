package cc.qianlang.aiclassroom.proxy.zai.chat;

import cc.qianlang.aiclassroom.proxy.zai.chat.request.ChatRequest;
import cc.qianlang.aiclassroom.proxy.zai.chat.request.Thinking;
import cc.qianlang.aiclassroom.proxy.zai.chat.request.message.UserMessage;
import cc.qianlang.aiclassroom.proxy.zai.chat.response.ChatResponse;
import cc.qianlang.aiclassroom.proxy.zai.chat.response.ChatStreamChunk;
import cc.qianlang.aiclassroom.proxy.zai.chat.response.ChatStreamChunkChoice;
import cc.qianlang.web.common.core.util.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 基于 Z.AI API 的 LLM 对话客户端实现。
 * <p>
 * 使用 Spring WebFlux {@link WebClient} 直接调用 Z.AI 对话补全接口，
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/chat/completions}。
 * <p>
 * 所需配置项：
 * <ul>
 *   <li>{@code app.zai.api-key} — Z.AI API 密钥（必填）</li>
 *   <li>{@code app.zai.base-url} — API 基础地址，默认 {@code https://open.bigmodel.cn/api}</li>
 *   <li>{@code app.zai.model} — 模型 ID，默认 {@code glm-5.1}</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
public class ZaiChatClient {

	private final WebClient webClient;

	/**
	 * 构造 Z.AI 聊天客户端。
	 *
	 * @param baseUrl API 基础地址，如 {@code https://open.bigmodel.cn/api}
	 * @param apiKey  Z.AI API 密钥
	 */
	public ZaiChatClient(String baseUrl, String apiKey) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.defaultHeader("Authorization", "Bearer " + apiKey)
				.build();
	}


	/**
	 * 发起同步聊天请求。
	 *
	 * @param request 聊天请求参数
	 * @return Z.AI API 返回的完整响应
	 * @throws IllegalArgumentException 请求参数序列化失败
	 * @throws RuntimeException         API 请求失败或响应为空
	 */
	public ChatResponse chat(ChatRequest request) {

		// 将请求对象序列化为 JSON 字符串
		String payload = ObjectMapperFactory.jsonify(request);
		if (payload == null) {
			throw new IllegalArgumentException("序列化请求参数失败: 结果为 null");
		}

		// 发起 POST 请求并阻塞等待响应
		ChatResponse response = this.webClient.post()
				.uri("/paas/v4/chat/completions")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("Z.AI API 请求失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToMono(String.class)
				.map(body -> {
					try {
						return ObjectMapperFactory.objectifyOrThrow(body, ChatResponse.class);
					} catch (JsonProcessingException e) {
						throw new RuntimeException("解析 Z.AI 响应失败: " + body, e);
					}
				})
				.block();

		// 校验响应有效性
		if (response == null || response.getChoices().isEmpty()) {
			throw new RuntimeException("Z.AI API 返回的响应为空或缺少 choices");
		}

		return response;
	}

	/**
	 * 发起流式聊天请求。
	 *
	 * @param request 聊天请求参数
	 * @return 响应流，每个元素为一个数据块
	 * @throws IllegalArgumentException 请求参数序列化失败
	 */
	public Flux<ChatStreamChunk> chatStream(ChatRequest request) {

		// 将请求对象序列化为 JSON 字符串
		String payload = ObjectMapperFactory.jsonify(request);
		if (payload == null) {
			throw new IllegalArgumentException("序列化请求参数失败: 结果为 null");
		}

		// 发起 POST 请求，接收 SSE 流式响应
		return this.webClient.post()
				.uri("/paas/v4/chat/completions")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("Z.AI API 请求流失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToFlux(new ParameterizedTypeReference<ServerSentEvent<String>>() {
				})
				// 过滤掉空数据和结束标记 [DONE]
				.filter(sse -> sse.data() != null && !"[DONE]".equals(sse.data().trim()))
				// 解析每个 SSE 数据块为 ChatStreamChunk
				.flatMap(sse -> {
					try {
						String data = sse.data();
						if (data == null) return Mono.empty();
						ChatStreamChunk chunk = ObjectMapperFactory.objectifyOrThrow(data, ChatStreamChunk.class);
						return Mono.just(chunk);
					} catch (JsonProcessingException e) {
						return Mono.error(new RuntimeException("解析 Z.AI 响应流数据块失败: " + sse.data(), e));
					}
				});
	}

	public static void main(String[] args) {

		ZaiChatClient client = new ZaiChatClient(
				"https://open.bigmodel.cn/api",
				"0087ddfd4e6541dcbeb73c759fccf801.9Aykl2Q1IBkqFl6c"
		);

		ChatRequest request = ChatRequest.builder()
				.model("glm-4.5")
				.messages(
						List.of(
								UserMessage.builder().content("你好，请介绍你自己").build()
						)
				)
				.thinking(Thinking.enabled())
				.stream(true)
				.build();

		Flux<ChatStreamChunk> response = client.chatStream(request);

		// 订阅响应流，打印每个数据块
		response.subscribe(
				chunk -> {
					if (!chunk.getChoices().isEmpty()) {
						ChatStreamChunkChoice.Delta delta = chunk.getChoices().getFirst().getDelta();
						String reasoningContent = delta.getReasoningContent();
						String content = delta.getContent();
						if (reasoningContent != null) {
							System.out.print("\u001B[32m" + reasoningContent + "\u001B[0m");
						}
						if (content != null) {
							System.out.print(content);
						}
					}
				},
				error -> System.err.println("请求失败: " + error.getMessage()),
				() -> System.out.println("\n请求完成")
		);

		// 等待异步操作完成
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
