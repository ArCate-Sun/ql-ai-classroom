package cc.qianlang.aiclassroom.proxy.zai.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Z.AI 对话补全 API 流式响应的单个 SSE 数据块。
 * <p>
 * 流式输出格式说明：
 * <ul>
 *   <li>每个 SSE 事件的 {@code data} 字段包含一个此类型的 JSON 对象。</li>
 *   <li>流结束时发送 {@code data: [DONE]}，客户端收到后停止解析。</li>
 * </ul>
 * <p>
 * 思考模式流式输出顺序：推理内容（{@code delta.reasoningContent}）先于最终回答
 * （{@code delta.content}）到达，二者不会交织出现，客户端应分别拼接两个字段。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ChatStreamChunk {

	/**
	 * 任务 ID，同一请求的所有 chunk 共享此 ID
	 */
	private String id;

	/**
	 * 请求创建时间，Unix 时间戳（秒），同一请求的所有 chunk 值相同
	 */
	private long created;

	/**
	 * 实际处理本次请求的模型名称
	 */
	private String model;

	/**
	 * 增量候选列表。正常 chunk 始终有一个元素；流结束前的用量统计 chunk 此列表为空。
	 */
	private List<ChatStreamChunkChoice> choices;

	/**
	 * 本次模型调用的 tokens 数量统计（最后一个 chunk 中返回）
	 */
	@Nullable
	private Usage usage;

	/**
	 * 内容安全相关信息
	 */
	@Nullable
	private List<ContentFilter> contentFilter;


}
