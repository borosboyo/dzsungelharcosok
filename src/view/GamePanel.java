package view;

import model.Field;
import model.Game;
import model.Entity;
import java.io.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    Window window;
    int unit;

    public GamePanel(Window _window, int unit) {
        this.window = _window;
        this.unit = unit;
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
