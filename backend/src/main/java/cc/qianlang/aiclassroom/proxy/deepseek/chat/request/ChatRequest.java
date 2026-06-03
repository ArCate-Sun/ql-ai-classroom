package cc.qianlang.aiclassroom.proxy.deepseek.chat.request;

import cc.qianlang.aiclassroom.proxy.deepseek.chat.request.message.*;
import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * DeepSeek Chat Completions API 请求体。
 * <p>
 * 接口地址：{@code POST https://api.deepseek.com/chat/completions}（兼容 OpenAI 格式）。
 * <p>
 * 思考模式（Thinking Mode）注意事项：
 * <ul>
 *   <li>开启思考模式时，{@code temperature}、{@code topP}、{@code presencePenalty}、
 *       {@code frequencyPenalty} 将被服务端忽略，设置无效。</li>
 *   <li>关闭思考模式时，需同时设置 {@code thinking.type=DISABLED} 和
 *       {@code reasoningEffort=NONE}。</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class ChatRequest {

	// -------------------------------------------------------------------------
	// 必填字段
	// -------------------------------------------------------------------------

	/**
	 * 模型 ID，例如 {@code "deepseek-v4-pro"}、{@code "deepseek-v4-flash"}。
	 */
	private String model;

	/**
	 * 对话消息列表，至少包含一条消息。
	 * 元素类型为 {@link AbstractMessage} 的子类：
	 * {@link SystemMessage}、{@link UserMessage}、
	 * {@link AssistantMessage}、{@link ToolMessage}。
	 */
	private List<AbstractMessage> messages;

	// -------------------------------------------------------------------------
	// 生成控制（思考模式下被忽略）
	// -------------------------------------------------------------------------

	/**
	 * 采样温度（0~2），默认 1。值越高输出越随机，值越低输出越确定。
	 * 不建议与 {@code topP} 同时使用。思考模式下此参数被忽略。
	 */
	@Nullable
	private Double temperature;

	/**
	 * 核采样概率阈值（0~1），默认 1。仅考虑累计概率质量前 {@code topP} 的 Token。
	 * 不建议与 {@code temperature} 同时使用。思考模式下此参数被忽略。
	 */
	@Nullable
	private Double topP;

	/**
	 * 存在惩罚（-2~2），默认 0。正值惩罚已出现的 Token，鼓励模型涉及新话题。
	 * 思考模式下此参数被忽略。
	 */
	@Deprecated
	@Nullable
	private Double presencePenalty;

	/**
	 * 频率惩罚（-2~2），默认 0。正值惩罚已多次出现的 Token，减少逐字重复。
	 * 思考模式下此参数被忽略。
	 */
	@Deprecated
	@Nullable
	private Double frequencyPenalty;

	/**
	 * 最大输出 Token 数，默认 2048。
	 */
	@Nullable
	private Integer maxTokens;

	/**
	 * 停止序列列表，最多 16 个字符串。生成到其中任意一个时停止输出。
	 */
	@Nullable
	private List<String> stop;

	// -------------------------------------------------------------------------
	// 输出格式
	// -------------------------------------------------------------------------

	/**
	 * 响应格式控制，默认 {@link ResponseFormatType#TEXT}。
	 * 使用 {@link ResponseFormatType#JSON_OBJECT} 时需在消息中明确要求模型输出 JSON。
	 */
	@Nullable
	private ResponseFormat responseFormat;

	/**
	 * 是否返回每个输出 Token 的对数概率，默认 false。
	 */
	@Nullable
	private Boolean logprobs;

	/**
	 * 每个位置返回的最高概率备选 Token 数量（0~20），需先设置 {@code logprobs=true}。
	 */
	@Nullable
	private Integer topLogprobs;

	// -------------------------------------------------------------------------
	// 流式输出
	// -------------------------------------------------------------------------

	/**
	 * 是否开启流式输出（Server-Sent Events），默认 false。
	 */
	@Nullable
	private Boolean stream;

	/**
	 * 流式输出附加选项，仅在 {@code stream=true} 时有效。
	 */
	@Nullable
	private StreamOptions streamOptions;

	// -------------------------------------------------------------------------
	// 工具调用（Function Calling）
	// -------------------------------------------------------------------------

	/**
	 * 可供模型调用的工具（函数）定义列表。
	 */
	@Nullable
	private List<Tool> tools;

	/**
	 * 工具调用策略，有两种形式：
	 * <ul>
	 *   <li>{@link ToolChoice} — 字符串预设，{@code NONE}（默认，不调用工具）、
	 *       {@code AUTO}（模型自行决定）、{@code REQUIRED}（必须调用某个工具）</li>
	 *   <li>{@link NamedToolChoice} — 强制调用指定名称的函数</li>
	 * </ul>
	 */
	@Nullable
	private IToolChoice toolChoice;

	// -------------------------------------------------------------------------
	// 思考模式（Thinking Mode）
	// -------------------------------------------------------------------------

	/**
	 * 思考模式开关。默认行为取决于模型（{@code deepseek-v4-pro} 默认开启）。
	 * 传 {@code null} 时使用模型默认行为。
	 */
	@Nullable
	private Thinking thinking;

	/**
	 * 推理强度，控制链式思考的深度。仅在思考模式开启时生效。
	 * <ul>
	 *   <li>{@link ReasoningEffort#HIGH} — 高强度（默认）</li>
	 *   <li>{@link ReasoningEffort#MAX} — 最大强度，适合复杂推理</li>
	 * </ul>
	 */
	@Nullable
	private ReasoningEffort reasoningEffort;

	// -------------------------------------------------------------------------
	// 其他
	// -------------------------------------------------------------------------

	/**
	 * 调用方用户标识符（仅含 {@code [a-zA-Z0-9\-_]}，最长 512 字符），用于安全审计，不应包含个人信息。
	 */
	@Nullable
	private String userId;

}
