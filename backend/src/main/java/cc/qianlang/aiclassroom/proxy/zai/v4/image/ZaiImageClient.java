package cc.qianlang.aiclassroom.proxy.zai.v4.image;

import cc.qianlang.aiclassroom.proxy.zai.v4.image.request.ImageRequest;
import cc.qianlang.aiclassroom.proxy.zai.v4.image.response.ImageGenerationResponse;
import cc.qianlang.web.common.core.util.ObjectMapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 基于 Z.AI 图像生成 API 的客户端实现。
 * <p>
 * 使用 Spring WebFlux {@link WebClient} 调用 Z.AI 图像生成接口，
 * 接口地址：{@code POST https://open.bigmodel.cn/api/paas/v4/images/generations}。
 * <p>
 * 所需配置项：
 * <ul>
 *   <li>{@code app.zai.api-key} — Z.AI API 密钥（必填）</li>
 *   <li>{@code app.zai.base-url} — API 基础地址，默认 {@code https://open.bigmodel.cn/api}</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
public class ZaiImageClient {

	private final WebClient webClient;

	/**
	 * 构造 Z.AI 图像生成客户端。
	 *
	 * @param baseUrl API 基础地址，如 {@code https://open.bigmodel.cn/api}
	 * @param apiKey  Z.AI API 密钥
	 */
	public ZaiImageClient(String baseUrl, String apiKey) {
		this.webClient = WebClient.builder()
				.baseUrl(baseUrl)
				.defaultHeader("Authorization", "Bearer " + apiKey)
				.build();
	}

	/**
	 * 发起图像生成请求。
	 *
	 * @param request 图像生成请求参数
	 * @return Z.AI API 返回的图像生成响应
	 * @throws IllegalArgumentException 请求参数序列化失败
	 * @throws RuntimeException         API 请求失败或响应为空
	 */
	public ImageGenerationResponse generate(ImageRequest request) {

		// 将请求对象序列化为 JSON 字符串
		String payload = ObjectMapperFactory.jsonify(request);
		if (payload == null) {
			throw new IllegalArgumentException("序列化请求参数失败: 结果为 null");
		}

		// 发起 POST 请求并阻塞等待响应
		ImageGenerationResponse response = this.webClient.post()
				.uri("/images/generations")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(payload)
				.retrieve()
				.onStatus(
						status -> !status.is2xxSuccessful(),
						clientResponse -> clientResponse.bodyToMono(String.class)
								.flatMap(body -> Mono.error(
										new RuntimeException("Z.AI 图像生成 API 请求失败 [" + clientResponse.statusCode() + "]: " + body)
								))
				)
				.bodyToMono(String.class)
				.map(body -> {
					try {
						return ObjectMapperFactory.objectifyOrThrow(body, ImageGenerationResponse.class);
					} catch (JsonProcessingException e) {
						throw new RuntimeException("解析 Z.AI 图像生成响应失败: " + body, e);
					}
				})
				.block();

		// 校验响应有效性
		if (response == null || response.getData().isEmpty()) {
			throw new RuntimeException("Z.AI 图像生成 API 返回的响应为空或缺少 data");
		}

		return response;
	}

	public static void main(String[] args) {

		ZaiImageClient client = new ZaiImageClient(
				"https://open.bigmodel.cn/api/paas/v4",
				"..."
		);

		ImageRequest request = ImageRequest.builder()
				.model("glm-image")
				.prompt("一只可爱的小猫咪，坐在阳光明媚的窗台上，背景是蓝天白云")
				.size("1280x1280")
				.build();

		ImageGenerationResponse response = client.generate(request);

		System.out.println("图片链接: " + response.getData().getFirst().getUrl());
	}

}
