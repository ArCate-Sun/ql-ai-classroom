package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 火山方舟 API user 角色消息，表示人类用户发送的输入内容。
 * <p>
 * {@code content} 支持两种形式：
 * <ul>
 *   <li>纯文本 — {@link MessageContent#text(String)}</li>
 *   <li>多模态内容列表 — {@link MessageContent#parts(java.util.List)}，
 *       支持文本、图片、视频、音频模态</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class UserMessage extends AbstractMessage {

	/**
	 * 用户消息内容，必填。
	 * 使用 {@link MessageContent#text(String)} 创建纯文本内容，
	 * 或使用 {@link MessageContent#parts(java.util.List)} 创建多模态内容列表。
	 */
	private MessageContent content;

	@Override
	public MessageRole getRole() {
		return MessageRole.USER;
	}
}
