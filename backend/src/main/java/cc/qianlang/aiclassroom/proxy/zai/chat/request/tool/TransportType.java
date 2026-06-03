package cc.qianlang.aiclassroom.proxy.zai.chat.request.tool;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * MCP 传输类型。
 */
@NullMarked
public enum TransportType {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * Server-Sent Events
	 */
	SSE("sse"),

	/**
	 * 可流式 HTTP
	 */
	STREAMABLE_HTTP("streamable-http");

	private final String code;

	TransportType(String code) {
		this.code = code;
	}

	/**
	 * 获取该传输类型在 API 中的字符串表现形式。
	 *
	 * @return 传输类型字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 TransportType 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #STREAMABLE_HTTP}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 transport_type 字符串
	 * @return 对应的 TransportType 枚举值
	 */
	@JsonCreator
	public static TransportType fromCode(String code) {
		if (StringUtils.isBlank(code)) return TransportType.STREAMABLE_HTTP;
		for (TransportType value : TransportType.values()) {
			if (value.code.equals(code)) return value;
		}
		return TransportType.STREAMABLE_HTTP;
	}
}
