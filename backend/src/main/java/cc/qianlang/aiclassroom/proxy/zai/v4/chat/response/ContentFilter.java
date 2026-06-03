package cc.qianlang.aiclassroom.proxy.zai.v4.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 内容安全审核结果条目。
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ContentFilter {

	/**
	 * 安全生效环节：{@code assistant}（模型推理）、{@code user}（用户输入）、{@code history}（历史上下文）
	 */
	@Nullable
	private String role;

	/**
	 * 严重程度 0~3，{@code 0} 表示最严重，{@code 3} 表示轻微
	 */
	@Nullable
	private Integer level;
}
