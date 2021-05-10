package view.ObjectView;


import model.Objects.Entity;
import model.Objects.Settler;
import model.Objects.Ufo;
import model.Objects.Robot;
import view.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Entity view.
 */
public class EntityView implements Drawable {
    private final Entity entity;
    private final ArrayList<EntityView> entityViews;
    private int _x, _y, size;

    /**
     * Instantiates a new Entity view.
     *
     * @param entity      the entity
     * @param entityViews the entity views
     */
    public EntityView(Entity entity, ArrayList<EntityView> entityViews) {
        this.entity = entity;
        this.entityViews = entityViews;
    }

    /**
     * Sets transparenty.
     *
     * @param i the transparenty scale
     * @return the transparenty
     */
    public AlphaComposite setTransparenty(int i) {
        float alpha = i * 0.1f;
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }


    @Override
    public void draw(Graphics g, int unit, int x, int y) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = null;
        //ha az entitás egy telepes
        if (entity instanceof Settler) {
            //ha kivan választva
            if (((Settler) entity).isSelected()) {
                try {//kép betöltése
                    i = ImageIO.read(new File("images/settlerselected.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else { // ha nincs kiválasztva
                try {//kép betöltése
                    i = ImageIO.read(new File("images/settler.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //ha már befejezte a körét a teleps akkor legyen átlátszóbb
            if (((Settler) entity).isFinishedTurn()) {
                ((Graphics2D) g).setComposite(setTransparenty(3));
            }
            entityViews.add(this);
        }

        //ha az entitás ufo
        if (entity instanceof Ufo) {
            i = t.getImage("images/ufo.png");
        }
        //ha az entitás robot
        if (entity instanceof Robot) {
            i = t.getImage("images/robot.png");
        }

        //elhelyezés korrigálása
        _x = x-unit/4;
        _y = y-unit/4;
        //méret beállítása
        size = unit / 2;
        //kirajzoltatás
        g.drawImage(i, _x, _y, size, size, null);
        ((Graphics2D) g).setComposite(setTransparenty(10));
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return _x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return _y;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets entity.
     *
     * @return the entity
     */
    public Entity getEntity() {
        return entity;
    }

}
