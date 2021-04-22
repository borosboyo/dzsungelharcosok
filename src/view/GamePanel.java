package view;

import model.Field;
import model.Game;

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

        fi.getSettlers().stream().map(settler -> new EntityView(settler)).forEach(settlerView -> settlerView.draw(window_frame));
        fi.getUfos().stream().map(settler -> new EntityView(settler)).forEach(settlerView -> settlerView.draw(window_frame));
        fi.getRobots().stream().map(settler -> new EntityView(settler)).forEach(settlerView -> settlerView.draw(window_frame));

        fi.getAsteroids().stream().map(settler -> new EntityView(settler)).forEach(settlerView -> settlerView.draw(window_frame));

        for (int i = 0; i < fi.getAsteroids().size(); i++){
            fi.getAsteroids().get(i).getMaterial().stream().map(settler -> new EntityView(settler)).forEach(settlerView -> settlerView.draw(window_frame));
        }
    }
}
