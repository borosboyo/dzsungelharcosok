package view;

import model.Asteroid;
import model.Entity;

import java.awt.*;

public class AsteroidView implements Drawable {
    private Asteroid asteroid;
    private MaterialView matView;

    public AsteroidView(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    @Override
    public void draw(Graphics g, int unit, int _x, int _y) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i;
        if(asteroid.isNearSun()) {
            i = t.getImage("images/asteroid-nearsun.png");
        }else {
            i = t.getImage("images/asteroid.png");
        }

        int x = asteroid.getX();
        int y = asteroid.getY();

        g.drawImage(i, x, y, unit, unit, null);

        /**
         *Teleport, Entity, Material draw
         */
        for(int j = 0; j < asteroid.getEntities().size(); j++ ){
            EntityView e = new EntityView(asteroid.getEntities().get(j));
            e.draw(g, unit, x, y);

        }



        asteroid.getEntities().stream().map(entity -> new EntityView(entity)).forEach(entityView -> entityView.draw(g, unit, x, y));
        asteroid.getTeleports().stream().map(teleport -> new TeleportView(teleport)).forEach(teleportView -> teleportView.draw(g, unit, x, y));
        matView = new MaterialView(asteroid.getMaterial());
        matView.draw(g, unit, x, y);
    }
}
