package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * JSON Schema 格式定义，对应请求体 {@code response_format.json_schema}。
 * 仅在 {@code response_format.type = "json_schema"} 时使用。
 */
@Getter
@Builder
@NullMarked
public class JsonSchema {

	/**
	 * 用户自定义的 JSON 结构名称，必填。
	 */
	private String name;

	/**
	 * 回复用途描述，模型将根据此描述决定如何以该格式回复（可选）。
	 */
	@Nullable
	private String description;

	/**
	 * 回复格式的 JSON Schema 定义，以 JSON Schema 对象的形式描述，必填。
	 */
	private Object schema;

	/**
	 * 是否启用严格遵循模式，默认 false。
	 * {@code true} 时模型将始终严格遵循 schema 字段中定义的格式。
	 */
	@Nullable
	private Boolean strict;
}
