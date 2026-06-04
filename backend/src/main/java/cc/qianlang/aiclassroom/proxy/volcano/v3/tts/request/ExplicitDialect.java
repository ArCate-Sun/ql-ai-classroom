package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 明确方言枚举，对应请求体 {@code req_params.additions.explicit_dialect} 字段。
 * <p>
 * 目前仅 {@code zh_female_vv_uranus_bigtts} 音色支持。
 * <p>
 * 参数使用说明：
 * <ul>
 *   <li>speaker_id = zh_female_xiaohe_uranus_bigtts，explicit_language 不传，explicit_dialect = dongbei，则报参数错误（语种和方言不对应）</li>
 *   <li>speaker_id = zh_female_vv_uranus_bigtts，explicit_language 不传，explicit_dialect = dongbei，则正常完成东北方言的合成</li>
 *   <li>speaker_id = zh_female_vv_uranus_bigtts，explicit_language = ja，explicit_dialect = dongbei，则报参数错误（语种和方言不对应）</li>
 *   <li>speaker_id = zh_female_vv_uranus_bigtts，explicit_language = ja，explicit_dialect 不传，则按照语种正常合成</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum ExplicitDialect {

	UNKNOWN("unknown"),

	/**
	 * 东北话
	 */
	东北话("dongbei"),

	/**
	 * 陕西话
	 */
	陕西话("shaanxi"),

	/**
	 * 四川话
	 */
	四川话("sichuan"),

	;

	private final String code;

	ExplicitDialect(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static ExplicitDialect fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ExplicitDialect.UNKNOWN;
		for (ExplicitDialect value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ExplicitDialect.UNKNOWN;
	}
}
