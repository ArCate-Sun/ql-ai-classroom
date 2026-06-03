package cc.qianlang.aiclassroom.proxy.deepseek.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 流式候选，包含本 chunk 的增量内容。
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ChatStreamChunkChoice {

	/**
	 * 候选索引（从 0 开始），当前始终为 0。
	 */
	private int index;

	/**
	 * 本 chunk 的增量内容。
	 */
	private Delta delta;

	/**
	 * 停止原因，仅在最后一个内容 chunk 中非 null；
	 * 可能值与非流式响应的 {@code finishReason} 相同。
	 */
	@Nullable
	private FinishReason finishReason;

	/**
	 * Token 对数概率信息，仅在请求时设置 {@code logprobs=true} 时返回，否则为 null。
	 */
	@Nullable
	private Logprobs logprobs;

	/**
	 * 增量内容，携带本 chunk 新增的文本片段。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Delta {

		/**
		 * 消息角色，仅在第一个 chunk 中为 {@code "assistant"}，后续 chunk 为 null。
		 */
		@Nullable
		private String role;

		/**
		 * 最终回答的增量文本片段。思考阶段此字段为 null；回答阶段逐 Token 填充。
		 */
		@Nullable
		private String content;

		/**
		 * 链式思考的增量文本片段（思考模式专属）。
		 * 推理阶段逐 Token 填充；回答阶段为 null；非思考模式始终为 null。
		 */
		@Nullable
		private String reasoningContent;

	}
}
