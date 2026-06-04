package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 用户自定义扩展参数，对应请求体 {@code req_params.additions} 字段。
 * <p>
 * 注意：火山引擎接口要求此字段以 JSON 字符串形式传递（jsonstring 类型），
 * 序列化时需将本对象先序列化为 JSON 字符串，再嵌入请求体。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class TtsAdditions {

	/**
	 * 句尾静音时长（ms），范围 [0, 30000]，默认 0。
	 */
	@Nullable
	private Integer silenceDuration;

	/**
	 * 是否自动识别语种，默认 {@code false}。
	 */
	@Nullable
	private Boolean enableLanguageDetector;

	/**
	 * 是否解析并过滤 Markdown 语法，默认 {@code false}。
	 * <p>
	 * 为 {@code true} 时，如 {@code **你好**} 会读为"你好"；
	 * 为 {@code false} 时会读为"星星'你好'星星"。
	 */
	@Nullable
	private Boolean disableMarkdownFilter;

	/**
	 * 是否在文本中不过滤 emoji 表情，默认 {@code false}。
	 */
	@Nullable
	private Boolean disableEmojiFilter;

	/**
	 * 是否播报 LaTeX 公式，需同时将 {@code disableMarkdownFilter} 设为 {@code true}，默认 {@code false}。
	 */
	@Nullable
	private Boolean enableLatexTn;

	/**
	 * 是否使用 LID 能力播报 LaTeX 公式，效果优于 {@code enableLatexTn}。
	 * 值为 {@code "v2"} 时启用，为空时不支持。需同时将 {@code disableMarkdownFilter} 设为 {@code true}。
	 */
	@Nullable
	private String latexParser;

	/**
	 * 明确语种，仅读指定语种文本。
	 */
	@Nullable
	private ExplicitLanguage explicitLanguage;

	/**
	 * 参考语种，为模型提供西欧语种合成的参考。
	 */
	@Nullable
	private ContextLanguage contextLanguage;

	/**
	 * 明确方言，目前仅 {@code zh_female_vv_uranus_bigtts} 音色支持。
	 * <p>
	 * 参数使用说明：
	 * <ul>
	 *   <li>speaker_id = zh_female_xiaohe_uranus_bigtts，explicit_language 不传，explicit_dialect = dongbei，则报参数错误（语种和方言不对应）</li>
	 *   <li>speaker_id = zh_female_vv_uranus_bigtts，explicit_language 不传，explicit_dialect = dongbei，则正常完成东北方言的合成</li>
	 *   <li>speaker_id = zh_female_vv_uranus_bigtts，explicit_language = ja，explicit_dialect = dongbei，则报参数错误（语种和方言不对应）</li>
	 *   <li>speaker_id = zh_female_vv_uranus_bigtts，explicit_language = ja，explicit_dialect 不传，则按照语种正常合成</li>
	 * </ul>
	 */
	@Nullable
	private ExplicitDialect explicitDialect;

	/**
	 * 不支持字符比例阈值，默认 {@code 0.3}，最大值 {@code 1.0}。
	 * 检测出不支持合成的文本超过设置的比例，则会返回错误。
	 */
	@Nullable
	private Double unsupportedCharRatioThresh;

	/**
	 * 是否在合成结尾增加音频节奏标识（AIGC 水印），默认 {@code false}。
	 */
	@Nullable
	private Boolean aigcWatermark;

	/**
	 * AIGC 元数据水印配置，在合成音频 header 加入元数据隐式表示。
	 * 支持 mp3/wav/ogg_opus 格式。
	 */
	@Nullable
	private AigcMetadata aigcMetadata;

	/**
	 * 缓存配置，开启后合成相同文本时会直接读取缓存，缓存数据保留 1 小时。
	 */
	@Nullable
	private CacheConfig cacheConfig;

	/**
	 * 后处理配置。
	 */
	@Nullable
	private PostProcess postProcess;

	/**
	 * 语音指令列表，用于辅助模型调整语速、情感等，仅第一个元素有效。
	 * 仅适用于 TTS 2.0 及"豆包声音复刻大模型 2.0"表现力增强版本。
	 * <p>
	 * 常见示例：
	 * <ul>
	 *   <li>语速调整：context_texts: ["你可以说慢一点吗？"]</li>
	 *   <li>情绪/语气调整：context_texts=["你可以用特别特别痛心的语气说话吗?"]、context_texts=["嗯，你的语气再欢乐一点"]</li>
	 *   <li>音量调整：context_texts=["你嗓门再小点。"]</li>
	 *   <li>音感调整：context_texts=["你能用骄傲的语气来说话吗？"]</li>
	 * </ul>
	 * <p>
	 * 注意：
	 * <ul>
	 *   <li>该字段仅适用于"豆包语音合成模型 2.0"的音色，"豆包声音复刻大模型 2.0"的表现力增强版本</li>
	 *   <li>当前字符串列表只第一个值有效</li>
	 *   <li>该字段文本不参与计费</li>
	 * </ul>
	 */
	@Nullable
	private List<String> contextTexts;

	/**
	 * 多轮会话 ID，用于关联同一上下文中的多次串行语音合成请求。
	 * 仅适用于 TTS 2.0 及 ICL 2.0 音色。
	 * <p>
	 * 服务端通过该 ID 在一次语音合成结束后保存对话历史，并在后续语音合成请求中，
	 * 使用相同的 ID 读取对应的历史记录。
	 * <p>
	 * 取值示例：如在一通电话中的多次 TTS 请求，建议为该通电话使用 UUID 生成一个唯一的 section_id，
	 * 并在所有 TTS 请求中传递相同的 section_id。
	 * <p>
	 * 示例：section_id="bf5b5771-31cd-4f7a-b30c-f4ddcbf2f9da"
	 * <p>
	 * 注意：
	 * <ul>
	 *   <li>该字段仅适用于"豆包语音合成模型 2.0"的音色，"豆包声音复刻大模型 2.0"的音色</li>
	 *   <li>服务端对历史上下文有相应的轮数限制和超时时间</li>
	 * </ul>
	 */
	@Nullable
	private String sectionId;


	/**
	 * 是否开启语音标签 CoT 解析能力，默认 {@code false}。
	 * CoT 能力可以辅助当前语音合成，对语速、情感等进行调整。
	 * 仅适用于"豆包声音复刻大模型 2.0"表现力增强版本。
	 * <p>
	 * 注意：
	 * <ul>
	 *   <li>该字段仅适用于"豆包声音复刻大模型 2.0"的表现力增强版本</li>
	 *   <li>文本长度：单句的 text 字符长度最好小于 64（cot 标签也计算在内）</li>
	 *   <li>cot 能力生效的范围是单句</li>
	 * </ul>
	 * <p>
	 * 示例：
	 * 支持单组和多组 cot 标签：
	 * {@code <cot text=急促难耐>工作占据了生活的绝大部分</cot>，只有去做自己认为伟大的工作，才能获得满足感。<cot text=语速缓慢>不管生活再苦再累，都绝不放弃寻找</cot>。}
	 */
	@Nullable
	private Boolean useTagParser;

}
