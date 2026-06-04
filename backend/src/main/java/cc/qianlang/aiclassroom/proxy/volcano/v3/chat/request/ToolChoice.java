package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山方舟工具调用预设策略，对应请求体 {@code tool_choice} 字符串形式。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ToolChoice implements IToolChoice {

	UNKNOWN("unknown"),

	/**
	 * 不调用任何工具，模型只输出文本
	 */
	NONE("none"),

	/**
	 * 由模型自行决定是否调用工具（存在工具时的默认值）
	 */
	AUTO("auto"),

	/**
	 * 强制模型必须调用某个工具（但不指定哪一个）
	 */
	REQUIRED("required"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "none"、"auto"、"required"
	 */
	private final String code;

	ToolChoice(String code) {
		this.code = code;
	}

	/**
	 * 获取该策略在火山方舟 API 中的字符串表现形式。
	 *
	 * @return 小写 tool_choice 字符串，例如 {@code "none"}、{@code "auto"}、{@code "required"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ToolChoice 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 tool_choice 字符串，例如 "none"、"auto"、"required"
	 * @return 对应的 ToolChoice 枚举值
	 */
	@JsonCreator
	public static ToolChoice fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ToolChoice.UNKNOWN;
		for (ToolChoice value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ToolChoice.UNKNOWN;
	}
}
