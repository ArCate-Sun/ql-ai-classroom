package cc.qianlang.aiclassroom.proxy.zai.chat.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 工具调用策略。
 */
@NullMarked
public enum ToolChoice {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 模型自行决定是否调用工具
	 */
	AUTO("auto");

	private final String code;

	ToolChoice(String code) {
		this.code = code;
	}

	/**
	 * 获取该工具调用策略在 API 中的字符串表现形式。
	 *
	 * @return 工具调用策略字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ToolChoice 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #AUTO}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 tool_choice 字符串
	 * @return 对应的 ToolChoice 枚举值
	 */
	@JsonCreator
	public static ToolChoice fromCode(String code) {
		if (StringUtils.isBlank(code)) return ToolChoice.AUTO;
		for (ToolChoice value : ToolChoice.values()) {
			if (value.code.equals(code)) return value;
		}
		return ToolChoice.AUTO;
	}
}
