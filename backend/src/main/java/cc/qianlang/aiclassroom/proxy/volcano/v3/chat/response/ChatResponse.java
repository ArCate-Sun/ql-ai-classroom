package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.response;

import cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request.ServiceTier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * 火山方舟 Chat Completions API 非流式响应体（{@code object = "chat.completion"}）。
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
	 * 本次请求的唯一标识符
	 */
	private String id;

	/**
	 * 对象类型，固定为 {@code "chat.completion"}
	 */
	private String object;

	/**
	 * 创建时间的 Unix 时间戳（秒）
	 */
	private long created;

	/**
	 * 本次请求实际使用的模型名称和版本
	 */
	private String model;

	/**
	 * 本次请求实际使用的推理模式：{@code scale}、{@code default}、{@code fast}
	 */
	private ServiceTier serviceTier;

	/**
	 * 候选回答列表。当前始终只返回一个候选。
	 */
	private List<ChatResponseChoice> choices;

	/**
	 * 本次请求的 Token 消耗统计
	 */
	private Usage usage;
}
