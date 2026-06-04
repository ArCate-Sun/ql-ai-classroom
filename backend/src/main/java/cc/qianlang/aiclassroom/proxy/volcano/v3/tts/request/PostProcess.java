package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 后处理配置，对应请求体 {@code req_params.additions.post_process} 字段。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class PostProcess {

	/**
	 * 音调，取值范围是 [-12, 12]，默认 0。
	 */
	@Nullable
	private Integer pitch;
}
