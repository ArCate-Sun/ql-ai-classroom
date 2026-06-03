package cc.qianlang.aiclassroom.proxy.deepseek.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * DeepSeek API 返回的 Token 消耗统计，非流式响应和流式响应末尾块均包含此字段。
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
	 * 输入 Token 总数，等于 {@code promptCacheHitTokens} + {@code promptCacheMissTokens}。
	 */
	private int promptTokens;

	/**
	 * 命中前缀缓存（KV Cache）的输入 Token 数，这部分 Token 费用较低。
	 */
	private int promptCacheHitTokens;

	/**
	 * 未命中缓存的输入 Token 数，需完整计算。
	 */
	private int promptCacheMissTokens;

	/**
	 * 输出 Token 总数，包含思考模式下的推理 Token。
	 */
	private int completionTokens;

	/**
	 * 输入与输出 Token 之和。
	 */
	private int totalTokens;

	/**
	 * 输出 Token 的详细分类，包含推理 Token 数。
	 * 某些情况下（如非思考模式）可能为 null。
	 */
	@Nullable
	private CompletionTokensDetails completionTokensDetails;

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
