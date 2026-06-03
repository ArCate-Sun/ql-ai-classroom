package cc.qianlang.aiclassroom.proxy.zai.chat.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 模型发起的工具调用，对应响应体 {@code message.tool_calls[]}。
 * 支持函数调用（{@link #function}）和 MCP 调用（{@link #mcp}）两种形式。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NullMarked
public class ToolCall {

	/**
	 * 工具调用索引
	 */
	@Nullable
	private Integer index;

	/**
	 * 工具调用的唯一标识符
	 */
	private String id;

	/**
	 * 工具类型，支持 {@link ToolCallType#FUNCTION} 和 {@link ToolCallType#MCP}
	 */
	private ToolCallType type;

	/**
	 * 函数调用详情，当 {@code type=function} 时不为空
	 */
	@Nullable
	private Function function;

	/**
	 * MCP 工具调用详情，当 {@code type=mcp} 时不为空
	 */
	@Nullable
	private Mcp mcp;

	/**
	 * 函数调用详情，对应响应体 {@code tool_calls[].function}。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@NullMarked
	public static class Function {

		/**
		 * 模型调用的函数名称
		 */
		private String name;

		/**
		 * 要调用的函数参数，JSON 格式字符串。
		 * 注意：模型并不总是生成有效的 JSON，调用函数前请先在代码中验证参数。
		 */
		private String arguments;
	}

	/**
	 * MCP 工具调用详情，对应响应体 {@code tool_calls[].mcp}。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@NullMarked
	public static class Mcp {

		/**
		 * MCP 工具调用唯一标识
		 */
		@Nullable
		private String id;

		/**
		 * 工具调用类型，例如 {@code mcp_list_tools}、{@code mcp_call}
		 */
		@Nullable
		private McpToolType type;

		/**
		 * MCP 服务器标签
		 */
		@Nullable
		private String serverLabel;

		/**
		 * 错误信息（如果有）
		 */
		@Nullable
		private String error;

		/**
		 * 工具列表，当 {@code type=mcp_list_tools} 时返回
		 */
		@Nullable
		private List<Tool> tools;

		/**
		 * 工具调用参数，JSON 字符串
		 */
		@Nullable
		private String arguments;

		/**
		 * 工具名称
		 */
		@Nullable
		private String name;

		/**
		 * 工具返回的结果输出
		 */
		@Nullable
		private Object output;


		/**
		 * MCP 工具列表中的单个工具描述。
		 */
		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		@Builder
		@NullMarked
		public static class Tool {

			/**
			 * 工具名称
			 */
			@Nullable
			private String name;

			/**
			 * 工具描述
			 */
			@Nullable
			private String description;

			/**
			 * 工具注解
			 */
			@Nullable
			private Object annotations;

			/**
			 * 工具输入参数规范（JSON Schema）
			 */
			@Nullable
			private Object inputSchema;
		}
	}

}
