package view;

import model.Asteroid;
import model.Entity;

import java.awt.*;

public class EntityView implements Drawable {
    private Entity entity;

    public EntityView useEntity(Entity entity) {
        this.entity = entity;
        return this;
    }



    @Override
    public void draw(Graphics g) {

    }
}
