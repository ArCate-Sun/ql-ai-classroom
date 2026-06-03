package cc.qianlang.aiclassroom.proxy.deepseek.chat.request;


import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * DeepSeek 工具类型枚举，对应请求体 {@code tools[].type}，目前仅支持函数类型工具。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ToolType {

	/**
	 * 函数类型工具
	 */
	FUNCTION("function"),

	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "function"
	 */
	private final String code;

	ToolType(String code) {
		this.code = code;
	}

	/**
	 * 获取该工具类型在 DeepSeek OpenAI 兼容 API 中的字符串表现形式。
	 *
	 * @return 小写 type 字符串，例如 {@code "function"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ToolType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #FUNCTION}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 tools[].type 字符串，例如 "function"
	 * @return 对应的 ToolType 枚举值
	 */
	@JsonCreator
	public static ToolType fromCode(@Nullable String code) {

		if (StringUtils.isBlank(code)) return ToolType.FUNCTION;

		for (ToolType value : ToolType.values()) {
			if (value.code.equals(code)) return value;
		}
		return ToolType.FUNCTION;
	}
}