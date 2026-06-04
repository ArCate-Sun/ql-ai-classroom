package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 明确语种枚举，对应请求体 {@code req_params.additions.explicit_language} 字段。
 * <p>
 * 用于指定仅读指定语种文本，根据音色类型和训练模型选择对应语种。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum ExplicitLanguage {

	UNKNOWN("unknown"),

	/**
	 * 启用多语种前端（包含 zh/en/ja/es-mx/id/pt-br）
	 */
	CROSSLINGUAL("crosslingual"),

	/**
	 * 中文为主，支持中英混
	 */
	ZH_CN("zh-cn"),

	/**
	 * 仅英文
	 */
	EN("en"),

	/**
	 * 仅日文
	 */
	JA("ja"),

	/**
	 * 仅墨西哥语
	 */
	ES_MX("es-mx"),

	/**
	 * 仅印尼语
	 */
	ID("id"),

	/**
	 * 仅巴西葡萄牙语
	 */
	PT_BR("pt-br"),

	/**
	 * 仅德语
	 */
	DE("de"),

	/**
	 * 仅法语
	 */
	FR("fr"),

	/**
	 * 仅韩语
	 */
	KO("ko"),

	;

	private final String code;

	ExplicitLanguage(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static ExplicitLanguage fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ExplicitLanguage.UNKNOWN;
		for (ExplicitLanguage value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ExplicitLanguage.UNKNOWN;
	}
}
