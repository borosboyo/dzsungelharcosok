package view.ObjectView;

import model.Objects.Asteroid;
import view.Drawable;

import java.awt.*;
import java.util.ArrayList;

/**
 * The type Asteroid view.
 */
public class AsteroidView implements Drawable {
    private final Asteroid asteroid;
    private MaterialView matView;
    private final ArrayList<EntityView> entityViews;
    private final ArrayList<TeleportView> teleportViews;

    /**
     * Instantiates a new Asteroid view.
     *
     * @param asteroid      the asteroid
     * @param entityViews   the entity views
     * @param teleportViews the teleport views
     */
    public AsteroidView(Asteroid asteroid, ArrayList<EntityView> entityViews, ArrayList<TeleportView> teleportViews) {
        this.asteroid = asteroid;
        this.entityViews = entityViews;
        this.teleportViews = teleportViews;
    }

    @Override
    public void draw(Graphics g, int unit, int _x, int _y) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i, i2;
        //betöltjük az aszteroida képét, attól függően, hogy napközeli e vagy nem
        if (asteroid.isNearSun()) {
            i = t.getImage("images/asteroid-nearsun.png");
        } else {
            i = t.getImage("images/asteroid.png");
        }

        int x = asteroid.getX();
        int y = asteroid.getY();
        //kirajzoljuk az aszteroidát
        g.drawImage(i, x, y, unit, unit, null);
        //betöltjük a kéreg méretét mutató képet, majd kirajzoljuk
        i2 = t.getImage("images/CrustBar_" + asteroid.getCrustThickness() + ".png");
        g.drawImage(i2, x + 8, y + 8, unit - 15, unit - 15, null);

        /**
         *Teleport, Entity, Material draw
         */
        //entitások és teleportok száma az aszteroidán
        int objects = asteroid.getEntities().size() + asteroid.getTeleports().size();
        if (objects != 0) {
            int r = unit / 2;
            //kirajzoljuk az objektumat az aszteroda köré esztétikusan
            for (int j = 0; j < objects; j++) {
                double angle = Math.toRadians(360 / objects);
                int new_x = (int) ((x + r) + ((r + (unit / 4)) * Math.cos(angle * (j + 1))));
                int new_y = (int) ((y + r) + ((r + (unit / 4)) * Math.sin(angle * (j + 1))));
                if (j < asteroid.getEntities().size()) {  //entitások rajzolása
                    EntityView ev = new EntityView(asteroid.getEntities().get(j), entityViews);
                    ev.draw(g, unit, new_x, new_y);
                } else {   //teleportok rajzolása
                    TeleportView tv = new TeleportView(asteroid.getTeleports().get((j - asteroid.getEntities().size())));
                    tv.draw(g, unit, new_x, new_y);
                    teleportViews.add(tv);
                }
            }
        }
        //nyersanyag kirajzolása
        matView = new MaterialView(asteroid.getMaterial());
        matView.draw(g, unit, x, y);
    }
}
