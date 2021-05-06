package view;

import model.Asteroid;
import view.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class AsteroidView implements Drawable {
    private Asteroid asteroid;
    private MaterialView matView;
    private ArrayList<EntityView> entityViews;
    private ArrayList<TeleportView> teleportViews;

    public AsteroidView(Asteroid asteroid, ArrayList<EntityView> entityViews, ArrayList<TeleportView> teleportViews) {
        this.asteroid = asteroid;
        this.entityViews = entityViews;
        this.teleportViews = teleportViews;
    }

    @Override
    public void draw(Graphics g, int unit, int _x, int _y) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i, i2;
        if (asteroid.isNearSun()) {
            i = t.getImage("images/asteroid-nearsun.png");
        } else {
            i = t.getImage("images/asteroid.png");
        }

        int x = asteroid.getX();
        int y = asteroid.getY();

        g.drawImage(i, x, y, unit, unit, null);

        i2 = t.getImage("images/CrustBar_" + asteroid.getCrustThickness() + ".png");
        g.drawImage(i2, x + 8, y + 8, unit - 15, unit - 15, null);
          g.setColor(Color.white);
         g.setFont(new Font("Arial Black", Font.BOLD, 15));
          g.drawString(String.valueOf(asteroid.getId()), x + 50, y + 50);

        /**
         *Teleport, Entity, Material draw
         */
        int objects = asteroid.getEntities().size() + asteroid.getTeleports().size();
        if (objects != 0) {

            int r = unit / 2;
            for (int j = 0; j < objects; j++) {
                double angle = Math.toRadians(360 / objects);
                int new_x = (int) ((x + r) + ((r + (unit / 4)) * Math.cos(angle * (j + 1))));
                int new_y = (int) ((y + r) + ((r + (unit / 4)) * Math.sin(angle * (j + 1))));
                if (j < asteroid.getEntities().size()) {
                    EntityView ev = new EntityView(asteroid.getEntities().get(j), entityViews);
                    ev.draw(g, unit, new_x, new_y);
                } else {
                    TeleportView tv = new TeleportView(asteroid.getTeleports().get((j - asteroid.getEntities().size())));
                    tv.draw(g, unit, new_x, new_y);
                    teleportViews.add(tv);
                }
            }
        }

        //asteroid.getEntities().stream().map(entity -> new EntityView(entity)).forEach(entityView -> entityView.draw(g, unit, x, y));
        //asteroid.getTeleports().stream().map(teleport -> new TeleportView(teleport)).forEach(teleportView -> teleportView.draw(g, unit, x, y));
        matView = new MaterialView(asteroid.getMaterial());
        matView.draw(g, unit, x, y);
    }
}
