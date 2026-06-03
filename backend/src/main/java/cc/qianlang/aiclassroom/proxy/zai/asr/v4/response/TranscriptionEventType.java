package cc.qianlang.aiclassroom.proxy.zai.asr.v4.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 流式转录事件类型，对应流式响应体 {@code type} 字段。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum TranscriptionEventType {

	UNKNOWN("unknown"),

	/**
	 * 正在转录，模型增量返回音频转录内容
	 */
	DELTA("transcript.text.delta"),

	/**
	 * 转录完成
	 */
	DONE("transcript.text.done"),

	;

	private final String code;

	TranscriptionEventType(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static TranscriptionEventType fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return TranscriptionEventType.UNKNOWN;
		for (TranscriptionEventType value : values()) {
			if (value.code.equals(code)) return value;
		}
		return TranscriptionEventType.UNKNOWN;
	}
}
