package cc.qianlang.aiclassroom.proxy.deepseek.chat.response;


import cc.qianlang.aiclassroom.proxy.deepseek.chat.MessageRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 单个候选回答。
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ChatResponseChoice {

	/**
	 * 候选索引（从 0 开始），当前始终为 0。
	 */
	private int index;

	/**
	 * 停止原因：
	 * <ul>
	 *   <li>{@code "stop"}：自然结束或触发停止序列</li>
	 *   <li>{@code "length"}：达到 {@code max_tokens} 限制</li>
	 *   <li>{@code "content_filter"}：内容被安全过滤</li>
	 *   <li>{@code "tool_calls"}：模型调用了工具</li>
	 *   <li>{@code "insufficient_system_resource"}：服务端资源不足中断</li>
	 * </ul>
	 */
	private FinishReason finishReason;

	/**
	 * 模型生成的完整消息。
	 */
	private Message message;

	/**
	 * Token 对数概率信息，仅在请求时设置 {@code logprobs=true} 时返回，否则为 null。
	 */
	@Nullable
	private Logprobs logprobs;

	/**
	 * 模型生成的完整消息。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Message {

		/**
		 * 消息角色，固定为 {@code "assistant"}。
		 */
		private MessageRole role;

		/**
		 * 模型生成的最终回答文本。若模型调用了工具，此字段可能为 null。
		 */
		@Nullable
		private String content;

		/**
		 * 思考模式下模型的链式思考（Chain of Thought）内容。
		 * 非思考模式时为 null。
		 * <p>
		 * 多轮对话中，若中间存在工具调用，需将此字段回传给 API；否则无需回传。
		 */
		@Nullable
		private String reasoningContent;

		/**
		 * 模型发起的工具调用列表，非工具调用时为 null。
		 */
		@Nullable
		private List<ToolCall> toolCalls;

	}
}
