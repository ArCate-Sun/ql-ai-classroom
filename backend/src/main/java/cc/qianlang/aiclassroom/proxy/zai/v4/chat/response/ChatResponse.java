package cc.qianlang.aiclassroom.proxy.zai.v4.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Z.AI 对话补全 API 非流式响应体。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class ChatResponse {

	/**
	 * 任务 ID
	 */
	private String id;

	/**
	 * 请求 ID
	 */
	private String requestId;

	/**
	 * 请求创建时间，Unix 时间戳（秒）
	 */
	private long created;

	/**
	 * 实际处理本次请求的模型名称
	 */
	private String model;

	/**
	 * 模型响应列表
	 */
	private List<ChatResponseChoice> choices;

	/**
	 * 本次请求的 Token 消耗统计
	 */
	@Nullable
	private Usage usage;

	/**
	 * 视频生成结果（当模型生成视频时返回）
	 */
	@Nullable
	private List<VideoResult> videoResult;

	/**
	 * 网络搜索相关信息（使用 WebSearchTool 时返回）
	 */
	@Nullable
	private List<WebSearchResult> webSearch;

	/**
	 * 内容安全相关信息
	 */
	@Nullable
	private List<ContentFilter> contentFilter;

	/**
	 * 视频生成结果条目。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class VideoResult {

		/**
		 * 视频链接
		 */
		@Nullable
		private String url;

		/**
		 * 视频封面链接
		 */
		@Nullable
		private String coverImageUrl;
	}

	/**
	 * 网络搜索结果条目。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class WebSearchResult {

		/**
		 * 来源网站的图标
		 */
		@Nullable
		private String icon;

		/**
		 * 搜索结果的标题
		 */
		@Nullable
		private String title;

		/**
		 * 搜索结果的网页链接
		 */
		@Nullable
		private String link;

		/**
		 * 搜索结果网页的媒体来源名称
		 */
		@Nullable
		private String media;

		/**
		 * 网站发布时间
		 */
		@Nullable
		private String publishDate;

		/**
		 * 搜索结果网页引用的文本内容
		 */
		@Nullable
		private String content;

		/**
		 * 角标序号
		 */
		@Nullable
		private String refer;
	}

}
