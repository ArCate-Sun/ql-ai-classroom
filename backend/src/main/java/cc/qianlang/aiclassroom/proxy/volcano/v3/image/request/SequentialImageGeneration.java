package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 顺序图像生成模式，对应请求体 {@code sequential_image_generation}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum SequentialImageGeneration {

	UNKNOWN("unknown"),

	/**
	 * 自动模式，由模型决定是否启用组图生成
	 */
	AUTO("auto"),

	/**
	 * 禁用顺序图像生成（默认）
	 */
	DISABLED("disabled"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "auto"、"disabled"
	 */
	private final String code;

	SequentialImageGeneration(String code) {
		this.code = code;
	}

	/**
	 * 获取该模式在请求中的字符串表现形式。
	 *
	 * @return 小写字符串，例如 {@code "auto"}、{@code "disabled"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 SequentialImageGeneration 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的字符串，例如 "auto"、"disabled"
	 * @return 对应的 SequentialImageGeneration 枚举值
	 */
	@JsonCreator
	public static SequentialImageGeneration fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return SequentialImageGeneration.UNKNOWN;
		for (SequentialImageGeneration value : values()) {
			if (value.code.equals(code)) return value;
		}
		return SequentialImageGeneration.UNKNOWN;
	}
}
