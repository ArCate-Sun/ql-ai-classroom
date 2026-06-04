package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 请求消耗的用量返回控制标记。
 * <p>
 * 当携带此字段，在合成音频结束时会返回所需的用量数据。
 */
@NullMarked
public enum RequireUsageTokensReturn {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 计费字符数
	 */
	TEXT_WORDS("text_words");

	private final String code;

	RequireUsageTokensReturn(String code) {
		this.code = code;
	}

	/**
	 * 获取该用量返回标记在 API 中的字符串表现形式。
	 *
	 * @return 用量返回标记字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 RequireUsageTokensReturn 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 require_usage_tokens_return 字符串
	 * @return 对应的 RequireUsageTokensReturn 枚举值
	 */
	@JsonCreator
	public static RequireUsageTokensReturn fromCode(String code) {
		if (StringUtils.isBlank(code)) return UNKNOWN;
		for (RequireUsageTokensReturn value : RequireUsageTokensReturn.values()) {
			if (value.code.equals(code)) return value;
		}
		return UNKNOWN;
	}
}
