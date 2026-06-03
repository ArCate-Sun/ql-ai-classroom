package cc.qianlang.aiclassroom.proxy.zai.chat.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * 响应格式控制，对应请求体 {@code response_format} 字段。
 * 仅文本模型支持此字段。
 */
@Getter
@Builder
@NullMarked
public class ResponseFormat {

	/**
	 * 格式类型，{@link ResponseFormatType#TEXT}（默认）或 {@link ResponseFormatType#JSON_OBJECT}。
	 */
	private ResponseFormatType type;

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
}
