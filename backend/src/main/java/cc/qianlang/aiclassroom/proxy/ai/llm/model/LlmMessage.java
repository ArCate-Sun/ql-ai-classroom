package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;

/**
 * 对话消息，对应 OpenAI Chat Completions API 的单条 message 对象。
 *
 * <p>通过 sealed 限定合法的子类型，与 {@link MessageRole} 一一对应。
 */
@NullMarked
public sealed interface LlmMessage permits SystemMessage, UserMessage, AssistantMessage {

	MessageRole role();

	String content();

}
