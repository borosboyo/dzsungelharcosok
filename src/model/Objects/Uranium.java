package model.Objects;

/**
 * Uranium, the radioactive type of Material.
 */
public class Uranium extends Material{
    public int getExponation() {
        return exponation;
    }

    public void setExponation(int exponation) {
        this.exponation = exponation;
    }

    int exponation = 0;
    /**
     * If the material is triggered the asteroid explodes,
     * that kills all settlers and blows away all robots which are on the asteroid.
     * The trigger happens, if the asteroid is near the sun and the core is exposed.
     */
    @Override
    public void Trigger() {
        exponation++;
        if(exponation >= 3 && asteroid != null){
            asteroid.Explode();
        }

    }

    /**
     * Returns name of the material
     */
    @Override
    public String getName(){
        return "Uranium[" + exponation + "]";
    }
}
