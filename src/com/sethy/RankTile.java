package com.sethy;

import java.awt.*;

public abstract class RankTile extends Tile {
    protected int rank;
    private static Polygon dot;
    private static Polygon mDot;

    private int spacing = 5;
    private int[] xGrid;
    private int[] yGrid;

    private int[][] grid0 ={
            {0,0,0,0,0,0,0,0,0}, //0
            {0,0,0,0,0,0,0,0,0}, //1
            {0,0,0,0,0,0,0,0,0}, //2
            {0,0,0,0,0,0,0,0,0}, //3
            {0,0,0,0,0,0,0,0,0}, //4
            {0,0,0,0,0,0,0,0,0}, //5
            {0,0,0,0,0,0,0,0,0}, //6
            {0,0,0,0,0,0,0,0,0}, //7
            {0,0,0,0,0,0,0,0,0}, //8

    };

    private int[][] grid1 ={
            {0,0,0,0,0,0,0,0,0}, //0
            {0,0,0,0,0,0,0,0,0}, //1
            {0,0,0,0,0,0,0,0,0}, //2
            {0,0,0,0,0,0,0,0,0}, //3
            {0,0,0,0,1,0,0,0,0}, //4
            {0,0,0,0,0,0,0,0,0}, //5
            {0,0,0,0,0,0,0,0,0}, //6
            {0,0,0,0,0,0,0,0,0}, //7
            {0,0,0,0,0,0,0,0,0}, //8

    };

    private int[][] grid2 ={
            {0,0,0,0,0,0,0,0,0}, //0
            {0,0,0,0,0,0,0,0,0}, //1
            {0,0,0,0,0,0,0,0,0}, //2
            {0,0,0,0,1,0,0,0,0}, //3
            {0,0,0,0,0,0,0,0,0}, //4
            {0,0,0,0,1,0,0,0,0}, //5
            {0,0,0,0,0,0,0,0,0}, //6
            {0,0,0,0,0,0,0,0,0}, //7
            {0,0,0,0,0,0,0,0,0}, //8

    };

    static{
        dot = new Polygon(new int[]{0,5,5,0},new int[]{0,0,5,5},4);
    }

    public RankTile(int rank){
        this.rank = rank;
        setToolTipText(String.valueOf(rank));
    }

    public RankTile(RankTile rankTile){
        this.rank = rankTile.rank;
        setToolTipText(String.valueOf(rank));
    }

    private void fillGrid(){

    }

    private int[] translateArray(int[] x,int y){
        for(int i = 0; i< x.length; i++){
            x[i] += y;
        }

        return x;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Polygon circle = mDot;

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);
    }

    protected void drawSpot(Graphics2D g2, int x, int y) {
        g2.fillOval(x - 2 / 2, y - 2 / 2, 2, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);




        /*
        for(int i = 0; i< grid2.length;i++){
            //System.out.println(i);
            for(int j = 0; j< grid2[i].length;j++){
                //System.out.println(j);
                if(grid2[i][j] != 0){
                    mDot = dot;
                    mDot.translate(spacing*j,spacing*i);
                    System.out.println(
                                    mDot.xpoints[0] + ", " +
                                    mDot.xpoints[1] + ", " +
                                    mDot.xpoints[2] + ", " +
                                    mDot.xpoints[3] + ", " +
                                    " : " +
                                    mDot.ypoints[0] + ", " +
                                    mDot.xpoints[1] + ", " +
                                    mDot.xpoints[2] + ", " +
                                    mDot.xpoints[3]
                                );
                    draw(g);
                }
            }
        }*/
        //g.drawPolygon(dot);

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
        return this.rank == ((RankTile)other).rank;
    }
}
