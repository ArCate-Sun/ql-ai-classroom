package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 顺序图像生成选项，对应请求体 {@code sequential_image_generation_options}。
 * <p>
 * 仅在 {@code sequential_image_generation=auto} 时有效，
 * 且仅 seedream-5.0-lite/4.5/4.0 模型支持。
 * 使用静态工厂方法构造实例：{@link #of(int)}。
 */
@Getter
@Builder
@NullMarked
public class SequentialImageGenerationOptions {

	/**
	 * 最大生成图像数量，取值范围 [1, 15]
	 */
	@Nullable
	private Integer maxImages;

	/**
	 * 构造顺序图像生成选项。
	 *
	 * @param maxImages 最大生成图像数量，取值范围 [1, 15]
	 * @return 顺序图像生成选项实例
	 */
	public static SequentialImageGenerationOptions of(int maxImages) {
		return new SequentialImageGenerationOptions(maxImages);
	}
}
