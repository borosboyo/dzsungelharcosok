package view;

import model.Field;
import model.Game;
import model.Entity;
import java.io.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    int unit;

    public GamePanel(int unit) {
        this.unit = unit;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D window_frame = (Graphics2D) g;
        Field fi = Game.getInstance().field;
        MaterialView mat;

        this.setBackground(Color.BLACK);

        /**
         * Asteroids draw
         */

        fi.getAsteroids().stream().map(asteroid -> new AsteroidView(asteroid)).forEach(asteroidView -> asteroidView.draw(window_frame, unit));

        /**
         *  Teleport, Entity, Material draw
         */
      for (int i = 0; i < fi.getAsteroids().size(); i++){
            fi.getAsteroids().get(i).getEntities().stream().map(entity -> new EntityView(entity)).forEach(entityView -> entityView.draw(window_frame, unit));
            fi.getAsteroids().get(i).getTeleports().stream().map(teleport -> new TeleportView(teleport)).forEach(teleportView -> teleportView.draw(window_frame, unit));
            mat = new MaterialView(fi.getAsteroids().get(i).getMaterial());
            mat.draw(window_frame, unit);
        }


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
