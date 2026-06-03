package cc.qianlang.aiclassroom.proxy.deepseek.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * DeepSeek Chat Completions API 非流式响应体（{@code object = "chat.completion"}）。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ChatResponse {

	/**
	 * 本次请求的唯一标识符，格式如 {@code "chatcmpl-abc123"}。
	 */
	private String id;

	/**
	 * 对象类型，固定为 {@code "chat.completion"}。
	 */
	private String object;

	/**
	 * 创建时间的 Unix 时间戳（秒）。
	 */
	private long created;

	/**
	 * 实际处理本次请求的模型 ID。
	 */
	private String model;

	/**
	 * 后端配置指纹，可用于排查不同后端配置导致的输出差异。
	 */
	private String systemFingerprint;

	/**
	 * 候选回答列表。当前 DeepSeek API 始终只返回一个候选（{@code n=1}）。
	 */
	private List<ChatResponseChoice> choices;

	/**
	 * 本次请求的 Token 消耗统计。
	 */
	private Usage usage;

}
