package cc.qianlang.aiclassroom.proxy.deepseek.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * DeepSeek API system 角色消息，用于向模型注入系统级指令或背景知识。
 * <p>
 * system 消息通常放在对话列表的第一条，对整个对话生效。
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
	 */
	private String content;

	/**
	 * 参与者名称，可用于区分同一 system 角色的多个提示词（可选）。
	 */
	@Nullable
	private String name;

	@Override
	public MessageRole getRole() {
		return MessageRole.SYSTEM;
	}
}
