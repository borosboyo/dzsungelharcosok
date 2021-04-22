package view;

import model.Asteroid;

import java.awt.*;

public class AsteroidView implements Drawable {
    private Asteroid asteroid;

    public AsteroidView(Asteroid asteroid) {
        this.asteroid = asteroid;
    }


    @Override
    public void draw(Graphics g, int unit) {
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i;

        i = t.getImage("images/asteroid.png");


        g.drawImage(i, 10,10,5, 5, null);

    }
}
