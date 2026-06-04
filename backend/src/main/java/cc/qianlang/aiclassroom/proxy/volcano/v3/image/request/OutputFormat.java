package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 图像输出格式，对应请求体 {@code output_format}，仅 seedream-5.0-lite 模型支持。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum OutputFormat {

	UNKNOWN("unknown"),

	/**
	 * JPEG 格式（默认）
	 */
	JPEG("jpeg"),

	/**
	 * PNG 格式
	 */
	PNG("png"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "jpeg"、"png"
	 */
	private final String code;

	OutputFormat(String code) {
		this.code = code;
	}

	/**
	 * 获取该输出格式在请求中的字符串表现形式。
	 *
	 * @return 小写格式字符串，例如 {@code "jpeg"}、{@code "png"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 OutputFormat 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的字符串，例如 "jpeg"、"png"
	 * @return 对应的 OutputFormat 枚举值
	 */
	@JsonCreator
	public static OutputFormat fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return OutputFormat.UNKNOWN;
		for (OutputFormat value : values()) {
			if (value.code.equals(code)) return value;
		}
		return OutputFormat.UNKNOWN;
	}
}
