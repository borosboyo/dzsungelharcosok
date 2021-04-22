package view;

import model.Asteroid;

import java.awt.*;

public class AsteroidView implements Drawable {
    private Asteroid asteroid;

    public AsteroidView useAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
        return this;
    }


    @Override
    public void draw(Graphics g) {

    }
}
