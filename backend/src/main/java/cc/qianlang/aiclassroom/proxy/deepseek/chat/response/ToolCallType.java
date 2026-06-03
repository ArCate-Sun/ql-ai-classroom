package cc.qianlang.aiclassroom.proxy.deepseek.chat.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 工具调用类型枚举，对应响应体 {@code tool_calls[].type}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ToolCallType {

	UNKNOWN("unknown"),

	/**
	 * 函数类型工具
	 */
	FUNCTION("function"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "function"
	 */
	private final String code;

	ToolCallType(String code) {
		this.code = code;
	}

	/**
	 * 获取该工具调用类型在 DeepSeek OpenAI 兼容 API 中的字符串表现形式。
	 *
	 * @return 小写 type 字符串，例如 {@code "function"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ToolCallType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 tool_calls[].type 字符串，例如 "function"
	 * @return 对应的 ToolCallType 枚举值
	 */
	@JsonCreator
	public static ToolCallType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ToolCallType.UNKNOWN;
		for (ToolCallType value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ToolCallType.UNKNOWN;
	}
}
