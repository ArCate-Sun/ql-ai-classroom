package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * LLM 对话响应，对应 OpenAI Chat Completions API 的响应体（非流式）。
 *
 * @param content    模型输出的完整文本内容
 * @param tokenUsage 本次请求的 token 用量；部分模型服务可能不返回，此时为 null
 */
@NullMarked
public record LlmChatResponse(
		String content,
		@Nullable TokenUsage tokenUsage
) {
}
