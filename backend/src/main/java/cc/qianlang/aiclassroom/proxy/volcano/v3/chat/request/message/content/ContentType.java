package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 多模态消息内容部分的类型，对应各内容对象的 {@code type} 字段。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ContentType {

	UNKNOWN("unknown"),

	/**
	 * 文本模态
	 */
	TEXT("text"),

	/**
	 * 图片模态
	 */
	IMAGE_URL("image_url"),

	/**
	 * 视频模态
	 */
	VIDEO_URL("video_url"),

	/**
	 * 音频模态
	 */
	INPUT_AUDIO("input_audio"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "text"、"image_url"
	 */
	private final String code;

	ContentType(String code) {
		this.code = code;
	}

	/**
	 * 获取该内容类型在火山方舟 API 中的字符串表现形式。
	 *
	 * @return 小写 type 字符串，例如 {@code "text"}、{@code "image_url"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ContentType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 type 字符串，例如 "text"、"image_url"
	 * @return 对应的 ContentType 枚举值
	 */
	@JsonCreator
	public static ContentType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ContentType.UNKNOWN;
		for (ContentType value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ContentType.UNKNOWN;
	}
}
