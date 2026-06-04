package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;

/**
 * 情感设置，仅部分音色支持。
 * <p>
 * 详见
 * <a href="https://www.volcengine.com/docs/6561/1257544">大模型语音合成 API 音色列表 - 多情感音色</a>。
 */
@NullMarked
public enum TtsEmotion {

	/**
	 * 未知类型
	 */
	UNKNOWN("unknown"),

	// 中文音色
	/**
	 * 开心
	 */
	ZH_HAPPY("happy"),
	/**
	 * 悲伤
	 */
	ZH_SAD("sad"),
	/**
	 * 生气
	 */
	ZH_ANGRY("angry"),
	/**
	 * 惊讶
	 */
	ZH_SURPRISED("surprised"),
	/**
	 * 恐惧
	 */
	ZH_FEAR("fear"),
	/**
	 * 厌恶
	 */
	ZH_HATE("hate"),
	/**
	 * 激动
	 */
	ZH_EXCITED("excited"),
	/**
	 * 冷漠
	 */
	ZH_COLDNESS("coldness"),
	/**
	 * 中性
	 */
	ZH_NEUTRAL("neutral"),
	/**
	 * 沮丧
	 */
	ZH_DEPRESSED("depressed"),
	/**
	 * 撒娇
	 */
	ZH_LOVEY_DOVEY("lovey-dovey"),
	/**
	 * 害羞
	 */
	ZH_SHY("shy"),
	/**
	 * 安慰鼓励
	 */
	ZH_COMFORT("comfort"),
	/**
	 * 咆哮/焦急
	 */
	ZH_TENSION("tension"),
	/**
	 * 温柔
	 */
	ZH_TENDER("tender"),
	/**
	 * 讲故事 / 自然讲述
	 */
	ZH_STORYTELLING("storytelling"),
	/**
	 * 情感电台
	 */
	ZH_RADIO("radio"),
	/**
	 * 磁性
	 */
	ZH_MAGNETIC("magnetic"),
	/**
	 * 广告营销
	 */
	ZH_ADVERTISING("advertising"),
	/**
	 * 气泡音
	 */
	ZH_VOCAL_FRY("vocal-fry"),
	/**
	 * 低语 (ASMR)
	 */
	ZH_ASMR("asmr"),
	/**
	 * 新闻播报
	 */
	ZH_NEWS("news"),
	/**
	 * 娱乐八卦
	 */
	ZH_ENTERTAINMENT("entertainment"),
	/**
	 * 方言
	 */
	ZH_DIALECT("dialect"),

	// 英文音色
	/**
	 * 中性
	 */
	EN_NEUTRAL("neutral"),
	/**
	 * 愉悦
	 */
	EN_HAPPY("happy"),
	/**
	 * 愤怒
	 */
	EN_ANGRY("angry"),
	/**
	 * 悲伤
	 */
	EN_SAD("sad"),
	/**
	 * 兴奋
	 */
	EN_EXCITED("excited"),
	/**
	 * 对话 / 闲聊
	 */
	EN_CHAT("chat"),
	/**
	 * 低语 (ASMR)
	 */
	EN_ASMR("asmr"),
	/**
	 * 温暖
	 */
	EN_WARM("warm"),
	/**
	 * 深情
	 */
	EN_AFFECTIONATE("affectionate"),
	/**
	 * 权威
	 */
	EN_AUTHORITATIVE("authoritative");

	private final String code;

	TtsEmotion(String code) {
		this.code = code;
	}

	/**
	 * 获取该情感在 API 中的字符串表现形式。
	 *
	 * @return 情感字符串
	 */
	@JsonValue
	public String getCode() {
		return this.code;
	}

	/**
	 * 从服务端返回的字符串值反序列化为 TtsEmotion 枚举。
	 * <p>
	 * 空白或未匹配的字符串默认返回 {@link #UNKNOWN}，保证反序列化健壮性。
	 *
	 * @param code 服务端返回的 emotion 字符串
	 * @return 对应的 TtsEmotion 枚举值
	 */
	@JsonCreator
	public static TtsEmotion fromCode(String code) {
		if (StringUtils.isBlank(code)) return UNKNOWN;

		for (TtsEmotion value : TtsEmotion.values()) {
			if (value.code.equals(code)) return value;
		}
		return UNKNOWN;
	}
}
