package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 图片理解精细度枚举，对应 {@code image_url.detail} 字段。
 * 精细度影响模型理解图片的分辨率和 token 消耗。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ImageDetail {

	UNKNOWN("unknown"),

	/**
	 * 低精细度，token 消耗少，速度快
	 */
	LOW("low"),

	/**
	 * 高精细度
	 */
	HIGH("high"),

	/**
	 * 超高精细度，适合需要识别细小文字或图形的场景
	 */
	XHIGH("xhigh"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "low"、"high"、"xhigh"
	 */
	private final String code;

	ImageDetail(String code) {
		this.code = code;
	}

	/**
	 * 获取该精细度在火山方舟 API 中的字符串表现形式。
	 *
	 * @return 小写 detail 字符串，例如 {@code "low"}、{@code "xhigh"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ImageDetail 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 detail 字符串，例如 "low"、"high"、"xhigh"
	 * @return 对应的 ImageDetail 枚举值
	 */
	@JsonCreator
	public static ImageDetail fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ImageDetail.UNKNOWN;
		for (ImageDetail value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ImageDetail.UNKNOWN;
	}
}
