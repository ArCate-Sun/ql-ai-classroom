package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 流式输出附加选项。仅在 {@code stream=true} 时有效。
 */
@Getter
@Builder
@NullMarked
public class StreamOptions {

	/**
	 * 是否在流结束前额外返回一个包含完整 Token 用量的数据块，默认 false。
	 * {@code true} 时会在 {@code data: [DONE]} 前额外输出一个 chunk，其 choices 为空数组，usage 包含完整用量。
	 */
	@Nullable
	private Boolean includeUsage;

	/**
	 * 是否在每个流式 chunk 中返回累计 Token 用量，默认 false。
	 * {@code true} 时每个 chunk 的 usage 字段包含到当前 chunk 为止的累计用量。
	 */
	@Nullable
	private Boolean chunkIncludeUsage;

}
