package cc.qianlang.aiclassroom.proxy.zai.image.v4.response;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 内容安全审核生效环节，对应响应体 {@code content_filter[].role}。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum ContentFilterRole {

	UNKNOWN("unknown"),

	/**
	 * 模型推理阶段
	 */
	ASSISTANT("assistant"),

	/**
	 * 用户输入阶段
	 */
	USER("user"),

	/**
	 * 历史上下文阶段
	 */
	HISTORY("history"),

	;

	private final String code;

	ContentFilterRole(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static ContentFilterRole fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return ContentFilterRole.UNKNOWN;
		for (ContentFilterRole value : values()) {
			if (value.code.equals(code)) return value;
		}
		return ContentFilterRole.UNKNOWN;
	}
}
