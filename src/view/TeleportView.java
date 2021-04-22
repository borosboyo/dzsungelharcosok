package view;

import model.Asteroid;
import model.Teleport;

import java.awt.*;

public class TeleportView implements Drawable {
    private Teleport teleport;

    public TeleportView useTeleport(Teleport teleport) {
        this.teleport = teleport;
        return this;
    }



    @Override
    public void draw(Graphics g) {

    }
}
