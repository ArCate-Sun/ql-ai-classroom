package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山方舟 API 返回的 Token 消耗统计，非流式响应和流式响应末尾块均包含此字段。
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
	 * 输入 Token 总数
	 */
	private int promptTokens;

	/**
	 * 输入 Token 用量详情
	 */
	@Nullable
	private PromptTokensDetails promptTokensDetails;

	/**
	 * 输出 Token 总数，包含思考模式下的推理 Token
	 */
	private int completionTokens;

	/**
	 * 输入与输出 Token 之和
	 */
	private int totalTokens;

	/**
	 * 输出 Token 的详细分类，包含推理 Token 数。
	 * 某些情况下（如非思考模式）可能为 null。
	 */
	@Nullable
	private CompletionTokensDetails completionTokensDetails;

	/**
	 * 输入 Token 用量详情。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class PromptTokensDetails {

		/**
		 * 缓存命中的输入内容（含文本、音频等所有类型）所消耗的 token 总数
		 */
		private int cachedTokens;

		/**
		 * 音频输入内容所消耗的 token 数量
		 */
		private int audioTokens;

		/**
		 * 缓存命中的音频输入内容所消耗的 token 数量
		 */
		private int audioCachedTokens;
	}

	/**
	 * 输出 Token 详情。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class CompletionTokensDetails {

		/**
		 * 思考模式下链式思考（Chain of Thought）消耗的 Token 数，计入总输出 Token 费用中。
		 * 非思考模式时为 0。
		 */
		private int reasoningTokens;
	}
}
