package view;

import model.Asteroid;
import model.Field;
import model.Game;
import model.MenuState;

import java.awt.event.*;

import java.awt.event.KeyEvent;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    Window window;
    int unit; //TODO:ehhez arányosan kéne majd az x,y,width,high értkékeket beállítani (asteroida megkapja, a többi még nem)

    public GamePanel(Window _window, int unit) {
        this.window = _window;
        this.unit = unit;

        //TODO::ez így nem működik
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_3) {
                    //Game.getInstance().getMenu().setMenuState(MenuState.LOADMENU);
                    //Game.getInstance().getMenu().menu_step(0);
                    //TODO::Boros swith to MenuPanel
                    System.exit(0); //Ezt utána ki lehet szedni
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    for(Asteroid a : Game.getInstance().field.getAsteroids()){
                        int newY = a.getY() -10;
                        a.setY(newY);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    for(Asteroid a : Game.getInstance().field.getAsteroids()){
                        int newY = a.getY() + 10;
                        a.setY(newY);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    for(Asteroid a : Game.getInstance().field.getAsteroids()){
                        int newX = a.getX() +10;
                        a.setX(newX);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    for(Asteroid a : Game.getInstance().field.getAsteroids()){
                        int newX = a.getX() -10;
                        a.setX(newX);
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Field fi = Game.getInstance().field;

        this.setBackground(Color.DARK_GRAY);

        /**
         * Asteroids draw
         */
       fi.getAsteroids().stream().map(asteroid -> new AsteroidView(asteroid)).forEach(asteroidView -> asteroidView.draw(g, unit, 0, 0));

        //TODO::itt lehetne fontot is allitani es a szovegek helyet is megkene , mas szovegeket is leheten itt megadni, ha kell
        Font font = new Font(Font.SERIF, Font.PLAIN, (int) (20));
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Settler turn:",2,20);
        for(int i = 0; i < fi.getSettlers().size(); i++){
            if(!fi.getSettlers().get(i).isFinishedTurn()){
                g.drawString(String.valueOf(fi.getSettlers().get(i).getId()),0,40+ (i*20));
            }
        }
    }

}
