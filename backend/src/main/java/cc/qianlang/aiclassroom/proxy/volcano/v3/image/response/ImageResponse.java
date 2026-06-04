package cc.qianlang.aiclassroom.proxy.volcano.v3.image.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 火山方舟图像生成 API 非流式响应体。
 * <p>
 * 接口地址：{@code POST https://ark.cn-beijing.volces.com/api/v3/images/generations}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ImageResponse {

	/**
	 * 本次请求实际使用的模型 ID
	 */
	private String model;

	/**
	 * 创建时间的 Unix 时间戳（秒）
	 */
	private long created;

	/**
	 * 生成的图像信息列表
	 */
	private List<ImageData> data;

	/**
	 * 已配置的工具列表，未配置工具时为 null
	 */
	@Nullable
	private List<Tool> tools;

	/**
	 * 本次请求的用量统计
	 */
	private Usage usage;

	/**
	 * 请求级别的错误信息，请求整体失败时存在
	 */
	@Nullable
	private Error error;

	// -------------------------------------------------------------------------
	// 内部类
	// -------------------------------------------------------------------------

	/**
	 * 单张图像的生成结果。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class ImageData {

		/**
		 * 图像 URL，当 {@code response_format=url} 时返回，有效期 24 小时
		 */
		@Nullable
		private String url;

		/**
		 * Base64 编码的图像内容，当 {@code response_format=b64_json} 时返回
		 */
		@Nullable
		private String b64Json;

		/**
		 * 图像实际像素尺寸（如 {@code "2048x2048"}），
		 * 仅 seedream-5.0-lite/4.5/4.0 模型返回
		 */
		@Nullable
		private String size;

		/**
		 * 单张图像级别的错误信息，该张图像生成失败时存在
		 */
		@Nullable
		private ImageError error;

		/**
		 * 单张图像的错误详情。
		 */
		@Getter
		@NoArgsConstructor
		@AllArgsConstructor
		@NullMarked
		public static class ImageError {

			/**
			 * 错误码
			 */
			private String code;

			/**
			 * 错误描述信息
			 */
			private String message;
		}
	}

	/**
	 * 已配置的工具信息。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Tool {

		/**
		 * 工具类型，例如 {@code "web_search"}
		 */
		private String type;
	}

	/**
	 * 请求级别的错误详情。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Error {

		/**
		 * 错误码
		 */
		private String code;

		/**
		 * 错误描述信息
		 */
		private String message;
	}
}
