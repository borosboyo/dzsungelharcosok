package view;

import model.Asteroid;

import java.awt.*;
import java.util.Random;

public class AsteroidView implements Drawable {
    private Asteroid asteroid;
    private  MaterialView matView;
    public AsteroidView(Asteroid asteroid) {
        this.asteroid = asteroid;
    }


    @Override
    public void draw(Graphics g, int unit, int _x, int _y) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i;

        i = t.getImage("images/asteroid.png");

        int x = asteroid.getX();
        int y = asteroid.getY();

        g.drawImage(i,x ,y,50, 50, null);

        /**
         *  Teleport, Entity, Material draw
         */
        asteroid.getEntities().stream().map(entity -> new EntityView(entity)).forEach(entityView -> entityView.draw(g, unit, x, y)); //TODO::lehetne azt, hogy amelyik teleps nem lépett az halványan látszódik, de akár fordítva is, ezt is a transparentel lehetne állítani
        asteroid.getTeleports().stream().map(teleport -> new TeleportView(teleport)).forEach(teleportView -> teleportView.draw(g, unit, x, y));
        matView = new MaterialView(asteroid.getMaterial());
        matView.draw(g, unit, x, y); //TODO::lehetne azt, hogy csak akkor látszódik, ha már teljesen ki van fúrva (transparent használatával nem kell a rajzolás sorrendes változtatni
    }
}
