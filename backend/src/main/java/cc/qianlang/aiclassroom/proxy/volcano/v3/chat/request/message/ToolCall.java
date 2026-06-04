package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 模型发起的工具调用，对应响应体 {@code message.tool_calls[]}。
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NullMarked
public class ToolCall {

	/**
	 * 工具调用的唯一标识符，由模型生成
	 */
	private String id;

	/**
	 * 工具类型，目前仅支持 {@link ToolCallType#FUNCTION}
	 */
	private ToolCallType type;

	/**
	 * 函数调用详情
	 */
	private Function function;

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
		 * 要调用的函数的参数，由模型生成，格式为 JSON 字符串。
		 * 请注意，模型并不总是生成有效的 JSON，并且可能会虚造出函数模式中未定义的参数。
		 * 在调用函数之前，请在代码中验证这些参数。
		 */
		@Nullable
		private String arguments;
	}
}
