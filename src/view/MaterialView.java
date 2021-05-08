package view;

import model.*;

import java.awt.*;

/**
 * The type Material view.
 */
public class MaterialView implements Drawable {
    private final Material material;

    /**
     * Instantiates a new Material view.
     *
     * @param material the material
     */
    public MaterialView(Material material) {
        this.material = material;
    }


    @Override
    public void draw(Graphics g, int unit, int x, int y) {
        int coord = unit / 2;
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = null;
        //material képének betöltése
        if (material instanceof Ice)
            i = t.getImage("images/ice.png");

        if (material instanceof Iron)
            i = t.getImage("images/iron.png");

        if (material instanceof Uranium)
            i = t.getImage("images/uranium.png");

        if (material instanceof Coal)
            i = t.getImage("images/coal.png");

        //material kirajzolása
        g.drawImage(i, x + coord / 2, y + coord / 2, coord, coord, null);
    }
}
