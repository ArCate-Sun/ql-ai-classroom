package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * TTS 句子结束帧，对应 {@link TtsEventType#TTS_SENTENCE_END}（event=351）。
 * <p>
 * {@code sentence} 含句子原文及字/词级别时间戳列表。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public final class TtsSentenceEndChunk extends TtsChunk {

	/**
	 * 句子时间戳信息。
	 */
	private TtsSentence sentence;

	/**
	 * 句子时间戳信息。
	 *
	 * @author 阿猫_ACat
	 * @version 0.1
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class TtsSentence {

		/**
		 * 句子原文。
		 */
		@Nullable
		private String text;

		/**
		 * 字/词级别时间戳列表。
		 * <ul>
		 *   <li>TTS 1.0 / ICL 1.0：{@code word} 字段对应 TN（文本归一化）结果</li>
		 *   <li>TTS 2.0 / ICL 2.0：{@code word} 字段对应原文</li>
		 * </ul>
		 */
		@Nullable
		private List<TtsWord> words;


		/**
		 * 单词/字级别时间戳信息。
		 *
		 * @author 阿猫_ACat
		 * @version 0.1
		 */
		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		@NullMarked
		public static class TtsWord {

			/**
			 * 字/词文本内容。
			 */
			@JsonProperty("word")
			private String word;

			/**
			 * 开始时间（秒），相对于整个会话起点。
			 */
			@JsonProperty("startTime")
			private double startTime;

			/**
			 * 结束时间（秒），相对于整个会话起点。
			 */
			@JsonProperty("endTime")
			private double endTime;

			/**
			 * 识别置信度，范围 [0, 1]。
			 */
			@JsonProperty("confidence")
			private double confidence;

		}
	}

}
