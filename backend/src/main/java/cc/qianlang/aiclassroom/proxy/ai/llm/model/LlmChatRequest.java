package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * LLM 对话请求，对应 OpenAI Chat Completions API 的请求体。
 *
 * <p>可选参数为 {@code null} 时，由具体 {@link cc.qianlang.aiclassroom.proxy.ai.llm.LlmChatClient}
 * 实现使用模型默认值。
 *
 * @param messages    对话消息列表，至少包含一条消息
 * @param temperature 采样温度，控制输出随机性，范围 [0, 2]；为 null 时使用模型默认值
 * @param maxTokens   最大输出 token 数；为 null 时使用模型默认值
 */
@NullMarked
public record LlmChatRequest(
		List<LlmMessage> messages,
		@Nullable Double temperature,
		@Nullable Integer maxTokens
) {

	/**
	 * 仅携带消息列表，其余参数均使用模型默认值。
	 */
	public static LlmChatRequest of(List<LlmMessage> messages) {
		return new LlmChatRequest(messages, null, null);
	}

}
