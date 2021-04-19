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
        asteroid.RemoveMaterial();
    }

    @Override
    public String getName(){
        return "Ice";
    }

}
