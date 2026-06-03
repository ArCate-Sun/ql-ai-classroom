package cc.qianlang.aiclassroom.proxy.deepseek.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * DeepSeek API assistant 角色消息，表示模型生成的回复。
 * <p>
 * 在多轮对话中，需将模型的历史回复以此类型放入 {@code messages} 上下文。
 * <p>
 * 思考模式（Thinking Mode）多轮对话注意事项：
 * <ul>
 *   <li>若两轮之间 <strong>没有</strong>工具调用，{@code reasoningContent} 无需回传，API 会忽略它。</li>
 *   <li>若两轮之间 <strong>存在</strong>工具调用，{@code reasoningContent} <strong>必须</strong>回传，
 *       否则 API 返回 400 错误。</li>
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
	 * 模型生成的最终回答文本。
	 * 若本轮模型发起了工具调用，此字段可能为 null。
	 */
	private String content;

	/**
	 * 参与者名称（可选）。
	 */
	@Nullable
	private String name;

	/**
	 * 是否强制模型以此消息的 {@code content} 作为下一次回复的前缀（Beta，可选）。
	 * 仅在 Prefix Completion 场景下使用。
	 */
	@Nullable
	private Boolean prefix;

	/**
	 * 思考模式下模型的链式思考内容（可选）。
	 * <ul>
	 *   <li>多轮对话中存在工具调用时，必须将此字段原样回传。</li>
	 *   <li>其他情况下可省略（传 null）。</li>
	 * </ul>
	 */
	@Nullable
	private String reasoningContent;

	@Override
	public MessageRole getRole() {
		return MessageRole.ASSISTANT;
	}
}
