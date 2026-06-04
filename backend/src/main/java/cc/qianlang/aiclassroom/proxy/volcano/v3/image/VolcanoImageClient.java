package cc.qianlang.aiclassroom.proxy.volcano.v3.image;

import cc.qianlang.aiclassroom.proxy.volcano.v3.image.request.ImageInput;
import cc.qianlang.aiclassroom.proxy.volcano.v3.image.request.ImageRequest;
import cc.qianlang.aiclassroom.proxy.volcano.v3.image.response.ImageResponse;
import cc.qianlang.web.common.core.util.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 基于火山方舟 API 的图像生成客户端实现。
 * <p>
 * 使用 Spring WebFlux {@link WebClient} 直接调用火山方舟图像生成接口，
 * 接口地址：{@code POST https://ark.cn-beijing.volces.com/api/v3/images/generations}。
 * <p>
 * 所需配置项：
 * <ul>
 *   <li>{@code app.volcano.api-key} — 火山方舟 API 密钥（必填）</li>
 *   <li>{@code app.volcano.base-url} — API 基础地址，默认 {@code https://ark.cn-beijing.volces.com/api/v3}</li>
 *   <li>{@code app.volcano.image.model} — 模型 ID 或 Endpoint ID，例如 {@code "seedream-3.0"}</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
public class VolcanoImageClient {

	private final WebClient webClient;

	/**
	 * 构造火山方舟图像生成客户端。
	 *
	 * @param baseUrl API 基础地址，如 {@code https://ark.cn-beijing.volces.com/api/v3}
	 * @param apiKey  火山方舟 API 密钥
	 */
	public VolcanoImageClient(String baseUrl, String apiKey) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.defaultHeader("Authorization", "Bearer " + apiKey)
				.build();
	}


	/**
	 * 发起同步图像生成请求。
	 *
	 * @param request 图像生成请求参数
	 * @return 火山方舟 API 返回的完整响应
	 * @throws IllegalArgumentException 请求参数序列化失败
	 * @throws RuntimeException         API 请求失败或响应为空
	 */
	public ImageResponse generate(ImageRequest request) {

		// 将请求对象序列化为 JSON 字符串
		String payload = ObjectMapperFactory.jsonify(request);
		if (payload == null) {
			throw new IllegalArgumentException("序列化请求参数失败: 结果为 null");
		}

		// 发起 POST 请求并阻塞等待响应
		ImageResponse response = this.webClient.post()
				.uri("/images/generations")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("火山方舟 API 请求失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToMono(String.class)
				.map(body -> {
					try {
						return ObjectMapperFactory.objectifyOrThrow(body, ImageResponse.class);
					} catch (JsonProcessingException e) {
						throw new RuntimeException("解析火山方舟图像响应失败: " + body, e);
					}
				})
				.block();

		// 校验响应有效性
		if (response == null || response.getData().isEmpty()) {
			throw new RuntimeException("火山方舟 API 返回的响应为空或缺少 data");
		}

		return response;
	}

	public static void main(String[] args) {

		VolcanoImageClient client = new VolcanoImageClient(
				"https://ark.cn-beijing.volces.com/api/v3",
				"..."
		);

		ImageRequest request = ImageRequest.builder()
				.model("doubao-seedream-5-0-260128")
				.image(ImageInput.of("https://ark-project.tos-cn-beijing.volces.com/doc_image/seedream4_5_imageToimage.png"))
				.prompt("保持模特姿势和液态服装的流动形状不变。将服装材质从银色金属改为完全透明的清水（或玻璃）。透过液态水流，可以看到模特的皮肤细节。光影从反射变为折射。")
				.size("2K")
				.watermark(false)
				.build();

		ImageResponse response = client.generate(request);

		// 打印每张生成图像的 URL
		response.getData().forEach(imageData -> {
			if (imageData.getUrl() != null) {
				System.out.println("图像 URL: " + imageData.getUrl());
			}
			if (imageData.getError() != null) {
				System.err.println("图像生成失败: [" + imageData.getError().getCode() + "] " + imageData.getError().getMessage());
			}
		});

		System.out.println("生成图像数: " + response.getUsage().getGeneratedImages());
		System.out.println("输出 Token 数: " + response.getUsage().getOutputTokens());
	}
}
