package cc.qianlang.aiclassroom.proxy.volcano.v3.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 响应格式控制。
 */
@Getter
@Builder
@NullMarked
public class ResponseFormat {

	/**
	 * 格式类型，{@link ResponseFormatType#TEXT}（默认）、{@link ResponseFormatType#JSON_OBJECT}
	 * 或 {@link ResponseFormatType#JSON_SCHEMA}。
	 */
	private ResponseFormatType type;

	/**
	 * JSON Schema 格式定义，仅在 {@code type = JSON_SCHEMA} 时填写。
	 */
	@Nullable
	private JsonSchema jsonSchema;

	/**
	 * 使用纯文本格式（默认）
	 */
	public static ResponseFormat text() {
		return ResponseFormat.builder().type(ResponseFormatType.TEXT).build();
	}

	/**
	 * 使用 JSON 对象格式，模型输出将是合法的 JSON 字符串
	 */
	public static ResponseFormat jsonObject() {
		return ResponseFormat.builder().type(ResponseFormatType.JSON_OBJECT).build();
	}

	/**
	 * 使用 JSON Schema 格式，模型输出遵循给定的结构定义
	 *
	 * @param jsonSchema JSON Schema 格式定义
	 */
	public static ResponseFormat jsonSchema(JsonSchema jsonSchema) {
		return ResponseFormat.builder()
				.type(ResponseFormatType.JSON_SCHEMA)
				.jsonSchema(jsonSchema)
				.build();
	}
}
