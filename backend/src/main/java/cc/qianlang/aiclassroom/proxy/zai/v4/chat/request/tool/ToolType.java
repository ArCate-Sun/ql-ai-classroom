package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.tool;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Z.AI 工具类型枚举，对应请求体 {@code tools[].type}。
 * 每个枚举常量携带服务端要求的小写字符串（{@link #code}）。
 */
@NullMarked
public enum ToolType {

	UNKNOWN("unknown"),

	/**
	 * 函数调用工具
	 */
	FUNCTION("function"),

	/**
	 * 知识库检索工具
	 */
	RETRIEVAL("retrieval"),

	/**
	 * 网络搜索工具
	 */
	WEB_SEARCH("web_search"),

	/**
	 * MCP 工具
	 */
	MCP("mcp"),
	;

	/**
	 * 与远端 API 对应的小写枚举值，例如 "function"、"web_search"
	 */
	private final String code;

	ToolType(String code) {
		this.code = code;
	}

	/**
	 * 获取该工具类型在 Z.AI 对话补全 API 中的字符串表现形式。
	 *
	 * @return 小写 type 字符串，例如 {@code "function"}、{@code "web_search"}
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
	 * @param code 服务端返回的 tools[].type 字符串，例如 "function"
	 * @return 对应的 ToolType 枚举值
	 */
	@JsonCreator
	public static ToolType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ToolType.UNKNOWN;
		for (ToolType value : ToolType.values()) {
			if (value.code.equals(code)) return value;
		}
		return ToolType.UNKNOWN;
	}
}
