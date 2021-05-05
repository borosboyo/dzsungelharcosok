package view;

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

    public AlphaComposite setTransparenty(int i) {

        float alpha = i * 0.1f;
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }


    @Override
    public void draw(Graphics g, int unit, int x, int y) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = null;

        if (entity instanceof Settler) {
            if(((Settler) entity).isSelected()) {
                i = t.getImage("images/settler-selected.png");
            }else {
                i = t.getImage("images/settler.png");
            }


            if (((Settler) entity).isFinishedTurn()) {
                ((Graphics2D) g).setComposite(setTransparenty(3));
            }
        }


        if (entity instanceof Ufo) {
            i = t.getImage("images/ufo.png");


        }

        if (entity instanceof Robot) {
            i = t.getImage("images/robot.png");

        }


        g.drawImage(i, x-15 , y-15 , unit/2, unit/2, null);
        ((Graphics2D) g).setComposite(setTransparenty(10));
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Arial Black", Font.BOLD, 15));
       g.drawString(String.valueOf(entity.getId()), x, y + 30);
    }
}
