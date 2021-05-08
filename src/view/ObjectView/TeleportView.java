package view.ObjectView;

import model.Objects.Teleport;
import view.Drawable;

import java.awt.*;

/**
 * The type Teleport view.
 */
public class TeleportView implements Drawable {
    private final Teleport teleport;
    private int _x, _y, size;

    /**
     * Instantiates a new Teleport view.
     *
     * @param teleport the teleport
     */
    public TeleportView(Teleport teleport) {
        this.teleport = teleport;
    }

    @Override
    public void draw(Graphics g, int unit, int x, int y) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i;
        //teleport képének betöltése
        i = t.getImage("images/teleport.png");
        //elhelyezés korrigálása
        _x = x - unit/4;
        _y = y - unit/4;
        size = unit / 2;
        //teleport kirajzolása
        g.drawImage(i, _x, _y, size, size, null);

        //id kiiratása párok azonosítása miatt
        g.setColor(Color.RED);
        g.setFont(new Font("Arial Black", Font.BOLD, 12));
        g.drawString(String.valueOf(teleport.getId()), _x+(size/2), _y+(size/2));
    }

    /**
     * Gets x.
     *
     * @return the x coord
     */
    public int getX() {
        return _x;
    }

    /**
     * Gets y.
     *
     * @return the y coord
     */
    public int getY() {
        return _y;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets teleport.
     *
     * @return the teleport
     */
    public Teleport getTeleport() {
        return teleport;
    }

}
