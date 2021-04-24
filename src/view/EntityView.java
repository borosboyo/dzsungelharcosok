package view;

import model.Asteroid;
import model.Entity;
import model.Robot;
import model.Settler;
import model.Ufo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.util.logging.Filter;

public class EntityView implements Drawable {
    private Entity entity;

    public EntityView(Entity entity) {
        this.entity = entity;
    }

    public AlphaComposite setTransparenty(int i) {

        float alpha = i * 0.1f;
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
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

            if (((Settler) entity).isFinishedTurn()) {
                ((Graphics2D) g).setComposite(setTransparenty(3));
            }

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
        ((Graphics2D) g).setComposite(setTransparenty(10));
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial Black", Font.BOLD, 15));
        g.drawString(String.valueOf(entity.getId()), x + 50, y + 50);
    }
}
