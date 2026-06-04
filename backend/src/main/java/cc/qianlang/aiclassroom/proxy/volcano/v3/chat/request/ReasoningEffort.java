package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 推理强度枚举，控制火山方舟思考模式（Thinking Mode）的推理深度。
 * <p>
 * 减少思考深度可提升速度，思考花费的 token 更少。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum ReasoningEffort {

	UNKNOWN("unknown"),

	/**
	 * 关闭思考，直接回答
	 */
	MINIMAL("minimal"),

	/**
	 * 轻量思考，侧重快速响应
	 */
	LOW("low"),

	/**
	 * 均衡模式，兼顾速度与深度（默认）
	 */
	MEDIUM("medium"),

	/**
	 * 深度分析，处理复杂问题
	 */
	HIGH("high"),

	/**
	 * 最高程度思考，适配高难度推理任务（仅部分模型支持）
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
	 * @param value 服务端返回的 reasoning_effort 字符串，例如 "low"、"medium"、"high"
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
