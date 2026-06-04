package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.response;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山引擎 TTS SSE 响应事件类型枚举，对应 SSE {@code event} 字段（数字字符串）。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum TtsEventType {

	UNKNOWN(-1),

	/**
	 * 会话取消（SessionCancel）
	 */
	SESSION_CANCEL(151),

	/**
	 * 会话结束（SessionFinish），音频合成完成的标志帧
	 */
	SESSION_FINISH(152),

	/**
	 * 会话失败（SessionFailed）
	 */
	SESSION_FAILED(153),

	/**
	 * TTS 语句处理结束（TTSSentenceEnd），包含句子时间戳信息
	 */
	TTS_SENTENCE_END(351),

	/**
	 * TTS 合成内容（TTSResponse），包含 Base64 编码的音频数据
	 */
	TTS_RESPONSE(352),

	;

	private final int code;

	TtsEventType(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public static TtsEventType fromCode(@Nullable String code) {
		if (code == null || code.isBlank()) return TtsEventType.UNKNOWN;
		try {
			int intCode = Integer.parseInt(code.trim());
			for (TtsEventType value : values()) {
				if (value.code == intCode) return value;
			}
		} catch (NumberFormatException ignored) {
		}
		return TtsEventType.UNKNOWN;
	}
}
