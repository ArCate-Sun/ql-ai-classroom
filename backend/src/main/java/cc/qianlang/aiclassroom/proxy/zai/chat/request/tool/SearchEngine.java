package cc.qianlang.aiclassroom.proxy.zai.chat.request.tool;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 搜索引擎类型。
 */
@NullMarked
public enum SearchEngine {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 标准搜索
	 */
	SEARCH_STD("search_std"),

	/**
	 * 专业搜索
	 */
	SEARCH_PRO("search_pro"),

	/**
	 * 搜狗专业搜索
	 */
	SEARCH_PRO_SOGOU("search_pro_sogou"),

	/**
	 * 夸克专业搜索
	 */
	SEARCH_PRO_QUARK("search_pro_quark");

	private final String code;

	SearchEngine(String code) {
		this.code = code;
	}

	/**
	 * 获取该搜索引擎类型在 API 中的字符串表现形式。
	 *
	 * @return 搜索引擎类型字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 SearchEngine 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 search_engine 字符串
	 * @return 对应的 SearchEngine 枚举值
	 */
	@JsonCreator
	public static SearchEngine fromCode(String code) {
		if (StringUtils.isBlank(code)) return SearchEngine.UNKNOWN;
		for (SearchEngine value : SearchEngine.values()) {
			if (value.code.equals(code)) return value;
		}
		return SearchEngine.UNKNOWN;
	}
}
