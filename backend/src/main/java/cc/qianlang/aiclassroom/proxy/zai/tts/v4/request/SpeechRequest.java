package cc.qianlang.aiclassroom.proxy.zai.tts.v4.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Z.AI 文本转语音 API 请求体。
 * <p>
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/audio/speech}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class SpeechRequest {

	// -------------------------------------------------------------------------
	// 必填字段
	// -------------------------------------------------------------------------

	/**
	 * 要使用的 TTS 模型编码，例如 {@code "glm-tts"}。
	 */
	private String model;

	/**
	 * 要转换为语音的文本，最大长度 1024 字符，必填。
	 */
	private String input;

	/**
	 * 生成音频时使用的音色，必填。
	 * 支持系统音色（{@link TtsVoice}）及复刻音色（直接传入复刻音色 ID）。
	 */
	private TtsVoice voice;

	// -------------------------------------------------------------------------
	// 可选字段
	// -------------------------------------------------------------------------

	/**
	 * 是否为 AI 生成音频添加水印。
	 * <ul>
	 *   <li>{@code true}（默认）— 启用显式水印及隐式数字水印，符合政策要求</li>
	 *   <li>{@code false} — 关闭所有水印，仅对已完成去水印操作的用户生效</li>
	 * </ul>
	 */
	@Nullable
	private Boolean watermarkEnabled;

	/**
	 * 是否启用流式输出（Server-Sent Events），默认 {@code false}。
	 * <ul>
	 *   <li>{@code true} — 模型逐块返回编码后的音频内容</li>
	 *   <li>{@code false} — 模型生成完毕后一次性返回完整音频二进制数据</li>
	 * </ul>
	 */
	@Nullable
	private Boolean stream;

	/**
	 * 语速，默认 1.0，取值范围 [0.5, 2]。
	 */
	@Nullable
	private Double speed;

	/**
	 * 音量，默认 1.0，取值范围 (0, 10]。
	 */
	@Nullable
	private Double volume;

	/**
	 * 流式返回时的音频编码格式，默认 {@link TtsEncodeFormat#BASE64}。
	 * 仅在 {@code stream=true} 时生效。
	 */
	@Nullable
	private TtsEncodeFormat encodeFormat;

	/**
	 * 音频输出格式，默认 {@link TtsResponseFormat#PCM}。
	 * 流式生成时仅支持 {@link TtsResponseFormat#PCM}。
	 */
	@Nullable
	private TtsResponseFormat responseFormat;

}
