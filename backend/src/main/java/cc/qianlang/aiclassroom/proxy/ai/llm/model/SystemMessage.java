package cc.qianlang.aiclassroom.proxy.ai.llm.model;

import org.jspecify.annotations.NullMarked;

/**
 * 系统消息，用于设定模型的行为背景和角色指令。
 */
@NullMarked
public record SystemMessage(String content) implements LlmMessage {

	@Override
	public MessageRole role() {
		return MessageRole.SYSTEM;
	}

}
