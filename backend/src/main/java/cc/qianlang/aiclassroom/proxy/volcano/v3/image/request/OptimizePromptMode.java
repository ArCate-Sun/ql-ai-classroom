package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 提示词优化模式，对应请求体 {@code optimize_prompt_options.mode}。
 * 仅 seedream-5.0-lite/4.5/4.0 模型支持。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum OptimizePromptMode {

	UNKNOWN("unknown"),

	/**
	 * 标准优化模式（默认）
	 */
	STANDARD("standard"),

	/**
	 * 快速优化模式，速度更快
	 */
	FAST("fast"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "standard"、"fast"
	 */
	private final String code;

	OptimizePromptMode(String code) {
		this.code = code;
	}

	/**
	 * 获取该优化模式在请求中的字符串表现形式。
	 *
	 * @return 小写模式字符串，例如 {@code "standard"}、{@code "fast"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 OptimizePromptMode 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的字符串，例如 "standard"、"fast"
	 * @return 对应的 OptimizePromptMode 枚举值
	 */
	@JsonCreator
	public static OptimizePromptMode fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return OptimizePromptMode.UNKNOWN;
		for (OptimizePromptMode value : values()) {
			if (value.code.equals(code)) return value;
		}
		return OptimizePromptMode.UNKNOWN;
	}
}
