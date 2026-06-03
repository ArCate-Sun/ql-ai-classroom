package cc.qianlang.aiclassroom.proxy.deepseek.chat.request.message;

import cc.qianlang.aiclassroom.proxy.deepseek.chat.MessageRole;
import org.jspecify.annotations.NullMarked;

/**
 * DeepSeek API 请求消息的抽象基类。
 * <p>
 * 每种角色的消息字段集合不同，通过子类分别建模：
 * <ul>
 *   <li>{@link SystemMessage} — {@code system}，系统提示词</li>
 *   <li>{@link UserMessage}   — {@code user}，用户输入</li>
 *   <li>{@link AssistantMessage} — {@code assistant}，模型回复（含工具调用、思考内容）</li>
 *   <li>{@link ToolMessage}   — {@code tool}，工具执行结果</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public sealed abstract class AbstractMessage
		permits SystemMessage, UserMessage, AssistantMessage, ToolMessage {

	/**
	 * 消息角色，序列化为 JSON 的 {@code "role"} 字段。
	 * 由各子类固定返回对应角色字符串。
	 */
	public abstract MessageRole getRole();
}
