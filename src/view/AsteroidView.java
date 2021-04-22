package view;

import model.Asteroid;

import java.awt.*;

public class AsteroidView implements Drawable {
    private Asteroid asteroid;

    public AsteroidView(Asteroid asteroid) {
        this.asteroid = asteroid;
    }


    @Override
    public void draw(Graphics g) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i;
        i = t.getImage("images/asteroid.jpg");


        g.drawImage(i, x, y, size, size, null);

    }
}
