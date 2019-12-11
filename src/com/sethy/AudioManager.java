package com.sethy;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class AudioManager {

    Clip clip;
    FloatControl  gainControl;
    public AudioManager(URL file){
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioIn);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);

    }

    public void play(){
        clip.start();
    }

    public void rePlay(){
        clip.setMicrosecondPosition(0);
        play();
    }

    public void stop(){
        clip.stop();
    }

    public void setVolume(float volume){
        gainControl.setValue(volume);
    }

}
