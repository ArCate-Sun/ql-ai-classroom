package cc.qianlang.aiclassroom.proxy.zai.chat.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 模型推理终止原因，对应响应体 {@code choices[].finish_reason}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum FinishReason {

	UNKNOWN("unknown"),

	/**
	 * 模型自然结束生成，或触发 stop 词
	 */
	STOP("stop"),

	/**
	 * 达到 max_tokens 长度限制，输出可能被截断
	 */
	LENGTH("length"),

	/**
	 * 模型调用了工具（函数）
	 */
	TOOL_CALLS("tool_calls"),

	/**
	 * 内容被安全审核接口拦截，用户应判断并决定是否撤回公开内容
	 */
	SENSITIVE("sensitive"),

	/**
	 * 模型推理异常
	 */
	NETWORK_ERROR("network_error"),

	/**
	 * 超出模型上下文窗口
	 */
	MODEL_CONTEXT_WINDOW_EXCEEDED("model_context_window_exceeded"),
	;

	/**
	 * 与远端 API 对应的小写枚举值
	 */
	private final String code;

	FinishReason(String code) {
		this.code = code;
	}

	/**
	 * 获取该停止原因在 Z.AI 对话补全 API 中的字符串表现形式。
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
