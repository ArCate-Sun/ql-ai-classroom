package cc.qianlang.aiclassroom.proxy.zai.chat.request.message;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * Z.AI 工具调用类型。
 */
@NullMarked
public enum ToolType {

	/**
	 * 函数调用
	 */
	FUNCTION("function"),

	/**
	 * 网络搜索
	 */
	WEB_SEARCH("web_search"),

	/**
	 * 知识库检索
	 */
	RETRIEVAL("retrieval");

	private final String code;

	ToolType(String code) {
		this.code = code;
	}

	/**
	 * 获取该工具类型在 API 中的字符串表现形式。
	 *
	 * @return 工具类型字符串，例如 {@code "function"}、{@code "web_search"}
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ToolType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #FUNCTION}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 type 字符串
	 * @return 对应的 ToolType 枚举值
	 */
	@JsonCreator
	public static ToolType fromCode(String code) {
		if (StringUtils.isBlank(code)) return FUNCTION;

		for (ToolType value : ToolType.values()) {
			if (value.code.equals(code)) return value;
		}
		return FUNCTION;
	}
}
