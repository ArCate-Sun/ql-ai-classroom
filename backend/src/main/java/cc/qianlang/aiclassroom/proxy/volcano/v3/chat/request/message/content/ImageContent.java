package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 图片模态内容部分，对应 {@code content} 数组中 {@code type = "image_url"} 的对象。
 */
@Getter
@Builder
@NullMarked
public final class ImageContent implements IMessageContent {

	/**
	 * 图片内容，必填。
	 */
	private ImageUrl imageUrl;

	@Override
	public ContentType getType() {
		return ContentType.IMAGE_URL;
	}

	/**
	 * 图片模态内容，对应 {@code image_url} 对象。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class ImageUrl {

		/**
		 * 图片 URL 或 Base64 编码，必填。
		 */
		private String url;

		/**
		 * 图片理解精细度（可选）：{@link ImageDetail#LOW}、{@link ImageDetail#HIGH}、
		 * {@link ImageDetail#XHIGH}，不同模型默认取值不同。
		 */
		@Nullable
		private ImageDetail detail;

		/**
		 * 输入给模型的图片像素范围（可选）。设置后优先级高于 {@code detail} 字段。
		 * 图片像素范围需在 [196, 36,000,000]。
		 */
		@Nullable
		private ImagePixelLimit imagePixelLimit;

		/**
		 * 图片像素范围限制，对应 {@code image_url.image_pixel_limit} 对象。
		 */
		@Getter
		@Builder
		@NullMarked
		public static class ImagePixelLimit {

			/**
			 * 最大像素限制，大于此像素的图片将等比例缩小（可选）。
			 */
			@Nullable
			private Integer maxPixels;

			/**
			 * 最小像素限制，小于此像素的图片将等比例放大（可选）。
			 */
			@Nullable
			private Integer minPixels;
		}
	}

}
