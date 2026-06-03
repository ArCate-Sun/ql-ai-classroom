package cc.qianlang.aiclassroom.proxy.deepseek.chat;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * DeepSeek OpenAI 兼容接口中的消息角色类型，对应请求/响应 JSON 里的 {@code role} 字段。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
public enum MessageRole {

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
}
