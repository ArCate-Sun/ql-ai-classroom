package cc.qianlang.aiclassroom.proxy.zai.v4.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Z.AI 对话补全 API 返回的 Token 消耗统计，
 * 非流式响应和流式响应末尾块均包含此字段。
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
	 * 用户输入的 Token 数量。
	 * 对于 glm-4-voice，1 秒音频 = 12.5 Tokens（向上取整）。
	 */
	private int promptTokens;

	/**
	 * 模型输出的 Token 数量。
	 */
	private int completionTokens;

	/**
	 * Token 总数。
	 */
	private int totalTokens;

	/**
	 * 输入 Token 的详细分类（可选）。
	 */
	@Nullable
	private PromptTokensDetails promptTokensDetails;

	/**
	 * 输入 Token 详情。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class PromptTokensDetails {

		/**
		 * 命中缓存的 Token 数量
		 */
		private int cachedTokens;
	}
}
