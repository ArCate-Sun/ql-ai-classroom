package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 在线推理模式枚举，对应请求体 {@code service_tier} 及响应体 {@code service_tier}。
 * <p>
 * 请求时可选 {@link #FAST}、{@link #AUTO}（默认）、{@link #DEFAULT}；
 * 响应时返回 {@link #FAST}、{@link #SCALE}、{@link #DEFAULT}，表示本次请求实际使用的推理模式。
 */
@NullMarked
public enum ServiceTier {

	UNKNOWN("unknown"),

	/**
	 * 优先使用在线推理（低延迟）模式；请求参数可用，响应结果可返回
	 */
	FAST("fast"),

	/**
	 * 优先使用在线推理（TPM 保障包）模式，额度不足时降级至常规模式（请求参数默认值）
	 */
	AUTO("auto"),

	/**
	 * 只使用在线推理（常规）模式；请求参数可用，响应结果可返回
	 */
	DEFAULT("default"),

	/**
	 * 本次请求实际使用了在线推理（TPM 保障包）模式（仅响应结果返回）
	 */
	SCALE("scale"),

	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "fast"、"auto"、"scale"
	 */
	private final String code;

	ServiceTier(String code) {
		this.code = code;
	}

	/**
	 * 获取该推理模式在火山方舟 API 中的字符串表现形式。
	 *
	 * @return 小写 service_tier 字符串，例如 {@code "fast"}、{@code "scale"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ServiceTier 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 service_tier 字符串，例如 "fast"、"scale"、"default"
	 * @return 对应的 ServiceTier 枚举值
	 */
	@JsonCreator
	public static ServiceTier fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ServiceTier.UNKNOWN;
		for (ServiceTier value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ServiceTier.UNKNOWN;
	}
}
