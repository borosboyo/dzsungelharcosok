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

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i = null;

        if(entity instanceof Settler)
            i = t.getImage("images/settler.png");

        if(entity instanceof Ufo)
            i = t.getImage("images/ufo.png");

        if(entity instanceof Robot)
            i = t.getImage("images/robot.png");


        g.drawImage(i, x-5, y-40, 40, 40, null);

    }
}
