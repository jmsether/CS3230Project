package com.sethy;

import javax.swing.*;
import java.awt.*;

public class MahJongBoard extends JLayeredPane {


    public MahJongModel tileManager;

    private boolean music = true;
    private float volume = -25.0f;
    private AudioManager background = new AudioManager(getClass().getResource("audio/background.wav"));


    private void startMusic(){

        background.setVolume(volume);
        background.play();
    }

    private void stopMusic(){
        background.stop();
    }

    public void toggleMusic(){
        music = !music;
        if(music){
            startMusic();
        }else{
            stopMusic();
        }
    }

    public String musicStatus(){
        if(music){
            return "on";
        }else{
            return "off";
        }
    }


    public MahJongBoard(){
        setOpaque(true);
        setBackground(Color.blue);

        if(music){
            startMusic();
        }

        //add(new MahJongTest.TestPanel());


        setVisible(true);


        tileManager = new MahJongModel(this);




    }

    public void newGame(){
        tileManager.newGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = new ImageIcon(getClass().getResource("images/"+"dragon_bg"+".png")).getImage();
        super.paintComponent(g);
        g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), null);
    }


}
