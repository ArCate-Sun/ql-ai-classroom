package cc.qianlang.aiclassroom.proxy.zai.v4.chat.response;

import cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.message.MessageRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 单个候选回答，对应非流式响应体 {@code choices[]}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ChatResponseChoice {

	/**
	 * 候选索引（从 0 开始）
	 */
	private int index;

	/**
	 * 推理终止原因。
	 * 详见 {@link FinishReason}。
	 */
	@Nullable
	private FinishReason finishReason;

	/**
	 * 模型生成的完整消息
	 */
	private Message message;

	/**
	 * 模型生成的完整消息，对应响应体 {@code choices[].message}。
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
		 * GLM-4.5V 系列返回内容可能包含思考过程标签 {@code <think></think>}。
		 */
		@Nullable
		private String content;

		/**
		 * 思维链内容，仅在使用 glm-4.5 系列、glm-4.1v-thinking 系列模型时返回
		 */
		@Nullable
		private String reasoningContent;

		/**
		 * 语音模型返回的音频内容，仅在使用 glm-4-voice 模型时返回
		 */
		@Nullable
		private Audio audio;

		/**
		 * 模型发起的工具调用列表，非工具调用时为 null
		 */
		@Nullable
		private List<ToolCall> toolCalls;

	}

}
