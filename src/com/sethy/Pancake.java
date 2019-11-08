package com.sethy;

import java.awt.*;

public class Pancake extends Circle {

    private Color mainColor = Color.green;


    public Pancake(int x, int y, int width) {
        super(x, y, width, Color.red);


    }

    protected void drawSpot(Graphics2D g2, int x, int y) {
        g2.fillOval(x - 2 / 2, y - 2 / 2, 4, 4);
    }

    @Override
    public void draw(Graphics g){
        g.setColor(mainColor);
        g.fillOval(x-width/2+width/20, y-width/2+width/20, width-width/10, width-width/10);

        g.setColor(Color.yellow);
        for(int i = 0; i < 8;i++){
            drawSpot((Graphics2D)g,x,y -width/3);
            drawSpot((Graphics2D)g,x,y +width/3);
            ((Graphics2D) g).rotate(Math.toRadians(45),x,y);
        }


        super.draw(g);
    }
}
