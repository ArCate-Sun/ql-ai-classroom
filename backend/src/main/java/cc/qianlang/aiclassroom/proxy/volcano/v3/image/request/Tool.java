package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 火山方舟图像生成接口中的工具定义，对应请求体 {@code tools[]}。
 * <p>
 * 当前仅支持 {@link ToolType#WEB_SEARCH} 类型（且仅 seedream-5.0-lite 模型可用）。
 * 使用静态工厂方法构造实例：{@link #webSearch()}。
 */
@Getter
@NullMarked
public class Tool {

	/**
	 * 工具类型，当前仅支持 {@link ToolType#WEB_SEARCH}
	 */
	private final ToolType type;

	private Tool(ToolType type) {
		this.type = type;
	}

	/**
	 * 构造联网搜索工具实例。
	 *
	 * @return 类型为 {@link ToolType#WEB_SEARCH} 的工具
	 */
	public static Tool webSearch() {
		return new Tool(ToolType.WEB_SEARCH);
	}
}
