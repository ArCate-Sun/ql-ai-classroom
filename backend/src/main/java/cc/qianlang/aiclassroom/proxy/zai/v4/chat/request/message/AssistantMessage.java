package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Z.AI 对话补全接口 assistant 角色消息，表示模型生成的回复。
 * <p>
 * 在多轮对话中，需将模型的历史回复以此类型放入 {@code messages} 上下文。
 * <p>
 * 思考模式多轮对话注意事项：
 * <ul>
 *   <li>若开启 {@code thinking.clearThinking=false}，需将历史回复的 {@code reasoningContent}
 *       原样回传；缺失、裁剪或改写会导致效果下降。</li>
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
	@Nullable
	private String content;

	/**
	 * 思考模式下模型的链式思考内容（可选）。
	 * 开启 {@code Preserved Thinking} 时需原样回传此字段。
	 */
	@Nullable
	private String reasoningContent;

	/**
	 * 模型发起的工具调用列表（可选）。
	 * 存在工具调用时，{@code content} 通常为 null。
	 */
	@Nullable
	private List<ToolCall> toolCalls;

	@Override
	public MessageRole getRole() {
		return MessageRole.ASSISTANT;
	}

}
