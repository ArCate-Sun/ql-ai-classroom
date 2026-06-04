package cc.qianlang.aiclassroom.proxy.volcano.v3.tts.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * 火山引擎 TTS SSE 响应数据块基类（密封类）。
 * <p>
 * 根据 {@link TtsEventType} 区分子类型：
 * <ul>
 *   <li>{@link TtsResponseChunk} — 音频数据帧（event=352）</li>
 *   <li>{@link TtsSentenceEndChunk} — 句子结束帧（event=351）</li>
 *   <li>{@link TtsSessionFinishChunk} — 会话结束帧（event=152）</li>
 *   <li>{@link TtsSessionCancelChunk} — 会话取消帧（event=151）</li>
 *   <li>{@link TtsSessionFailedChunk} — 会话失败帧（event=153）</li>
 * </ul>
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NullMarked
public abstract sealed class TtsChunk
		permits TtsResponseChunk, TtsSentenceEndChunk, TtsSessionFinishChunk,
		TtsSessionCancelChunk, TtsSessionFailedChunk {

	/**
	 * 状态码，{@code 0} 表示成功，{@code 20000000} 表示会话正常结束。
	 * 错误码见
	 * <a href="https://www.volcengine.com/docs/6561/1598757">错误码文档</a>。
	 */
	private int code;

	/**
	 * 状态描述，成功时通常为空字符串或 {@code "OK"}。
	 */
	@Nullable
	private String message;

}
