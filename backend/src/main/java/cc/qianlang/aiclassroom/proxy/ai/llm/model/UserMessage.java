package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;

/**
 * 用户消息，代表人类用户的输入。
 */
@NullMarked
public record UserMessage(String content) implements LlmMessage {

	@Override
	public MessageRole role() {
		return MessageRole.USER;
	}

}
