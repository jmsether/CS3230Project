package com.sethy;

import java.awt.*;
import java.util.HashMap;

import static java.lang.Character.toUpperCase;

public class CharacterTile extends Tile {
    protected char symbol;
    protected String wan = "\u842c";

    private static HashMap<Character, Character> EtC = new HashMap<>();

    private double scaleFactor = .8;



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if((int)symbol-49 <=9){
            //System.out.println((int)symbol+" : "+symbol);
            g.setFont(new Font("SansSerif",Font.PLAIN,(int)(getTileWidth()/2.5)));
            g.drawString(EtC.get(symbol).toString(),getTileWidth()/4+getTileWidth()/5,getTileHeight()/2-getTileHeight()/10);
            g.setColor(Color.red);
            g.drawString(wan,getTileWidth()/4+getTileWidth()/5,getTileHeight()/2+getTileHeight()/2-getTileHeight()/8);

        }else{
            //System.out.println((int)symbol+" : "+symbol);
            if(symbol == 'C'){
                g.setColor(Color.red);
            }else if(symbol == 'F'){
                g.setColor(Color.GREEN);
            }else{
                g.setColor(Color.BLACK);
            }
            g.setFont(new Font("SansSerif",Font.PLAIN,(int)(getTileWidth()/1.5)));
            g.drawString(EtC.get(symbol).toString(),getTileWidth()/3,getTileHeight()-getTileHeight()/5);
        }
        g.setFont(new Font("SansSerif",Font.BOLD +Font.ITALIC,(getTileWidth()/6)));
        g.setColor(Color.red);
        g.drawString(String.valueOf(symbol),getTileWidth(),getTileHeight()/5);


    }

    public CharacterTile(char symbol){
        EtC.put('1','\u4E00');
        EtC.put('2','\u4E8C');
        EtC.put('3','\u4E09');
        EtC.put('4','\u56DB');
        EtC.put('5','\u4E94');
        EtC.put('6','\u516D');
        EtC.put('7','\u4E03');
        EtC.put('8','\u516B');
        EtC.put('9','\u4E5D');
        EtC.put('N','\u5317');
        EtC.put('E','\u6771');
        EtC.put('W','\u897F');
        EtC.put('S','\u5357');
        EtC.put('C','\u4E2D');
        EtC.put('F','\u767C');

        this.symbol = toUpperCase(symbol);
        setToolTipText(String.valueOf(symbol));
    }

    @Override
    public String toString() {
        switch (symbol){
            case 'N':
            case 'n':
                return "North Wind";
            case 'E':
            case 'e':
                return "East Wind";
            case 'W':
            case 'w':
                return "West Wind";
            case 'S':
            case 's':
                return "South Wind";
            case 'C':
            case 'c':
                return "Red Dragon";
            case 'F':
            case 'f':
                return "Green Dragon";
            default:
                return "Character " + symbol;
        }
    }

    public String toChinese(){
        return EtC.get(toUpperCase(symbol)).toString();
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
        return this.symbol == ((CharacterTile)other).symbol;
    }
}
