package view;

import model.Asteroid;
import model.Material;

import java.awt.*;

public class MaterialView implements Drawable {
    private Material material;

    public MaterialView useMaterial(Material material) {
        this.material = material;
        return this;
    }



    @Override
    public void draw(Graphics g) {

    }
}
