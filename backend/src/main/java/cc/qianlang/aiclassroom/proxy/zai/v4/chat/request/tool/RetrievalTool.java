package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.tool;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 知识库检索工具定义，对应请求体 {@code tools} 中 {@code type=retrieval} 的元素。
 * <p>
 * 序列化示例：
 * <pre>{@code
 * {
 *   "type": "retrieval",
 *   "retrieval": {
 *     "knowledge_id": "your_knowledge_id"
 *   }
 * }
 * }</pre>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public final class RetrievalTool extends AbstractTool {

	/**
	 * 知识库检索配置，必填。
	 */
	private Retrieval retrieval;

	@Override
	public ToolType getType() {
		return ToolType.RETRIEVAL;
	}

	/**
	 * 知识库检索配置详情。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class Retrieval {

		/**
		 * 知识库 ID，从平台创建或获取，必填。
		 */
		private String knowledgeId;

		/**
		 * 自定义请求模板（可选）。
		 * 包含占位符 {@code {{ knowledge }}} 和 {@code {{ question }}}。
		 */
		@Nullable
		private String promptTemplate;
	}
}
