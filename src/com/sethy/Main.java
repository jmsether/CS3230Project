package com.sethy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;

public class Main {


    public static void characterTest()
    {
        JTextArea area = new JTextArea();

        area.append(new CharacterTile('1').toChinese() + "  ");
        area.append(new CharacterTile('2').toChinese() + "  ");
        area.append(new CharacterTile('3').toChinese() + "  ");
        area.append(new CharacterTile('4').toChinese() + "  ");
        area.append(new CharacterTile('5').toChinese() + "  ");
        area.append(new CharacterTile('6').toChinese() + "  ");
        area.append(new CharacterTile('7').toChinese() + "  ");
        area.append(new CharacterTile('8').toChinese() + "  ");
        area.append(new CharacterTile('9').toChinese() + "  ");
        area.append(new CharacterTile('N').toChinese() + "  ");
        area.append(new CharacterTile('E').toChinese() + "  ");
        area.append(new CharacterTile('W').toChinese() + "  ");
        area.append(new CharacterTile('S').toChinese() + "  ");
        area.append(new CharacterTile('C').toChinese() + "  ");
        area.append(new CharacterTile('F').toChinese() + "  ");

        JOptionPane.showMessageDialog(null, area, "Character Tiles",
                JOptionPane.INFORMATION_MESSAGE);
    }


    public static JTable makeGame(long game)
    {
        TileDeck	deck = new TileDeck();
        Tile[][]	tiles = new Tile[12][12];
        String[]	cols = new String[12];

        Arrays.fill(cols, "");

        if (game < 0)
            deck.shuffle();
        else
            deck.shuffle(game);

        for (int i = 0; i < 144; i++)
        {
            Tile	tile = deck.deal();
            if (tile == null)
            {
                JOptionPane.showMessageDialog(null, "Empty Deck",
                        "Deal Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }

            tiles[i/12][i%12] = tile;
        }

        return new JTable(tiles, cols);

    }


    public static void main(String[] args)
    {
        characterTest();			// Assignment Part 1

        Object[] tables = new Object[6];	// Assignment Part 2

        tables[0] = "Random Game";
        tables[1] = makeGame(-1);
        tables[2] = "Fixed Game";
        tables[3] = makeGame(100);
        tables[4] = "Fixed Game";
        tables[5] = makeGame(100);

        JOptionPane.showMessageDialog(null, tables,
                "Tiles", JOptionPane.PLAIN_MESSAGE);
    }
    /*public static void main(String[] args)
    {
        Tile	c0 = new CircleTile(3);
        Tile	c1 = new CircleTile(3);
        Tile	c2 = new CircleTile(4);

        if (c0.matches(c1) && !c0.matches(c2))
            System.out.println("Circle Tile matches:          PASS");
        else
            System.out.println("Circle Tile matches:          FAIL");

        Tile	b0 = new BambooTile(3);
        Tile	b1 = new BambooTile(3);
        Tile	b2 = new BambooTile(4);

        if (b0.matches(b1) && !b0.matches(b2))
            System.out.println("Bamboo Tile matches:          PASS");
        else
            System.out.println("Bamboo Tile matches:          FAIL");


        if (c0.matches(b0))
            System.out.println("Circle/Bamboo Tile matches:   Fail");
        else
            System.out.println("Circle/Bamboo Tile matches:   PASS");


        Tile	ch0 = new CharacterTile('3');
        Tile	ch1 = new CharacterTile('3');
        Tile	ch2 = new CharacterTile('W');

        if (ch0.matches(ch1) && !ch0.matches(ch2))
            System.out.println("Character Tile matches:       PASS");
        else
            System.out.println("Character Tile matches:       FAIL");

        Tile	p0 = new SeasonTile("Spring");
        Tile	p1 = new SeasonTile("Summer");
        Tile	p2 = new FlowerTile("Plum");
        Tile	p3 = new FlowerTile("Orchid");

        if (p0.matches(p1) && p2.matches(p3) && !p0.matches(p2))
            System.out.println("Picture Tile matches:         PASS");
        else
            System.out.println("Picture Tile matches:         FAIL");


        Tile	wd0 = new WhiteDragonTile();
        Tile	wd1 = new WhiteDragonTile();
        Tile	b1t0 = new Bamboo1Tile();
        Tile	b1t1 = new Bamboo1Tile();

        if (wd0.matches(wd1) && b1t0.matches(b1t1) && !wd0.matches(b1t0))
            System.out.println("Special Case Tiles matches:   PASS");
        else
            System.out.println("Special Case Tiles matches:   FAIL");


        System.out.println();
        System.out.printf("Should print Circle 3%20s%n", c0);
        System.out.printf("Should print Bamboo 3%20s%n", b0);
        System.out.printf("Should print Character 3%20s%n", ch0);
        System.out.printf("Should print West Wind%20s%n", ch2);
        System.out.printf("Should print Spring%20s%n", p0);
        System.out.printf("Should print White Dragon%20s%n", wd0);
        System.out.printf("Should print Bamboo 1%20s%n", b1t1);
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CharacterTile a1 = new CharacterTile('1');
        CharacterTile a2 = new CharacterTile('2');
        CharacterTile a3 = new CharacterTile('3');
        CharacterTile p4 = new CharacterTile('4');
        CharacterTile p5 = new CharacterTile('5');
        CharacterTile p6 = new CharacterTile('6');
        CharacterTile p7 = new CharacterTile('7');
        CharacterTile p8 = new CharacterTile('8');
        CharacterTile p9 = new CharacterTile('9');
        CharacterTile p10 = new CharacterTile('N');
        CharacterTile p11 = new CharacterTile('E');
        CharacterTile p12 = new CharacterTile('W');
        CharacterTile p13 = new CharacterTile('S');
        CharacterTile p14 = new CharacterTile('C');
        CharacterTile p15 = new CharacterTile('F');
        JLabel textLabel = new JLabel(a1.toChinese()+
                a2.toChinese()+
                a3.toChinese()+
                p4.toChinese()+
                p5.toChinese()+
                p6.toChinese()+
                p7.toChinese()+
                p8.toChinese()+
                p9.toChinese()+
                p10.toChinese()+
                p11.toChinese()+
                p12.toChinese()+
                p13.toChinese()+
                p14.toChinese()+
                p15.toChinese() ,SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(textLabel, BorderLayout.CENTER);
        frame.setVisible(true);

    }*/
}
