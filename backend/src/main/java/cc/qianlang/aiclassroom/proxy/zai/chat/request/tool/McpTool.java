package cc.qianlang.aiclassroom.proxy.zai.chat.request.tool;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * MCP 工具定义，对应请求体 {@code tools} 中 {@code type=mcp} 的元素。
 * <p>
 * 序列化示例：
 * <pre>{@code
 * {
 *   "type": "mcp",
 *   "mcp": {
 *     "server_label": "your_mcp_code"
 *   }
 * }
 * }</pre>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class McpTool extends AbstractTool {

	/**
	 * MCP 工具配置，必填。
	 */
	private Mcp mcp;

	@Override
	public ToolType getType() {
		return ToolType.MCP;
	}

	/**
	 * MCP 工具配置详情。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class Mcp {

		/**
		 * MCP server 标识，必填。
		 * 若连接智谱的 MCP server，以 mcp code 填充；无需填写 {@code serverUrl}。
		 */
		private String serverLabel;

		/**
		 * MCP server 地址（可选）。
		 */
		@Nullable
		private String serverUrl;

		/**
		 * 传输类型，默认 {@code streamable-http}（可选）。支持 {@code sse}、{@code streamable-http}。
		 */
		@Nullable
		private TransportType transportType;

		/**
		 * 允许的工具集合（可选）。
		 */
		@Nullable
		private List<String> allowedTools;

		/**
		 * MCP server 需要的鉴权信息（可选）。
		 */
		@Nullable
		private Map<String, String> headers;
	}
}
