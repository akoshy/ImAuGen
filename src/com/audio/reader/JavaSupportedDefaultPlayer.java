package com.audio.reader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.media.MediaPlayer;

public class JavaSupportedDefaultPlayer extends AudioPlayer {
	Long currentFrame;
	Clip clip;
	String status;
	static boolean isWav = true;
	MediaPlayer mediaPlayer;
	AudioInputStream audioInputStream;

	public JavaSupportedDefaultPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		// create AudioInputStream object
		super(filePath);

		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
		clip = AudioSystem.getClip();
		// open audioInputStream to the clip
		clip.open(audioInputStream);
	}

	private void gotoChoice(int c) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		switch (c) {
		case 1:
			pause();
			break;
		case 2:
			resume();
			break;
		case 3:
			restart();
			break;
		case 4:
			stop();
			break;
		case 5:
			System.out.println("Enter time (" + 0 + ", " + clip.getMicrosecondLength() + ")");
			Scanner sc = new Scanner(System.in);
			long c1 = sc.nextLong();
			jump(c1);
			break;

		}

	}

	// Method to play the audio
	public void play() {
		// start the clip
		if (isWav)
			clip.start();
		else
			mediaPlayer.play();

		status = "play";
	}

	// Method to pause the audio
	public void pause() {
		if (status.equals("paused")) {
			System.out.println("audio is already paused");
			return;
		}
		this.currentFrame = this.clip.getMicrosecondPosition();
		clip.stop();
		status = "paused";
	}

	// Method to resume the audio
	public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (status.equals("play")) {
			System.out.println("Audio is already " + "being played");
			return;
		}
		clip.close();
		resetAudioStream();
		clip.setMicrosecondPosition(currentFrame);
		this.play();
	}

	// Method to restart the audio
	public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		clip.stop();
		clip.close();
		resetAudioStream();
		currentFrame = 0L;
		clip.setMicrosecondPosition(0);
		this.play();
	}

	// Method to stop the audio
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		currentFrame = 0L;
		clip.stop();
		clip.close();
	}

	// Method to jump over a specific part
	public void jump(long c) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (c > 0 && c < clip.getMicrosecondLength()) {
			clip.stop();
			clip.close();
			resetAudioStream();
			currentFrame = c;
			clip.setMicrosecondPosition(c);
			this.play();
		}
	}

	// Method to reset audio stream
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		audioInputStream = AudioSystem.getAudioInputStream(new File(this.filePath).getAbsoluteFile());
		clip.open(audioInputStream);
	}

	public static void main(String[] args) {
		try {
			String filePath = "C:\\Users\\akoshy\\Desktop\\Underdog.wav";
			JavaSupportedDefaultPlayer audioPlayer = new JavaSupportedDefaultPlayer(filePath);

			audioPlayer.play();
			Scanner sc = new Scanner(System.in);

			while (true) {
				System.out.println("1. pause");
				System.out.println("2. resume");
				System.out.println("3. restart");
				System.out.println("4. stop");
				System.out.println("5. Jump to specific time");
				int c = sc.nextInt();
				audioPlayer.gotoChoice(c);
				if (c == 4)
					break;
			}
			sc.close();
		}

		catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();

		}
	}

}
