package cc.qianlang.aiclassroom.proxy.zai.asr.v4.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Z.AI 语音转文本 API 非流式响应体。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class TranscriptionResponse {

	/**
	 * 任务 ID。
	 */
	@Nullable
	private String id;

	/**
	 * 请求创建时间，Unix 时间戳（秒）。
	 */
	private long created;

	/**
	 * 请求唯一标识符，由用户端传入或平台自动生成。
	 */
	@Nullable
	private String requestId;

	/**
	 * 实际处理本次请求的模型名称。
	 */
	@Nullable
	private String model;

	/**
	 * 音频转录的完整文本内容。
	 */
	@Nullable
	private String text;

}
