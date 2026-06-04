package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 混音参数，对应请求体 {@code req_params.mix_speaker} 字段。
 * <p>
 * 仅适用于"豆包语音合成模型 1.0"音色，使用时须将
 * {@code req_params.speaker} 设为 {@link TtsSpeaker#CUSTOM_MIX}。
 * <p>
 * 注意：
 * <ul>
 *   <li>最多支持 3 个音色混音</li>
 *   <li>音色风格差异较大的两个音色（如男女混），以 0.5-0.5 同等比例混合时，可能出现偶发跳变，建议尽量避免</li>
 *   <li>使用 Mix 能力时，req_params.speaker = custom_mix_bigtts</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class TtsMixSpeaker {

	/**
	 * 混音音色列表，最多 3 个。
	 */
	@Nullable
	private List<Speaker> speakers;

	/**
	 * 单个混音源音色配置。
	 */
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	@NullMarked
	public static class Speaker {

		/**
		 * 混音源音色名，支持"豆包语音合成模型 1.0"音色及声音复刻大模型音色。
		 * <p>
		 * 注意：
		 * <ul>
		 *   <li>使用声音复刻大模型音色时，使用 {@code S_} 开头的 speakerid，或者使用查询接口获取的 {@code icl_} 的 speakerid</li>
		 *   <li>不支持 {@code DiT_} 或者 {@code saturn_} 开头的 speakerid</li>
		 * </ul>
		 */
		@Nullable
		private TtsSpeaker sourceSpeaker;

		/**
		 * 混音影响因子，所有音色因子之和必须为 1。
		 */
		private float mixFactor;

	}

}
