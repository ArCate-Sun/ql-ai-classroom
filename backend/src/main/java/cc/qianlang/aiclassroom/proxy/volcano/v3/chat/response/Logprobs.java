package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Token 对数概率信息，对应响应体 {@code choices[].logprobs}。
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NullMarked
public class Logprobs {

	/**
	 * 包含输出 token 对数概率信息的列表
	 */
	@Nullable
	private List<TokenLogprob> content;

	/**
	 * 单个 token 的对数概率信息。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@NullMarked
	public static class TokenLogprob {

		/**
		 * 输出的 token
		 */
		private String token;

		/**
		 * 该 token 的对数概率
		 */
		private double logprob;

		/**
		 * 该 token UTF-8 字节表示的整数列表。
		 * 一般在一个 UTF-8 字符被拆分成多个 token 来表示时有用。
		 * 如果 token 没有对应的字节表示，则该值为 null。
		 */
		@Nullable
		private List<Integer> bytes;

		/**
		 * 在该输出位置上，输出概率 top N 的 token 的列表，以及它们的对数概率。
		 * 在罕见情况下，返回的 token 数量可能少于请求参数中指定的 top_logprobs 值。
		 */
		@Nullable
		private List<TokenLogprob> topLogprobs;
	}
}
