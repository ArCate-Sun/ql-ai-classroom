package cc.qianlang.aiclassroom.proxy.volcano.v3.image.request;

import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * 图像输入参数包装，对应请求体 {@code image} 字段。
 * <p>
 * 该字段在 JSON 中有两种形式：
 * <ul>
 *   <li>单张图像引用：序列化为字符串（URL 或 Base64）</li>
 *   <li>多张图像引用：序列化为字符串数组（2–14 张 URL 或 Base64）</li>
 * </ul>
 * 使用静态工厂方法构造实例：{@link #of(String)} 或 {@link #of(List)}。
 */
@NullMarked
public class ImageInput {

	/**
	 * 实际序列化值，类型为 {@link String}（单张）或 {@link List}{@code <String>}（多张）
	 */
	private final Object value;

	private ImageInput(Object value) {
		this.value = value;
	}

	/**
	 * 构造单张图像引用。
	 *
	 * @param urlOrBase64 图像 URL 或 Base64 编码字符串
	 * @return 单张图像输入
	 */
	public static ImageInput of(String urlOrBase64) {
		return new ImageInput(urlOrBase64);
	}

	/**
	 * 构造多张图像引用。
	 *
	 * @param urlsOrBase64List 图像 URL 或 Base64 编码字符串列表，2–14 张
	 * @return 多张图像输入
	 */
	public static ImageInput of(List<String> urlsOrBase64List) {
		return new ImageInput(List.copyOf(urlsOrBase64List));
	}

	/**
	 * 返回实际序列化值，单张时为 {@link String}，多张时为 {@link List}{@code <String>}。
	 *
	 * @return 序列化值
	 */
	@JsonValue
	public Object getValue() {
		return this.value;
	}
}
