package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山引擎文本转语音 API 请求体。
 * <p>
 * 接口地址（HTTP Chunked）：
 * {@code POST https://openspeech.bytedance.com/api/v3/tts/unidirectional}
 * <p>
 * 接口地址（SSE）：
 * {@code POST https://openspeech.bytedance.com/api/v3/tts/unidirectional/sse}
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class TtsRequest {

	/**
	 * 资源 ID，如 {@code "seed-tts-2.0"}、{@code "seed-tts-1.0"}
	 */
	private String resourceId;

	/**
	 * 标识客户端请求ID，uuid随机字符串
	 */
	@Nullable
	private String requestId;

	/**
	 * 请求消耗的用量返回控制标记。
	 * 当携带此字段，在合成音频结束时会返回所需的用量数据。
	 */
	@Nullable
	private RequireUsageTokensReturn requireUsageTokensReturn;


	/**
	 * 用户信息。
	 */
	@Nullable
	private User user;

	/**
	 * 核心请求参数，必填。
	 */
	private Parameters reqParams;

	/**
	 * 火山引擎 TTS 请求的用户信息。
	 *
	 * @author 阿猫_ACat
	 * @version 0.1
	 */
	@Getter
	@Builder
	@NullMarked
	public static class User {

		/**
		 * 用户 UID。
		 */
		@Nullable
		private String uid;

	}

	/**
	 * 火山引擎 TTS 请求的核心参数，对应请求体 {@code req_params} 字段。
	 *
	 * @author 阿猫_ACat
	 * @version 0.1
	 */
	@Getter
	@Builder
	@NullMarked
	public static class Parameters {

		/**
		 * 输入文本，必填。
		 * {@code ssml} 和 {@code text} 字段，至少有一个不为空。
		 */
		@Nullable
		private String text;

		/**
		 * SSML 文本，优先级高于 {@code text}。
		 * {@code ssml} 和 {@code text} 字段，至少有一个不为空。
		 */
		@Nullable
		private String ssml;

		/**
		 * 发音人，必填。
		 * 支持系统音色（{@link TtsSpeaker}）及声音复刻音色（直接传入复刻音色 ID）。
		 * 完整音色列表见
		 * <a href="https://www.volcengine.com/docs/6561/1257544">发音人列表</a>。
		 */
		private TtsSpeaker speaker;

		/**
		 * 音频参数，必填。
		 */
		private TtsAudioParameters audioParams;


		/**
		 * 用户自定义扩展参数。
		 * <p>
		 * 注意：火山引擎接口要求此字段以 JSON 字符串形式传递，
		 * 序列化前需将 {@link TtsAdditions} 对象先序列化为 JSON 字符串。
		 */
		@Nullable
		private String additions;

		/**
		 * 混音参数，仅适用于"豆包语音合成模型 1.0"音色。
		 * 使用时须将 {@code speaker} 设为 {@link TtsSpeaker#CUSTOM_MIX}。
		 */
		@Nullable
		private TtsMixSpeaker mixSpeaker;

	}

}
