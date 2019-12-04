package com.sethy;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MahJongModel {

    private int nextIndex = 0;
    private int tileSize = 80;
    private Random r = new Random();

    Tile[][] bigArray = new Tile[9][9];
    Tile[][] smallArray;

    Tile left = new Tile();
    Tile right1 = new Tile();
    Tile right2 = new Tile();

    Tile layer1 = new Tile();
    Tile[][] layer2 = new Tile[2][2];
    Tile[][] layer3 = new Tile[4][4];
    Tile[][] layer4 = new Tile[6][6];
    Tile[][] layer5 = new Tile[8][12];

    private int globalXOffset = 0;
    private int globalYOffset = tileSize/2;

    private int[][] bottomMask = {
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,1,1,1,1,1,1,1,1,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,0},
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1},
            {0,1,1,1,1,1,1,1,1,1,1,0},
            {0,0,1,1,1,1,1,1,1,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1},
    };

    JTable game;

    TileDeck	deck = new TileDeck();

    public Tile[] gameTiles= {
            new CharacterTile('1'),
            new CharacterTile('2'),
            new CharacterTile('3'),
            new CharacterTile('4'),
            new CharacterTile('5'),
            new CharacterTile('6'),
            new CharacterTile('7'),
            new CharacterTile('8'),
            new CharacterTile('9'),
            new CircleTile(1),
            new CircleTile(2),
            new CircleTile(3),
            new CircleTile(4),
            new CircleTile(5),
            new CircleTile(6),
            new CircleTile(7),
            new CircleTile(8),
            new CircleTile(9),
            new Bamboo1Tile(),
            new BambooTile(2),
            new BambooTile(3),
            new BambooTile(4),
            new BambooTile(5),
            new BambooTile(6),
            new BambooTile(7),
            new BambooTile(8),
            new BambooTile(9),
            new CharacterTile('N'),
            new CharacterTile('E'),
            new CharacterTile('S'),
            new CharacterTile('W'),
            new CharacterTile('C'),
            new CharacterTile('F'),
            new WhiteDragonTile(),
            new FlowerTile("Chrysanthemum"),
            new FlowerTile("Orchid"),
            new FlowerTile("Plum"),
            new FlowerTile("Bamboo"),
            new SeasonTile("Spring"),
            new SeasonTile("Summer"),
            new SeasonTile("Fall"),
            new SeasonTile("Winter")
    };

    private ArrayList<Tile>gameTileArray = new ArrayList<>();

    //Tile[][] layers = {{layer1},{layer2},};

    public void fillSpecials(JLayeredPane jPanel){
        int centerOffset = (int)(tileSize*.20)*(4);
        left.setLocation(tileSize+(int)(tileSize*.2)+globalXOffset,(9/2)*tileSize-(tileSize/2)-(int)(tileSize*.2)+globalYOffset);
        jPanel.add(left);
        jPanel.moveToFront(left);

        right1.setLocation((tileSize*14)+(int)(tileSize*.2)+globalXOffset,(9/2)*tileSize-(tileSize/2)-(int)(tileSize*.2)+globalYOffset);
        jPanel.add(right1);
        //jPanel.moveToFront(right1);

        right2.setLocation((tileSize*15)+(int)(tileSize*.2)+globalXOffset,(9/2)*tileSize-(tileSize/2)-(int)(tileSize*.2)+globalYOffset);
        jPanel.add(right2);
        //jPanel.moveToFront(right1);

        layer1.setLocation((tileSize*8)+(int)(tileSize*.2)-(tileSize/2)+(int)(tileSize*.2)+globalXOffset+(centerOffset-(int)(tileSize*.2)),(9/2)*tileSize-(tileSize/2)-(int)(tileSize*.2)+globalYOffset-centerOffset);
        jPanel.add(layer1);
        jPanel.moveToFront(layer1);

    }

    public void fillGrid(Tile[][] tiles, JLayeredPane jPanel){

        /*Tile t;
        int edgeOffset = (int)(tileSize*.20);
        int offsetX = ((16/2-(tiles.length)+(tiles.length/2))*tileSize)+edgeOffset;
        int offsetY = ((9/2-(tiles.length)+(tiles.length/2))*tileSize)-edgeOffset;
        for(int x = tiles.length-1; x>=0;x--){
            for(int y = 0; y<tiles.length;y++){
                t = deck.deal();
                t.setLocation((offsetX)+(y*tileSize),(offsetY)+(x*tileSize));
                jPanel.add(t);
                //jPanel.moveToBack(t);
                tiles[x][y] = t;
            }
        }*/
        int[][] mask = new int[tiles.length][tiles[0].length];
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[0].length; j++) {
                mask[i][j] = 1;
            }
        }
        fillGridWithMask(tiles,jPanel,mask);
    }

    public void fillGridWithMask(Tile[][] tiles, JLayeredPane jPanel,int[][] mask){
        Tile t;
        int edgeOffset = (int)(tileSize*.20)*((10-tiles.length)/2);
        int offsetX = ((16/2-(tiles[0].length)+(tiles[0].length/2))*tileSize)+edgeOffset;
        int offsetY = ((9/2-(tiles.length)+(tiles.length/2))*tileSize)-edgeOffset;
        for(int x = tiles.length-1; x>=0;x--){
        //for(int x = 0; x<tiles.length;x++){
            for(int y = 0; y<tiles[x].length;y++){
                if(x<mask.length){
                    if(mask[x][y] == 1){
                        t = deck.deal();
                        t.setLocation((offsetX)+(y*tileSize)+globalXOffset,(offsetY)+(x*tileSize)+globalYOffset);
                        jPanel.add(t);
                        tiles[x][y] = t;
                    }
                }
            }
        }
    }

    public MahJongModel(JLayeredPane jPanel){
        //JOptionPane.showMessageDialog(null, game, "Tiles", JOptionPane.PLAIN_MESSAGE);
        deck.shuffle();
        /*
        Tile t;
        for(int x = 8; x>=0;x--){
            for(int y = 0; y<9;y++){
                t = new CircleTile(x);
                t.setLocation(y*tileSize,x*tileSize);
                jPanel.add(t);
                bigArray[x][y] = t;
            }
        }




        t = new SeasonTile("Spring");
        t.setLocation(100, 100);
        jPanel.add(t);
        System.out.println(jPanel.getComponentZOrder(t));

        t = new SeasonTile("Summer");
        t.setLocation(180, 100);
        jPanel.add(t);
        System.out.println(jPanel.getComponentZOrder(t));

        t = new SeasonTile("Fall");
        t.setLocation(260, 100);
        jPanel.add(t);
        System.out.println(jPanel.getComponentZOrder(t));

        t = new SeasonTile("Winter");
        t.setLocation(340, 100);
        jPanel.add(t);
        System.out.println(jPanel.getComponentZOrder(t));

         */
        left = deck.deal();
        right1 = deck.deal();
        right2 = deck.deal();

        layer1 = deck.deal();

        fillGrid(layer2,jPanel);
        fillGrid(layer3,jPanel);
        fillGrid(layer4,jPanel);
        fillGridWithMask(layer5,jPanel,bottomMask);
        fillSpecials(jPanel);
    }

}
