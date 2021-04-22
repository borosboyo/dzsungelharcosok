package view;

import model.*;
import model.Robot;

import java.awt.*;

public class MaterialView implements Drawable {
    private Material material;

    public MaterialView(Material material) {
        this.material = material;
    }



    @Override
    public void draw(Graphics g) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i;
        if(material instanceof Ice)
            i = t.getImage("images/ice.jpg");

        if(material instanceof Iron)
            i = t.getImage("images/iron.jpg");

        if(material instanceof Uranium)
            i = t.getImage("images/uranium.jpg");

        if(material instanceof Coal)
            i = t.getImage("images/coal.jpg");



        g.drawImage(i, x, y, size, size, null);


    }
}
