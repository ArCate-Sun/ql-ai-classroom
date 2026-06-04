package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;

/**
 * TTS 音频数据帧，对应 {@link TtsEventType#TTS_RESPONSE}（event=352）。
 * <p>
 * {@code audioData} 为 Base64 编码的音频片段，需解码后才能播放。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public final class TtsResponseChunk extends TtsChunk {

	/**
	 * Base64 编码的音频数据。
	 */
	private String data;

}
