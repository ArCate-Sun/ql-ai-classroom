package cc.qianlang.aiclassroom.proxy.zai.v4.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * TTS 音色枚举，对应请求体 {@code voice} 字段。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum TtsVoice {

	UNKNOWN("unknown"),

	/**
	 * 彤彤，默认音色
	 */
	TONGTONG("tongtong"),

	/**
	 * 锤锤
	 */
	CHUICHUI("chuichui"),

	/**
	 * 小陈
	 */
	XIAOCHEN("xiaochen"),

	/**
	 * 动动动物圈 jam 音色
	 */
	JAM("jam"),

	/**
	 * 动动动物圈 kazi 音色
	 */
	KAZI("kazi"),

	/**
	 * 动动动物圈 douji 音色
	 */
	DOUJI("douji"),

	/**
	 * 动动动物圈 luodo 音色
	 */
	LUODO("luodo"),

	;

	private final String code;

	TtsVoice(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static TtsVoice fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return TtsVoice.UNKNOWN;
		for (TtsVoice value : values()) {
			if (value.code.equals(code)) return value;
		}
		return TtsVoice.UNKNOWN;
	}
}
