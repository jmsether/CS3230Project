package com.sethy;

import java.awt.*;

public class CircleTile extends RankTile {

    public CircleTile(int rank){
        super(rank);
    }

    @Override
    protected void drawSpot(Graphics2D g2, int x, int y) {

        g2.fillOval(x - 2 / 2 -getTileWidth()/10, y - 2 / 2 -getTileWidth()/10, getTileWidth()/5, getTileHeight()/5);
        g2.setColor(Color.gray);
        g2.fillOval(x - 2 / 2 -getTileWidth()/20, y - 2 / 2 -getTileWidth()/20, getTileWidth()/10, getTileHeight()/10);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; // See note below
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        switch(rank) {
            case 1:
                new Pancake(getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2,getTileWidth()).draw(g);
                break;
            case 2:
                //TODO
                //Get this mess cleaned up :/
                g2.setColor(Color.green);
                drawSpot(g2, 2 * getTileWidth() / 4 + getTileWidth() / 5, getTileHeight() / 4);
                g2.setColor(Color.red);
                drawSpot(g2, 2 * getTileWidth() / 4 + getTileWidth() / 5, 3 * getTileHeight() / 4);
                break;
            case 3:
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth()/5, getTileHeight() / 2);
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 4);
                g2.setColor(Color.green);
                drawSpot(g2, 3*getTileWidth() / 4 + getTileWidth()/5, 3 * getTileHeight() / 4);

                break;
            case 5:
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2);
                // Fall thru to next case
            case 4:
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth() / 5, getTileHeight() / 4);
                g2.setColor(Color.blue);
                drawSpot(g2, 3 * getTileWidth() / 4 + getTileWidth() / 5, 3 * getTileHeight() / 4);
                g2.setColor(Color.green);
                drawSpot(g2, 3 * getTileWidth() / 4 + getTileWidth() / 5, getTileHeight() / 4);
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth() / 5, 3 * getTileHeight() / 4);
                break;
            case 6:
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 4);
                g2.setColor(Color.red);
                drawSpot(g2, 3 * getTileWidth() / 4 + getTileWidth()/5, 3 * getTileHeight() / 4);
                g2.setColor(Color.green);
                drawSpot(g2, 3 * getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 4);
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth()/5, 3 * getTileHeight() / 4);
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 2);
                g2.setColor(Color.red);
                drawSpot(g2, 3 * getTileWidth() / 4 + getTileWidth()/5, getTileHeight() / 2);
                break;
            case 7:
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 4 + getTileWidth() / 5, getTileHeight() / 5);
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 5 + (getTileHeight() / 20) * 2);
                g2.setColor(Color.green);
                drawSpot(g2, 3 * getTileWidth() / 4 + getTileWidth() / 5, getTileHeight() / 5 + (getTileHeight() / 20) * 4);

                g2.setColor(Color.red);
                drawSpot(g2, 3 * getTileWidth() / 5 + getTileWidth() / 3 - getTileWidth() / 20, getTileHeight() / 2 + getTileHeight() / 10);
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 5 + getTileWidth() / 4 + getTileWidth() / 20, getTileHeight() / 2 + getTileHeight() / 10);
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 5 + getTileWidth() / 4 + getTileWidth() / 20, 3 * getTileHeight() / 4 + getTileHeight() / 10);
                g2.setColor(Color.red);
                drawSpot(g2, 3 * getTileWidth() / 5 + getTileWidth() / 3 - getTileWidth() / 20, 3 * getTileHeight() / 4 + getTileHeight() / 10);
                break;
            case 8:
                g2.setColor(Color.blue);
                drawSpot(g2, 3 * getTileWidth() / 5 + getTileWidth() / 3 - getTileWidth() / 20, getTileHeight() / 2 - getTileHeight() / 20 * 7 - getTileHeight() / 50);
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 5 + getTileWidth() / 4 + getTileWidth() / 20, getTileHeight() / 2 - getTileHeight() / 20 * 7 - getTileHeight() / 50);
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 5 + getTileWidth() / 4 + getTileWidth() / 20, getTileHeight() / 2 - getTileHeight() / 20 * 2 - getTileHeight() / 50);
                g2.setColor(Color.blue);
                drawSpot(g2, 3 * getTileWidth() / 5 + getTileWidth() / 3 - getTileWidth() / 20, getTileHeight() / 2 - getTileHeight() / 20 * 2 - getTileHeight() / 50);

                g2.setColor(Color.blue);
                drawSpot(g2, 3 * getTileWidth() / 5 + getTileWidth() / 3 - getTileWidth() / 20, getTileHeight() / 2 + getTileHeight() / 20 * 3 - getTileHeight() / 50);
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 5 + getTileWidth() / 4 + getTileWidth() / 20, getTileHeight() / 2 + getTileHeight() / 20 * 3 - getTileHeight() / 50);
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 5 + getTileWidth() / 4 + getTileWidth() / 20, getTileHeight() / 2 + getTileHeight() / 20 * 8 - getTileHeight() / 50);
                g2.setColor(Color.blue);
                drawSpot(g2, 3 * getTileWidth() / 5 + getTileWidth() / 3 - getTileWidth() / 20, getTileHeight() / 2 + getTileHeight() / 20 * 8 - getTileHeight() / 50);
                break;
            case 9:
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2);

                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 - (getTileWidth() / 12 * 4), getTileHeight() / 2);
                g2.setColor(Color.red);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 + (getTileWidth() / 12 * 4), getTileHeight() / 2);

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2 - (getTileWidth() / 12 * 4));
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5, getTileHeight() / 2 + (getTileWidth() / 12 * 4));

                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 - (getTileWidth() / 12 * 4), getTileHeight() / 2 - (getTileWidth() / 12 * 4));
                g2.setColor(Color.green);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 + (getTileWidth() / 12 * 4), getTileHeight() / 2 - (getTileWidth() / 12 * 4));

                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 - (getTileWidth() / 12 * 4), getTileHeight() / 2 + (getTileWidth() / 12 * 4));
                g2.setColor(Color.blue);
                drawSpot(g2, getTileWidth() / 2 + getTileWidth() / 5 + (getTileWidth() / 12 * 4), getTileHeight() / 2 + (getTileWidth() / 12 * 4));
                break;
            }
        }

    @Override
    public String toString() {
        return "Circle " + rank;
    }

    @Override
    public boolean matches(Tile other){
        if(this == other){
            return true;
        }
        if(this == null){
            return false;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }
        return this.rank == ((CircleTile)other).rank;
    }
}
