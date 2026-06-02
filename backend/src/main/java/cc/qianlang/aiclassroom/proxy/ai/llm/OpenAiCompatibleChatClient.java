package cc.qianlang.aiclassroom.proxy.ai.llm;

import cc.qianlang.aiclassroom.proxy.ai.llm.model.*;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 基于 OpenAI Chat Completions API 规范的 LLM 对话客户端。
 *
 * <p>可对接任何遵循 OpenAI API 规范的模型服务（如 OpenAI、DeepSeek、通义千问等）。
 *
 * <p>原命名 {@code OpenAiLlmProvider} 已废弃，请使用本类。
 */
@NullMarked
public class OpenAiCompatibleChatClient implements LlmChatClient {

	/**
	 * 模型服务的 Base URL，例如 {@code https://api.openai.com/v1}
	 */
	@Getter
	private final String baseUrl;

	/**
	 * 模型服务的 API Key
	 */
	@Getter
	private final String apiKey;

	/**
	 * 调用的模型名称，例如 {@code gpt-4o}、{@code deepseek-chat}
	 */
	@Getter
	private final String model;

	private final ChatModel chatModel;
	private final StreamingChatModel streamingChatModel;

	public OpenAiCompatibleChatClient(String baseUrl, String apiKey, String model) {
		this.baseUrl = baseUrl;
		this.apiKey = apiKey;
		this.model = model;
		this.chatModel = OpenAiChatModel.builder()
				.baseUrl(baseUrl)
				.apiKey(apiKey)
				.modelName(model)
				.build();
		this.streamingChatModel = OpenAiStreamingChatModel.builder()
				.baseUrl(baseUrl)
				.apiKey(apiKey)
				.modelName(model)
				.build();
	}

	OpenAiCompatibleChatClient(
			String baseUrl, String apiKey, String model,
			ChatModel chatModel, StreamingChatModel streamingChatModel
	) {
		this.baseUrl = baseUrl;
		this.apiKey = apiKey;
		this.model = model;
		this.chatModel = chatModel;
		this.streamingChatModel = streamingChatModel;
	}

	@Override
	public LlmChatResponse chat(LlmChatRequest request) {
		ChatResponse response = this.chatModel.chat(buildChatRequest(request));
		return new LlmChatResponse(
				response.aiMessage().text(),
				toTokenUsage(response.tokenUsage())
		);
	}

	@Override
	public Flux<LlmChatStreamChunk> chatStream(LlmChatRequest request) {
		ChatRequest lcRequest = buildChatRequest(request);
		return Flux.create(sink -> this.streamingChatModel.chat(lcRequest, new StreamingChatResponseHandler() {
			@Override
			public void onPartialResponse(String delta) {
				sink.next(new LlmChatStreamChunk(delta, false, null));
			}

			@Override
			public void onCompleteResponse(ChatResponse response) {
				// 流结束时附带完整 token 用量（部分服务不返回，此时为 null）
				sink.next(new LlmChatStreamChunk("", true, toTokenUsage(response.tokenUsage())));
				sink.complete();
			}

			@Override
			public void onError(Throwable error) {
				sink.error(error);
			}
		}));
	}

	// ----- 类型转换 -----

	private ChatRequest buildChatRequest(LlmChatRequest request) {
		// 将我方消息类型逐一映射为 LangChain4j 消息类型
		List<ChatMessage> messages = request.messages().stream()
				.map(OpenAiCompatibleChatClient::toLangChain4jMessage)
				.toList();

		ChatRequest.Builder builder = ChatRequest.builder().messages(messages);
		// 可选参数为 null 时不传递，由模型使用默认值
		if (request.temperature() != null) {
			builder.temperature(request.temperature());
		}
		if (request.maxTokens() != null) {
			builder.maxOutputTokens(request.maxTokens());
		}
		return builder.build();
	}

	private static ChatMessage toLangChain4jMessage(LlmMessage message) {
		return switch (message) {
			case cc.qianlang.aiclassroom.proxy.ai.llm.model.SystemMessage m ->
					dev.langchain4j.data.message.SystemMessage.from(m.content());
			case cc.qianlang.aiclassroom.proxy.ai.llm.model.UserMessage m ->
					new dev.langchain4j.data.message.UserMessage(m.content());
			case AssistantMessage m -> new AiMessage(m.content());
		};
	}

	/**
	 * 将 LangChain4j 的 TokenUsage 转为我方类型；入参为 null 时返回 null。
	 */
	@Nullable
	private static TokenUsage toTokenUsage(dev.langchain4j.model.output.@Nullable TokenUsage tu) {
		if (tu == null) return null;
		// LangChain4j 的 token 计数字段为 Integer（可能为 null），缺失时取 0
		return new TokenUsage(
				tu.inputTokenCount() != null ? tu.inputTokenCount() : 0,
				tu.outputTokenCount() != null ? tu.outputTokenCount() : 0,
				tu.totalTokenCount() != null ? tu.totalTokenCount() : 0
		);
	}

	public static void main(String[] args) {
		OpenAiCompatibleChatClient client = new OpenAiCompatibleChatClient(
				"https://api.deepseek.com",
				"sk-...",
				"deepseek-v4-flash"
		);
		Flux<LlmChatStreamChunk> response = client.chatStream(LlmChatRequest.of(List.of(new UserMessage("hi"))));
		response.doOnNext(chunk -> {
			System.out.print(chunk.delta());
			if (chunk.finished()) {
				System.out.println();
				System.out.println("Token usage: " + chunk.tokenUsage());
			}
		}).blockLast();
	}
}
