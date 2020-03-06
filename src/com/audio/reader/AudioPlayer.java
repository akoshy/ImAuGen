package com.audio.reader;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;

public abstract class AudioPlayer {

    public String filePath;
    
    public AudioPlayer(String filePath) {
    	this.filePath = filePath;
    }
    
    public abstract void play();
    
    public abstract void pause();
    
    public abstract void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException;
    
    public abstract void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException, JavaLayerException;
    
    public abstract void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    
    public abstract void jump(long newPosition) throws UnsupportedAudioFileException, IOException, LineUnavailableException, JavaLayerException;
}
