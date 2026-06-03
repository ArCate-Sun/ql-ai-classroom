package cc.qianlang.aiclassroom.proxy.deepseek.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 思考模式开关配置。
 */
@Getter
@Builder
@NullMarked
public class Thinking {

	/**
	 * 思考模式类型：{@link ThinkingType#ENABLED} 开启，{@link ThinkingType#DISABLED} 关闭。
	 */
	private ThinkingType type;

	/**
	 * 开启思考模式
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