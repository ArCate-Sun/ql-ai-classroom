package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 内容审核命中类型枚举，对应响应体 {@code choices[].moderation_hit_type}。
 * 仅视觉理解模型且开启内容护栏时返回此字段。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ModerationHitType {

	UNKNOWN("unknown"),

	/**
	 * 模型输出文字涉及严重违规
	 */
	SEVERE_VIOLATION("severe_violation"),

	/**
	 * 模型输出文字涉及激进行为
	 */
	VIOLENCE("violence"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "severe_violation"、"violence"
	 */
	private final String code;

	ModerationHitType(String code) {
		this.code = code;
	}

	/**
	 * 获取该命中类型在火山方舟 API 中的字符串表现形式。
	 *
	 * @return 小写 moderation_hit_type 字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ModerationHitType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 moderation_hit_type 字符串
	 * @return 对应的 ModerationHitType 枚举值
	 */
	@JsonCreator
	public static ModerationHitType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ModerationHitType.UNKNOWN;
		for (ModerationHitType value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ModerationHitType.UNKNOWN;
	}
}
