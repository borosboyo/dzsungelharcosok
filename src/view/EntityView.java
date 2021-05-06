package view;

import model.Entity;
import model.Robot;
import model.Settler;
import model.Ufo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EntityView implements Drawable {
    private Entity entity;
    private ArrayList<EntityView> entityViews;
    private int _x, _y, size;

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int getSize() {
        return size;
    }

    public Entity getEntity() {
        return entity;
    }

    public EntityView(Entity entity, ArrayList<EntityView> entityViews) {
        this.entity = entity;
        this.entityViews = entityViews;
    }

    public AlphaComposite setTransparenty(int i) {

        float alpha = i * 0.1f;
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }


    @Override
    public void draw(Graphics g, int unit, int x, int y) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = null;

        if (entity instanceof Settler) {
            if (((Settler) entity).isSelected()) {
                // i = t.getImage("images/settlerselected.png");
                try {
                    i = ImageIO.read(new File("images/settlerselected.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //i = t.getImage("images/settlerselected.png");
                try {
                    i = ImageIO.read(new File("images/settler.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if (((Settler) entity).isFinishedTurn()) {
                ((Graphics2D) g).setComposite(setTransparenty(3));
            }

            entityViews.add(this);
        }


        if (entity instanceof Ufo) {
            i = t.getImage("images/ufo.png");


        }

        if (entity instanceof Robot) {
            i = t.getImage("images/robot.png");

        }

        _x = x - 15;
        _y = y - 15;
        size = unit / 2;

        g.drawImage(i, _x, _y, size, size, null);
        ((Graphics2D) g).setComposite(setTransparenty(10));
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Arial Black", Font.BOLD, 15));
        g.drawString(String.valueOf(entity.getId()), x, y + 30);
    }

}
