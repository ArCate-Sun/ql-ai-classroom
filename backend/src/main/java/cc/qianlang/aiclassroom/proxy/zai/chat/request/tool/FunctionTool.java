package cc.qianlang.aiclassroom.proxy.zai.chat.request.tool;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 函数调用工具定义，对应请求体 {@code tools} 中 {@code type=function} 的元素。
 * <p>
 * 序列化示例：
 * <pre>{@code
 * {
 *   "type": "function",
 *   "function": {
 *     "name": "get_weather",
 *     "description": "获取指定城市的天气信息",
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
public final class FunctionTool extends AbstractTool {

	/**
	 * 函数定义，必填。
	 */
	private Function function;

	@Override
	public ToolType getType() {
		return ToolType.FUNCTION;
	}

	/**
	 * 函数定义详情，最多支持 128 个函数。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class Function {

		/**
		 * 函数名称，必填。仅支持 {@code a-z、A-Z、0-9}、下划线和破折号，最大长度 64。
		 */
		private String name;

		/**
		 * 函数功能描述，供模型决策是否调用此函数，必填。
		 */
		private String description;

		/**
		 * 函数参数的 JSON Schema 定义，必填。
		 * 内容应符合 JSON Schema 规范，例如 {@code {"type":"object","properties":{...}}}。
		 */
		private Object parameters;

	}
}
