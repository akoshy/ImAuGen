package com.file.reader;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;

public interface Codec {
	public byte[] readFile(String filePath) throws JavaLayerException, IOException;
	public void writeFile(String sourcePath, String destinationPath, byte[] imageBytes) throws IOException, UnsupportedAudioFileException;
}
