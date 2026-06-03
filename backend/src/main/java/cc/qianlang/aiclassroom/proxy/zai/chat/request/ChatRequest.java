package cc.qianlang.aiclassroom.proxy.zai.chat.request;

import cc.qianlang.aiclassroom.proxy.zai.chat.request.message.*;
import cc.qianlang.aiclassroom.proxy.zai.chat.request.tool.*;
import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Z.AI 对话补全 API 请求体。
 * <p>
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/chat/completions}。
 * <p>
 * 注意事项：
 * <ul>
 *   <li>{@code messages} 不能只包含 system 或 assistant 消息，至少需要一条 user 消息。</li>
 *   <li>建议根据应用场景调整 {@code temperature} 或 {@code topP}，但不建议同时调整两个参数。</li>
 *   <li>工具调用仅在 {@code toolChoice=auto} 时生效（ZAI 目前仅支持 {@code auto}）。</li>
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
	 * 模型 ID，例如 {@code "glm-5.1"}、{@code "glm-4.7-flash"}。
	 */
	private String model;

	/**
	 * 对话消息列表，至少包含一条消息（且不能只含 system/assistant 消息）。
	 * 元素类型为 {@link AbstractMessage} 的子类：
	 * {@link SystemMessage}、{@link UserMessage}、
	 * {@link AssistantMessage}、{@link ToolMessage}。
	 */
	private List<AbstractMessage> messages;

	// -------------------------------------------------------------------------
	// 流式输出
	// -------------------------------------------------------------------------

	/**
	 * 是否开启流式输出（Server-Sent Events），默认 false。
	 * 流式输出结束时会返回 {@code data: [DONE]} 消息。
	 */
	@Nullable
	private Boolean stream;

	// -------------------------------------------------------------------------
	// 思考模式
	// -------------------------------------------------------------------------

	/**
	 * 思考模式开关配置，仅 GLM-4.5 及以上模型支持。
	 * 传 {@code null} 时使用模型默认行为。
	 */
	@Nullable
	private Thinking thinking;

	// -------------------------------------------------------------------------
	// 生成控制
	// -------------------------------------------------------------------------

	/**
	 * 是否启用采样策略，默认 true。
	 * 设为 {@code false} 时模型总是选择概率最高的词汇，{@code temperature} 和 {@code topP} 参数将被忽略。
	 */
	@Nullable
	private Boolean doSample;

	/**
	 * 采样温度（0~1，限两位小数），值越高输出越随机。
	 * 不建议与 {@code topP} 同时使用。
	 */
	@Nullable
	private Double temperature;

	/**
	 * 核采样概率阈值（0.01~1，限两位小数）。
	 * 不建议与 {@code temperature} 同时使用。
	 */
	@Nullable
	private Double topP;

	/**
	 * 最大输出 Token 数，建议不小于 1024。
	 */
	@Nullable
	private Integer maxTokens;

	/**
	 * 停止词列表，目前仅支持单个停止词，格式如 {@code ["stop_word1"]}，最多 4 个。
	 */
	@Nullable
	private List<String> stop;

	// -------------------------------------------------------------------------
	// 输出格式
	// -------------------------------------------------------------------------

	/**
	 * 响应格式控制，默认文本格式。仅文本模型支持此字段。
	 */
	@Nullable
	private ResponseFormat responseFormat;

	// -------------------------------------------------------------------------
	// 工具调用（Function Calling）
	// -------------------------------------------------------------------------

	/**
	 * 可供模型调用的工具列表。支持函数调用（{@link FunctionTool}）、
	 * 知识库检索（{@link RetrievalTool}）、网络搜索（{@link WebSearchTool}）、
	 * MCP（{@link McpTool}）。
	 */
	@Nullable
	private List<AbstractTool> tools;

	/**
	 * 工具调用策略，目前仅支持 {@code "auto"}（模型自行决定是否调用工具）。
	 * 仅在工具类型为 {@code function} 时补充。
	 */
	@Nullable
	private ToolChoice toolChoice;

	/**
	 * 是否开启流式响应 Function Calls，默认 false。
	 * 仅限 GLM-5.1、GLM-5、GLM-5-Turbo、GLM-4.7、GLM-4.6 系列支持。
	 */
	@Nullable
	private Boolean toolStream;

	// -------------------------------------------------------------------------
	// 其他
	// -------------------------------------------------------------------------

	/**
	 * 请求唯一标识符（可选）。长度 6~64 字符，建议使用 UUID 格式。
	 * 若未提供，平台将自动生成。
	 */
	@Nullable
	private String requestId;

	/**
	 * 终端用户唯一标识符（可选）。长度 6~128 字符，不应包含敏感信息。
	 */
	@Nullable
	private String userId;

}
