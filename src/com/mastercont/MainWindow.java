package com.mastercont;

import javax.swing.*;

public class MainWindow extends JFrame {
    MainWindow(){
        setSize(812,812);
        setTitle("An exciting game 'DED, CATCH THE SERZHIKA!' (Snake)");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new GameField());
        setVisible(true);
    }
}
