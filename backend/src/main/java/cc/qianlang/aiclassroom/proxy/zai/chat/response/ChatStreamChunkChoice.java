package cc.qianlang.aiclassroom.proxy.zai.chat.response;

import cc.qianlang.aiclassroom.proxy.zai.chat.request.message.MessageRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 流式候选，对应流式响应 SSE 数据块中的 {@code choices[]} 元素。
 * 包含本 chunk 的增量内容。
 *
 * @author 阿猫_ACat
 * @version 0.1
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
	 * 推理终止原因，仅在最后一个内容 chunk 中非 null
	 */
	@Nullable
	private FinishReason finishReason;

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
		 * 最终回答的增量文本片段。思考阶段此字段为 null；调用工具时此字段为 null。
		 * GLM-4.5V 系列返回内容可能包含思考过程标签 {@code <think></think>}。
		 */
		@Nullable
		private String content;

		/**
		 * 思维链增量文本片段，仅 glm-4.5 系列支持。
		 * 推理阶段逐 Token 填充；回答阶段为 null；非思考模式始终为 null。
		 */
		@Nullable
		private String reasoningContent;

		/**
		 * 语音模型返回的音频内容，仅在使用 glm-4-voice 模型时返回
		 */
		@Nullable
		private Audio audio;

		/**
		 * 生成的应该被调用的工具信息，流式返回时会逐步生成
		 */
		@Nullable
		private List<ToolCall> toolCalls;
	}

}
