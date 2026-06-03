package cc.qianlang.aiclassroom.proxy.zai.v4.image.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Z.AI 图像生成 API 请求体。
 * <p>
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/images/generations}。
 * <p>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class ImageRequest {

	// -------------------------------------------------------------------------
	// 必填字段
	// -------------------------------------------------------------------------

	/**
	 * 模型 ID，例如 {@code "glm-image"}、{@code "cogview-4-250304"}、{@code "cogview-4"}、{@code "cogview-3-flash"}。
	 */
	private String model;

	/**
	 * 所需图像的文本描述，必填。
	 */
	private String prompt;

	// -------------------------------------------------------------------------
	// 可选字段
	// -------------------------------------------------------------------------

	/**
	 * 生成图像的质量，默认值取决于模型：
	 * {@code glm-image} 默认 {@link ImageQuality#HD}，其它模型默认 {@link ImageQuality#STANDARD}。
	 */
	@Nullable
	private ImageQuality quality;

	/**
	 * 图片尺寸，格式为 {@code "宽x高"}，例如 {@code "1280x1280"}。
	 * <p>
	 * {@code glm-image} 推荐枚举值：{@code 1280x1280}（默认）、{@code 1568x1056}、
	 * {@code 1056x1568}、{@code 1472x1088}、{@code 1088x1472}、{@code 1728x960}、{@code 960x1728}。
	 * 自定义参数：长宽推荐 1024px~2048px，最大像素数不超过 2^22px，长宽均需为 32 的整数倍。
	 * <p>
	 * 其它模型推荐枚举值：{@code 1024x1024}（默认）、{@code 768x1344}、{@code 864x1152}、
	 * {@code 1344x768}、{@code 1152x864}、{@code 1440x720}、{@code 720x1440}。
	 * 自定义参数：长宽均需 512px~2048px，被 16 整除，最大像素数不超过 2^21px。
	 */
	@Nullable
	private String size;

	/**
	 * 是否为 AI 生成图片添加水印。
	 * <ul>
	 *   <li>{@code true}（默认）— 启用显式水印及隐式数字水印，符合政策要求</li>
	 *   <li>{@code false} — 关闭所有水印，仅允许已签署免责声明的客户使用</li>
	 * </ul>
	 */
	@Nullable
	private Boolean watermarkEnabled;

	/**
	 * 终端用户的唯一 ID，用于协助平台对违规行为进行干预。
	 * 长度要求：6~128 个字符。
	 */
	@Nullable
	private String userId;

}
