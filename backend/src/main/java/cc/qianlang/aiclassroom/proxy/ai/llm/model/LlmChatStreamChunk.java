package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * LLM 流式对话的单个增量块，对应 OpenAI Chat Completions API 的 SSE chunk。
 *
 * <p>调用方可拼接所有 {@link #delta()} 以还原完整输出；
 * {@link #finished()} 为 true 时表示本轮输出结束，此后不再有新 chunk。
 *
 * @param delta      本 chunk 的增量文本内容；最后一个 chunk 可能为空字符串
 * @param finished   是否为最后一个 chunk（stop reason 已触发）
 * @param tokenUsage 完整 token 用量，仅在最后一个 chunk（{@code finished=true}）中可能存在；
 *                   部分模型服务不返回此字段
 */
@NullMarked
public record LlmChatStreamChunk(
		String delta,
		boolean finished,
		@Nullable TokenUsage tokenUsage
) {
}
