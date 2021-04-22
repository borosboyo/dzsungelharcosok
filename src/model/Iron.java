package model;

/**
 * model.Iron type of material.
 */
public class Iron  extends Material{

    /**
     * model.Iron does not have a trigger event.
     */
    @Override
    public void Trigger() {

    }

    /**
     * Name getter
     * @return with the model.Material name
     */
    @Override
    public String getName(){
        return "model.Iron";
    }
}
