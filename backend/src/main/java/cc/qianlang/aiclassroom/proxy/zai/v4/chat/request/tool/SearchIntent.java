package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.tool;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 搜索意图识别策略。
 */
@NullMarked
public enum SearchIntent {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 执行搜索意图识别，有搜索意图后执行搜索
	 */
	TRUE("true"),

	/**
	 * 跳过搜索意图识别，直接执行搜索
	 */
	FALSE("false");

	private final String code;

	SearchIntent(String code) {
		this.code = code;
	}

	/**
	 * 获取该搜索意图策略在 API 中的字符串表现形式。
	 *
	 * @return 搜索意图策略字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 SearchIntent 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #TRUE}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 search_intent 字符串
	 * @return 对应的 SearchIntent 枚举值
	 */
	@JsonCreator
	public static SearchIntent fromCode(String code) {
		if (StringUtils.isBlank(code)) return SearchIntent.TRUE;
		for (SearchIntent value : SearchIntent.values()) {
			if (value.code.equals(code)) return value;
		}
		return SearchIntent.TRUE;
	}
}
