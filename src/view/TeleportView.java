package view;

import model.Asteroid;
import model.Teleport;

import java.awt.*;

public class TeleportView implements Drawable {
    private Teleport teleport;

    public TeleportView(Teleport teleport) {
        this.teleport = teleport;
    }



    @Override
    public void draw(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i;

        i = t.getImage("images/teleport.jpg");


        g.drawImage(i, x, y, size, size, null);


    }
}
