package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 提示词优化选项，对应请求体 {@code optimize_prompt_options}。
 * <p>
 * 仅 seedream-5.0-lite/4.5/4.0 模型支持。
 * 使用静态工厂方法构造实例：{@link #standard()} 或 {@link #fast()}。
 */
@Getter
@NullMarked
public class OptimizePromptOptions {

	/**
	 * 提示词优化模式
	 */
	private final OptimizePromptMode mode;

	private OptimizePromptOptions(OptimizePromptMode mode) {
		this.mode = mode;
	}

	/**
	 * 构造标准提示词优化选项（{@link OptimizePromptMode#STANDARD}，默认）。
	 *
	 * @return 标准优化选项实例
	 */
	public static OptimizePromptOptions standard() {
		return new OptimizePromptOptions(OptimizePromptMode.STANDARD);
	}

	/**
	 * 构造快速提示词优化选项（{@link OptimizePromptMode#FAST}）。
	 *
	 * @return 快速优化选项实例
	 */
	public static OptimizePromptOptions fast() {
		return new OptimizePromptOptions(OptimizePromptMode.FAST);
	}
}
