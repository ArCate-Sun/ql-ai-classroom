package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山方舟思考模式（thinking）开关枚举，对应请求体 {@code thinking.type}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ThinkingType {

	UNKNOWN("unknown"),

	/**
	 * 开启思考模式，模型强制先思考再回答
	 */
	ENABLED("enabled"),

	/**
	 * 关闭思考模式，模型直接回答问题，不进行思考
	 */
	DISABLED("disabled"),

	/**
	 * 自动思考模式，模型根据问题自主判断是否需要思考，简单题目直接回答
	 */
	AUTO("auto"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "enabled"、"disabled"、"auto"
	 */
	private final String code;

	ThinkingType(String code) {
		this.code = code;
	}

	/**
	 * 获取该思考模式在火山方舟 API 中的字符串表现形式。
	 *
	 * @return 小写 thinking.type 字符串，例如 {@code "enabled"}、{@code "auto"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ThinkingType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 thinking.type 字符串，例如 "enabled"、"auto"
	 * @return 对应的 ThinkingType 枚举值
	 */
	@JsonCreator
	public static ThinkingType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ThinkingType.UNKNOWN;
		for (ThinkingType value : ThinkingType.values()) {
			if (value.code.equals(code)) return value;
		}
		return ThinkingType.UNKNOWN;
	}
}
