package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 强制调用指定函数的工具选择配置，对应 {@code tool_choice} 字段的对象形式。
 * <p>
 * 序列化示例：
 * <pre>{@code
 * {"type": "function", "function": {"name": "get_weather"}}
 * }</pre>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class NamedToolChoice implements IToolChoice {

	/**
	 * 指定要强制调用的函数信息，必填。
	 */
	private Function function;

	/**
	 * 工具类型，固定为 {@code "function"}，序列化为 JSON 的 {@code "type"} 字段。
	 */
	public ToolType getType() {
		return ToolType.FUNCTION;
	}

	/**
	 * 被强制调用的函数的引用信息。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class Function {

		/**
		 * 要调用的函数名称，需与 {@code tools} 中定义的函数名一致。
		 */
		private String name;
	}
}
