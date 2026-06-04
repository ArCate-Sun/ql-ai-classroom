package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 音频格式 MIME 类型枚举，对应 {@code input_audio.format} 字段。
 * <p>
 * 使用 {@code data} 时必填。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum AudioFormat {

	UNKNOWN("unknown"),

	/**
	 * MP3 格式
	 */
	MP3("audio/mpeg"),

	/**
	 * WAV 格式
	 */
	WAV("audio/wav"),

	/**
	 * AAC 格式
	 */
	AAC("audio/aac"),

	/**
	 * M4A/ALAC 格式
	 */
	MP4("audio/mp4"),

	/**
	 * PCM 格式
	 */
	L16("audio/L16"),

	/**
	 * AC3 格式
	 */
	AC3("audio/ac3"),

	;

	private final String code;

	AudioFormat(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static AudioFormat fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return AudioFormat.UNKNOWN;
		for (AudioFormat value : values()) {
			if (value.code.equals(code)) return value;
		}
		return AudioFormat.UNKNOWN;
	}
}
