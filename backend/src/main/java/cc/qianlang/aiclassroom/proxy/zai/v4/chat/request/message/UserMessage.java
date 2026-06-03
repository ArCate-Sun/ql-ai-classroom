package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * Z.AI 对话补全接口 user 角色消息，表示用户发送的输入内容。
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

	@Override
	public MessageRole getRole() {
		return MessageRole.USER;
	}
}
