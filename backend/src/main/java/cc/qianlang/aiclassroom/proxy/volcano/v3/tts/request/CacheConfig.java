package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import lombok.Builder;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 缓存配置，对应请求体 {@code req_params.additions.cache_config} 字段。
 * <p>
 * 开启缓存后，合成相同文本时，服务会直接读取缓存返回上一次合成该文本的音频，
 * 可明显加快相同文本的合成速率，缓存数据保留时间 1 小时。
 * <p>
 * 通过缓存返回的数据不会附带时间戳。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@Builder
@NullMarked
public class CacheConfig {

	/**
	 * 文本类型，和 {@code useCache} 参数一起使用，需要开启缓存时传 1。
	 */
	@Nullable
	private Integer textType;

	/**
	 * 是否使用缓存，和 {@code textType} 参数一起使用，需要开启缓存时传 {@code true}。
	 */
	@Nullable
	private Boolean useCache;
}
