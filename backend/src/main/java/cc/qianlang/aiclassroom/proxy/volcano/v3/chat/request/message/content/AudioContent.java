package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.content;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 音频模态内容部分，对应 {@code content} 数组中 {@code type = "input_audio"} 的对象。
 * <p>
 * {@code url} 与 {@code data} 字段二选一传入：
 * <ul>
 *   <li>{@code url} — 音频内容的 URL</li>
 *   <li>{@code data} — 音频内容的 Base64 编码；使用时 {@code format} 为必填</li>
 * </ul>
 */
@Getter
@Builder
@NullMarked
public final class AudioContent implements IMessageContent {

	/**
	 * 音频内容，必填。
	 */
	private InputAudio inputAudio;

	@Override
	public ContentType getType() {
		return ContentType.INPUT_AUDIO;
	}

	/**
	 * 音频模态内容，对应 {@code input_audio} 对象。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class InputAudio {

		/**
		 * 音频内容的 URL（与 {@code data} 二选一）。
		 */
		@Nullable
		private String url;

		/**
		 * 音频内容的 Base64 编码（与 {@code url} 二选一）。使用时 {@code format} 为必填。
		 * 文件大小不超过 25 MB，单次请求纯音频总时长不超过 120 分钟。
		 */
		@Nullable
		private String data;

		/**
		 * 音频格式（MIME 类型），使用 {@code data} 时必填。
		 */
		@Nullable
		private AudioFormat format;
	}
}
