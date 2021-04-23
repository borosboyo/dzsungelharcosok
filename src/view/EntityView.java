package view;

import model.Asteroid;
import model.Entity;
import model.Robot;
import model.Settler;
import model.Ufo;

import java.awt.*;

public class EntityView implements Drawable {
    private Entity entity;

    public EntityView(Entity entity) {
        this.entity = entity;
    }


    @Override
    public void draw(Graphics g, int unit, int x, int y) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = null;
        int xMove = 0;
        int yMove = 0;

        if (entity instanceof Settler) {
            i = t.getImage("images/settler.png");
            xMove = -5;
            yMove = -35;
        }


        if (entity instanceof Ufo) {
            i = t.getImage("images/ufo.png");
            xMove = -5;
            yMove = 35;

        }

        if (entity instanceof Robot) {
            i = t.getImage("images/robot.png");
            xMove = -35;
            yMove = 0;
        }


        g.drawImage(i, x + xMove, y + yMove, 40, 40, null);

    }
}
