package cc.qianlang.aiclassroom.proxy.zai.chat.request.tool;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 网页摘要字数控制。
 */
@NullMarked
public enum ContentSize {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	/**
	 * 返回摘要信息，满足大模型的基础推理需求
	 */
	MEDIUM("medium"),

	/**
	 * 最大化上下文，信息量较大但内容详细
	 */
	HIGH("high");

	private final String code;

	ContentSize(String code) {
		this.code = code;
	}

	/**
	 * 获取该内容大小在 API 中的字符串表现形式。
	 *
	 * @return 内容大小字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 ContentSize 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 content_size 字符串
	 * @return 对应的 ContentSize 枚举值
	 */
	@JsonCreator
	public static ContentSize fromCode(String code) {
		if (StringUtils.isBlank(code)) return ContentSize.UNKNOWN;
		for (ContentSize value : ContentSize.values()) {
			if (value.code.equals(code)) return value;
		}
		return ContentSize.UNKNOWN;
	}
}
