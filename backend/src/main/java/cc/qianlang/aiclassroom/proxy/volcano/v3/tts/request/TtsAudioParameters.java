package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 音频参数，对应请求体 {@code req_params.audio_params} 字段。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class TtsAudioParameters {

	/**
	 * 音频编码格式，默认 {@link TtsAudioFormat#MP3}。
	 * <p>
	 * 注意：流式场景下传入 WAV 会多次返回 WAV header，建议流式时使用 {@link TtsAudioFormat#PCM}。
	 */
	@Nullable
	private TtsAudioFormat format;

	/**
	 * 音频采样率（Hz），可选值：8000、16000、22050、24000、32000、44100、48000，默认 24000。
	 */
	@Nullable
	private Integer sampleRate;

	/**
	 * 音频比特率，仅针对 MP3 格式生效，默认范围 64k～160k。
	 * <p>
	 * 默认设置范围为 64k～160k，传了 {@code disable_default_bit_rate} 为 {@code true} 后可以设置到64k以下。
	 * <p>
	 * 注：{@code bit_rate} 只针对 MP3 格式，wav 计算比特率跟 pcm 一样是 比特率 (bps) = 采样率 × 位深度 × 声道数
	 * <p>
	 * 目前大模型 TTS 只能改采样率，所以对于 wav 格式来说只能通过改采样率来变更音频的比特率。
	 */
	@Nullable
	private Integer bitRate;

	/**
	 * 情感设置，仅部分音色支持。
	 * <p>
	 * 详见 <a href="https://www.volcengine.com/docs/6561/1257544">大模型语音合成 API 音色列表 - 多情感音色</a>。
	 */
	@Nullable
	private TtsEmotion emotion;

	/**
	 * 情绪强度，范围 [1, 5]，默认 4。仅在设置了 {@code emotion} 后生效。
	 * <p>
	 * 调用 {@code emotion} 设置情感参数后可使用 {@code emotion_scale} 进一步设置情绪值，范围 1~5，不设置时默认值为 4。
	 * <p>
	 * 注：理论上情绪值越大，情感越明显。但情绪值 1~5 实际为非线性增长，可能存在超过某个值后，情绪增加不明显，例如设置 3 和 5 时情绪值可能接近。
	 */
	@Nullable
	private Float emotionScale;

	/**
	 * 语速，取值范围 [-50, 100]，100 为 2.0 倍速，-50 为 0.5 倍速，默认 0。
	 */
	@Nullable
	private Float speechRate;

	/**
	 * 音量，取值范围 [-50, 100]，100 为 2.0 倍音量，-50 为 0.5 倍音量，默认 0。
	 * mix 音色暂不支持。
	 */
	@Nullable
	private Float loudnessRate;

	/**
	 * 是否返回句级别字的时间戳，默认 {@code false}。
	 * <p>
	 * 开启后，在原有返回的事件 {@code event=TTSSentenceEnd} 中，新增该子句的时间戳信息。
	 * <p>
	 * <ul>
	 *     <li>一个子句的时间戳返回之后才会开始返回下一句音频。</li>
	 *     <li>
	 *         合成有多个子句会多次返回 {@code event=TTSSentenceStart} 和 {@code event=TTSSentenceEnd}。
	 *         开启字幕后字幕跟随 {@code event=TTSSentenceEnd} 返回。
	 *     </li>
	 *     <li>字/词粒度的时间戳，其中字/词是tn。具体可以看下面的例子。</li>
	 *     <li>支持中、英，其他语种、方言暂时不支持。</li>
	 * </ul>
	 * <p>
	 * 仅适用于"豆包语音合成模型 1.0"音色。
	 */
	@Nullable
	private Boolean enableTimestamp;

	/**
	 * 是否返回字幕信息，默认 {@code false}。
	 * <p>
	 * 开启后，新增返回事件 {@code event=TTSSubtitle}，包含字幕信息。
	 * <p>
	 * <ul>
	 *     <li>
	 *         在一句音频合成之后，不会立即返回该句的字幕。
	 *         合成进度不会被字幕识别阻塞，当一句的字幕识别完成后立即返回。
	 *         可能一个子句的字幕返回的时候，已经返回下一句的音频帧给调用方了。
	 *     </li>
	 *     <li>
	 *         合成有多个子句，仅返回一次 {@code event=TTSSentenceStart} 和 {@code event=TTSSentenceEnd}。
	 *         开启字幕后会多次返回 {@code event=TTSSubtitle}。
	 *     </li>
	 *     <li>字/词粒度的时间戳，其中字/词是原文。</li>
	 *     <li>支持中、英，其他语种、方言暂时不支持。</li>
	 *     <li>latex公式不支持。</li>
	 *     <li>ssml不支持。</li>
	 * </ul>
	 * 仅适用于 TTS 2.0 及 ICL 2.0 音色（{@code enable_subtitle} 字段）。
	 */
	@Nullable
	private Boolean enableSubtitle;

}
