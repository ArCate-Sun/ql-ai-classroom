package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.request;

import cc.qianlang.web.common.core.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 发音人枚举，对应请求体 {@code req_params.speaker} 字段。
 * <p>
 * 支持豆包语音合成大模型音色（{@link TtsSpeaker}）及声音复刻音色（直接传入复刻音色 ID）。
 * 完整音色列表见
 * <a href="https://www.volcengine.com/docs/6561/1257544">发音人列表</a>。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public enum TtsSpeaker {

	UNKNOWN("unknown"),

	/**
	 * 自定义混音音色，用于混音功能。
	 * 使用时须配合 {@code req_params.mix_speaker} 参数。
	 * 仅适用于"豆包语音合成模型 1.0"音色。
	 */
	CUSTOM_MIX("custom_mix_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：Vivi 2.0
	 * 语种：中文、日文、印尼、墨西哥西班牙语、四川、陕西、东北
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	VIVI_2_0("zh_female_vv_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：小何 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	小何_2_0("zh_female_xiaohe_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：云舟 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	云舟_2_0("zh_male_m191_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：小天 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	小天_2_0("zh_male_taocheng_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：刘飞 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	刘飞_2_0("zh_male_liufei_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：魅力苏菲 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	魅力苏菲_2_0("zh_female_sophie_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：清新女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	清新女声_2_0("zh_female_qingxinnvsheng_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：知性灿灿 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	知性灿灿_2_0("zh_female_cancan_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：撒娇学妹 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	撒娇学妹_2_0("zh_female_sajiaoxuemei_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：甜美小源 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	甜美小源_2_0("zh_female_tianmeixiaoyuan_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：甜美桃子 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	甜美桃子_2_0("zh_female_tianmeitaozi_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：爽快思思 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	爽快思思_2_0("zh_female_shuangkuaisisi_uranus_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：佩奇猪 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	佩奇猪_2_0("zh_female_peiqi_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：邻家女孩 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	邻家女孩_2_0("zh_female_linjianvhai_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：少年梓辛/Brayan 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	少年梓辛_2_0("zh_male_shaonianzixin_uranus_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：猴哥 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	猴哥_2_0("zh_male_sunwukong_uranus_bigtts"),

	/**
	 * 场景：教育场景
	 * 音色：Tina老师 2.0
	 * 语种：中文、英式英语
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	Tina老师_2_0("zh_female_yingyujiaoxue_uranus_bigtts"),

	/**
	 * 场景：客服场景
	 * 音色：暖阳女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	暖阳女声_2_0("zh_female_kefunvsheng_uranus_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：儿童绘本 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	儿童绘本_2_0("zh_female_xiaoxue_uranus_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：大壹 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	大壹_2_0("zh_male_dayi_uranus_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：黑猫侦探社咪仔 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	黑猫侦探社咪仔_2_0("zh_female_mizai_uranus_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：鸡汤女 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	鸡汤女_2_0("zh_female_jitangnv_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：魅力女友 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	魅力女友_2_0("zh_female_meilinvyou_uranus_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：流畅女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	流畅女声_2_0("zh_female_liuchangnv_uranus_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：儒雅逸辰 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	儒雅逸辰_2_0("zh_male_ruyayichen_uranus_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Tim
	 * 语种：美式英语
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	TIM("en_male_tim_uranus_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Dacey
	 * 语种：美式英语
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	DACEY("en_female_dacey_uranus_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Stokie
	 * 语种：美式英语
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	STOKIE("en_female_stokie_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：温柔妈妈 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	温柔妈妈_2_0("zh_female_wenroumama_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：解说小明 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	解说小明_2_0("zh_male_jieshuoxiaoming_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：TVB女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	TVB女声_2_0("zh_female_tvbnv_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：译制片男 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	译制片男_2_0("zh_male_yizhipiannan_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：俏皮女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	俏皮女声_2_0("zh_female_qiaopinv_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：直率英子 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	直率英子_2_0("zh_female_zhishuaiyingzi_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：邻家男孩 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	邻家男孩_2_0("zh_male_linjiananhai_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：四郎 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	四郎_2_0("zh_male_silang_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：儒雅青年 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：番茄小说同款, 豆包同款, 剪映同款
	 */
	儒雅青年_2_0("zh_male_ruyaqingnian_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：擎苍 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：番茄小说同款,豆包同款,抖音同款, 剪映同款
	 */
	擎苍_2_0("zh_male_qingcang_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：熊二 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	熊二_2_0("zh_male_xionger_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：樱桃丸子 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	樱桃丸子_2_0("zh_female_yingtaowanzi_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：温暖阿虎/Alvin 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	温暖阿虎_2_0("zh_male_wennuanahu_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：奶气萌娃 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：剪映同款,豆包同款
	 */
	奶气萌娃_2_0("zh_male_naiqimengwa_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：婆婆 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	婆婆_2_0("zh_female_popo_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：高冷御姐 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	高冷御姐_2_0("zh_female_gaolengyujie_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：傲娇霸总 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	傲娇霸总_2_0("zh_male_aojiaobazong_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：懒音绵宝 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	懒音绵宝_2_0("zh_male_lanyinmianbao_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：反卷青年 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	反卷青年_2_0("zh_male_fanjuanqingnian_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：温柔淑女 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：番茄小说同款, 豆包同款, 剪映同款
	 */
	温柔淑女_2_0("zh_female_wenroushunv_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：古风少御 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	古风少御_2_0("zh_female_gufengshaoyu_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：活力小哥 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	活力小哥_2_0("zh_male_huolixiaoge_uranus_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：霸气青叔 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：番茄小说同款, 豆包同款, 剪映同款
	 */
	霸气青叔_2_0("zh_male_baqiqingshu_uranus_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：悬疑解说 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	悬疑解说_2_0("zh_male_xuanyijieshuo_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：萌丫头/Cutey 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	萌丫头_2_0("zh_female_mengyatou_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：贴心女声/Candy 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	贴心女声_2_0("zh_female_tiexinnvsheng_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：鸡汤妹妹/Hope 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款
	 */
	鸡汤妹妹_2_0("zh_female_jitangmei_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：磁性解说男声/Morgan 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 剪映同款
	 */
	磁性解说男声_2_0("zh_male_cixingjieshuonan_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：亮嗓萌仔 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	亮嗓萌仔_2_0("zh_male_liangsangmengzai_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：开朗姐姐 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	开朗姐姐_2_0("zh_female_kailangjiejie_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：高冷沉稳 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：猫箱同款
	 */
	高冷沉稳_2_0("zh_male_gaolengchenwen_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：深夜播客 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	深夜播客_2_0("zh_male_shenyeboke_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：鲁班七号 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	鲁班七号_2_0("zh_male_lubanqihao_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：娇喘女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 剪映同款
	 */
	娇喘女声_2_0("zh_female_jiaochuannv_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：林潇 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	林潇_2_0("zh_female_linxiao_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：玲玲姐姐 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	玲玲姐姐_2_0("zh_female_lingling_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：春日部姐姐 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款, 剪映同款
	 */
	春日部姐姐_2_0("zh_female_chunribu_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：唐僧 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 豆包同款
	 */
	唐僧_2_0("zh_male_tangseng_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：庄周 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 剪映同款
	 */
	庄周_2_0("zh_male_zhuangzhou_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：开朗弟弟 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 剪映同款
	 */
	开朗弟弟_2_0("zh_male_kailangdidi_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：猪八戒 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：豆包同款, 剪映同款
	 */
	猪八戒_2_0("zh_male_zhubajie_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：感冒电音姐姐 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 剪映同款
	 */
	感冒电音姐姐_2_0("zh_female_ganmaodianyin_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：谄媚女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 剪映同款
	 */
	谄媚女声_2_0("zh_female_chanmeinv_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：女雷神 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：剪映同款,豆包同款
	 */
	女雷神_2_0("zh_female_nvleishen_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：亲切女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：豆包同款
	 */
	亲切女声_2_0("zh_female_qinqienv_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：快乐小东 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：豆包同款
	 */
	快乐小东_2_0("zh_male_kuailexiaodong_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：开朗学长 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：豆包同款
	 */
	开朗学长_2_0("zh_male_kailangxuezhang_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：悠悠君子 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：豆包同款
	 */
	悠悠君子_2_0("zh_male_youyoujunzi_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：文静毛毛 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：豆包同款
	 */
	文静毛毛_2_0("zh_female_wenjingmaomao_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：知性女声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	知性女声_2_0("zh_female_zhixingnv_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：清爽男大 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：豆包同款
	 */
	清爽男大_2_0("zh_male_qingshuangnanda_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：渊博小叔 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	渊博小叔_2_0("zh_male_yuanboxiaoshu_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：阳光青年 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	阳光青年_2_0("zh_male_yangguangqingnian_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：清澈梓梓 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	清澈梓梓_2_0("zh_female_qingchezizi_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：甜美悦悦 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	甜美悦悦_2_0("zh_female_tianmeiyueyue_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：心灵鸡汤 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	心灵鸡汤_2_0("zh_female_xinlingjitang_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：温柔小哥 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	温柔小哥_2_0("zh_male_wenrouxiaoge_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：柔美女友 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	柔美女友_2_0("zh_female_roumeinvyou_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：东方浩然 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	东方浩然_2_0("zh_male_dongfanghaoran_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：温柔小雅 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	温柔小雅_2_0("zh_female_wenrouxiaoya_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：天才童声 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	天才童声_2_0("zh_male_tiancaitongsheng_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：武则天 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：剪映同款
	 */
	武则天_2_0("zh_female_wuzetian_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：顾姐 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：抖音同款, 剪映同款
	 */
	顾姐_2_0("zh_female_gujie_uranus_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：广告解说 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：剪映同款
	 */
	广告解说_2_0("zh_male_guanggaojieshuo_uranus_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：少儿故事 2.0
	 * 语种：中文
	 * 支持能力：情感变化、指令遵循、ASMR
	 * 特殊标签：
	 */
	少儿故事_2_0("zh_female_shaoergushi_uranus_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：调皮公主
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	调皮公主("saturn_zh_female_tiaopigongzhu_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：爽朗少年
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	爽朗少年("saturn_zh_male_shuanglangshaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：天才同桌
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	天才同桌("saturn_zh_male_tiancaitongzhuo_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：知性灿灿
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	知性灿灿("saturn_zh_female_cancan_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：傲娇女友 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	傲娇女友_2_0_SATURN("saturn_zh_female_aojiaonvyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病娇姐姐 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	病娇姐姐_2_0("saturn_zh_female_bingjiaojiejie_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：成熟姐姐 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	成熟姐姐_2_0("saturn_zh_female_chengshujiejie_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：可爱女生 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	可爱女生_2_0("saturn_zh_female_keainvsheng_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：暖心学姐 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	暖心学姐_2_0("saturn_zh_female_nuanxinxuejie_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：贴心女友 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	贴心女友_2_0("saturn_zh_female_tiexinnvyou_tob"),

	/**
	 * 场景：通用场景
	 * 音色：温柔文雅 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	温柔文雅_2_0("saturn_zh_female_wenrouwenya_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：妩媚御姐 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	妩媚御姐_2_0("saturn_zh_female_wumeiyujie_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：性感御姐 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	性感御姐_2_0("saturn_zh_female_xingganyujie_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：傲气凌人 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	傲气凌人_2_0("saturn_zh_male_aiqilingren_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：傲娇公子 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	傲娇公子_2_0("saturn_zh_male_aojiaogongzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：傲娇精英 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	傲娇精英_2_0("saturn_zh_male_aojiaojingying_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：傲慢少爷 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	傲慢少爷_2_0("saturn_zh_male_aomanshaoye_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：霸道少爷 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	霸道少爷_2_0("saturn_zh_male_badaoshaoye_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病娇白莲 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	病娇白莲_2_0("saturn_zh_male_bingjiaobailian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：不羁青年 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	不羁青年_2_0("saturn_zh_male_bujiqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：成熟总裁 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	成熟总裁_2_0("saturn_zh_male_chengshuzongcai_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：磁性男嗓 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	磁性男嗓_2_0("saturn_zh_male_cixingnansang_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：醋精男友 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	醋精男友_2_0("saturn_zh_male_cujingnanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：风发少年 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	风发少年_2_0("saturn_zh_male_fengfashaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：腹黑公子 2.0
	 * 语种：中文
	 * 支持能力：指令遵循、COT/QA功能
	 * 特殊标签：
	 */
	腹黑公子_2_0("saturn_zh_male_fuheigongzi_tob"),

	/**
	 * 场景：客服场景
	 * 音色：轻盈朵朵 2.0
	 * 语种：中文
	 * 支持能力：指令遵循
	 * 特殊标签：
	 */
	轻盈朵朵_2_0("saturn_zh_female_qingyingduoduo_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：温婉珊珊 2.0
	 * 语种：中文
	 * 支持能力：指令遵循
	 * 特殊标签：
	 */
	温婉珊珊_2_0("saturn_zh_female_wenwanshanshan_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：热情艾娜 2.0
	 * 语种：中文
	 * 支持能力：指令遵循
	 * 特殊标签：
	 */
	热情艾娜_2_0("saturn_zh_female_reqingaina_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：清新沐沐 2.0
	 * 语种：中文
	 * 支持能力：指令遵循
	 * 特殊标签：
	 */
	清新沐沐_2_0("saturn_zh_male_qingxinmumu_cs_tob"),

	/**
	 * 场景：多情感
	 * 音色：冷酷哥哥（多情感）
	 * 语种：中文
	 * 支持的情感：生气、冷漠、恐惧、开心、厌恶、中性、悲伤、沮丧
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	冷酷哥哥_多情感("zh_male_lengkugege_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：甜心小美（多情感）
	 * 语种：中文
	 * 支持的情感：悲伤、恐惧、厌恶、中性
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	甜心小美_多情感("zh_female_tianxinxiaomei_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：高冷御姐（多情感）
	 * 语种：中文
	 * 支持的情感：开心、悲伤、生气、惊讶、恐惧、厌恶、激动、冷漠、中性
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：高冷御姐 2.0
	 * 是否支持 MIX：否
	 */
	高冷御姐_多情感("zh_female_gaolengyujie_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：傲娇霸总（多情感）
	 * 语种：中文
	 * 支持的情感：中性、开心、愤怒、厌恶
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：傲娇霸总 2.0
	 * 是否支持 MIX：否
	 */
	傲娇霸总_多情感("zh_male_aojiaobazong_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：广州德哥（多情感）
	 * 语种：中文
	 * 支持的情感：生气、恐惧、中性
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	广州德哥_多情感("zh_male_guangzhoudege_emo_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：京腔侃爷（多情感）
	 * 语种：中文
	 * 支持的情感：开心、生气、惊讶、厌恶、中性
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	京腔侃爷_多情感("zh_male_jingqiangkanye_emo_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：邻居阿姨（多情感）
	 * 语种：中文
	 * 支持的情感：中性、愤怒、冷漠、沮丧、惊讶
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	邻居阿姨_多情感("zh_female_linjuayi_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：优柔公子（多情感）
	 * 语种：中文
	 * 支持的情感：开心、生气、恐惧、厌恶、激动、中性、沮丧
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	优柔公子_多情感("zh_male_yourougongzi_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：儒雅男友（多情感）
	 * 语种：中文
	 * 支持的情感：开心、悲伤、生气、恐惧、激动、冷漠、中性
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	儒雅男友_多情感("zh_male_ruyayichen_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：俊朗男友（多情感）
	 * 语种：中文
	 * 支持的情感：开心、悲伤、生气、惊讶、恐惧、中性
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	俊朗男友_多情感("zh_male_junlangnanyou_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：北京小爷（多情感）
	 * 语种：中文
	 * 支持的情感：生气，惊讶，恐惧，激动，冷漠，中性
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	北京小爷_多情感("zh_male_beijingxiaoye_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：柔美女友（多情感）
	 * 语种：中文
	 * 支持的情感：开心，悲伤，生气，惊讶，恐惧，厌恶，激动，冷漠，中性
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	柔美女友_多情感("zh_female_roumeinvyou_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：阳光青年（多情感）
	 * 语种：中文
	 * 支持的情感：开心，悲伤，生气，恐惧，激动，冷漠，中性
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	阳光青年_多情感("zh_male_yangguangqingnian_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：魅力女友（多情感）
	 * 语种：中文
	 * 支持的情感：悲伤，恐惧，中性
	 * 特殊标签：
	 * 对应 2.0 音色：魅力女友 2.0
	 * 是否支持 MIX：否
	 */
	魅力女友_多情感("zh_female_meilinvyou_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：爽快思思（多情感）
	 * 语种：中文,英式英语
	 * 支持的情感：开心，悲伤，生气，惊讶，激动，冷漠，中性
	 * 特殊标签：
	 * 对应 2.0 音色：爽快思思 2.0
	 * 是否支持 MIX：否
	 */
	爽快思思_多情感("zh_female_shuangkuaisisi_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：Candice
	 * 语种：美式英语
	 * 支持的情感：深情、愤怒、ASMR、对话/闲聊、兴奋、愉悦、中性、温暖
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	CANDICE("en_female_candice_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：Serena
	 * 语种：美式英语
	 * 支持的情感：深情、愤怒、ASMR、对话/闲聊、兴奋、愉悦、中性、悲伤、温暖
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	SERENA("en_female_skye_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：Glen
	 * 语种：美式英语
	 * 支持的情感：深情、愤怒、ASMR、对话/闲聊、深情、兴奋、愉悦、中性、悲伤、温暖
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	GLEN("en_male_glen_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：Sylus
	 * 语种：美式英语
	 * 支持的情感：深情、愤怒、ASMR、权威、对话/闲聊、兴奋、愉悦、中性、悲伤、温暖
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	SYLUS("en_male_sylus_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：Corey
	 * 语种：英式英语
	 * 支持的情感：愤怒、ASMR、权威、对话/闲聊、深情、兴奋、愉悦、中性、悲伤、温暖
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	COREY("en_male_corey_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：Nadia
	 * 语种：英式英语
	 * 支持的情感：深情、愤怒、ASMR、对话/闲聊、深情、兴奋、愉悦、中性、悲伤、温暖
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	NADIA("en_female_nadia_tips_emo_v2_mars_bigtts"),

	/**
	 * 场景：多情感
	 * 音色：深夜播客
	 * 语种：中文
	 * 支持的情感：惊讶、悲伤、中性、厌恶、开心、恐惧、激动、沮丧、冷漠、生气
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：深夜播客 2.0
	 * 是否支持 MIX：否
	 */
	深夜播客_多情感("zh_male_shenyeboke_emo_v2_mars_bigtts"),

	/**
	 * 场景：教育场景
	 * 音色：Tina老师
	 * 语种：中文,英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：Tina老师 2.0
	 * 是否支持 MIX：是
	 */
	Tina老师("zh_female_yingyujiaoyu_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：温柔女神
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔女神("ICL_zh_female_wenrounvshen_239eff5e8ffa_tob"),

	/**
	 * 场景：通用场景
	 * 音色：Vivi
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：Vivi 2.0
	 * 是否支持 MIX：是
	 */
	VIVI("zh_female_vv_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：亲切女声
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：亲切女声 2.0
	 * 是否支持 MIX：是
	 */
	亲切女声("zh_female_qinqienvsheng_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：机灵小伙
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	机灵小伙("ICL_zh_male_shenmi_v1_tob"),

	/**
	 * 场景：通用场景
	 * 音色：元气甜妹
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	元气甜妹("ICL_zh_female_wuxi_tob"),

	/**
	 * 场景：通用场景
	 * 音色：知心姐姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	知心姐姐("ICL_zh_female_wenyinvsheng_v1_tob"),

	/**
	 * 场景：通用场景
	 * 音色：阳光阿辰
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	阳光阿辰("zh_male_qingyiyuxuan_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：快乐小东
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：快乐小东 2.0
	 * 是否支持 MIX：是
	 */
	快乐小东("zh_male_xudong_conversation_wvae_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：冷酷哥哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷酷哥哥("ICL_zh_male_lengkugege_v1_tob"),

	/**
	 * 场景：通用场景
	 * 音色：纯澈女生
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	纯澈女生("ICL_zh_female_feicui_v1_tob"),

	/**
	 * 场景：通用场景
	 * 音色：初恋女友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	初恋女友("ICL_zh_female_yuxin_v1_tob"),

	/**
	 * 场景：通用场景
	 * 音色：贴心闺蜜
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	贴心闺蜜("ICL_zh_female_xnx_tob"),

	/**
	 * 场景：通用场景
	 * 音色：温柔白月光
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔白月光("ICL_zh_female_yry_tob"),

	/**
	 * 场景：通用场景
	 * 音色：炀炀
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	炀炀("ICL_zh_male_BV705_streaming_cs_tob"),

	/**
	 * 场景：通用场景
	 * 音色：开朗学长
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：开朗学长 2.0
	 * 是否支持 MIX：是
	 */
	开朗学长("en_male_jason_conversation_wvae_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：魅力苏菲
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：魅力苏菲 2.0
	 * 是否支持 MIX：是
	 */
	魅力苏菲("zh_female_sophie_conversation_wvae_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：贴心妹妹
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	贴心妹妹("ICL_zh_female_yilin_tob"),

	/**
	 * 场景：通用场景
	 * 音色：甜美桃子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：甜美桃子 2.0
	 * 是否支持 MIX：是
	 */
	甜美桃子("zh_female_tianmeitaozi_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：清新女声
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：清新女声 2.0
	 * 是否支持 MIX：是
	 */
	清新女声("zh_female_qingxinnvsheng_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：知性女声
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：知性女声 2.0
	 * 是否支持 MIX：是
	 */
	知性女声("zh_female_zhixingnvsheng_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：清爽男大
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：清爽男大 2.0
	 * 是否支持 MIX：是
	 */
	清爽男大("zh_male_qingshuangnanda_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：邻家女孩
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：邻家女孩 2.0
	 * 是否支持 MIX：是
	 */
	邻家女孩("zh_female_linjianvhai_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：渊博小叔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、剪映同款
	 * 对应 2.0 音色：渊博小叔 2.0
	 * 是否支持 MIX：是
	 */
	渊博小叔("zh_male_yuanboxiaoshu_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：阳光青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：阳光青年 2.0
	 * 是否支持 MIX：是
	 */
	阳光青年("zh_male_yangguangqingnian_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：甜美小源
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：甜美小源 2.0
	 * 是否支持 MIX：是
	 */
	甜美小源("zh_female_tianmeixiaoyuan_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：清澈梓梓
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：清澈梓梓 2.0
	 * 是否支持 MIX：是
	 */
	清澈梓梓("zh_female_qingchezizi_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：解说小明
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：解说小明 2.0
	 * 是否支持 MIX：是
	 */
	解说小明("zh_male_jieshuoxiaoming_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：开朗姐姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：开朗姐姐 2.0
	 * 是否支持 MIX：是
	 */
	开朗姐姐("zh_female_kailangjiejie_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：邻家男孩
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：邻家男孩 2.0
	 * 是否支持 MIX：是
	 */
	邻家男孩("zh_male_linjiananhai_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：甜美悦悦
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：甜美悦悦 2.0
	 * 是否支持 MIX：是
	 */
	甜美悦悦("zh_female_tianmeiyueyue_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：心灵鸡汤
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：心灵鸡汤 2.0
	 * 是否支持 MIX：是
	 */
	心灵鸡汤("zh_female_xinlingjitang_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：知性温婉
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	知性温婉("ICL_zh_female_zhixingwenwan_tob"),

	/**
	 * 场景：通用场景
	 * 音色：暖心体贴
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	暖心体贴("ICL_zh_male_nuanxintitie_tob"),

	/**
	 * 场景：通用场景
	 * 音色：开朗轻快
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	开朗轻快("ICL_zh_male_kailangqingkuai_tob"),

	/**
	 * 场景：通用场景
	 * 音色：活泼爽朗
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	活泼爽朗("ICL_zh_male_huoposhuanglang_tob"),

	/**
	 * 场景：通用场景
	 * 音色：率真小伙
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	率真小伙("ICL_zh_male_shuaizhenxiaohuo_tob"),

	/**
	 * 场景：通用场景
	 * 音色：温柔小哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：温柔小哥 2.0
	 * 是否支持 MIX：是
	 */
	温柔小哥("zh_male_wenrouxiaoge_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：灿灿/Shiny
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：知性灿灿 2.0
	 * 是否支持 MIX：是
	 */
	灿灿("zh_female_cancan_mars_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：爽快思思/Skye
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：爽快思思 2.0
	 * 是否支持 MIX：是
	 */
	爽快思思("zh_female_shuangkuaisisi_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：温暖阿虎/Alvin
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：温暖阿虎/Alvin 2.0
	 * 是否支持 MIX：是
	 */
	温暖阿虎("zh_male_wennuanahu_moon_bigtts"),

	/**
	 * 场景：通用场景
	 * 音色：少年梓辛/Brayan
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款、剪映同款
	 * 对应 2.0 音色：少年梓辛/Brayan 2.0
	 * 是否支持 MIX：是
	 */
	少年梓辛("zh_male_shaonianzixin_moon_bigtts"),

	/**
	 * 场景：通用场景、S2S-SC
	 * 音色：温柔文雅
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔文雅("ICL_zh_female_wenrouwenya_tob"),

	/**
	 * 场景：IP仿音
	 * 音色：沪普男
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	沪普男("zh_male_hupunan_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：鲁班七号
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款、豆包同款
	 * 对应 2.0 音色：鲁班七号 2.0
	 * 是否支持 MIX：是
	 */
	鲁班七号("zh_male_lubanqihao_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：林潇
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款、豆包同款
	 * 对应 2.0 音色：林潇 2.0
	 * 是否支持 MIX：是
	 */
	林潇("zh_female_yangmi_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：玲玲姐姐
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款、豆包同款
	 * 对应 2.0 音色：玲玲姐姐 2.0
	 * 是否支持 MIX：是
	 */
	玲玲姐姐("zh_female_linzhiling_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：春日部姐姐
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款、豆包同款
	 * 对应 2.0 音色：春日部姐姐 2.0
	 * 是否支持 MIX：是
	 */
	春日部姐姐("zh_female_jiyejizi2_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：唐僧
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：抖音同款、豆包同款
	 * 对应 2.0 音色：唐僧 2.0
	 * 是否支持 MIX：是
	 */
	唐僧("zh_male_tangseng_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：庄周
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款
	 * 对应 2.0 音色：庄周 2.0
	 * 是否支持 MIX：是
	 */
	庄周("zh_male_zhuangzhou_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：猪八戒
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、豆包同款
	 * 对应 2.0 音色：猪八戒 2.0
	 * 是否支持 MIX：是
	 */
	猪八戒("zh_male_zhubajie_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：感冒电音姐姐
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款
	 * 对应 2.0 音色：感冒电音姐姐 2.0
	 * 是否支持 MIX：是
	 */
	感冒电音姐姐("zh_female_ganmaodianyin_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：直率英子
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款、豆包同款
	 * 对应 2.0 音色：直率英子 2.0
	 * 是否支持 MIX：是
	 */
	直率英子("zh_female_naying_mars_bigtts"),

	/**
	 * 场景：IP仿音
	 * 音色：女雷神
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、豆包同款
	 * 对应 2.0 音色：女雷神 2.0
	 * 是否支持 MIX：是
	 */
	女雷神("zh_female_leidian_mars_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：粤语小溏
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	粤语小溏("zh_female_yueyunv_mars_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：豫州子轩
	 * 语种：中文-河南口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	豫州子轩("zh_male_yuzhouzixuan_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：呆萌川妹
	 * 语种：中文-四川口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	呆萌川妹("zh_female_daimengchuanmei_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：广西远舟
	 * 语种：中文-广西口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	广西远舟("zh_male_guangxiyuanzhou_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：双节棍小哥
	 * 语种：中文-台湾口音
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款、豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	双节棍小哥("zh_male_zhoujielun_emo_v2_mars_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：湾湾小何
	 * 语种：中文-台湾口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：小何 2.0
	 * 是否支持 MIX：是
	 */
	湾湾小何("zh_female_wanwanxiaohe_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：湾区大叔
	 * 语种：中文-广东口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	湾区大叔("zh_female_wanqudashu_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：广州德哥
	 * 语种：中文-广东口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	广州德哥("zh_male_guozhoudege_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：浩宇小哥
	 * 语种：中文-青岛口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	浩宇小哥("zh_male_haoyuxiaoge_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：北京小爷
	 * 语种：中文-北京口音
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	北京小爷("zh_male_beijingxiaoye_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：京腔侃爷/Harmony
	 * 语种：中文-北京口音,美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	京腔侃爷("zh_male_jingqiangkanye_moon_bigtts"),

	/**
	 * 场景：趣味口音
	 * 音色：妹坨洁儿
	 * 语种：中文-长沙口音
	 * 支持能力：
	 * 特殊标签：豆包同款、剪映同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	妹坨洁儿("zh_female_meituojieer_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：纯真少女
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	纯真少女("ICL_zh_female_chunzhenshaonv_e588402fb8ad_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：奶气小生
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	奶气小生("ICL_zh_male_xiaonaigou_edf58cf28b8b_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：精灵向导
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	精灵向导("ICL_zh_female_jinglingxiangdao_1beb294a9e3e_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：闷油瓶小哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	闷油瓶小哥("ICL_zh_male_menyoupingxiaoge_ffed9fc2fee7_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：黯刃秦主
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	黯刃秦主("ICL_zh_male_anrenqinzhu_cd62e63dcdab_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：霸道总裁
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	霸道总裁("ICL_zh_male_badaozongcai_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：妩媚可人
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	妩媚可人("ICL_zh_female_ganli_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：邪魅御姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	邪魅御姐("ICL_zh_female_xiangliangya_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：嚣张小哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	嚣张小哥("ICL_zh_male_ms_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：油腻大叔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	油腻大叔("ICL_zh_male_you_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：孤傲公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	孤傲公子("ICL_zh_male_guaogongzi_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：胡子叔叔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	胡子叔叔("ICL_zh_male_huzi_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：性感魅惑
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	性感魅惑("ICL_zh_female_luoqing_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病弱公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病弱公子("ICL_zh_male_bingruogongzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：邪魅女王
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	邪魅女王("ICL_zh_female_bingjiao3_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：傲慢青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	傲慢青年("ICL_zh_male_aomanqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：醋精男生
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	醋精男生("ICL_zh_male_cujingnansheng_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：撒娇男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	撒娇男友("ICL_zh_male_sajiaonanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：温柔男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔男友("ICL_zh_male_wenrounanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：温顺少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温顺少年("ICL_zh_male_wenshunshaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：粘人男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	粘人男友("ICL_zh_male_naigounanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：撒娇男生
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	撒娇男生("ICL_zh_male_sajiaonansheng_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：活泼男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	活泼男友("ICL_zh_male_huoponanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：甜系男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	甜系男友("ICL_zh_male_tianxinanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：活力青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	活力青年("ICL_zh_male_huoliqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：开朗青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	开朗青年("ICL_zh_male_kailangqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：冷漠兄长
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷漠兄长("ICL_zh_male_lengmoxiongzhang_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：翩翩公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	翩翩公子("ICL_zh_male_pianpiangongzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：懵懂青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	懵懂青年("ICL_zh_male_mengdongqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：冷脸兄长
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷脸兄长("ICL_zh_male_lenglianxiongzhang_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病娇少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病娇少年("ICL_zh_male_bingjiaoshaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病娇男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病娇男友("ICL_zh_male_bingjiaonanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病弱少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病弱少年("ICL_zh_male_bingruoshaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：意气少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	意气少年("ICL_zh_male_yiqishaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：干净少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	干净少年("ICL_zh_male_ganjingshaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：冷漠男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷漠男友("ICL_zh_male_lengmonanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：精英青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	精英青年("ICL_zh_male_jingyingqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：热血少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	热血少年("ICL_zh_male_rexueshaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：清爽少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清爽少年("ICL_zh_male_qingshuangshaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：中二青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	中二青年("ICL_zh_male_zhongerqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：凌云青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	凌云青年("ICL_zh_male_lingyunqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：自负青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	自负青年("ICL_zh_male_zifuqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：不羁青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	不羁青年("ICL_zh_male_bujiqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：儒雅君子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	儒雅君子("ICL_zh_male_ruyajunzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：低音沉郁
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	低音沉郁("ICL_zh_male_diyinchenyu_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：冷脸学霸
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷脸学霸("ICL_zh_male_lenglianxueba_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：儒雅总裁
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	儒雅总裁("ICL_zh_male_ruyazongcai_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：深沉总裁
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	深沉总裁("ICL_zh_male_shenchenzongcai_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：小侯爷
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	小侯爷("ICL_zh_male_xiaohouye_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：孤高公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	孤高公子("ICL_zh_male_gugaogongzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：仗剑君子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	仗剑君子("ICL_zh_male_zhangjianjunzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：温润学者
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温润学者("ICL_zh_male_wenrunxuezhe_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：亲切青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	亲切青年("ICL_zh_male_qinqieqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：温柔学长
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔学长("ICL_zh_male_wenrouxuezhang_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：高冷总裁
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	高冷总裁("ICL_zh_male_gaolengzongcai_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：冷峻高智
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷峻高智("ICL_zh_male_lengjungaozhi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：孱弱少爷
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	孱弱少爷("ICL_zh_male_chanruoshaoye_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：自信青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	自信青年("ICL_zh_male_zixinqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：青涩青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	青涩青年("ICL_zh_male_qingseqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：学霸同桌
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	学霸同桌("ICL_zh_male_xuebatongzhuo_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：冷傲总裁
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷傲总裁("ICL_zh_male_lengaozongcai_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：元气少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	元气少年("ICL_zh_male_yuanqishaonian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：洒脱青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	洒脱青年("ICL_zh_male_satuoqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：直率青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	直率青年("ICL_zh_male_zhishuaiqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：斯文青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	斯文青年("ICL_zh_male_siwenqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：俊逸公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	俊逸公子("ICL_zh_male_junyigongzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：仗剑侠客
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	仗剑侠客("ICL_zh_male_zhangjianxiake_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：机甲智能
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	机甲智能("ICL_zh_male_jijiaozhineng_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：奶气萌娃
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：剪映同款、豆包同款
	 * 对应 2.0 音色：奶气萌娃 2.0
	 * 是否支持 MIX：是
	 */
	奶气萌娃("zh_male_naiqimengwa_mars_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：婆婆
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款、豆包同款
	 * 对应 2.0 音色：婆婆 2.0
	 * 是否支持 MIX：是
	 */
	婆婆("zh_female_popo_mars_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：高冷御姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：高冷御姐 2.0
	 * 是否支持 MIX：是
	 */
	高冷御姐("zh_female_gaolengyujie_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：傲娇霸总
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：傲娇霸总 2.0
	 * 是否支持 MIX：是
	 */
	傲娇霸总("zh_male_aojiaobazong_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：魅力女友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、剪映同款
	 * 对应 2.0 音色：魅力女友 2.0
	 * 是否支持 MIX：是
	 */
	魅力女友("zh_female_meilinvyou_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：深夜播客
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：深夜播客 2.0
	 * 是否支持 MIX：是
	 */
	深夜播客("zh_male_shenyeboke_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：柔美女友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、剪映同款
	 * 对应 2.0 音色：柔美女友 2.0
	 * 是否支持 MIX：是
	 */
	柔美女友("zh_female_sajiaonvyou_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：撒娇学妹
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、剪映同款
	 * 对应 2.0 音色：撒娇学妹 2.0
	 * 是否支持 MIX：是
	 */
	撒娇学妹("zh_female_yuanqinvyou_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：病弱少女
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病弱少女("ICL_zh_female_bingruoshaonv_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：活泼女孩
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	活泼女孩("ICL_zh_female_huoponvhai_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：东方浩然
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：东方浩然 2.0
	 * 是否支持 MIX：是
	 */
	东方浩然("zh_male_dongfanghaoran_moon_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：绿茶小哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	绿茶小哥("ICL_zh_male_lvchaxiaoge_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：娇弱萝莉
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	娇弱萝莉("ICL_zh_female_jiaoruoluoli_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：冷淡疏离
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷淡疏离("ICL_zh_male_lengdanshuli_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：憨厚敦实
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	憨厚敦实("ICL_zh_male_hanhoudunshi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：活泼刁蛮
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	活泼刁蛮("ICL_zh_female_huopodiaoman_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：固执病娇
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	固执病娇("ICL_zh_male_guzhibingjiao_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：撒娇粘人
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	撒娇粘人("ICL_zh_male_sajiaonianren_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：傲慢娇声
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	傲慢娇声("ICL_zh_female_aomanjiaosheng_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：潇洒随性
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	潇洒随性("ICL_zh_male_xiaosasuixing_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：诡异神秘
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	诡异神秘("ICL_zh_male_guiyishenmi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：儒雅才俊
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	儒雅才俊("ICL_zh_male_ruyacaijun_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：正直青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	正直青年("ICL_zh_male_zhengzhiqingnian_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：娇憨女王
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	娇憨女王("ICL_zh_female_jiaohannvwang_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病娇萌妹
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病娇萌妹("ICL_zh_female_bingjiaomengmei_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：青涩小生
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	青涩小生("ICL_zh_male_qingsenaigou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：纯真学弟
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	纯真学弟("ICL_zh_male_chunzhenxuedi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：优柔帮主
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	优柔帮主("ICL_zh_male_youroubangzhu_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：优柔公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	优柔公子("ICL_zh_male_yourougongzi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：贴心男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	贴心男友("ICL_zh_male_tiexinnanyou_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：少年将军
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	少年将军("ICL_zh_male_shaonianjiangjun_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：病娇哥哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病娇哥哥("ICL_zh_male_bingjiaogege_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：学霸男同桌
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	学霸男同桌("ICL_zh_male_xuebanantongzhuo_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：幽默叔叔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	幽默叔叔("ICL_zh_male_youmoshushu_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：假小子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	假小子("ICL_zh_female_jiaxiaozi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：温柔男同桌
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔男同桌("ICL_zh_male_wenrounantongzhuo_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：幽默大爷
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	幽默大爷("ICL_zh_male_youmodaye_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：枕边低语
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：抖音同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	枕边低语("ICL_zh_male_asmryexiu_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：神秘法师
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	神秘法师("ICL_zh_male_shenmifashi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：娇喘女声
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款
	 * 对应 2.0 音色：娇喘女声 2.0
	 * 是否支持 MIX：是
	 */
	娇喘女声("zh_female_jiaochuan_mars_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：开朗弟弟
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款
	 * 对应 2.0 音色：开朗弟弟 2.0
	 * 是否支持 MIX：是
	 */
	开朗弟弟("zh_male_livelybro_mars_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：谄媚女声
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款
	 * 对应 2.0 音色：谄媚女声 2.0
	 * 是否支持 MIX：是
	 */
	谄媚女声("zh_female_flattery_mars_bigtts"),

	/**
	 * 场景：角色扮演
	 * 音色：冷峻上司
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	冷峻上司("ICL_zh_male_lengjunshangsi_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：寡言小哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	寡言小哥("ICL_zh_male_xiaoge_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：清朗温润
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清朗温润("ICL_zh_male_renyuwangzi_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：潇洒随性
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	潇洒随性_V1("ICL_zh_male_xiaosha_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：清冷矜贵
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清冷矜贵("ICL_zh_male_liyisheng_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：沉稳优雅
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	沉稳优雅("ICL_zh_male_qinglen_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：清逸苏感
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清逸苏感("ICL_zh_male_chongqingzhanzhan_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：温柔内敛
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔内敛("ICL_zh_male_xingjiwangzi_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：低沉缱绻
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	低沉缱绻("ICL_zh_male_sigeshiye_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：蓝银草魂师
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	蓝银草魂师("ICL_zh_male_lanyingcaohunshi_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：清冷高雅
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清冷高雅("ICL_zh_female_liumengdie_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：甜美娇俏
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	甜美娇俏("ICL_zh_female_linxueying_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：柔骨魂师
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	柔骨魂师("ICL_zh_female_rouguhunshi_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：甜美活泼
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	甜美活泼("ICL_zh_female_tianmei_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：成熟温柔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	成熟温柔("ICL_zh_female_chengshu_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：贴心闺蜜
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	贴心闺蜜_V1("ICL_zh_female_xnx_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：温柔白月光
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温柔白月光_V1("ICL_zh_female_yry_v1_tob"),

	/**
	 * 场景：角色扮演
	 * 音色：高冷沉稳
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：高冷沉稳 2.0
	 * 是否支持 MIX：是
	 */
	高冷沉稳("zh_male_bv139_audiobook_ummv3_bigtts"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：醋精男友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	醋精男友("ICL_zh_male_cujingnanyou_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：风发少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	风发少年("ICL_zh_male_fengfashaonian_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：磁性男嗓
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	磁性男嗓("ICL_zh_male_cixingnansang_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：成熟总裁
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	成熟总裁("ICL_zh_male_chengshuzongcai_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：傲娇精英
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	傲娇精英("ICL_zh_male_aojiaojingying_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：傲娇公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	傲娇公子("ICL_zh_male_aojiaogongzi_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：霸道少爷
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	霸道少爷("ICL_zh_male_badaoshaoye_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：腹黑公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	腹黑公子("ICL_zh_male_fuheigongzi_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：暖心学姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	暖心学姐("ICL_zh_female_nuanxinxuejie_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：可爱女生
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	可爱女生("ICL_zh_female_keainvsheng_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：成熟姐姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	成熟姐姐("ICL_zh_female_chengshujiejie_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：病娇姐姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病娇姐姐("ICL_zh_female_bingjiaojiejie_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：妩媚御姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	妩媚御姐("ICL_zh_female_wumeiyujie_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：傲娇女友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	傲娇女友("ICL_zh_female_aojiaonvyou_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：贴心女友
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	贴心女友("ICL_zh_female_tiexinnvyou_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：性感御姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	性感御姐("ICL_zh_female_xingganyujie_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：病娇弟弟
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病娇弟弟("ICL_zh_male_bingjiaodidi_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：傲慢少爷
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款、猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	傲慢少爷("ICL_zh_male_aomanshaoye_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：傲气凌人
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	傲气凌人("ICL_zh_male_aiqilingren_tob"),

	/**
	 * 场景：角色扮演、S2S-SC
	 * 音色：病娇白莲
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：猫箱同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	病娇白莲("ICL_zh_male_bingjiaobailian_tob"),

	/**
	 * 场景：多语种
	 * 音色：Lauren
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	LAUREN("en_female_lauren_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：EnergeticMaleII
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	ENERGETIC_MALE_II("en_male_campaign_jamal_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：GothamHero
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	GOTHAM_HERO("en_male_chris_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：FlirtyFemale
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	FLIRTY_FEMALE("en_female_product_darcie_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：PeacefulFemale
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	PEACEFUL_FEMALE("en_female_emotional_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Nara
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	NARA("en_female_nara_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Bruce
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	BRUCE("en_male_bruce_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Michael
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	MICHAEL("en_male_michael_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Cartoon Chef
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	CARTOON_CHEF("ICL_en_male_cc_sha_v1_tob"),

	/**
	 * 场景：多语种
	 * 音色：Lucas
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	LUCAS("zh_male_M100_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Sophie
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	SOPHIE("zh_female_sophie_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Daisy
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	DAISY("en_female_dacey_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Owen
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	OWEN("en_male_charlie_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Luna
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	LUNA("en_female_sarah_new_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Michael
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	MICHAEL_ICL("ICL_en_male_michael_tob"),

	/**
	 * 场景：多语种
	 * 音色：Charlie
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	CHARLIE("ICL_en_female_cc_cm_v1_tob"),

	/**
	 * 场景：多语种
	 * 音色：Big Boogie
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	BIG_BOOGIE("ICL_en_male_oogie2_tob"),

	/**
	 * 场景：多语种
	 * 音色：Frosty Man
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	FROSTY_MAN("ICL_en_male_frosty1_tob"),

	/**
	 * 场景：多语种
	 * 音色：The Grinch
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	THE_GRINCH("ICL_en_male_grinch2_tob"),

	/**
	 * 场景：多语种
	 * 音色：Zayne
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	ZAYNE("ICL_en_male_zayne_tob"),

	/**
	 * 场景：多语种
	 * 音色：Jigsaw
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	JIGSAW("ICL_en_male_cc_jigsaw_tob"),

	/**
	 * 场景：多语种
	 * 音色：Chucky
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	CHUCKY("ICL_en_male_cc_chucky_tob"),

	/**
	 * 场景：多语种
	 * 音色：Clown Man
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	CLOWN_MAN("ICL_en_male_cc_penny_v1_tob"),

	/**
	 * 场景：多语种
	 * 音色：Kevin McCallister
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	KEVIN_MCCALLISTER("ICL_en_male_kevin2_tob"),

	/**
	 * 场景：多语种
	 * 音色：Xavier
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	XAVIER("ICL_en_male_xavier1_v1_tob"),

	/**
	 * 场景：多语种
	 * 音色：Noah
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	NOAH("ICL_en_male_cc_dracula_v1_tob"),

	/**
	 * 场景：多语种
	 * 音色：Adam
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	ADAM("en_male_adam_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Amanda
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	AMANDA("en_female_amanda_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Jackson
	 * 语种：美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	JACKSON("en_male_jackson_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：DelicateGirl
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	DELICATE_GIRL("en_female_daisy_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Dave
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	DAVE("en_male_dave_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Hades
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	HADES("en_male_hades_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Onez
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	ONEZ("en_female_onez_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Emily
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	EMILY("en_female_emily_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Daniel
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	DANIEL("zh_male_xudong_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Alastor
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	ALASTOR("ICL_en_male_cc_alastor_tob"),

	/**
	 * 场景：多语种
	 * 音色：Smith
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	SMITH("en_male_smith_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Anna
	 * 语种：英式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	ANNA("en_female_anna_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Ethan
	 * 语种：澳洲英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	ETHAN("ICL_en_male_aussie_v1_tob"),

	/**
	 * 场景：多语种
	 * 音色：Sarah
	 * 语种：澳洲英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	SARAH("en_female_sarah_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Dryw
	 * 语种：澳洲英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	DRYW("en_male_dryw_mars_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Diana
	 * 语种：西语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	DIANA("multi_female_maomao_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Lucía
	 * 语种：西语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	LUCIA("multi_male_M100_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Sofía
	 * 语种：西语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	SOFIA("multi_female_sophie_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：Daníel
	 * 语种：西语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	DANIEL_SPANISH("multi_male_xudong_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：ひかる（光）
	 * 语种：日语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	HIKARU("multi_zh_male_youyoujunzi_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：さとみ（智美）
	 * 语种：日语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	SATOMI("multi_female_sophie_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：まさお（正男）
	 * 语种：日语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	MASAO("multi_male_xudong_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：つき（月）
	 * 语种：日语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	TSUKI("multi_female_maomao_conversation_wvae_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：あけみ（朱美）
	 * 语种：日语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	AKEMI("multi_female_gaolengyujie_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：かずね（和音）/JavierorÁlvaro
	 * 语种：日语,西语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	KAZUNE("multi_male_jingqiangkanye_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：はるこ（晴子）/Esmeralda
	 * 语种：日语,西语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	HARUKO("multi_female_shuangkuaisisi_moon_bigtts"),

	/**
	 * 场景：多语种
	 * 音色：ひろし（広志）/Roberto
	 * 语种：日语,西语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：否
	 */
	HIROSHI("multi_male_wanqudashu_moon_bigtts"),

	/**
	 * 场景：客服场景
	 * 音色：理性圆子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	理性圆子("ICL_zh_female_lixingyuanzi_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：清甜桃桃
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清甜桃桃("ICL_zh_female_qingtiantaotao_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：清晰小雪
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清晰小雪("ICL_zh_female_qingxixiaoxue_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：清甜莓莓
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清甜莓莓("ICL_zh_female_qingtianmeimei_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：开朗婷婷
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	开朗婷婷("ICL_zh_female_kailangtingting_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：爽朗小阳
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	爽朗小阳("ICL_zh_male_shuanglangxiaoyang_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：清新波波
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	清新波波("ICL_zh_male_qingxinbobo_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：温婉珊珊
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：温婉珊珊 2.0
	 * 是否支持 MIX：是
	 */
	温婉珊珊("ICL_zh_female_wenwanshanshan_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：甜美小雨
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	甜美小雨("ICL_zh_female_tianmeixiaoyu_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：热情艾娜
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：热情艾娜 2.0
	 * 是否支持 MIX：是
	 */
	热情艾娜("ICL_zh_female_reqingaina_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：甜美小橘
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	甜美小橘("ICL_zh_female_tianmeixiaoju_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：沉稳明仔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	沉稳明仔("ICL_zh_male_chenwenmingzai_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：亲切小卓
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	亲切小卓("ICL_zh_male_qinqiexiaozhuo_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：灵动欣欣
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	灵动欣欣("ICL_zh_female_lingdongxinxin_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：乖巧可儿
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	乖巧可儿("ICL_zh_female_guaiqiaokeer_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：暖心茜茜
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	暖心茜茜("ICL_zh_female_nuanxinqianqian_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：软萌团子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	软萌团子("ICL_zh_female_ruanmengtuanzi_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：阳光洋洋
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	阳光洋洋("ICL_zh_male_yangguangyangyang_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：软萌糖糖
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	软萌糖糖("ICL_zh_female_ruanmengtangtang_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：秀丽倩倩
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	秀丽倩倩("ICL_zh_female_xiuliqianqian_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：开心小鸿
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	开心小鸿("ICL_zh_female_kaixinxiaohong_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：轻盈朵朵
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：轻盈朵朵 2.0
	 * 是否支持 MIX：是
	 */
	轻盈朵朵("ICL_zh_female_qingyingduoduo_cs_tob"),

	/**
	 * 场景：客服场景
	 * 音色：暖阳女声
	 * 语种：仅中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：暖阳女声 2.0
	 * 是否支持 MIX：是
	 */
	暖阳女声("zh_female_kefunvsheng_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：悠悠君子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：悠悠君子 2.0
	 * 是否支持 MIX：是
	 */
	悠悠君子("zh_male_M100_conversation_wvae_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：文静毛毛
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：文静毛毛 2.0
	 * 是否支持 MIX：是
	 */
	文静毛毛("zh_female_maomao_conversation_wvae_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：倾心少女
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	倾心少女("ICL_zh_female_qiuling_v1_tob"),

	/**
	 * 场景：视频配音
	 * 音色：醇厚低音
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	醇厚低音("ICL_zh_male_buyan_v1_tob"),

	/**
	 * 场景：视频配音
	 * 音色：咆哮小哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	咆哮小哥("ICL_zh_male_BV144_paoxiaoge_v1_tob"),

	/**
	 * 场景：视频配音
	 * 音色：和蔼奶奶
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	和蔼奶奶("ICL_zh_female_heainainai_tob"),

	/**
	 * 场景：视频配音
	 * 音色：邻居阿姨
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	邻居阿姨("ICL_zh_female_linjuayi_tob"),

	/**
	 * 场景：视频配音
	 * 音色：温柔小雅
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：豆包同款
	 * 对应 2.0 音色：温柔小雅 2.0
	 * 是否支持 MIX：是
	 */
	温柔小雅("zh_female_wenrouxiaoya_moon_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：天才童声
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：天才童声 2.0
	 * 是否支持 MIX：是
	 */
	天才童声("zh_male_tiancaitongsheng_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：猴哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：猴哥 2.0
	 * 是否支持 MIX：是
	 */
	猴哥("zh_male_sunwukong_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：熊二
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款、豆包同款
	 * 对应 2.0 音色：熊二 2.0
	 * 是否支持 MIX：是
	 */
	熊二("zh_male_xionger_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：佩奇猪
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款、豆包同款
	 * 对应 2.0 音色：佩奇猪 2.0
	 * 是否支持 MIX：是
	 */
	佩奇猪("zh_female_peiqi_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：武则天
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：武则天 2.0
	 * 是否支持 MIX：是
	 */
	武则天("zh_female_wuzetian_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：顾姐
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款
	 * 对应 2.0 音色：顾姐 2.0
	 * 是否支持 MIX：是
	 */
	顾姐("zh_female_gujie_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：樱桃丸子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款、豆包同款
	 * 对应 2.0 音色：樱桃丸子 2.0
	 * 是否支持 MIX：是
	 */
	樱桃丸子("zh_female_yingtaowanzi_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：广告解说
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：剪映同款
	 * 对应 2.0 音色：广告解说 2.0
	 * 是否支持 MIX：是
	 */
	广告解说("zh_male_chunhui_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：少儿故事
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：少儿故事 2.0
	 * 是否支持 MIX：是
	 */
	少儿故事("zh_female_shaoergushi_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：四郎
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款、豆包同款
	 * 对应 2.0 音色：四郎 2.0
	 * 是否支持 MIX：是
	 */
	四郎("zh_male_silang_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：俏皮女声
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：俏皮女声 2.0
	 * 是否支持 MIX：是
	 */
	俏皮女声("zh_female_qiaopinvsheng_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：懒音绵宝
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：懒音绵宝 2.0
	 * 是否支持 MIX：是
	 */
	懒音绵宝("zh_male_lanxiaoyang_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：亮嗓萌仔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：亮嗓萌仔 2.0
	 * 是否支持 MIX：是
	 */
	亮嗓萌仔("zh_male_dongmanhaimian_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：磁性解说男声/Morgan
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：抖音同款、剪映同款
	 * 对应 2.0 音色：磁性解说男声/Morgan 2.0
	 * 是否支持 MIX：是
	 */
	磁性解说男声("zh_male_jieshuonansheng_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：鸡汤妹妹/Hope
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：抖音同款、豆包同款
	 * 对应 2.0 音色：鸡汤妹妹/Hope 2.0
	 * 是否支持 MIX：是
	 */
	鸡汤妹妹("zh_female_jitangmeimei_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：贴心女声/Candy
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：贴心女声/Candy 2.0
	 * 是否支持 MIX：是
	 */
	贴心女声_CANDY("zh_female_tiexinnvsheng_mars_bigtts"),

	/**
	 * 场景：视频配音
	 * 音色：萌丫头/Cutey
	 * 语种：中文,美式英语
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：萌丫头/Cutey 2.0
	 * 是否支持 MIX：是
	 */
	萌丫头("zh_female_mengyatou_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：内敛才俊
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	内敛才俊("ICL_zh_male_neiliancaijun_e991be511569_tob"),

	/**
	 * 场景：有声阅读
	 * 音色：温暖少年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	温暖少年("ICL_zh_male_yangyang_v1_tob"),

	/**
	 * 场景：有声阅读
	 * 音色：儒雅公子
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：
	 * 是否支持 MIX：是
	 */
	儒雅公子("ICL_zh_male_flc_v1_tob"),

	/**
	 * 场景：有声阅读
	 * 音色：悬疑解说
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：剪映同款、抖音同款、豆包同款
	 * 对应 2.0 音色：悬疑解说 2.0
	 * 是否支持 MIX：是
	 */
	悬疑解说("zh_male_changtianyi_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：儒雅青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：番茄小说同款、豆包同款、剪映同款
	 * 对应 2.0 音色：儒雅青年 2.0
	 * 是否支持 MIX：是
	 */
	儒雅青年("zh_male_ruyaqingnian_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：霸气青叔
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：番茄小说同款、豆包同款、剪映同款
	 * 对应 2.0 音色：霸气青叔 2.0
	 * 是否支持 MIX：是
	 */
	霸气青叔("zh_male_baqiqingshu_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：擎苍
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：番茄小说同款、剪映同款、豆包同款、抖音同款
	 * 对应 2.0 音色：擎苍 2.0
	 * 是否支持 MIX：是
	 */
	擎苍("zh_male_qingcang_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：活力小哥
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：活力小哥 2.0
	 * 是否支持 MIX：是
	 */
	活力小哥("zh_male_yangguangqingnian_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：古风少御
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：古风少御 2.0
	 * 是否支持 MIX：是
	 */
	古风少御("zh_female_gufengshaoyu_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：温柔淑女
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：番茄小说同款、豆包同款、剪映同款
	 * 对应 2.0 音色：温柔淑女 2.0
	 * 是否支持 MIX：是
	 */
	温柔淑女("zh_female_wenroushunv_mars_bigtts"),

	/**
	 * 场景：有声阅读
	 * 音色：反卷青年
	 * 语种：中文
	 * 支持能力：
	 * 特殊标签：
	 * 对应 2.0 音色：反卷青年 2.0
	 * 是否支持 MIX：是
	 */
	反卷青年("zh_male_fanjuanqingnian_mars_bigtts"),

	;

	private final String code;

	TtsSpeaker(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return this.code;
	}

	@JsonCreator
	public static TtsSpeaker fromCode(@Nullable String code) {
		if (StringUtils.isBlank(code)) return TtsSpeaker.UNKNOWN;
		for (TtsSpeaker value : values()) {
			if (value.code.equals(code)) return value;
		}
		return TtsSpeaker.UNKNOWN;
	}
}
