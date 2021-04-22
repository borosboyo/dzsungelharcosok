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
    public void draw(Graphics g, int unit) {

        Toolkit t=Toolkit.getDefaultToolkit();
        Image i = null;
        if(material instanceof Ice)
            i = t.getImage("images/ice.png");

        if(material instanceof Iron)
            i = t.getImage("images/iron.png");

        if(material instanceof Uranium)
            i = t.getImage("images/uranium.png");

        if(material instanceof Coal)
            i = t.getImage("images/coal.png");



        g.drawImage(i, 10,10, 5, 5, null);


    }
}
