package cc.qianlang.aiclassroom.proxy.zai.chat.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Z.AI 对话补全接口响应格式类型，对应请求体 {@code response_format.type}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ResponseFormatType {

	UNKNOWN("unknown"),

	/**
	 * 纯文本格式（默认）
	 */
	TEXT("text"),

	/**
	 * JSON 对象格式，模型输出保证是合法的 JSON 字符串。
	 * 使用此格式时建议在提示词中明确说明需要 JSON 格式输出。
	 */
	JSON_OBJECT("json_object"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "text"、"json_object"
	 */
	private final String code;

	ResponseFormatType(String code) {
		this.code = code;
	}

	/**
	 * 获取该响应格式在请求中的字符串表现形式。
	 *
	 * @return 小写格式字符串，例如 {@code "text"}、{@code "json_object"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ResponseFormatType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 response_format.type 字符串，例如 "text"、"json_object"
	 * @return 对应的 ResponseFormatType 枚举值
	 */
	@JsonCreator
	public static ResponseFormatType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ResponseFormatType.UNKNOWN;
		for (ResponseFormatType value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ResponseFormatType.UNKNOWN;
	}
}
