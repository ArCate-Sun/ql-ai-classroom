package cc.qianlang.aiclassroom.proxy.deepseek.chat.request.message;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * DeepSeek API tool 角色消息，用于向模型返回工具（函数）的执行结果。
 * <p>
 * 工具调用流程：
 * <ol>
 *   <li>发送包含 {@code tools} 定义的请求；</li>
 *   <li>模型回复 {@link AssistantMessage}，其 {@code toolCalls} 字段包含调用信息；</li>
 *   <li>本地执行工具，将结果以 {@link ToolMessage} 附加到 {@code messages} 中；</li>
 *   <li>再次请求模型，获取综合工具结果后的最终回答。</li>
 * </ol>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class ToolMessage extends AbstractMessage {

	/**
	 * 工具执行结果的文本内容，必填。
	 */
	private String content;

	/**
	 * 对应的工具调用 ID，必填。需与 {@link ToolMessage#getToolCallId()} 中的 {@code id} 对应。
	 */
	private String toolCallId;

	@Override
	public MessageRole getRole() {
		return MessageRole.TOOL;
	}
}
