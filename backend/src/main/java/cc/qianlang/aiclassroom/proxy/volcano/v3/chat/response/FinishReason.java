package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 模型停止生成 token 的原因，对应响应体 {@code choices[].finish_reason}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum FinishReason {

	UNKNOWN("unknown"),

	/**
	 * 模型输出自然结束，或因命中请求参数 stop 中指定的字段而被截断
	 */
	STOP("stop"),

	/**
	 * 模型输出因达到 max_tokens / max_completion_tokens / context_window 限制而被截断
	 */
	LENGTH("length"),

	/**
	 * 模型输出被内容审核拦截
	 */
	CONTENT_FILTER("content_filter"),

	/**
	 * 模型调用了工具（函数）
	 */
	TOOL_CALLS("tool_calls"),
	;

	/**
	 * 与远端 API 对应的小写枚举值
	 */
	private final String code;

	FinishReason(String code) {
		this.code = code;
	}

	/**
	 * 获取该停止原因在火山方舟 API 中的字符串表现形式。
	 *
	 * @return 小写 finish_reason 字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 FinishReason 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 finish_reason 字符串
	 * @return 对应的 FinishReason 枚举值
	 */
	@JsonCreator
	public static FinishReason fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return FinishReason.UNKNOWN;
		for (FinishReason value : values()) {
			if (value.code.equals(code)) return value;
		}
		return FinishReason.UNKNOWN;
	}
}
