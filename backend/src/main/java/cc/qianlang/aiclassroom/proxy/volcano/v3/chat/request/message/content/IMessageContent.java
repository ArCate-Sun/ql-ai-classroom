package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import org.jspecify.annotations.NullMarked;

/**
 * 多模态消息内容部分的类型接口，对应 {@code content} 数组中的单个对象。
 * <p>
 * 每种模态通过不同子类建模：
 * <ul>
 *   <li>{@link TextContent}  — {@code type = "text"}，文本模态</li>
 *   <li>{@link ImageContent} — {@code type = "image_url"}，图片模态</li>
 *   <li>{@link VideoContent} — {@code type = "video_url"}，视频模态</li>
 *   <li>{@link AudioContent} — {@code type = "input_audio"}，音频模态</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public sealed interface IMessageContent permits TextContent, ImageContent, VideoContent, AudioContent {

	/**
	 * 内容模态类型，序列化为 JSON 的 {@code "type"} 字段。
	 */
	ContentType getType();
}
