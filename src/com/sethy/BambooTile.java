package com.sethy;

import java.awt.*;
import java.util.Arrays;

public class BambooTile extends RankTile{
    public BambooTile(int rank){
        super(rank);

    }

    private static final int[] x = {0,4,4,3,3,4,4,3,3,4,4,0,0,1,1,0,0,1,1,0};
    private static final int[] y = {0,0,1,2,3,4,5,6,7,8,9,9,8,7,6,5,4,3,2,1};


    protected int[] multiArray(int[] x, int y){
        int[] xCopy = Arrays.copyOf(x, x.length);
        for(int i = 0; i < x.length; i++){
            xCopy[i] *= y;
        }
        return xCopy;
    }

    @Override
    protected void drawSpot(Graphics2D g2, int x, int y) {
        g2.fillPolygon(modifyArray(multiArray(this.x,getTileWidth()/28),x -getTileWidth()/20),modifyArray(multiArray(this.y,getTileWidth()/28),y -getTileWidth()/8),this.x.length);
        g2.setColor(Color.gray);
        g2.fillRect(x ,y -getTileWidth()/10, getTileWidth()/50,getTileHeight()/15);
        g2.fillRect(x ,y, getTileWidth()/50,getTileHeight()/15);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // See note below
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        switch(rank){
            case 1:
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2);
                break;
            case 2:
                g2.setColor(Color.blue);
                drawSpot(g2, 2*getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 4);
                g2.setColor(Color.green);
                drawSpot(g2, 2*getTileWidth() / 4 + getTileWidth()/5, 3*getTileHeight() / 4);
                break;
            case 3:
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2 - (getTileWidth() / 12 * 2));

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 + (getTileWidth() / 12 * 3), getTileHeight() / 2 + (getTileWidth() / 12 * 2));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 - (getTileWidth() / 12 * 3), getTileHeight() / 2 + (getTileWidth() / 12 * 2));

                break;
            case 5:
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2);
                // Fall thru to next case
            case 4:
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 4);
                g2.setColor(Color.blue);
                drawSpot(g2, 3 * getTileWidth() / 4+ getTileWidth()/5, 3 * getTileHeight() / 4);
                g2.setColor(Color.green);
                drawSpot(g2, 3 * getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 4);
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth()/5, 3 * getTileHeight() / 4);
                break;
            case 6:
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2 + (getTileWidth() / 12 * 2));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2 - (getTileWidth() / 12 * 2));

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 + (getTileWidth() / 12 * 3), getTileHeight() / 2 - (getTileWidth() / 12 * 2));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 - (getTileWidth() / 12 * 3), getTileHeight() / 2 - (getTileWidth() / 12 * 2));

                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 + (getTileWidth() / 12 * 3), getTileHeight() / 2 + (getTileWidth() / 12 * 2));
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 - (getTileWidth() / 12 * 3), getTileHeight() / 2 + (getTileWidth() / 12 * 2));

                break;
            case 7:
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2);

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*4), getTileHeight() / 2);
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*4), getTileHeight() / 2);

                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2 -(getTileWidth()/12*4));
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2 +(getTileWidth()/12*4));

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*4), getTileHeight() / 2 +(getTileWidth()/12*4));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*4), getTileHeight() / 2 +(getTileWidth()/12*4));
                break;
            case 8:

                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*2), getTileHeight() / 2);
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*2), getTileHeight() / 2);

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2 -(getTileWidth()/12*4));
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2 +(getTileWidth()/12*4));

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*4), getTileHeight() / 2 -(getTileWidth()/12*4));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*4), getTileHeight() / 2 -(getTileWidth()/12*4));

                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*4), getTileHeight() / 2 +(getTileWidth()/12*4));
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*4), getTileHeight() / 2 +(getTileWidth()/12*4));
                break;
            case 9:
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2);


                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*4), getTileHeight() / 2);
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*4), getTileHeight() / 2);

                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2 -(getTileWidth()/12*4));
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2 +(getTileWidth()/12*4));

                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*4), getTileHeight() / 2 -(getTileWidth()/12*4));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*4), getTileHeight() / 2 -(getTileWidth()/12*4));

                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5-(getTileWidth()/12*4), getTileHeight() / 2 +(getTileWidth()/12*4));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5+(getTileWidth()/12*4), getTileHeight() / 2 +(getTileWidth()/12*4));
                break;

        }

    }

    @Override
    public String toString() {
        return "Bamboo " + rank;
    }
}
