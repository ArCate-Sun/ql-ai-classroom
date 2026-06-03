package cc.qianlang.aiclassroom.proxy.deepseek.chat.response;

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
	 * 模型自然停止生成，或遇到 stop 序列中列出的字符串
	 */
	STOP("stop"),

	/**
	 * 输出长度达到了模型上下文长度限制，或达到了 max_tokens 的限制
	 */
	LENGTH("length"),

	/**
	 * 输出内容因触发过滤策略而被过滤
	 */
	CONTENT_FILTER("content_filter"),

	/**
	 * 模型调用了工具（函数）
	 */
	TOOL_CALLS("tool_calls"),

	/**
	 * 系统推理资源不足，生成被打断
	 */
	INSUFFICIENT_SYSTEM_RESOURCE("insufficient_system_resource"),
	;

	/**
	 * 与远端 API 对应的小写枚举值
	 */
	private final String code;

	FinishReason(String code) {
		this.code = code;
	}

	/**
	 * 获取该停止原因在 DeepSeek OpenAI 兼容 API 中的字符串表现形式。
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
