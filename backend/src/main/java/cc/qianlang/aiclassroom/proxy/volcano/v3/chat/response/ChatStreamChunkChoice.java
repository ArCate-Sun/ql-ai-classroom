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
 * 流式候选，包含本 chunk 的增量内容。
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ChatStreamChunkChoice {

	/**
	 * 候选索引（从 0 开始），当前始终为 0
	 */
	private int index;

	/**
	 * 本 chunk 的增量内容
	 */
	private Delta delta;

	/**
	 * 停止原因，仅在最后一个内容 chunk 中非 null；
	 * 可能值与非流式响应的 {@code finishReason} 相同。
	 */
	@Nullable
	private FinishReason finishReason;

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
	 * 增量内容，携带本 chunk 新增的文本片段。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Delta {

		/**
		 * 消息角色，仅在第一个 chunk 中为 {@code "assistant"}，后续 chunk 为 null
		 */
		@Nullable
		private MessageRole role;

		/**
		 * 最终回答的增量文本片段。思考阶段此字段为 null；回答阶段逐 Token 填充。
		 */
		@Nullable
		private String content;

		/**
		 * 链式思考的增量文本片段（思考模式专属）。
		 * 推理阶段逐 Token 填充；回答阶段为 null；非思考模式始终为 null。
		 * 自 {@code doubao-seed-2-0-lite-260428} 版本起，返回思考内容摘要。
		 */
		@Nullable
		private String reasoningContent;

		/**
		 * 经加密及压缩处理后的思考内容原文。
		 * 自 {@code doubao-seed-2-0-lite-260428} 版本起支持，在思考内容输出完成后、
		 * 正式应答内容输出前输出包含完整 {@code encryptedContent} 的数据。
		 */
		@Nullable
		private String encryptedContent;

		/**
		 * 模型生成的工具调用增量列表，非工具调用时为 null
		 */
		@Nullable
		private List<ToolCall> toolCalls;
	}
}
