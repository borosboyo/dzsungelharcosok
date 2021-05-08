package model.Objects;

/**
 * Iron type of material.
 */
public class Iron  extends Material{

    /**
     * Iron does not have a trigger event.
     */
    @Override
    public void Trigger() {

    }

    /**
     * Name getter
     *
     * @return with the Material name
     */
    @Override
    public String getName(){
        return "Iron";
    }
}
