package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 火山方舟 API assistant 角色消息，表示模型生成的回复。
 * <p>
 * 在多轮对话中，需将模型的历史回复以此类型放入 {@code messages} 上下文。
 * {@code content} 与 {@code toolCalls} 至少填写其一。
 * <p>
 * 思考模式（Thinking Mode）多轮对话注意事项：
 * <ul>
 *   <li>{@code encryptedContent} 优先级高于 {@code reasoningContent}，回传 {@code encryptedContent}
 *       时将忽略 {@code reasoningContent} 内容。</li>
 *   <li>回传 {@code encryptedContent} 内容需有效，篡改或无法还原时服务端返回错误。</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class AssistantMessage extends AbstractMessage {

	/**
	 * 模型生成的最终回答文本（可选）。
	 * 若本轮模型发起了工具调用，此字段可能为 null，需填写 {@code toolCalls}。
	 */
	@Nullable
	private String content;

	/**
	 * 模型消息中思维链内容（可选）。
	 * 多轮对话时，如需保持上下文一致性可回传此字段。
	 * 若同时回传 {@code encryptedContent}，则本字段被忽略。
	 */
	@Nullable
	private String reasoningContent;

	/**
	 * 经加密及压缩处理后的思考内容原文（可选）。
	 * 自 {@code doubao-seed-2-0-lite-260428} 版本起支持。
	 * 回传时优先级高于 {@code reasoningContent}。
	 */
	@Nullable
	private String encryptedContent;

	/**
	 * 模型消息中工具调用部分（可选）。
	 * 多轮对话中，若上一轮模型发起了工具调用，需将此字段原样回传。
	 */
	@Nullable
	private List<ToolCall> toolCalls;

	@Override
	public MessageRole getRole() {
		return MessageRole.ASSISTANT;
	}
}
