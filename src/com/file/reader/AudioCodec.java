package com.file.reader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;

public class AudioCodec implements Codec {

	public byte[] readFile(String audioFilePath) throws JavaLayerException, IOException {
		AudioInputStream audioInputStream = null, tempInputStream = null;
		byte[] audioBytes = null;

		File audioFile = new File(audioFilePath);
		try {
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			if (audioFilePath.indexOf("mp3") != -1) {
				AudioFormat convertFormat = getConvertFormat(audioInputStream);
				tempInputStream = AudioSystem.getAudioInputStream(convertFormat, audioInputStream);
				//audioInputStream = AudioSystem.getAudioInputStream(new AudioFormat(Encoding.PCM_SIGNED, 16000, 16, 1, 2, 16000, true), audioInputStream);
				audioInputStream = tempInputStream;

			}
			int bytesPerFrame = audioInputStream.getFormat().getFrameSize();
			// Set an arbitrary buffer size of 1024 frames.
			int numBytes = 154600 * bytesPerFrame;
			audioBytes = new byte[numBytes];
			try {
				audioInputStream.read(audioBytes);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audioBytes;
	}

	public void writeFile(String sourcePath, String destinationPath, byte[] audioBytes)	throws UnsupportedAudioFileException, IOException {
		File fileOut = new File(destinationPath);
		ByteArrayInputStream byteIS = new ByteArrayInputStream(audioBytes);
		AudioInputStream sourceStream = AudioSystem.getAudioInputStream(new File(sourcePath));
		AudioFormat convertFormat = getConvertFormat(sourceStream);
		AudioInputStream convertedStream = new AudioInputStream(byteIS, convertFormat, sourceStream.getFrameLength());
		if (AudioSystem.isFileTypeSupported(AudioFileFormat.Type.WAVE, convertedStream)) {
			try {
				AudioSystem.write(convertedStream, AudioFileFormat.Type.WAVE, fileOut);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private AudioFormat getConvertFormat(AudioInputStream sourceStream) {
		return new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,	sourceStream.getFormat().getSampleRate(), 16, sourceStream.getFormat().getChannels(),
				sourceStream.getFormat().getChannels() * 2, sourceStream.getFormat().getSampleRate(), false);
	}
	/*
	 * public static void main(String args[]) { AudioCodec ac = new AudioCodec();
	 * try { ac.readFile("C:/Users/akoshy/Desktop/Beyblade V Force - Underdog.mp3");
	 * } catch (JavaLayerException | IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } }
	 */
}
