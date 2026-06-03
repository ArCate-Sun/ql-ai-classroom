package cc.qianlang.aiclassroom.proxy.zai.v4.tts.response;

import cc.qianlang.aiclassroom.proxy.zai.v4.tts.request.TtsResponseFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Z.AI 文本转语音 API 流式响应的单个 SSE 数据块。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class SpeechStreamChunk {

	/**
	 * 任务 ID。
	 */
	@Nullable
	private String id;

	/**
	 * 请求创建时间，Unix 时间戳（秒）。
	 */
	private long created;

	/**
	 * 实际处理本次请求的模型名称。
	 */
	@Nullable
	private String model;

	/**
	 * 候选列表，目前始终只包含一个候选。
	 */
	@Nullable
	private List<Choice> choices;

	/**
	 * 单个候选，对应流式响应中的 {@code choices[]} 元素。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Choice {

		private int index;

		/**
		 * 增量音频内容，音频数据块中存在，终止帧中为 {@code null}。
		 */
		@Nullable
		private Delta delta;

		/**
		 * 终止原因，仅在最后一个数据块中存在。
		 */
		@Nullable
		private SpeechFinishReason finishReason;

		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		@NullMarked
		public static class Delta {

			/**
			 * 角色，固定为 {@code "assistant"}。
			 */
			@Nullable
			private String role;

			/**
			 * 当前数据块的音频内容，Base64 编码（默认）或 Hex 编码，
			 * 由请求体中的 {@code encodeFormat} 决定。
			 */
			@Nullable
			private String content;

			/**
			 * 实际返回的音频采样率（Hz），例如 {@code 24000}。
			 */
			@Nullable
			private Integer returnSampleRate;

			/**
			 * 实际返回的音频格式。
			 */
			@Nullable
			private TtsResponseFormat returnFormat;
		}
	}

}
