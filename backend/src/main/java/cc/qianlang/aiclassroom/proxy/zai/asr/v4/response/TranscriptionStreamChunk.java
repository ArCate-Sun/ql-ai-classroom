package cc.qianlang.aiclassroom.proxy.zai.asr.v4.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Z.AI 语音转文本 API 流式响应的单个 SSE 数据块。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class TranscriptionStreamChunk {

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
	 * 实际处理本次请求的模型名称。
	 */
	@Nullable
	private String model;

	/**
	 * 音频转录事件类型：
	 * {@link TranscriptionEventType#DELTA} 表示正在转录，
	 * {@link TranscriptionEventType#DONE} 表示转录完成。
	 */
	@Nullable
	private TranscriptionEventType type;

	/**
	 * 模型增量返回的音频转录内容。
	 */
	@Nullable
	private String delta;

}
