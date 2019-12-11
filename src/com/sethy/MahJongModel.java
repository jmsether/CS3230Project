package com.sethy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MahJongModel {

    private boolean tournament = false;
    Thread thread;
    private int seconds = 0;
    private boolean running = true;


    JFrame topFrame;
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

    Tile[][][] tileStack;

    private boolean sound = true;
    private AudioManager tileSelect = new AudioManager(getClass().getResource("audio/tile select.wav"));
    private AudioManager tilePickup = new AudioManager(getClass().getResource("audio/tile pickup.wav"));

    private boolean showNextValidMove = false;
    private ArrayList<Tile> hints = new ArrayList<>();

    private Tile selectedTile;
    private boolean position = false;

    private ArrayList<Tile[]> removedTilesPairs = new ArrayList<>();
    private ArrayList<Tile[]> redoTilesPairs = new ArrayList<>();
    private Tile[] sideQue = new Tile[8];

    private int globalXOffset = 0;
    private int globalYOffset = tileSize/2;

    private static MahJongModel single_instance = null;
    private static JLayeredPane jPanelInstance = null;

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


    JTable game;

    TileDeck	deck = new TileDeck();

    private Component cloneSwingComponent(Component c) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(c);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Component) ois.readObject();
        } catch (IOException |ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void updateSide(){
        if(!tournament){
            if(removedTilesPairs.size()>0){
                //System.out.println(removedTilesPairs.size());
                int top = 8;
                int x;
                if(removedTilesPairs.size()<= top){
                    x = removedTilesPairs.size();
                }else{
                    x = top;
                }
                //for (int i = removedTilesPairs.size()-1; i>=x; i--) {
                for (int i =0; i < x; i++) {
                    //System.out.println(removedTilesPairs.get(i)[0]);

                    //removedTilesPairs.get(i)[0].setLocation(jPanelInstance.getWidth()-(int)(tileSize*1.2),10+(i*(int)(tileSize*1.3)));
                    //removedTilesPairs.get(i)[0].setVisible(false);
                    //removedTilesPairs.get(i)[0].setVisible(true);
                    //jPanelInstance.repaint();
                    //removedTilesPairs.get(i)[0].disableLogic();
                    sideQue[i] = removedTilesPairs.get((removedTilesPairs.size()-1)-i)[0];


                }
                for (int i = 0; i < sideQue.length; i++) {
                    if(sideQue[i] != null){
                        sideQue[i].setLocation(jPanelInstance.getWidth()-(int)(tileSize*1.2),10+(i*(int)(tileSize*1.3)));
                        sideQue[i].highlight(false);
                        sideQue[i].setVisible(true);
                        jPanelInstance.repaint();
                    }
                }
            }
        }
    }


    public boolean getTournamentMode(){
        return tournament;
    }

    public void setTournamentMode(boolean x){
        if(tournament){
            if(!x){
                thread.stop();
                topFrame.setTitle("Mahjong");
            }
        }
        if(x){
            if(showNextValidMove){
                showNextValidMove = false;
            }
        }
        tournament = x;
        running = x;
    }

    public static MahJongModel getInstance(){
        if (single_instance == null) {
            return null;
        }
        return single_instance;
    }

    public void toggleSound(){
        sound = !sound;
    }

    public String soundStatus(){
        if(sound){
            return "on";
        }else{
            return "off";
        }
    }



    public void undo(){
        if(!removedTilesPairs.isEmpty()){
            removedTilesPairs.get(removedTilesPairs.size()-1)[0].setToSart();
            removedTilesPairs.get(removedTilesPairs.size()-1)[0].setVisibility(true);
            removedTilesPairs.get(removedTilesPairs.size()-1)[1].setVisibility(true);
            removedTilesPairs.get(removedTilesPairs.size()-1)[0].highlight(false);
            removedTilesPairs.get(removedTilesPairs.size()-1)[1].highlight(false);

            redoTilesPairs.add(removedTilesPairs.get(removedTilesPairs.size()-1));

            removedTilesPairs.remove(removedTilesPairs.size()-1);

        }else{
            JOptionPane.showMessageDialog(jPanelInstance, "Nothing left to undo!",
                    "Out of undo's", JOptionPane.INFORMATION_MESSAGE);
        }
        updateSide();
    }

    public JLayeredPane getPane(){
        return jPanelInstance;
    }

    public void redo(){
        if(!redoTilesPairs.isEmpty()){
            redoTilesPairs.get(redoTilesPairs.size()-1)[0].setVisibility(false);
            redoTilesPairs.get(redoTilesPairs.size()-1)[1].setVisibility(false);


            removedTilesPairs.add(redoTilesPairs.get(redoTilesPairs.size()-1));

            redoTilesPairs.remove(redoTilesPairs.size()-1);
        }else{
            JOptionPane.showMessageDialog(jPanelInstance, "Nothing left to redo!",
                    "Out of redo's", JOptionPane.INFORMATION_MESSAGE);
        }
        updateSide();
    }

    public boolean isValidMove(Tile tile){
        if(!tile.isEnabled()){
            return false;
        }
        if(tile == left){
            return true;
        }
        if(getSurounding(tile).length<2){
            return true;
        }else{
            return false;
        }
    }

    private void clearHints(){
        if(showNextValidMove){
            for (int i = 0; i <hints.size(); i++) {
                hints.get(i).highlight(false);
            }
        }
        while (!hints.isEmpty()){
            hints.remove(0);
        }
    }

    public void hint(){
        clearHints();
        if(layer1.isVisible() && layer1.isEnabled()){
            if(selectedTile.matches(layer1)){
                hints.add(layer1);
            }
        }
        if(left.isVisible() && left.isEnabled()){
            if(selectedTile.matches(left)){
                hints.add(left);
            }
        }
        if(right1.isVisible() && right1.isEnabled()){
            if(selectedTile.matches(right1)){
                if(isValidMove(right1)){
                    hints.add(right1);
                }
            }
        }
        if(right2.isVisible()&& right2.isEnabled()){
            if(selectedTile.matches(right2)){
                hints.add(right2);
            }
        }
        checkValid(layer2,selectedTile,hints);
        checkValid(layer3,selectedTile,hints);
        checkValid(layer4,selectedTile,hints);
        checkValid(layer5,selectedTile,hints);

        for (int i = 0; i <hints.size(); i++) {
            hints.get(i).hintHighlight();
            selectedTile.highlight(true);
        }

    }

    private void checkValid(Tile[][] tiles,Tile x,ArrayList t){
        for (int i = 0; i <tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if(tiles[i][j] != null && tiles[i][j].isVisible()){
                    if(tiles[i][j].matches(x) &&isValidMove(tiles[i][j])){
                        t.add(tiles[i][j]);
                    }
                }
            }
        }
    }

    public void emptyRedoQue(){
        while(!redoTilesPairs.isEmpty()){
            redoTilesPairs.remove(0);
        }
    }

    public void emptyUndoQue(){
        while(!removedTilesPairs.isEmpty()){
            removedTilesPairs.remove(0);
        }
    }

    public void toggleShowNextMove(){
        showNextValidMove = !showNextValidMove;
    }

    public boolean getToggle(){
        return showNextValidMove;
    }

    public void setSelection(Tile tile){
        if(sound){
            tileSelect.rePlay();
        }
        if(position){
            position = false;
            if(tile.matches(selectedTile)){
                removedTilesPairs.add(new Tile[]{selectedTile,tile});
                selectedTile.setVisibility(false);
                tile.setVisibility(false);
                emptyRedoQue();
                updateSide();
                if(sound){
                    tilePickup.rePlay();
                }
                if(showNextValidMove){
                    clearHints();
                }
            }else{
                //tile.highlight(false);
                selectedTile.highlight(false);
                selectedTile = tile;
                if(showNextValidMove){
                    hint();
                }
                position=true;
            }

        }else{
            position = true;
            selectedTile = tile;
            if(showNextValidMove){
                hint();
            }

        }
        if(removedTilesPairs.size() == 72){
            JOptionPane.showMessageDialog(null,"Congratulations, you win!!!","You Win!",JOptionPane.INFORMATION_MESSAGE);
            if(tournament){
                running = false;
                JOptionPane.showMessageDialog(null,"Score: "+removedTilesPairs.size()*2+" Time: "+(seconds/60)/60+":"+(seconds/60)%60+":"+seconds%60,"Tournament Info",JOptionPane.INFORMATION_MESSAGE);
                thread.stop();
            }
        }
    }
    public void removeSelection(Tile tile){
        if(position){
            position = false;
            if(showNextValidMove){
                clearHints();
            }

        }
    }


    public Tile[] getSurounding(Tile tile){
        ArrayList<Tile> stack = new ArrayList<Tile>();
        if(tile == (layer1)){
            return new Tile[]{layer1};
        }
        if(tile  == (left)){
            return new Tile[]{left,layer5[3][0],layer5[4][0]};
        }
        if(tile == (right1)){
            //return new Tile[]{right2,layer5[3][11],layer5[4][11]};
            stack.add(layer5[3][11]);
            if(right2.isVisible() && right2.isEnabled()){
                stack.add(right2);
            }
        }
        if(tile == (right2)){
            return new Tile[]{right1};
        }

        for (int x = 0; x < tileStack.length; x++) {
            for (int y = 0; y < tileStack[x].length; y++) {
                for (int z = 0; z < tileStack[x][y].length; z++) {
                    if(tileStack[x][y][z] == tile){
                        if((z-1) >= 0 &&tileStack[x][y][z-1] != null){
                            if(tileStack[x][y][z-1].isVisible() && tileStack[x][y][z-1].isEnabled()){
                                stack.add(tileStack[x][y][z-1]);
                            }
                            //System.out.println("left valid");
                        }
                        if((z+1) < tileStack[x][y].length && tileStack[x][y][z+1] != null){
                            if(tileStack[x][y][z+1].isVisible() && tileStack[x][y][z+1].isEnabled()){
                                stack.add(tileStack[x][y][z+1]);
                            }
                            //System.out.println("right valid");
                        }
                        /*if((y-1) >= 0 && tileStack[x][y-1][z] != null){
                            stack.add(tileStack[x][y-1][z]);
                            //System.out.println("up valid");
                        }
                        if(y < 7 && (y+1) < tileStack[x][y].length && tileStack[x][y+1][z] != null){
                            stack.add(tileStack[x][y+1][z]);
                            //System.out.println("down valid");
                        }*/
                        //System.out.println(x+":"+y+":"+z);
                        if(x == 3){
                            if(z>2&&z<9){

                                if(y<=6&&y>0){
                                    if(tileStack[2][y-1][z-3].isVisible() && tileStack[2][y-1][z-3].isEnabled()){
                                        stack.add(tileStack[2][y-1][z-3]);
                                        stack.add(new Tile());
                                        stack.add(new Tile());
                                    }
                                }
                            }

                            if(y == 3 || y == 4){
                                if(z == 0){
                                    if(left.isVisible() && left.isEnabled()){
                                        stack.add(left);
                                    }
                                }
                                if(z == tileStack[x][y].length-1){
                                    if(right1.isVisible()){
                                        stack.add(right1);
                                    }
                                }
                            }
                        }
                        if(x<3&&x>0){
                            if(y>0&&z>0){
                                if(y<tileStack[x].length-1&&z<tileStack[x][y].length-1){
                                    if(tileStack[x-1][y-1][z-1].isVisible() && tileStack[x-1][y-1][z-1].isEnabled()){
                                        stack.add(tileStack[x-1][y-1][z-1]);
                                        stack.add(new Tile());
                                        stack.add(new Tile());
                                        //tileStack[x-1][y-1][z-1].highlight(true);

                                    }
                                }

                            }

                        }
                        if(x == 0){
                            if(layer1.isVisible() && layer1.isEnabled()){
                                stack.add(layer1);
                            }
                        }
                    }
                }
            }
        }


        Tile[] tiles = new Tile[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            tiles[i]=stack.get(i);
        }
        return tiles;
    }

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
                        if(!t.isVisible()){
                            t.setVisibility(true);
                            t.highlight(false);
                        }

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
        single_instance = this;
        jPanelInstance = jPanel;
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
        fill();
        JTextField field = new JTextField();
        field.setText("testing this");
        field.setEditable(false);
        field.setLocation(20,20);
        jPanel.add(field);

    }

    public void newGame(){
        int c;
        if(tournament){
            c = 0;
        }else{
            c = JOptionPane.showConfirmDialog(null,"Are you sure you want to start a new game?","New Game",JOptionPane.OK_CANCEL_OPTION);
        }
        if(c == 0){
            jPanelInstance.removeAll();
            deck = new TileDeck();
            deck.shuffle();
            fill();
        }

    }

    public void newGame(long x){
        int c = JOptionPane.showConfirmDialog(null,"Are you sure you want to start a new game?","New Game",JOptionPane.OK_CANCEL_OPTION);
        if(c == 0){
            jPanelInstance.removeAll();
            deck = new TileDeck();
            deck.shuffle(x);
            fill();
        }

    }

    public void restart(){
        /*jPanelInstance.removeAll();
        deck.reShuffle();
        fill();*/
        while(!removedTilesPairs.isEmpty()){
            undo();
        }
        while(!redoTilesPairs.isEmpty()){
            redoTilesPairs.remove(0);
        }
    }

    public void fill(){
        topFrame = (JFrame)jPanelInstance.getTopLevelAncestor();
        emptyUndoQue();
        emptyRedoQue();
        left = deck.deal();
        right1 = deck.deal();
        right2 = deck.deal();

        layer1 = deck.deal();

        fillGrid(layer2,jPanelInstance);
        fillGrid(layer3,jPanelInstance);
        fillGrid(layer4,jPanelInstance);
        fillGridWithMask(layer5,jPanelInstance,bottomMask);
        fillSpecials(jPanelInstance);
        tileStack = new Tile[][][]{layer2, layer3, layer4, layer5};
        if(tournament){
            thread = new Thread(){
                public void run(){
                    seconds = 0;
                    while(running){
                        topFrame.setTitle("Score: "+removedTilesPairs.size()*2+" Time: "+(seconds/60)/60+":"+(seconds/60)%60+":"+seconds%60);
                        //System.out.println("Score: "+removedTilesPairs.size()*2+" Time: "+(seconds/60)/60+":"+(seconds/60)%60+":"+seconds%60);
                        seconds++;
                        try {
                            thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread Running");
                }
            };

            thread.start();

        }
    }

}
