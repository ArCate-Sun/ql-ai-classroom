package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 思考模式配置，对应请求体 {@code thinking} 字段。
 * <p>
 * 仅 {@code GLM-4.5} 及以上模型支持此参数。
 */
@Getter
@Builder
@NullMarked
public class Thinking {

	/**
	 * 思考模式开关：{@link ThinkingType#ENABLED} 开启，{@link ThinkingType#DISABLED} 关闭。
	 * 默认 {@link ThinkingType#ENABLED}。
	 */
	private ThinkingType type;

	/**
	 * 是否清除历史对话轮次中的 {@code reasoning_content}，默认 {@code true}。
	 * <ul>
	 *   <li>{@code true}（默认）：忽略历史 turns 的思考内容，降低上下文长度与成本。</li>
	 *   <li>{@code false}：保留历史 turns 的思考内容（Preserved Thinking），
	 *       需在 {@code messages} 中完整、按序透传历史 {@code reasoningContent}。</li>
	 * </ul>
	 */
	@Nullable
	private Boolean clearThinking;

	/**
	 * 开启思考模式（使用模型默认的 clearThinking=true）
	 */
	public static Thinking enabled() {
		return Thinking.builder().type(ThinkingType.ENABLED).build();
	}

	/**
	 * 关闭思考模式
	 */
	public static Thinking disabled() {
		return Thinking.builder().type(ThinkingType.DISABLED).build();
	}
}
