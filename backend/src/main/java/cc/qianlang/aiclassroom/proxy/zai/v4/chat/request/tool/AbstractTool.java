package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.tool;

import org.jspecify.annotations.NullMarked;

/**
 * Z.AI 对话补全接口工具定义的抽象基类，对应请求体 {@code tools} 数组元素。
 * <p>
 * 支持四种工具类型，通过子类分别建模：
 * <ul>
 *   <li>{@link FunctionTool}    — {@code function}，函数调用工具</li>
 *   <li>{@link RetrievalTool}   — {@code retrieval}，知识库检索工具</li>
 *   <li>{@link WebSearchTool}   — {@code web_search}，网络搜索工具</li>
 *   <li>{@link McpTool}         — {@code mcp}，MCP 工具</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public sealed abstract class AbstractTool
		permits FunctionTool, RetrievalTool, WebSearchTool, McpTool {

	/**
	 * 工具类型，序列化为 JSON 的 {@code "type"} 字段。
	 * 由各子类固定返回对应类型。
	 */
	public abstract ToolType getType();
}
