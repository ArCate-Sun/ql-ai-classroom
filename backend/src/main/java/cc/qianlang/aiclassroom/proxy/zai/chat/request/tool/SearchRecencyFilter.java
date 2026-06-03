package cc.qianlang.aiclassroom.proxy.zai.chat.request.tool;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 搜索时间范围过滤器。
 */
@NullMarked
public enum SearchRecencyFilter {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 一天内
	 */
	ONE_DAY("oneDay"),

	/**
	 * 一周内
	 */
	ONE_WEEK("oneWeek"),

	/**
	 * 一个月内
	 */
	ONE_MONTH("oneMonth"),

	/**
	 * 一年内
	 */
	ONE_YEAR("oneYear"),

	/**
	 * 不限
	 */
	NO_LIMIT("noLimit");

	private final String code;

	SearchRecencyFilter(String code) {
		this.code = code;
	}

	/**
	 * 获取该时间范围过滤器在 API 中的字符串表现形式。
	 *
	 * @return 时间范围过滤器字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 SearchRecencyFilter 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 search_recency_filter 字符串
	 * @return 对应的 SearchRecencyFilter 枚举值
	 */
	@JsonCreator
	public static SearchRecencyFilter fromCode(String code) {
		if (StringUtils.isBlank(code)) return SearchRecencyFilter.UNKNOWN;
		for (SearchRecencyFilter value : SearchRecencyFilter.values()) {
			if (value.code.equals(code)) return value;
		}
		return SearchRecencyFilter.UNKNOWN;
	}
}
