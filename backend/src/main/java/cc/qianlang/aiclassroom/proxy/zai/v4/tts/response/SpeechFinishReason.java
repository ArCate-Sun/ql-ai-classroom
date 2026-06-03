package cc.qianlang.aiclassroom.proxy.zai.v4.tts.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * TTS 流式响应终止原因，对应响应体 {@code choices[].finish_reason}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum SpeechFinishReason {

	UNKNOWN("unknown"),

	/**
	 * 正常完成
	 */
	STOP("stop"),

	;

	private final String code;

	SpeechFinishReason(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static SpeechFinishReason fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return SpeechFinishReason.UNKNOWN;
		for (SpeechFinishReason value : values()) {
			if (value.code.equals(code)) return value;
		}
		return SpeechFinishReason.UNKNOWN;
	}
}
