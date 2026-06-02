package cc.qianlang.aiclassroom.proxy.ai.llm;

import cc.qianlang.aiclassroom.proxy.ai.llm.model.LlmChatRequest;
import cc.qianlang.aiclassroom.proxy.ai.llm.model.LlmChatResponse;
import cc.qianlang.aiclassroom.proxy.ai.llm.model.LlmChatStreamChunk;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Flux;

/**
 * LLM 对话客户端接口，抽象对 Chat Completions 协议的调用。
 *
 * <p>具体实现可对接任何兼容 OpenAI Chat Completions API 的模型服务。
 */
@NullMarked
public interface LlmChatClient {

	/**
	 * 阻塞式对话，等待模型完整输出后返回。
	 *
	 * @param request 对话请求
	 * @return 模型完整响应
	 */
	LlmChatResponse chat(LlmChatRequest request);

	/**
	 * 流式对话，以 Server-Sent Events 方式逐块返回模型输出。
	 *
	 * @param request 对话请求
	 * @return 模型输出的增量块序列，完成后自动结束
	 */
	Flux<LlmChatStreamChunk> chatStream(LlmChatRequest request);

}
