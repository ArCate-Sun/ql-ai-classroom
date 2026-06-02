package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;

/**
 * Token 用量统计，对应 OpenAI Chat Completions API 响应的 usage 字段。
 *
 * @param promptTokens     输入（提示）消耗的 token 数
 * @param completionTokens 输出（补全）消耗的 token 数
 * @param totalTokens      总消耗 token 数
 */
@NullMarked
public record TokenUsage(
		int promptTokens,
		int completionTokens,
		int totalTokens
) {
}
