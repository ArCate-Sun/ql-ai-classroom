package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * AIGC 元数据水印配置，对应请求体 {@code req_params.additions.aigc_metadata} 字段。
 * <p>
 * 在合成音频 header 加入元数据隐式表示，支持 mp3/wav/ogg_opus 格式。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class AigcMetadata {

	/**
	 * 是否启用隐式水印，默认 {@code false}。
	 */
	@Nullable
	private Boolean enable;

	/**
	 * 合成服务提供者的名称或编码，默认空字符串。
	 */
	@Nullable
	private String contentProducer;

	/**
	 * 内容制作编号，默认空字符串。
	 */
	@Nullable
	private String produceId;

	/**
	 * 内容传播服务提供者的名称或编码，默认空字符串。
	 */
	@Nullable
	private String contentPropagator;

	/**
	 * 内容传播编号，默认空字符串。
	 */
	@Nullable
	private String propagateId;
}
