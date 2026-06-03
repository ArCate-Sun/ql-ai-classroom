package cc.qianlang.aiclassroom.proxy.zai.chat.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * MCP 工具调用类型。
 */
@NullMarked
public enum McpToolType {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 列出 MCP 工具
	 */
	MCP_LIST_TOOLS("mcp_list_tools"),

	/**
	 * 调用 MCP 工具
	 */
	MCP_CALL("mcp_call");

	private final String code;

	McpToolType(String code) {
		this.code = code;
	}

	/**
	 * 获取该工具调用类型在 API 中的字符串表现形式。
	 *
	 * @return 工具调用类型字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 McpToolType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 type 字符串
	 * @return 对应的 McpToolType 枚举值
	 */
	@JsonCreator
	public static McpToolType fromCode(String code) {
		if (StringUtils.isBlank(code)) return UNKNOWN;

		for (McpToolType value : McpToolType.values()) {
			if (value.code.equals(code)) return value;
		}
		return UNKNOWN;
	}
}
