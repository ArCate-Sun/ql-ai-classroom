package cc.qianlang.aiclassroom.proxy.zai.chat.request.tool;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 搜索结果返回顺序。
 */
@NullMarked
public enum ResultSequence {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 在模型回复结果之前返回
	 */
	BEFORE("before"),

	/**
	 * 在模型回复结果之后返回
	 */
	AFTER("after");

	private final String code;

	ResultSequence(String code) {
		this.code = code;
	}

	/**
	 * 获取该返回顺序在 API 中的字符串表现形式。
	 *
	 * @return 返回顺序字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ResultSequence 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 result_sequence 字符串
	 * @return 对应的 ResultSequence 枚举值
	 */
	@JsonCreator
	public static ResultSequence fromCode(String code) {
		if (StringUtils.isBlank(code)) return ResultSequence.UNKNOWN;
		for (ResultSequence value : ResultSequence.values()) {
			if (value.code.equals(code)) return value;
		}
		return ResultSequence.UNKNOWN;
	}
}
