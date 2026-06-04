package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message.*;
import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * 火山方舟 Chat Completions API 请求体。
 * <p>
 * 接口地址：{@code POST https://ark.cn-beijing.volces.com/api/v3/chat/completions}。
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
	 * 模型 ID（Model ID）或推理接入点 ID（Endpoint ID）。
	 * 例如 {@code "doubao-seed-1.8"}、{@code "deepseek-v3.2"}。
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
	// 思考模式（Thinking Mode）
	// -------------------------------------------------------------------------

	/**
	 * 思考模式开关，默认 {@code {"type":"enabled"}}。
	 * 不同模型是否支持以及默认取值不同，传 {@code null} 时使用模型默认行为。
	 */
	@Nullable
	private Thinking thinking;

	/**
	 * 推理强度，限制思考的工作量，默认 {@link ReasoningEffort#MEDIUM}。
	 * 减少思考深度可提升速度，思考花费的 token 更少。
	 */
	@Nullable
	private ReasoningEffort reasoningEffort;

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
	// 生成控制
	// -------------------------------------------------------------------------

	/**
	 * 模型回答最大长度（单位 token），默认 4096。
	 * 不可与 {@code maxCompletionTokens} 同时设置。
	 */
	@Nullable
	private Integer maxTokens;

	/**
	 * 控制模型输出的最大长度（包括模型回答和思维链内容，单位 token）。
	 * 取值范围：{@code [1, 65536]}。配置后 {@code maxTokens} 默认值失效。
	 * 不可与 {@code maxTokens} 同时设置。
	 */
	@Nullable
	private Integer maxCompletionTokens;

	/**
	 * 控制使用的在线推理模式，默认 {@link ServiceTier#AUTO}。
	 * 取值范围：{@link ServiceTier#FAST}、{@link ServiceTier#AUTO}、{@link ServiceTier#DEFAULT}。
	 */
	@Nullable
	private ServiceTier serviceTier;

	/**
	 * 停止序列列表，最多 4 个字符串。生成到其中任意一个时停止输出（输出中不含该字符串）。
	 * 深度思考能力模型不支持该字段。
	 */
	@Nullable
	private List<String> stop;

	// -------------------------------------------------------------------------
	// 采样参数（思考模式下部分参数被忽略）
	// -------------------------------------------------------------------------

	/**
	 * 采样温度（0~2），默认 1。值越高输出越随机，值越低输出越确定。
	 * 不建议与 {@code topP} 同时使用。
	 */
	@Nullable
	private Double temperature;

	/**
	 * 核采样概率阈值（0~1），默认 0.7。仅考虑累计概率质量前 {@code topP} 的 Token。
	 * 不建议与 {@code temperature} 同时使用。
	 */
	@Nullable
	private Double topP;

	/**
	 * 频率惩罚（-2~2），默认 0。正值惩罚已多次出现的 Token，减少逐字重复。
	 * {@code doubao-seed-1.8}、{@code doubao-seed-2.0} 系列模型不支持该字段。
	 */
	@Nullable
	private Double frequencyPenalty;

	/**
	 * 存在惩罚（-2~2），默认 0。正值惩罚已出现的 Token，鼓励模型涉及新话题。
	 * {@code doubao-seed-1.8}、{@code doubao-seed-2.0} 系列模型不支持该字段。
	 */
	@Nullable
	private Double presencePenalty;

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
	 * 深度思考能力模型不支持该字段。
	 */
	@Nullable
	private Boolean logprobs;

	/**
	 * 每个位置返回的最高概率备选 Token 数量（0~20），需先设置 {@code logprobs=true}。
	 * 深度思考能力模型不支持该字段。
	 */
	@Nullable
	private Integer topLogprobs;

	/**
	 * 调整指定 Token 在模型输出中出现的概率。key 为 Token ID，value 为偏差值（-100~100）。
	 * 深度思考能力模型不支持该字段。
	 */
	@Nullable
	private Map<String, Integer> logitBias;

	// -------------------------------------------------------------------------
	// 工具调用（Function Calling）
	// -------------------------------------------------------------------------

	/**
	 * 可供模型调用的工具（函数）定义列表。
	 */
	@Nullable
	private List<Tool> tools;

	/**
	 * 本次请求是否允许模型返回多个待调用的工具，默认 true。
	 * {@code false} 时允许返回的待调用工具小于等于 1（{@code doubao-seed-1.6} 及之后系列生效）。
	 */
	@Nullable
	private Boolean parallelToolCalls;

	/**
	 * 工具调用策略，有两种形式：
	 * <ul>
	 *   <li>{@link ToolChoice} — 字符串预设，{@code NONE}（默认，不调用工具）、
	 *       {@code AUTO}（模型自行决定）、{@code REQUIRED}（必须调用某个工具）</li>
	 *   <li>{@link NamedToolChoice} — 强制调用指定名称的函数</li>
	 * </ul>
	 * 仅 {@code doubao-seed-1.6} 及之后系列模型支持此字段。
	 */
	@Nullable
	private IToolChoice toolChoice;
}
