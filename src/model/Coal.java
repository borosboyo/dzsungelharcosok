package model;

/**
 * model.Coal type of material.
 */
public class Coal extends Material{

    /**
     * model.Coal does not have a trigger event.
     */
    @Override
    public void Trigger() {

    }
    /**
     * Returns name of the material
    */
    @Override
    public String getName(){
        return "model.Coal";
    }
}
