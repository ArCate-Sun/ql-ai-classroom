package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * Z.AI 对话补全接口 system 角色消息，用于向模型注入系统级指令或角色设定。
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

	@Override
	public MessageRole getRole() {
		return MessageRole.SYSTEM;
	}
}
