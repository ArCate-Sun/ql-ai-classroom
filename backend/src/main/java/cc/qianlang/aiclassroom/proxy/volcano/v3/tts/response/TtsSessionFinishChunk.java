package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * TTS 会话结束帧，对应 {@link TtsEventType#SESSION_FINISH}（event=152）。
 * <p>
 * {@code code} 为 {@code 20000000}，表示合成正常完成。
 * {@code usage} 仅在请求 Header 中携带 {@code X-Control-Require-Usage-Tokens-Return} 时返回。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public final class TtsSessionFinishChunk extends TtsChunk {

	/**
	 * 用量统计，未携带 {@code X-Control-Require-Usage-Tokens-Return} Header 时为 {@code null}。
	 */
	@Nullable
	private TtsUsage usage;

	/**
	 * 用量统计，对应响应体 {@code usage} 字段。
	 * <p>
	 * 仅在请求 Header 中携带 {@code X-Control-Require-Usage-Tokens-Return} 时，
	 * 于会话结束帧中返回。
	 *
	 * @author 阿猫_ACat
	 * @version 0.1
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class TtsUsage {

		/**
		 * 本次合成计费字符数。
		 */
		private int textWords;

	}
}
