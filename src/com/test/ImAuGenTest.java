package com.test;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.encryption.EmbedExtractUtil;
import com.file.reader.AudioCodec;
import com.file.reader.Codec;
import com.file.reader.ImageCodec;

import javazoom.jl.decoder.JavaLayerException;

public class ImAuGenTest {

	public static void main(String args[]) {
		testImage();
		
	}
	
	private static void testAudio() {

		Codec audioCodec = new AudioCodec();
		// Codec imageCodec = new ImageCodec();
		String audioSource = "C:/Users/akoshy/Desktop/Beyblade V Force - Underdog.mp3",	audioDest = "C:/Users/akoshy/Desktop/newUnderdog.wav";
		String message = "Hello World";
		try {
			byte[] sourceFile = audioCodec.readFile(audioSource);
			byte len[] = EmbedExtractUtil.bitConversion(sourceFile.length);
			EmbedExtractUtil.encodeText(sourceFile, len, 0);
			EmbedExtractUtil.encodeText(sourceFile, message.getBytes(), 32);
			audioCodec.writeFile(audioSource, audioDest, sourceFile);
			byte[] destFile = audioCodec.readFile(audioDest);
			System.out.println(new String(EmbedExtractUtil.decodeText(destFile)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private static void testImage() {
		Codec imageCodec = new ImageCodec();
		String message = "Hello World";
		String imageSource = "C:/Users/akoshy/Desktop/tie.jpg",	imageDest = "C:/Users/akoshy/Desktop/newImage.jpg";
		try {
			byte[] sourceFile = imageCodec.readFile(imageSource);
			byte len[] = EmbedExtractUtil.bitConversion(sourceFile.length);
			EmbedExtractUtil.encodeText(sourceFile, len, 0);
			EmbedExtractUtil.encodeText(sourceFile, message.getBytes(), 32);
			imageCodec.writeFile(imageSource, imageDest, sourceFile);
			byte[] destFile = imageCodec.readFile(imageDest);
			System.out.println(new String(EmbedExtractUtil.decodeText(destFile)));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
