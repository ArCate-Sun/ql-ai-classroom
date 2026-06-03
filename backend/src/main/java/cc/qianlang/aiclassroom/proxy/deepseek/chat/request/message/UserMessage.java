package cc.qianlang.aiclassroom.proxy.deepseek.chat.request.message;

import cc.qianlang.aiclassroom.proxy.deepseek.chat.MessageRole;
import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * DeepSeek API user 角色消息，表示人类用户发送的输入内容。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class UserMessage extends AbstractMessage {

	/**
	 * 用户消息文本内容，必填。
	 */
	private String content;

	/**
	 * 参与者名称，可用于多用户场景下区分不同用户（可选）。
	 */
	@Nullable
	private String name;

	@Override
	public MessageRole getRole() {
		return MessageRole.USER;
	}
}
