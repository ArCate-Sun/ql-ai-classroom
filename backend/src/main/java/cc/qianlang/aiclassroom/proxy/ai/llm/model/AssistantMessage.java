package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;

/**
 * 助手消息，代表模型的历史输出，用于构造多轮对话上下文。
 */
@NullMarked
public record AssistantMessage(String content) implements LlmMessage {

	@Override
	public MessageRole role() {
		return MessageRole.ASSISTANT;
	}

}
