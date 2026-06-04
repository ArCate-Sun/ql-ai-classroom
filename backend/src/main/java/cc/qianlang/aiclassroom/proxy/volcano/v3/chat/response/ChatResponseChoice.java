package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response;

import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.MessageRole;
import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.ToolCall;
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
	 * 候选索引（从 0 开始），当前始终为 0
	 */
	private int index;

	/**
	 * 停止原因：{@code stop}、{@code length}、{@code content_filter}、{@code tool_calls}
	 */
	private FinishReason finishReason;

	/**
	 * 模型生成的完整消息
	 */
	private Message message;

	/**
	 * Token 对数概率信息，仅在请求时设置 {@code logprobs=true} 时返回，否则为 null
	 */
	@Nullable
	private Logprobs logprobs;

	/**
	 * 内容审核命中类型，仅视觉理解模型且开启内容护栏时返回，否则为 null
	 */
	@Nullable
	private ModerationHitType moderationHitType;

	/**
	 * 模型生成的完整消息。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Message {

		/**
		 * 消息角色，固定为 {@code "assistant"}
		 */
		private MessageRole role;

		/**
		 * 模型生成的最终回答文本。若模型调用了工具，此字段可能为 null。
		 */
		@Nullable
		private String content;

		/**
		 * 模型处理问题的思维链内容，仅深度推理模型支持，非思考模式时为 null
		 */
		@Nullable
		private String reasoningContent;

		/**
		 * 模型发起的工具调用列表，非工具调用时为 null
		 */
		@Nullable
		private List<ToolCall> toolCalls;
	}
}
