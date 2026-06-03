package cc.qianlang.aiclassroom.proxy.zai.chat.request.message;


import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 请求体 assistant 消息中的工具调用信息，
 * 用于在多轮对话中回传历史工具调用记录。
 */
@Getter
@Builder
@NullMarked
public class ToolCall {

	/**
	 * 工具调用 ID，需与对应 {@link ToolMessage#getToolCallId()} 保持一致。
	 */
	private String id;

	/**
	 * 工具类型，支持 {@code function}、{@code web_search}、{@code retrieval}。
	 */
	private ToolType type;

	/**
	 * 函数调用信息，当 {@code type} 为 {@code function} 时不为空。
	 */
	@Nullable
	private Function function;

	/**
	 * 函数调用详情。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class Function {

		/**
		 * 函数名称。
		 */
		private String name;

		/**
		 * 函数参数，JSON 格式字符串。
		 */
		private String arguments;
	}
}
