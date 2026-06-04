package cc.qianlang.aiclassroom.proxy.volcano.v3.chat;

import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.ChatRequest;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.MessageContent;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.UserMessage;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content.ImageContent;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content.TextContent;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response.ChatResponse;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response.ChatStreamChunk;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response.ChatStreamChunkChoice;
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
 * 基于火山方舟 API 的 LLM 对话客户端实现。
 * <p>
 * 使用 Spring WebFlux {@link WebClient} 直接调用火山方舟 Chat Completions 接口，
 * 接口地址：{@code POST https://ark.cn-beijing.volces.com/api/v3/chat/completions}。
 * <p>
 * 所需配置项：
 * <ul>
 *   <li>{@code app.volcano.api-key} — 火山方舟 API 密钥（必填）</li>
 *   <li>{@code app.volcano.base-url} — API 基础地址，默认 {@code https://ark.cn-beijing.volces.com}</li>
 *   <li>{@code app.volcano.model} — 模型 ID 或 Endpoint ID，例如 {@code "doubao-seed-1.8"}</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
public class VolcanoChatClient {

	private final WebClient webClient;

	/**
	 * 构造火山方舟聊天客户端。
	 *
	 * @param baseUrl API 基础地址，如 {@code https://ark.cn-beijing.volces.com}
	 * @param apiKey  火山方舟 API 密钥
	 */
	public VolcanoChatClient(String baseUrl, String apiKey) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.defaultHeader("Authorization", "Bearer " + apiKey)
				.build();
	}


	/**
	 * 发起同步聊天请求。
	 *
	 * @param request 聊天请求参数
	 * @return 火山方舟 API 返回的完整响应
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
				.uri("/chat/completions")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("火山方舟 API 请求失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToMono(String.class)
				.map(body -> {
					try {
						return ObjectMapperFactory.objectifyOrThrow(body, ChatResponse.class);
					} catch (JsonProcessingException e) {
						throw new RuntimeException("解析火山方舟响应失败: " + body, e);
					}
				})
				.block();

		// 校验响应有效性
		if (response == null || response.getChoices().isEmpty()) {
			throw new RuntimeException("火山方舟 API 返回的响应为空或缺少 choices");
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
				.uri("/chat/completions")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.TEXT_EVENT_STREAM)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("火山方舟 API 请求流失败 [" + clientResponse.statusCode() + "]: " + body)
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
						return Mono.error(new RuntimeException("解析火山方舟响应流数据块失败: " + sse.data(), e));
					}
				});
	}

	public static void main(String[] args) {

		VolcanoChatClient client = new VolcanoChatClient(
				"https://ark.cn-beijing.volces.com/api/v3",
				"..."
		);

		UserMessage message = UserMessage.builder()
				.content(
						MessageContent.parts(
								List.of(
										TextContent.builder().text("你看见了什么？").build(),
										ImageContent.builder().imageUrl(
												ImageContent.ImageUrl.builder().url("https://ark-project.tos-cn-beijing.volces.com/doc_image/ark_demo_img_1.png").build()
										).build()
								)
						)
				)
				.build();

		ChatRequest request = ChatRequest.builder()
				.model("doubao-seed-2-0-lite-260215")
				.messages(List.of(message))
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
