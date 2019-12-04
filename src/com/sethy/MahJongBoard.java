package com.sethy;

import javax.swing.*;
import java.awt.*;

public class MahJongBoard extends JLayeredPane {


    MahJongModel tileManager;

    public MahJongBoard(){
        setOpaque(true);
        setBackground(Color.blue);
        //add(new MahJongTest.TestPanel());


        setVisible(true);

        tileManager = new MahJongModel(this);


    }
}
