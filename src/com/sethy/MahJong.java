package com.sethy;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;

public class MahJong extends JFrame {


    int tileSize = 80;

    MahJongBoard board;

    public MahJong(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.green);


        setTitle("Starting");


        //"src/com/sethy/audio/background.mp3"
        //add(new MahJongTest.TestPanel());

        setSize(tileSize*18, tileSize*10);

        board = new MahJongBoard();
        add(board);
        //board.repaint();

        JMenuBar menuBar = new JMenuBar();
        JMenu game = new JMenu("Game");
        JMenu sound = new JMenu("Sound");
        JMenu move = new JMenu("Move");
        JMenu help = new JMenu("Help");

        JMenuItem newGame = new JMenuItem("Play",'P');
        JMenuItem restart = new JMenuItem("Restart",'R');
        JMenuItem newNumberedGame = new JMenuItem("Numbered",'N');
        JMenuItem undo = new JMenuItem("Undo",'U');
        JMenuItem redo = new JMenuItem("Redo",'R');

        JMenuItem tournament = new JMenuItem("New Tournament game",'T');

        JMenuItem music = new JMenuItem("Music "+ board.musicStatus(),'M');
        JMenuItem gameSound = new JMenuItem("Game Sound "+ board.tileManager.soundStatus(),'S');

        JMenuItem extraHelp = new JMenuItem("Show Next move: "+board.tileManager.getToggle(),'M');

        JMenuItem howTo = new JMenuItem("How To",'H');
        JMenuItem gameRules = new JMenuItem("Game rules",'G');

        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        restart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        newNumberedGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        game.add(newGame);
        game.add(tournament);
        game.add(restart);
        game.add(newNumberedGame);


        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        move.add(undo);
        move.add(redo);


        music.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
        gameSound.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        sound.add(music);
        sound.add(gameSound);

        menuBar.add(game);
        menuBar.add(sound);
        menuBar.add(move);
        menuBar.add(help);

        help.add(extraHelp);
        help.add(howTo);
        help.add(gameRules);

        setJMenuBar(menuBar);

        setVisible(true);

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.tileManager.undo();
            }
        });
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.tileManager.redo();
            }
        });
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.newGame();
                repaint();
            }
        });
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.tileManager.restart();
                repaint();
            }
        });
        newNumberedGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input;
                String string = "";
                do {
                    input = JOptionPane.showInputDialog("Enter new game number: ");
                    if (input.matches("^[0-9]*$")) {
                        string = input;
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter only a number!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } while (!input.matches("^[0-9]*$"));
                board.tileManager.newGame(Long.valueOf(string));
                repaint();
            }
        });
        extraHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.tileManager.toggleShowNextMove();
                extraHelp.setText("Show Next move: "+board.tileManager.getToggle());
            }
        });
        music.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.toggleMusic();
                music.setText("Music "+ board.musicStatus());
            }
        });
        gameSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.tileManager.toggleSound();
                gameSound.setText("Game Sound "+ board.tileManager.soundStatus());
            }
        });

        tournament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(board.tileManager.getTournamentMode()){
                    board.tileManager.setTournamentMode(false);
                    board.tileManager.newGame();
                    newGame.setEnabled(true);
                    newNumberedGame.setEnabled(true);
                    undo.setEnabled(true);
                    redo.setEnabled(true);
                    restart.setEnabled(true);
                    extraHelp.setEnabled(true);
                    tournament.setText("New Tournament game");

                }else{
                    board.tileManager.setTournamentMode(true);
                    board.tileManager.newGame();
                    newGame.setEnabled(false);
                    newNumberedGame.setEnabled(false);
                    undo.setEnabled(false);
                    redo.setEnabled(false);
                    restart.setEnabled(false);
                    extraHelp.setEnabled(false);
                    tournament.setText("click to exit tournament mode");
                }
            }
        });
        howTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Click on the tiles to highlight them and then match the tiles\n" +
                        "Ctrl+z to undo\n" +
                        "Crtl+y to redo\n" +
                        "Use the drop down menus to acces new game controls\n" +
                        "If you find yourself getting stuck you can use the 'show next move' button in the help menu","How to use", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gameRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "The Rules are simple.\n" +
                                "A valid tile is any tile that does not have a tile on its left or right.\n" +
                                "Click two valid matching tiles to remove them from the board.\n" +
                                "Remove all tile pairs to win the game.","How to use", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }


}
