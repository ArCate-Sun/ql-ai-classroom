package cc.qianlang.aiclassroom.proxy.volcano.v3.image.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山方舟图像生成 API 返回的用量统计，对应响应体 {@code usage}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class Usage {

	/**
	 * 成功生成的图像数量
	 */
	private int generatedImages;

	/**
	 * 输出 Token 数量，等于所有图像宽×高/256 之和
	 */
	private int outputTokens;

	/**
	 * 总 Token 数量，当前与 {@code outputTokens} 相同
	 */
	private int totalTokens;

	/**
	 * 工具用量统计，未使用工具时为 null
	 */
	@Nullable
	private ToolUsage toolUsage;

	/**
	 * 工具用量详情。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class ToolUsage {

		/**
		 * 联网搜索（web_search）调用次数
		 */
		private int webSearch;
	}
}
