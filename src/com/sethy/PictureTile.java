package com.sethy;

import javax.swing.*;
import java.awt.*;

public abstract class PictureTile extends Tile{
    private String name;

    private Image image;

    private double scaleFactor = .8;

    public PictureTile(String name){
        this.name = name;
        loadTileImg(name);
        setToolTipText(name);
    }

    public void loadTileImg(String name){
        image = new ImageIcon(getClass().getResource("images/"+name+".png")).getImage();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //System.out.println("heres an image: " + name);
        g.drawImage(image,getTileWidth()/5+getTileWidth()/10,getTileHeight()/10,(int)(getTileWidth()*scaleFactor),(int)(getTileHeight()*scaleFactor),this);
    }


    @Override
    public String toString() {
        return name;
    }
}
