package cc.qianlang.aiclassroom.common;

import org.jspecify.annotations.NullMarked;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * 音频播放工具类，支持 WAV 和原始 PCM 格式。
 *
 * @author 阿猫_ACat
 * @version 0.1
 */
@NullMarked
public class AudioPlayer {

	private AudioPlayer() {
	}

	/**
	 * 播放 WAV 音频数据，音频格式由文件头自动解析。
	 *
	 * @param wavData WAV 格式的音频字节数组
	 * @throws UnsupportedAudioFileException 不支持的音频格式
	 * @throws LineUnavailableException      音频设备不可用
	 * @throws IOException                   读取音频数据失败
	 */
	public static void playWav(byte[] wavData)
			throws UnsupportedAudioFileException, LineUnavailableException, IOException {

		try (AudioInputStream ais = AudioSystem.getAudioInputStream(new ByteArrayInputStream(wavData))) {
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
				line.open(format);
				line.start();
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = ais.read(buffer)) != -1) {
					line.write(buffer, 0, bytesRead);
				}
				line.drain();
			}
		}
	}

	/**
	 * 播放原始 PCM 音频数据（有符号 16 位小端，单声道，24000 Hz）。
	 *
	 * @param pcmData 原始 PCM 字节数组
	 * @throws LineUnavailableException 音频设备不可用
	 */
	public static void playPcm(byte[] pcmData) throws LineUnavailableException {
		AudioFormat format = new AudioFormat(24000, 16, 1, true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
			line.open(format);
			line.start();
			line.write(pcmData, 0, pcmData.length);
			line.drain();
		}
	}

}
