package model;

/**
 * Ice type of Material.
 */
public class Ice extends Material{

    /**
     * If the material is triggered it sublimates from the inside of the asteroid.
     * The trigger happens, if the asteroid is near the sun and the core is exposed.
     */
    @Override
    public void Trigger() {
        if(asteroid != null){
            asteroid.RemoveMaterial();
        }
    }

    /**
     * Name getter
     * @return with the material name
     */
    @Override
    public String getName(){
        return "Ice";
    }

}
