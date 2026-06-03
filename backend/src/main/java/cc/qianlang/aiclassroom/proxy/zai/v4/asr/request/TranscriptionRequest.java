package cc.qianlang.aiclassroom.proxy.zai.v4.asr.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * Z.AI 语音转文本 API 请求体。
 * <p>
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/audio/transcriptions}，
 * 请求格式为 {@code multipart/form-data}。
 * <p>
 * 音频文件说明：
 * <ul>
 *   <li>{@link #file} 与 {@link #fileBase64} 二选一传入，同时传入时以 {@link #file} 为准</li>
 *   <li>支持格式：{@code .wav}、{@code .mp3}</li>
 *   <li>规格限制：文件大小 ≤ 25 MB，音频时长 ≤ 30 秒</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class TranscriptionRequest {

	// -------------------------------------------------------------------------
	// 必填字段
	// -------------------------------------------------------------------------

	/**
	 * 模型 ID，例如 {@code "glm-asr-2512"}。
	 */
	private String model;

	// -------------------------------------------------------------------------
	// 音频来源（二选一）
	// -------------------------------------------------------------------------

	/**
	 * 需要转录的音频文件，支持 {@code .wav}、{@code .mp3} 格式。
	 * 与 {@link #fileBase64} 二选一，同时传入时以本字段为准。
	 */
	@Nullable
	private Resource file;

	/**
	 * 音频文件的 Base64 编码内容。
	 * 与 {@link #file} 二选一，同时传入时以 {@link #file} 为准。
	 */
	@Nullable
	private String fileBase64;

	// -------------------------------------------------------------------------
	// 可选字段
	// -------------------------------------------------------------------------

	/**
	 * 上下文提示，在长文本场景中可提供之前的转录结果作为参考。建议小于 8000 字。
	 */
	@Nullable
	private String prompt;

	/**
	 * 热词表，用于提升特定领域词汇识别率。建议不超过 100 个。
	 */
	@Nullable
	private List<String> hotwords;

	/**
	 * 是否开启流式输出（Server-Sent Events），默认 {@code false}。
	 * 为 {@code true} 时模型逐块返回转录内容，流结束时发送 {@code data: [DONE]}。
	 */
	@Nullable
	private Boolean stream;

	/**
	 * 请求唯一标识符，建议使用 UUID 格式，长度 6~64 字符。未提供时由平台自动生成。
	 */
	@Nullable
	private String requestId;

	/**
	 * 终端用户的唯一 ID，用于协助平台对违规行为进行干预。长度 6~128 字符。
	 */
	@Nullable
	private String userId;

}
