package cc.qianlang.aiclassroom.proxy.zai.v4.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 音频输出格式枚举，对应请求体 {@code response_format} 字段。
 * <p>
 * 注意：流式生成音频时仅支持 {@link #PCM} 格式。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum TtsResponseFormat {

	UNKNOWN("unknown"),

	/**
	 * WAV 格式
	 */
	WAV("wav"),

	/**
	 * PCM 格式（默认，流式时唯一支持的格式）
	 */
	PCM("pcm"),

	;

	private final String code;

	TtsResponseFormat(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static TtsResponseFormat fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return TtsResponseFormat.UNKNOWN;
		for (TtsResponseFormat value : values()) {
			if (value.code.equals(code)) return value;
		}
		return TtsResponseFormat.UNKNOWN;
	}
}
