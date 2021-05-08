package view;

import model.Teleport;

import java.awt.*;

public class TeleportView implements Drawable {
    private final Teleport teleport;
    private int _x, _y, size;

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public int getSize() {
        return size;
    }

    public Teleport getTeleport() {
        return teleport;
    }

    public TeleportView(Teleport teleport) {
        this.teleport = teleport;
    }

    @Override
    public void draw(Graphics g, int unit, int x, int y) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i;

        i = t.getImage("images/teleport.png");

        _x = x - 15;
        _y = y - 15;
        size = unit / 3;

        g.drawImage(i, _x, _y, size, size, null);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial Black", Font.BOLD, 12));
        g.drawString(String.valueOf(teleport.getId()), x - 7, y);

    }
}
