package cc.qianlang.aiclassroom.proxy.deepseek.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 流式输出附加选项。
 */
@Getter
@Builder
@NullMarked
public class StreamOptions {

	/**
	 * 是否在流结束前额外返回一个包含完整 Token 用量的数据块，默认 false。
	 * 建议始终设为 true 以便统计用量。
	 */
	private boolean includeUsage;

	/**
	 * 包含用量统计
	 */
	public static StreamOptions includeUsage() {
		return StreamOptions.builder().includeUsage(true).build();
	}

	/**
	 * 不包含用量统计
	 */
	public static StreamOptions excludeUsage() {
		return StreamOptions.builder().includeUsage(false).build();
	}
}
