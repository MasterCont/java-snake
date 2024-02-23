package com.mastercont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;


public class GameField extends JPanel implements ActionListener {
    int dots = 3;
    int appleX, appleY;
    int[] snakeX, snakeY;
    int speed = 100;
    Timer timer;
    int dotSize = 16;
    Random random = new Random();
    Image apple, dot;
    boolean right = false, left = false, up = true, down = false;

    boolean inGame = true;

    GameField() {
        setBackground(Color.blue);
        snakeY = new int[625];
        snakeX = new int[625];
        ImageIcon apple_img = new ImageIcon("sergik.png");
        ImageIcon dot_img = new ImageIcon("ded.png");
        apple = apple_img.getImage();
        dot = dot_img.getImage();
        initGame();
        setFocusable(true);
        addKeyListener(new KeyListenerSnake());
        System.out.println("=========================");
        System.out.print("Game Started!");
        System.out.println("  Logs:");

    }

    public void initGame() {
        for (int i = 0; i < dots; i++) {
            snakeX[i] = (dots - 1) * dotSize - i * dotSize;//
            snakeY[i] = 12 * dotSize;
        }
        timer = new Timer(speed, this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = random.nextInt(24) * dotSize;
        appleY = random.nextInt(24) * dotSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, snakeX[i], snakeY[i], this);
            }
        } else {
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 400, 400);
        }
    }


    public void move() {
        for (int i = dots - 1; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }
        if (right)
            snakeX[0] += dotSize;
        if (left)
            snakeX[0] -= dotSize;
        if (down)
            snakeY[0] += dotSize;
        if (up) snakeY[0] -= dotSize;
    }

    public void checkApple() {
        if (appleX == snakeX[0] && appleY == snakeY[0]) {
            createApple();
            snakeY[dots] = snakeY[dots - 1];
            snakeX[dots] = snakeX[dots - 1];
            dots++;
//            System.out.println("============Apple restarting=============");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if(inGame) {
            move();
            checkApple();
            checkCollisions();
        }
    }
public void checkCollisions(){
        if(snakeY[0]<0 || snakeX[0]<0 || snakeX[0]>=812-dotSize || snakeY[0]>=812-dotSize){
            inGame=false;
            System.out.println("---Game Over---");
            System.out.println("=========================");
        }
        if(dots>4){
for (int i=1;i<dots;i++){
    if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
        inGame=false;
        System.out.println("---Game Over---");
        System.out.println("=========================");
    }
}
        }
}
    class KeyListenerSnake extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==KeyEvent.VK_UP && !down){
                right=false;
                left=false;
                up=true;
//                System.out.println("Pressed UP");
            }
            if(e.getKeyCode()==KeyEvent.VK_DOWN && !up){
                down=true;
                right=false;
                left=false;
//                System.out.println("Pressed DOWN");
            }
            if(e.getKeyCode()==KeyEvent.VK_RIGHT && !left){
                right=true;
                up=false;
                down=false;
//                System.out.println("Pressed RIGHT");
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT &&!right){
                up=false;
                left=true;
                down=false;
//                System.out.println("Pressed LEFT");
            }
        }
    }
}