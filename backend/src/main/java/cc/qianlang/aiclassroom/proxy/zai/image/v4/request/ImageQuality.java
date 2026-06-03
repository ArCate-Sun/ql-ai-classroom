package cc.qianlang.aiclassroom.proxy.zai.image.v4.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 图像生成质量枚举，对应请求体 {@code quality} 字段。
 * <p>
 * 注意：{@code glm-image} 模型仅支持 {@link #HD}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum ImageQuality {

	UNKNOWN("unknown"),

	/**
	 * 高清质量，生成更精细、细节更丰富的图像，耗时约 20 秒。
	 * {@code glm-image} 默认值，且为唯一支持的质量等级。
	 */
	HD("hd"),

	/**
	 * 标准质量，快速生成图像，适合对速度有较高要求的场景，耗时约 5~10 秒。
	 * 其它模型的默认值。
	 */
	STANDARD("standard"),

	;

	private final String code;

	ImageQuality(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static ImageQuality fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ImageQuality.UNKNOWN;
		for (ImageQuality value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ImageQuality.UNKNOWN;
	}
}
