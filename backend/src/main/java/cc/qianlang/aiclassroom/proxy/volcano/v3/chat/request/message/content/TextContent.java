package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 文本模态内容部分，对应 {@code content} 数组中 {@code type = "text"} 的对象。
 */
@Getter
@Builder
@NullMarked
public final class TextContent implements IMessageContent {

	/**
	 * 文本内容，必填。
	 */
	private String text;

	@Override
	public ContentType getType() {
		return ContentType.TEXT;
	}
}
