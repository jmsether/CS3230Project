package com.sethy;

import java.awt.*;

public class WhiteDragonTile extends Tile{

    String name = "White Dragon";

    public WhiteDragonTile(){
        setToolTipText(name);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // See note below
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.blue);
        g2.fillRect(getTileWidth() ,getTileHeight()-getTileHeight()/10*3, getTileWidth()/10,getTileHeight()/5);
        g2.fillRect(0 +getTileWidth()/10*3,getTileHeight()-getTileHeight()/10*3, getTileWidth()/10,getTileHeight()/5);

        g2.setColor(Color.darkGray);
        g2.fillRect(getTileWidth() ,getTileHeight()-getTileHeight()/10*5, getTileWidth()/10,getTileHeight()/5);
        g2.fillRect(0 +getTileWidth()/10*3,getTileHeight()-getTileHeight()/10*5, getTileWidth()/10,getTileHeight()/5);

        g2.setColor(Color.blue);
        g2.fillRect(getTileWidth() ,getTileHeight()-getTileHeight()/10*7, getTileWidth()/10,getTileHeight()/5);
        g2.fillRect(0 +getTileWidth()/10*3,getTileHeight()-getTileHeight()/10*7, getTileWidth()/10,getTileHeight()/5);

        g2.setColor(Color.darkGray);
        g2.fillRect(getTileWidth() ,getTileHeight()-getTileHeight()/10*9, getTileWidth()/10,getTileHeight()/5);
        g2.fillRect(0 +getTileWidth()/10*3,getTileHeight()-getTileHeight()/10*9, getTileWidth()/10,getTileHeight()/5);

        g2.setColor(Color.blue);
        g2.fillRect(getTileWidth()-getTileWidth()/10*2 ,getTileHeight()-getTileHeight()/10*9, getTileWidth()/5,getTileHeight()/10);
        g2.fillRect(0 +getTileWidth()/10*4,getTileHeight()-getTileHeight()/10*9, getTileWidth()/5,getTileHeight()/10);

        g2.setColor(Color.darkGray);
        g2.fillRect(getTileWidth()-getTileWidth()/10*4 ,getTileHeight()-getTileHeight()/10*9, getTileWidth()/5,getTileHeight()/10);


        g2.fillRect(getTileWidth()-getTileWidth()/10*2 ,getTileHeight()-getTileHeight()/10*2, getTileWidth()/5,getTileHeight()/10);
        g2.fillRect(0 +getTileWidth()/10*4,getTileHeight()-getTileHeight()/10*2, getTileWidth()/5,getTileHeight()/10);

        g2.setColor(Color.blue);
        g2.fillRect(getTileWidth()-getTileWidth()/10*4 ,getTileHeight()-getTileHeight()/10*2, getTileWidth()/5,getTileHeight()/10);


    }

    @Override
    public String toString() {
        return name;
    }
}
