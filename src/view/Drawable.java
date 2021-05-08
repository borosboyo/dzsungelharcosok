package view;

import java.awt.*;

/**
 * The interface Drawable.
 */
public interface Drawable {
    /**
     * Draw.
     *
     * @param g    the graphics class
     * @param unit the unit of the views for consistency
     * @param x    the x coord
     * @param y    the y coord
     */
    void draw(Graphics g, int unit, int x, int y);
}
