package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 音频编码格式枚举，对应请求体 {@code req_params.audio_params.format} 字段。
 * <p>
 * 注意：流式场景下传入 WAV 会多次返回 WAV header，建议流式时使用 {@link #PCM}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum TtsAudioFormat {

	UNKNOWN("unknown"),

	/**
	 * MP3 格式（默认）
	 */
	MP3("mp3"),

	/**
	 * OGG Opus 格式
	 */
	OGG_OPUS("ogg_opus"),

	/**
	 * PCM 格式，流式场景推荐
	 */
	PCM("pcm"),

	;

	private final String code;

	TtsAudioFormat(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static TtsAudioFormat fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return TtsAudioFormat.UNKNOWN;
		for (TtsAudioFormat value : values()) {
			if (value.code.equals(code)) return value;
		}
		return TtsAudioFormat.UNKNOWN;
	}
}
