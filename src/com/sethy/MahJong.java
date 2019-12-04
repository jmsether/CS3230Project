package com.sethy;

import javax.swing.*;
import java.awt.*;

public class MahJong extends JFrame {


    int tileSize = 80;

    MahJongBoard board;

    public MahJong(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.green);

        //add(new MahJongTest.TestPanel());

        setSize(tileSize*18, tileSize*10);

        board = new MahJongBoard();
        add(board);
        //board.repaint();
        setVisible(true);
    }


}
