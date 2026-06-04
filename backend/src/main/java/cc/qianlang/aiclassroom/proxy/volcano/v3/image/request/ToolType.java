package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山方舟图像生成接口中的工具类型，对应请求体 {@code tools[].type}。
 * 每个枚举常量携带服务端要求的下划线字符串（{@link #code}）。
 */
@NullMarked
public enum ToolType {

	UNKNOWN("unknown"),

	/**
	 * 联网搜索工具，仅 seedream-5.0-lite 模型支持
	 */
	WEB_SEARCH("web_search"),
	;

	/**
	 * 与远端 API 对应的枚举值，例如 "web_search"
	 */
	private final String code;

	ToolType(String code) {
		this.code = code;
	}

	/**
	 * 获取该工具类型在请求中的字符串表现形式。
	 *
	 * @return 枚举值字符串，例如 {@code "web_search"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ToolType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 type 字符串，例如 "web_search"
	 * @return 对应的 ToolType 枚举值
	 */
	@JsonCreator
	public static ToolType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ToolType.UNKNOWN;
		for (ToolType value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ToolType.UNKNOWN;
	}
}
