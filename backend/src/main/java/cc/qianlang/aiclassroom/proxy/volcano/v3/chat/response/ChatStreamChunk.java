package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response;

import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.ServiceTier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 火山方舟 Chat Completions API 流式响应的单个 SSE 数据块（{@code object = "chat.completion.chunk"}）。
 * <p>
 * 流式输出格式说明：
 * <ul>
 *   <li>每个 SSE 事件的 {@code data} 字段包含一个此类型的 JSON 对象。</li>
 *   <li>流结束时发送 {@code data: [DONE]}，客户端收到后停止解析。</li>
 * </ul>
 * <p>
 * 思考模式流式输出顺序：推理内容（{@code delta.reasoningContent}）先于最终回答（{@code delta.content}）到达，
 * 二者不会交织出现，客户端应分别拼接两个字段。
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
	 * 本次请求的唯一标识符，同一请求的所有 chunk 共享此 ID
	 */
	private String id;

	/**
	 * 对象类型，固定为 {@code "chat.completion.chunk"}
	 */
	private String object;

	/**
	 * 创建时间的 Unix 时间戳（秒），同一请求的所有 chunk 值相同
	 */
	private long created;

	/**
	 * 本次请求实际使用的模型名称和版本
	 */
	private String model;

	/**
	 * 本次请求实际使用的推理模式：{@code scale}、{@code default}、{@code fast}
	 */
	private ServiceTier serviceTier;

	/**
	 * 增量候选列表。正常 chunk 始终有一个元素；流结束前的用量统计 chunk 此列表为空。
	 */
	private List<ChatStreamChunkChoice> choices;

	/**
	 * Token 消耗统计。仅在请求时设置 {@code streamOptions.includeUsage=true} 且在
	 * {@code data: [DONE]} 前的最后一个 chunk 中返回；其他 chunk 此字段为 null。
	 */
	@Nullable
	private Usage usage;
}
