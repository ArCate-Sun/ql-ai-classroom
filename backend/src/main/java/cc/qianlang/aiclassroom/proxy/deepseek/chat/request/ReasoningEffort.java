package cc.qianlang.aiclassroom.proxy.deepseek.chat.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 推理强度枚举，控制 DeepSeek 思考模式（Thinking Mode）的推理深度。
 * <p>
 * 思考模式开启时，模型会先进行链式思考（Chain of Thought），再输出最终答案。
 * 推理强度越高，思考越深入，但耗时和 token 消耗也越多。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum ReasoningEffort {

	UNKNOWN("unknown"),

	/**
	 * 高强度推理（默认）
	 */
	HIGH("high"),

	/**
	 * 最大强度推理，适合复杂推理任务
	 */
	MAX("max"),

	;

	private final String value;

	ReasoningEffort(String value) {
		this.value = value;
	}

	/**
	 * 返回 API 传参所用的字符串值，同时作为 Jackson 序列化值
	 */
	@JsonValue
	public String getValue() {
		return this.value;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ReasoningEffort 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param value 服务端返回的 reasoning_effort 字符串，例如 "high"、"max"、"none"
	 * @return 对应的 ReasoningEffort 枚举值
	 */
	@JsonCreator
	public static ReasoningEffort fromValue(@Nullable String value) {
		if (StringUtils.isBlank(value)) return ReasoningEffort.UNKNOWN;
		for (ReasoningEffort effort : values()) {
			if (effort.value.equals(value)) return effort;
		}
		return ReasoningEffort.UNKNOWN;
	}
}
