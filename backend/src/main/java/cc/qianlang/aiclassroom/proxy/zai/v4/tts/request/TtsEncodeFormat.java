package cc.qianlang.aiclassroom.proxy.zai.v4.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 流式返回时的音频编码格式枚举，对应请求体 {@code encode_format} 字段。
 * <p>
 * 仅在 {@code stream=true} 时生效，默认返回对应音频文件格式的 Base64 字符串。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum TtsEncodeFormat {

	UNKNOWN("unknown"),

	/**
	 * Base64 编码（默认）
	 */
	BASE64("base64"),

	/**
	 * 十六进制编码
	 */
	HEX("hex"),

	;

	private final String code;

	TtsEncodeFormat(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static TtsEncodeFormat fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return TtsEncodeFormat.UNKNOWN;
		for (TtsEncodeFormat value : values()) {
			if (value.code.equals(code)) return value;
		}
		return TtsEncodeFormat.UNKNOWN;
	}
}
