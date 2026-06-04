package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 火山方舟 API system 角色消息，用于向模型注入系统级指令或背景知识。
 * <p>
 * system 消息通常放在对话列表的第一条，对整个对话生效。
 * {@code content} 支持纯文本（{@link MessageContent#text(String)}）和多模态内容列表
 * （{@link MessageContent#parts(java.util.List)}）两种形式。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class SystemMessage extends AbstractMessage {

	/**
	 * 系统提示词内容，必填。
	 * 使用 {@link MessageContent#text(String)} 创建纯文本内容，
	 * 或使用 {@link MessageContent#parts(java.util.List)} 创建多模态内容列表。
	 */
	private MessageContent content;

	@Override
	public MessageRole getRole() {
		return MessageRole.SYSTEM;
	}
}
