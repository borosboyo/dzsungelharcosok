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
    public void draw(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i;

        if(entity instanceof Settler)
            i = t.getImage("images/settler.jpg");

        if(entity instanceof Ufo)
            i = t.getImage("images/ufo.jpg");

        if(entity instanceof Robot)
            i = t.getImage("images/robot.jpg");

        g.drawImage(i, x, y, size, size, null);

    }
}
