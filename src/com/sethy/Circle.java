package com.sethy;

import java.awt.*;

public class Circle {
    protected int x;
    protected int y;

    protected int width;

    protected Color color;

    public Circle(int x, int y, int width, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.color = color;
    }

    protected void draw(Graphics g2) {
        g2.setColor(color);
        g2.fillOval(x - 2 / 2 -width/10, y - 2 / 2 -width/10, width/5, width/5);
        g2.setColor(Color.gray);
        g2.fillOval(x - 2 / 2 -width/20, y - 2 / 2 -width/20, width/10, width/10);
    }

}
