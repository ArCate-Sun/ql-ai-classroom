package cc.qianlang.aiclassroom.proxy.zai.v4.image.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Z.AI 图像生成 API 响应体。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ImageGenerationResponse {

	/**
	 * 请求创建时间，Unix 时间戳（秒）。
	 */
	private long created;

	/**
	 * 生成的图片列表，目前数组中只包含一张图片。
	 */
	private List<ImageData> data;

	/**
	 * 内容安全审核信息列表。
	 */
	@Nullable
	private List<ContentFilter> contentFilter;

	/**
	 * 单张生成图片的数据。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class ImageData {

		/**
		 * 图片链接，临时链接有效期为 30 天，请及时转存。
		 */
		private String url;
	}

	/**
	 * 内容安全审核结果条目。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class ContentFilter {

		/**
		 * 安全生效环节（模型推理 / 用户输入 / 历史上下文）。
		 */
		@Nullable
		private ContentFilterRole role;

		/**
		 * 严重程度，0~3，{@code 0} 表示最严重，{@code 3} 表示轻微。
		 */
		@Nullable
		private Integer level;
	}

}
