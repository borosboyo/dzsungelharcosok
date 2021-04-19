/**
 * Coal type of material.
 */
public class Coal extends Material{

    /**
     * Coal does not have a trigger event.
     */
    @Override
    public void Trigger() {

    }

    /**
     * Getter Name
     * @return with the material name
     */
    @Override
    public String getName(){
        return "Coal";
    }
}
