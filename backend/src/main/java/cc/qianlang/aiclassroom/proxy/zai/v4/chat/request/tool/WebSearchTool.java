package cc.qianlang.aiclassroom.proxy.zai.v4.chat.request.tool;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 网络搜索工具定义，对应请求体 {@code tools} 中 {@code type=web_search} 的元素。
 * <p>
 * 序列化示例：
 * <pre>{@code
 * {
 *   "type": "web_search",
 *   "web_search": {
 *     "search_engine": "search_std",
 *     "enable": true
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
public final class WebSearchTool extends AbstractTool {

	/**
	 * 网络搜索配置，必填。
	 */
	private WebSearch webSearch;

	@Override
	public ToolType getType() {
		return ToolType.WEB_SEARCH;
	}

	/**
	 * 网络搜索配置详情。
	 */
	@Getter
	@Builder
	@NullMarked
	public static class WebSearch {

		/**
		 * 搜索引擎类型，必填。
		 * 支持 {@code search_std}、{@code search_pro}、
		 * {@code search_pro_sogou}、{@code search_pro_quark}。
		 */
		private SearchEngine searchEngine;

		/**
		 * 是否启用搜索功能，默认 {@code false}，启用时设置为 {@code true}（可选）。
		 */
		@Nullable
		private Boolean enable;

		/**
		 * 强制触发搜索的查询词（可选）。
		 */
		@Nullable
		private String searchQuery;

		/**
		 * 是否进行搜索意图识别（可选）。{@code true} 执行意图识别后搜索，{@code false} 直接搜索。
		 */
		@Nullable
		private SearchIntent searchIntent;

		/**
		 * 返回结果条数，范围 1~50，默认 10（可选）。
		 */
		@Nullable
		private Integer count;

		/**
		 * 限定搜索结果的白名单域名（可选）。
		 */
		@Nullable
		private String searchDomainFilter;

		/**
		 * 搜索指定时间范围内的网页（可选）。
		 * 可填值：{@code oneDay}、{@code oneWeek}、{@code oneMonth}、{@code oneYear}、{@code noLimit}。
		 */
		@Nullable
		private SearchRecencyFilter searchRecencyFilter;

		/**
		 * 控制网页摘要字数，默认 {@code medium}（可选）。可填值：{@code medium}、{@code high}。
		 */
		@Nullable
		private ContentSize contentSize;

		/**
		 * 搜索结果返回顺序，可填值：{@code before}、{@code after}，默认 {@code after}（可选）。
		 */
		@Nullable
		private ResultSequence resultSequence;

		/**
		 * 是否返回搜索来源的详细信息，默认 {@code false}（可选）。
		 */
		@Nullable
		private Boolean searchResult;

		/**
		 * 是否强制搜索结果才返回回答，默认 {@code false}（可选）。
		 */
		@Nullable
		private Boolean requireSearch;

		/**
		 * 用于定制搜索结果处理的 Prompt（可选）。
		 */
		@Nullable
		private String searchPrompt;
	}
}
