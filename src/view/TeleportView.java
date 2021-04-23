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
    public void draw(Graphics g, int unit, int x, int y) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i;

        i = t.getImage("images/teleport.png");

        g.drawImage(i, x + 50, y + 15, 20, 20, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial Black", Font.BOLD, 15));
        g.drawString(String.valueOf(teleport.getId()), x + 50, y + 50);

    }


}
