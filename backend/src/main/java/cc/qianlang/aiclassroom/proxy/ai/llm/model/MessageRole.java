package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;

/**
 * 对话消息的角色，对应 OpenAI Chat Completions API 的 role 字段。
 */
@NullMarked
public enum MessageRole {

	SYSTEM,
	USER,
	ASSISTANT,

}
