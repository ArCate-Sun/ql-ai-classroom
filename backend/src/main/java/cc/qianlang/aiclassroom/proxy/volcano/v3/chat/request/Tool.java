package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山方舟 API 工具（函数）定义，对应请求体的 {@code tools} 数组元素。
 * <p>
 * 序列化示例：
 * <pre>{@code
 * {
 *   "type": "function",
 *   "function": {
 *     "name": "get_weather",
 *     "description": "获取指定城市的当前天气",
 *     "parameters": { "type": "object", "properties": { ... } }
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
public class Tool {

	/**
	 * 函数定义，必填。
	 */
	private Function function;

	/**
	 * 工具类型，固定为 {@code "function"}，序列化为 JSON 的 {@code "type"} 字段。
	 * 目前火山方舟 API 仅支持函数类型。
	 */
	public ToolType getType() {
		return ToolType.FUNCTION;
	}

	/**
	 * 函数定义详情。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class Function {

		/**
		 * 函数名称，必填。应使用简洁的英文标识符，建议使用英文字段名，中文置于 description 字段中。
		 */
		private String name;

		/**
		 * 函数的功能描述，用于帮助模型决策是否调用此函数（可选但强烈建议填写）。
		 */
		@Nullable
		private String description;

		/**
		 * 函数参数的 JSON Schema 定义，描述函数接受的参数结构（可选）。
		 * 内容应符合 JSON Schema 规范，例如 {@code {"type":"object","properties":{...}}}。
		 */
		@Nullable
		private Object parameters;
	}
}
