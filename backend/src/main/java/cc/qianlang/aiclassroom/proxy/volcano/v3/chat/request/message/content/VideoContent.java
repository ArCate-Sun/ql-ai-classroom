package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 视频模态内容部分，对应 {@code content} 数组中 {@code type = "video_url"} 的对象。
 */
@Getter
@Builder
@NullMarked
public final class VideoContent implements IMessageContent {

	/**
	 * 视频内容，必填。
	 */
	private VideoUrl videoUrl;

	@Override
	public ContentType getType() {
		return ContentType.VIDEO_URL;
	}

	/**
	 * 视频模态内容，对应 {@code video_url} 对象。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class VideoUrl {

		/**
		 * 视频 URL 或 Base64 编码，必填。
		 */
		private String url;

		/**
		 * 抽帧频率（可选），取值范围 [0.2, 5]，默认 1。
		 * 取值越高对画面变化越敏感，取值越低 token 花费越少。
		 */
		@Nullable
		private Float fps;
	}
}
