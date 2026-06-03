package cc.qianlang.aiclassroom.proxy.deepseek.chat.request.message;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * DeepSeek OpenAI 兼容接口中的消息角色类型，对应请求/响应 JSON 里的 {@code role} 字段。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum MessageRole {

	UNKNOWN("unknown"),

	/**
	 * 系统提示（system prompt）
	 */
	SYSTEM("system"),

	/**
	 * 用户消息
	 */
	USER("user"),

	/**
	 * 助手回复
	 */
	ASSISTANT("assistant"),

	/**
	 * 工具调用或工具响应
	 */
	TOOL("tool");

	/**
	 * 与远端 API 对应的小写枚举值，例如 "system"、"tool"
	 */
	private final String code;

	MessageRole(String code) {
		this.code = code;
	}


	/**
	 * 获取该角色在 DeepSeek OpenAI 兼容 API 中的字符串表现形式。
	 *
	 * @return 小写 role 字符串，例如 {@code "system"}、{@code "tool"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 MessageRole 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 role 字符串
	 * @return 对应的 MessageRole 枚举值
	 */
	@JsonCreator
	public static MessageRole fromCode(String code) {
		if (StringUtils.isBlank(code)) return MessageRole.UNKNOWN;
		for (MessageRole value : MessageRole.values()) {
			if (value.code.equals(code)) return value;
		}
		return MessageRole.UNKNOWN;
	}

}
