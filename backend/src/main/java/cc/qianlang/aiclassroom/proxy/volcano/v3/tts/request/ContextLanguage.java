package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 参考语种枚举，对应请求体 {@code req_params.additions.context_language} 字段。
 * <p>
 * 为模型提供西欧语种合成的参考。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum ContextLanguage {

	UNKNOWN("unknown"),

	/**
	 * 西欧语种采用印尼语
	 */
	ID("id"),

	/**
	 * 西欧语种采用墨西哥语
	 */
	ES("es"),

	/**
	 * 西欧语种采用巴葡语
	 */
	PT("pt"),

	;

	private final String code;

	ContextLanguage(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static ContextLanguage fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ContextLanguage.UNKNOWN;
		for (ContextLanguage value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ContextLanguage.UNKNOWN;
	}
}
