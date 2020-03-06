package com.audio.reader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MP3Player extends AudioPlayer {
	private Player player;
	private int currentPosition;

	// constructor that takes the name of an MP3 file
	public MP3Player(String filePath) {
		super(filePath);
	}

	public void close() {
		if (player != null)
			player.close();
	}

	// play the MP3 file to the sound card
	public void play() {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
			player.play();
		} catch (Exception e) {
			System.out.println("Problem playing file " + filePath);
			System.out.println(e);
		}
	}

	public void pause() {
		currentPosition = player.getPosition();
		player.close();
	}

	public void resume() throws JavaLayerException {
		player.play(currentPosition);
	}

	public void restart() throws JavaLayerException {
		player.close();
		player.play();
	}

	public void stop() {
		player.close();
	}

	public void jump(long newPosition) throws JavaLayerException {
		player.close();
		player.play((int) newPosition);
	}

	// test client
	public static void main(String[] args) {
		String filename = "C:\\Users\\akoshy\\Desktop\\Beyblade V Force - Underdog.mp3";
		MP3Player mp3 = new MP3Player(filename);
		mp3.play();

	}

}
