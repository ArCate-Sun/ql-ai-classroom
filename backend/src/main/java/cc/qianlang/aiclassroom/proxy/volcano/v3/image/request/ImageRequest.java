package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 火山方舟图像生成 API 请求体。
 * <p>
 * 接口地址：{@code POST https://ark.cn-beijing.volces.com/api/v3/images/generations}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class ImageRequest {

	// -------------------------------------------------------------------------
	// 必填字段
	// -------------------------------------------------------------------------

	/**
	 * 模型 ID（Model ID）或推理接入点 ID（Endpoint ID）。
	 * 例如 {@code "seedream-3.0"}、{@code "seedream-5.0-lite"}。
	 */
	private String model;

	/**
	 * 文本提示词，支持中英文，推荐中文不超过约 300 字、英文不超过约 600 词。
	 */
	private String prompt;

	// -------------------------------------------------------------------------
	// 图像参考
	// -------------------------------------------------------------------------

	/**
	 * 图像参考输入。
	 * <ul>
	 *   <li>单张图像参考：传入一个 URL 或 Base64 字符串，使用 {@link ImageInput#of(String)}</li>
	 *   <li>多张图像参考：传入 2–14 个 URL 或 Base64 字符串，使用 {@link ImageInput#of(java.util.List)}</li>
	 * </ul>
	 */
	@Nullable
	private ImageInput image;

	// -------------------------------------------------------------------------
	// 生成控制
	// -------------------------------------------------------------------------

	/**
	 * 输出图像分辨率。支持关键词（{@code "2K"}、{@code "3K"}、{@code "4K"}）
	 * 和像素尺寸（如 {@code "2048x2048"}）两种格式。
	 * 要求总像素在 [3,686,400, 16,777,216] 之间，宽高比在 [1/16, 16] 之间。
	 */
	@Nullable
	private String size;

	/**
	 * 顺序图像生成模式，默认 {@link SequentialImageGeneration#DISABLED}。
	 * 设为 {@link SequentialImageGeneration#AUTO} 时启用组图生成功能。
	 */
	@Nullable
	private SequentialImageGeneration sequentialImageGeneration;

	/**
	 * 顺序图像生成选项，仅在 {@code sequentialImageGeneration=AUTO} 时有效。
	 * 仅 seedream-5.0-lite/4.5/4.0 模型支持。
	 */
	@Nullable
	private SequentialImageGenerationOptions sequentialImageGenerationOptions;

	/**
	 * 工具列表，当前仅支持 {@link ToolType#WEB_SEARCH}（联网搜索），
	 * 且仅 seedream-5.0-lite 模型支持。使用 {@link Tool#webSearch()} 构造。
	 */
	@Nullable
	private List<Tool> tools;

	/**
	 * 是否开启流式输出（Server-Sent Events），默认 false。
	 * 仅 seedream-5.0-lite/4.5/4.0 模型支持。
	 */
	@Nullable
	private Boolean stream;

	/**
	 * 引导系数，取值范围 [1, 10]，控制生成图像与提示词的契合程度。
	 * seedream-5.0-lite/4.5/4.0 模型不支持该字段。
	 */
	@Nullable
	private Float guidanceScale;

	// -------------------------------------------------------------------------
	// 输出格式
	// -------------------------------------------------------------------------

	/**
	 * 图像输出格式，默认 {@link OutputFormat#JPEG}。
	 * 仅 seedream-5.0-lite 模型支持。
	 */
	@Nullable
	private OutputFormat outputFormat;

	/**
	 * 响应格式，默认 {@link ResponseFormat#URL}。
	 * <ul>
	 *   <li>{@link ResponseFormat#URL} — 返回图像 URL，有效期 24 小时</li>
	 *   <li>{@link ResponseFormat#B64_JSON} — 返回 Base64 编码图像</li>
	 * </ul>
	 */
	@Nullable
	private ResponseFormat responseFormat;

	/**
	 * 是否在生成图像上添加水印，默认 true。
	 */
	@Nullable
	private Boolean watermark;

	/**
	 * 提示词优化选项，仅 seedream-5.0-lite/4.5/4.0 模型支持。
	 */
	@Nullable
	private OptimizePromptOptions optimizePromptOptions;
}
