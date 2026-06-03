package cc.qianlang.aiclassroom.proxy.zai.v4.chat.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 语音模型返回的音频内容。
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public class Audio {

	/**
	 * 音频内容 ID，可用于多轮对话输入
	 */
	@Nullable
	private String id;

	/**
	 * 音频内容 base64 编码
	 */
	@Nullable
	private String data;

	/**
	 * 音频内容过期时间
	 */
	@Nullable
	private String expiresAt;
}
