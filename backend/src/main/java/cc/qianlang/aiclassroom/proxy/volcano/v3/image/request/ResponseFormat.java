package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 图像响应格式，对应请求体 {@code response_format}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ResponseFormat {

	UNKNOWN("unknown"),

	/**
	 * 返回图像 URL（默认），URL 有效期 24 小时
	 */
	URL("url"),

	/**
	 * 返回 Base64 编码的图像内容
	 */
	B64_JSON("b64_json"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "url"、"b64_json"
	 */
	private final String code;

	ResponseFormat(String code) {
		this.code = code;
	}

	/**
	 * 获取该响应格式在请求中的字符串表现形式。
	 *
	 * @return 小写格式字符串，例如 {@code "url"}、{@code "b64_json"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ResponseFormat 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的字符串，例如 "url"、"b64_json"
	 * @return 对应的 ResponseFormat 枚举值
	 */
	@JsonCreator
	public static ResponseFormat fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ResponseFormat.UNKNOWN;
		for (ResponseFormat value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ResponseFormat.UNKNOWN;
	}
}
