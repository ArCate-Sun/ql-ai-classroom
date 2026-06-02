package cc.qianlang.aiclassroom.proxy.ai.llm;

import cc.qianlang.aiclassroom.proxy.ai.llm.model.*;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.model.output.TokenUsage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OpenAiCompatibleChatClientTest {

	@Mock
	private ChatModel chatModel;

	@Mock
	private StreamingChatModel streamingChatModel;

	private OpenAiCompatibleChatClient client;

	@BeforeEach
	void setUp() {
		this.client = new OpenAiCompatibleChatClient(
				"https://api.example.com", "test-key", "test-model",
				this.chatModel, this.streamingChatModel
		);
	}

	// ----- chat() -----

	/**
	 * 测试目的: 验证 chat() 能正确返回模型文本内容和 token 用量。
	 * 测试方案: mock chatModel 返回包含文本和 token 用量的 ChatResponse，调用 chat()。
	 * 期待结果: 响应中的 content 与 tokenUsage 各字段均正确映射。
	 */
	@Test
	void chat_正常响应包含文本和token用量() {
		// 构造 mock 响应，包含文本和完整 token 用量
		ChatResponse lcResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("模型回复"))
				.tokenUsage(new TokenUsage(10, 20, 30))
				.build();
		when(this.chatModel.chat(any(ChatRequest.class))).thenReturn(lcResponse);

		LlmChatRequest request = LlmChatRequest.of(List.of(new UserMessage("你好")));
		LlmChatResponse response = this.client.chat(request);

		assertThat(response.content()).isEqualTo("模型回复");
		assertThat(response.tokenUsage()).isNotNull();
		assertThat(response.tokenUsage().promptTokens()).isEqualTo(10);
		assertThat(response.tokenUsage().completionTokens()).isEqualTo(20);
		assertThat(response.tokenUsage().totalTokens()).isEqualTo(30);
	}

	/**
	 * 测试目的: 验证模型服务不返回 token 用量时，响应中的 tokenUsage 为 null。
	 * 测试方案: mock chatModel 返回不含 tokenUsage 的 ChatResponse，调用 chat()。
	 * 期待结果: response.tokenUsage() 为 null，不抛出异常。
	 */
	@Test
	void chat_模型不返回token用量时tokenUsage为null() {
		ChatResponse lcResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("无用量"))
				.build();
		when(this.chatModel.chat(any(ChatRequest.class))).thenReturn(lcResponse);

		LlmChatResponse response = this.client.chat(LlmChatRequest.of(List.of(new UserMessage("hi"))));

		assertThat(response.tokenUsage()).isNull();
	}

	/**
	 * 测试目的: 验证 TokenUsage 中各分项 count 为 null 时，对应字段被填充为 0。
	 * 测试方案: 构造 inputTokenCount 和 outputTokenCount 为 null、只有 totalTokenCount 有值的
	 * TokenUsage，调用 chat()。
	 * 期待结果: promptTokens 和 completionTokens 为 0，totalTokens 正确映射。
	 */
	@Test
	void chat_tokenUsage分项count为null时填充为0() {
		// 部分模型服务只返回总量，不返回分项，此时 inputTokenCount/outputTokenCount 为 null
		ChatResponse lcResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("ok"))
				.tokenUsage(new TokenUsage(null, null, 15))
				.build();
		when(this.chatModel.chat(any(ChatRequest.class))).thenReturn(lcResponse);

		LlmChatResponse response = this.client.chat(LlmChatRequest.of(List.of(new UserMessage("hi"))));

		assertThat(response.tokenUsage()).isNotNull();
		assertThat(response.tokenUsage().promptTokens()).isEqualTo(0);
		assertThat(response.tokenUsage().completionTokens()).isEqualTo(0);
		assertThat(response.tokenUsage().totalTokens()).isEqualTo(15);
	}

	/**
	 * 测试目的: 验证 chat() 将消息列表中每种角色正确转换为 LangChain4j 对应类型，且消息内容不丢失。
	 * 测试方案: 构造含 SYSTEM/USER/ASSISTANT/USER 四条消息的请求，通过 ArgumentCaptor 捕获
	 * 实际传入 chatModel 的 ChatRequest，检查各消息的类型与文本内容。
	 * 期待结果: 四条消息的类型和内容均与原始消息一致。
	 */
	@Test
	void chat_消息角色和内容正确映射为LangChain4j类型() {
		ChatResponse lcResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("ok"))
				.build();
		when(this.chatModel.chat(any(ChatRequest.class))).thenReturn(lcResponse);

		List<LlmMessage> messages = List.of(
				new SystemMessage("你是助手"),
				new UserMessage("问题"),
				new AssistantMessage("上轮回答"),
				new UserMessage("追问")
		);
		this.client.chat(LlmChatRequest.of(messages));

		// 捕获实际传给底层模型的请求，检查消息类型和内容映射
		ArgumentCaptor<ChatRequest> captor = ArgumentCaptor.forClass(ChatRequest.class);
		verify(this.chatModel).chat(captor.capture());
		List<ChatMessage> sent = captor.getValue().messages();

		assertThat(sent).hasSize(4);
		assertThat(sent.get(0)).isInstanceOfSatisfying(
				dev.langchain4j.data.message.SystemMessage.class,
				m -> assertThat(m.text()).isEqualTo("你是助手"));
		assertThat(sent.get(1)).isInstanceOfSatisfying(
				dev.langchain4j.data.message.UserMessage.class,
				m -> assertThat(m.singleText()).isEqualTo("问题"));
		assertThat(sent.get(2)).isInstanceOfSatisfying(
				dev.langchain4j.data.message.AiMessage.class,
				m -> assertThat(m.text()).isEqualTo("上轮回答"));
		assertThat(sent.get(3)).isInstanceOfSatisfying(
				dev.langchain4j.data.message.UserMessage.class,
				m -> assertThat(m.singleText()).isEqualTo("追问"));
	}

	/**
	 * 测试目的: 验证请求中的 temperature 和 maxTokens 被正确传递到底层 ChatRequest。
	 * 测试方案: 构造含 temperature=0.7、maxTokens=512 的请求，通过 ArgumentCaptor 捕获
	 * 实际传入 chatModel 的 ChatRequest，检查对应字段。
	 * 期待结果: ChatRequest 中 temperature 为 0.7，maxOutputTokens 为 512。
	 */
	@Test
	void chat_temperature和maxTokens正确传递到底层请求() {
		ChatResponse lcResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("ok"))
				.build();
		when(this.chatModel.chat(any(ChatRequest.class))).thenReturn(lcResponse);

		LlmChatRequest request = new LlmChatRequest(List.of(new UserMessage("hi")), 0.7, 512);
		this.client.chat(request);

		ArgumentCaptor<ChatRequest> captor = ArgumentCaptor.forClass(ChatRequest.class);
		verify(this.chatModel).chat(captor.capture());
		assertThat(captor.getValue().temperature()).isEqualTo(0.7);
		assertThat(captor.getValue().maxOutputTokens()).isEqualTo(512);
	}

	/**
	 * 测试目的: 验证请求中 temperature 和 maxTokens 为 null 时，不向底层 ChatRequest 传递这两个参数。
	 * 测试方案: 使用 LlmChatRequest.of()（temperature 和 maxTokens 均为 null），通过 ArgumentCaptor
	 * 捕获实际传入 chatModel 的 ChatRequest，检查对应字段为 null。
	 * 期待结果: ChatRequest 中 temperature 和 maxOutputTokens 均为 null，由模型使用默认值。
	 */
	@Test
	void chat_temperature和maxTokens为null时不传递到底层请求() {
		ChatResponse lcResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("ok"))
				.build();
		when(this.chatModel.chat(any(ChatRequest.class))).thenReturn(lcResponse);

		// LlmChatRequest.of() 的 temperature 和 maxTokens 均为 null
		LlmChatRequest request = LlmChatRequest.of(List.of(new UserMessage("hi")));
		this.client.chat(request);

		ArgumentCaptor<ChatRequest> captor = ArgumentCaptor.forClass(ChatRequest.class);
		verify(this.chatModel).chat(captor.capture());
		assertThat(captor.getValue().temperature()).isNull();
		assertThat(captor.getValue().maxOutputTokens()).isNull();
	}

	// ----- chatStream() -----

	/**
	 * 测试目的: 验证 chatStream() 正确将多个增量 chunk 和最终完成 chunk 发布到 Flux。
	 * 测试方案: mock streamingChatModel 依次触发两次 onPartialResponse 和一次 onCompleteResponse，
	 * 使用 StepVerifier 逐个断言 Flux 中的元素。
	 * 期待结果: 前两个 chunk finished=false、delta 有内容；最后一个 chunk finished=true、delta 为空、
	 * tokenUsage 正确；Flux 正常结束。
	 */
	@Test
	void chatStream_多个增量chunk后以完成chunk结束() {
		ChatResponse finalResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("你好世界"))
				.tokenUsage(new TokenUsage(5, 10, 15))
				.build();

		// 模拟流式回调：两段增量 + 完成信号
		doAnswer(invocation -> {
			StreamingChatResponseHandler handler = invocation.getArgument(1);
			handler.onPartialResponse("你好");
			handler.onPartialResponse("世界");
			handler.onCompleteResponse(finalResponse);
			return null;
		}).when(this.streamingChatModel).chat(any(ChatRequest.class), any(StreamingChatResponseHandler.class));

		Flux<LlmChatStreamChunk> flux = this.client.chatStream(LlmChatRequest.of(List.of(new UserMessage("hi"))));

		StepVerifier.create(flux)
				.assertNext(chunk -> {
					assertThat(chunk.delta()).isEqualTo("你好");
					assertThat(chunk.finished()).isFalse();
					assertThat(chunk.tokenUsage()).isNull();
				})
				.assertNext(chunk -> {
					assertThat(chunk.delta()).isEqualTo("世界");
					assertThat(chunk.finished()).isFalse();
				})
				.assertNext(chunk -> {
					// 完成 chunk：delta 为空，finished=true，附带 token 用量
					assertThat(chunk.delta()).isEmpty();
					assertThat(chunk.finished()).isTrue();
					assertThat(chunk.tokenUsage()).isNotNull();
					assertThat(chunk.tokenUsage().totalTokens()).isEqualTo(15);
				})
				.verifyComplete();
	}

	/**
	 * 测试目的: 验证模型服务回调 onError 时，Flux 以对应异常终止。
	 * 测试方案: mock streamingChatModel 触发 onError(RuntimeException("网络超时"))，
	 * 使用 StepVerifier 断言 Flux 以该异常结束。
	 * 期待结果: Flux 发出错误信号，错误消息为 "网络超时"，不产生任何正常元素。
	 */
	@Test
	void chatStream_模型回调onError时Flux以异常终止() {
		doAnswer(invocation -> {
			StreamingChatResponseHandler handler = invocation.getArgument(1);
			handler.onError(new RuntimeException("网络超时"));
			return null;
		}).when(this.streamingChatModel).chat(any(ChatRequest.class), any(StreamingChatResponseHandler.class));

		Flux<LlmChatStreamChunk> flux = this.client.chatStream(LlmChatRequest.of(List.of(new UserMessage("hi"))));

		StepVerifier.create(flux)
				.expectErrorMessage("网络超时")
				.verify();
	}

	/**
	 * 测试目的: 验证流式完成时模型未返回 token 用量，完成 chunk 的 tokenUsage 为 null。
	 * 测试方案: mock streamingChatModel 触发不含 tokenUsage 的 onCompleteResponse，
	 * 使用 StepVerifier 断言最后一个 chunk 的 tokenUsage 为 null。
	 * 期待结果: 完成 chunk finished=true，tokenUsage=null，Flux 正常结束。
	 */
	@Test
	void chatStream_完成时无token用量则完成chunk的tokenUsage为null() {
		ChatResponse finalResponse = ChatResponse.builder()
				.aiMessage(new AiMessage("done"))
				.build();

		doAnswer(invocation -> {
			StreamingChatResponseHandler handler = invocation.getArgument(1);
			handler.onPartialResponse("done");
			handler.onCompleteResponse(finalResponse);
			return null;
		}).when(this.streamingChatModel).chat(any(ChatRequest.class), any(StreamingChatResponseHandler.class));

		Flux<LlmChatStreamChunk> flux = this.client.chatStream(LlmChatRequest.of(List.of(new UserMessage("hi"))));

		// 跳过增量 chunk（其行为已由 chatStream_多个增量chunk后以完成chunk结束 覆盖），只断言完成 chunk
		StepVerifier.create(flux)
				.expectNextCount(1)
				.assertNext(chunk -> {
					assertThat(chunk.finished()).isTrue();
					assertThat(chunk.tokenUsage()).isNull();
				})
				.verifyComplete();
	}

}
