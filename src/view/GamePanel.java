package view;

import model.Field;
import model.Game;
import model.Material;

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
        MaterialView mat = null;

        //Asteroids draw
        fi.getAsteroids().stream().map(asteroid -> new AsteroidView(asteroid)).forEach(asteroidView -> asteroidView.draw(window_frame));

        for (int i = 0; i < fi.getAsteroids().size(); i++){
            fi.getAsteroids().get(i).getEntities().stream().map(entity -> new EntityView(entity)).forEach(entityView -> entityView.draw(window_frame));
            fi.getAsteroids().get(i).getTeleports().stream().map(teleport -> new TeleportView(teleport)).forEach(teleportView -> teleportView.draw(window_frame));
            mat = new MaterialView(fi.getAsteroids().get(i).getMaterial());
            mat.draw(window_frame);
        }

    }
}
