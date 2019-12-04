package com.sethy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.http.WebSocket;
import java.util.Arrays;

public class Tile extends JPanel {
    private static  int RECT_WIDTH = 80;
    private static  int RECT_HEIGHT = RECT_WIDTH;
    private static  int RECT_X = RECT_WIDTH/5;
    private static  int RECT_Y = RECT_X;
    private static  int offset = 0;
    private static Dimension      SIZE;
    private static Polygon        SIDE;
    private static Polygon       SIDE2;
    private static Polygon      BOTTOM;
    private static Polygon     BOTTOM2;
    private static Rectangle      FACE;
    private static GradientPaint GRAD0;
    private static GradientPaint GRAD1;
    private static GradientPaint GRAD2;

    private boolean render = true;

    static protected int[] modifyArray(int[]x, int y){
        int[] xCopy = Arrays.copyOf(x, x.length);
        for(int i = 0; i< xCopy.length; i++){
            xCopy[i] += y;
        }

        return xCopy;
    }


    public void resize(int size){
        RECT_WIDTH = size;
        RECT_HEIGHT = RECT_WIDTH;
        RECT_X = RECT_WIDTH/5;
        RECT_Y = RECT_X;
        System.out.println("resized to: "+size);
        iSize();
        getPreferredSize();
        repaint();

    }


    public void setSize(){

    }

    public static Tile newInstance(Tile tile){
        return new Tile(tile);
    }


    public Tile(Tile tile){
        new Tile();
        setOpaque(false);
        iSize();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                //resize(e.getComponent().getWidth());
            }
        });
        addMouseListener(new MouseAdapter() {
            private Color background;

            @Override
            public void mousePressed(MouseEvent e) {
                background = getBackground();
                setBackground(Color.RED);
                repaint();
                System.out.println("Ping");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(background);
            }
        });
    }

    public Tile(){
        setOpaque(false);
        iSize();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                //resize(e.getComponent().getWidth());
            }
        });
        addMouseListener(new MouseAdapter() {
            private Color background;

            @Override
            public void mousePressed(MouseEvent e) {
                background = getBackground();
                setBackground(Color.RED);
                setVisible(false);
                repaint();
                //System.out.println("Ping");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(background);
            }
        });
    }

    private void iSize(){
        SIZE = new Dimension(120, 120);
        setSize(SIZE);
        int[] x = { RECT_WIDTH/10, RECT_WIDTH/5, RECT_WIDTH/5, RECT_WIDTH/10 };
        int[] y = { RECT_HEIGHT/10, 0, RECT_HEIGHT, (int)(RECT_WIDTH*1.1)};
        int[] x2 = {RECT_WIDTH/10, RECT_WIDTH/5, (int)(RECT_WIDTH*1.2), (int)(RECT_WIDTH*1.1) };
        int[] y2 = {(int)(RECT_HEIGHT*1.1), RECT_HEIGHT, RECT_HEIGHT,  (int)(RECT_HEIGHT*1.1)};
        SIDE = new Polygon(x, y, 4);
        SIDE2 = new Polygon(modifyArray(x,-(RECT_WIDTH/10)), modifyArray(y,RECT_HEIGHT/10),4);
        BOTTOM = new Polygon(x2, y2,4);
        BOTTOM2 = new Polygon(modifyArray(x2,-(RECT_WIDTH/10)), modifyArray(y2,RECT_HEIGHT/10),4);
        FACE = new Rectangle(RECT_WIDTH/5, 0, RECT_WIDTH, RECT_HEIGHT);
        GRAD0 = new GradientPaint(20, 100, Color.darkGray, 120, 0, Color.lightGray);
        GRAD1 = new GradientPaint(20, 100, Color.lightGray, 120, 0, Color.DARK_GRAY);
        GRAD2 = new GradientPaint(10, 110, Color.darkGray, 20, 0, Color.lightGray);
        setBounds(0,0,(int)(RECT_WIDTH*1.2),(int)(RECT_HEIGHT*1.2));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawPolygon(SIDE);
        //g.drawRect(20, 0, 100, 100);

        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(GRAD1);
        //setComponentZOrder(this,1);
        g2.fill(SIDE);
        g2.fill(BOTTOM);

        //setComponentZOrder(this,1);
        g2.setPaint(GRAD0);
        g2.fill(BOTTOM2);
        g2.fill(SIDE2);

        //setComponentZOrder(this,1);
        g2.setPaint(GRAD2);
        g2.fill(FACE);
        g2.setColor(Color.BLACK);

        //setComponentZOrder(this,1);
        g2.draw(BOTTOM);
        g2.draw(BOTTOM2);
        g2.draw(SIDE);
        g2.draw(SIDE2);
        g2.setColor(Color.BLACK);


        //setComponentZOrder(this,2);
        g2.draw(FACE);
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(RECT_WIDTH + offset * RECT_X, RECT_HEIGHT + offset * RECT_Y);
    }

    public int getTileWidth() {
        return RECT_WIDTH;
    }

    public int getTileHeight() {
        return RECT_HEIGHT;
    }

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
        return true;
    }
}
