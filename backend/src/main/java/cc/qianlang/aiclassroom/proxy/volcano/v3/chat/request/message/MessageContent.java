package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message;

import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content.IMessageContent;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * 消息内容，支持纯文本和多模态两种形式，通过静态工厂方法创建。
 * <p>
 * Jackson 序列化时：{@link Text} 直接输出 JSON 字符串；{@link Parts} 直接输出 JSON 数组。
 * 两种形式均透明地序列化为 API 期望的格式，无需额外的包装字段。
 * <p>
 * 使用示例：
 * <pre>{@code
 * // 纯文本
 * MessageContent.text("你好")
 *
 * // 多模态（图片 + 文本）
 * MessageContent.parts(List.of(
 *     ImageContent.builder().imageUrl(ImageContent.ImageUrl.builder().url("https://...").build()).build(),
 *     TextContent.builder().text("描述这张图片").build()
 * ))
 * }</pre>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public abstract sealed class MessageContent permits MessageContent.Text, MessageContent.Parts {

	/**
	 * 创建纯文本消息内容
	 *
	 * @param text 文本内容
	 */
	public static MessageContent text(String text) {
		return new Text(text);
	}

	/**
	 * 创建多模态消息内容列表
	 *
	 * @param parts 内容部分列表，每个元素对应一种模态（文本、图片、视频、音频）
	 */
	public static MessageContent parts(List<IMessageContent> parts) {
		return new Parts(parts);
	}

	/**
	 * 纯文本内容，序列化为 JSON 字符串。
	 */
	@NullMarked
	public static final class Text extends MessageContent {

		private final String value;

		Text(String value) {
			this.value = value;
		}

		/**
		 * 返回文本字符串，Jackson 将其直接序列化为 JSON 字符串（无包装对象）。
		 */
		@JsonValue
		public String getValue() {
			return this.value;
		}
	}

	/**
	 * 多模态内容列表，序列化为 JSON 数组。
	 */
	@NullMarked
	public static final class Parts extends MessageContent {

		private final List<IMessageContent> value;

		Parts(List<IMessageContent> value) {
			this.value = value;
		}

		/**
		 * 返回内容部分列表，Jackson 将其直接序列化为 JSON 数组（无包装对象）。
		 */
		@JsonValue
		public List<IMessageContent> getValue() {
			return this.value;
		}
	}

}
