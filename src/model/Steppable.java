package model;

/**
 * model.Steppable interface that the classes model.Robot and model.Field implements.
 */
public interface Steppable {

    int counter=0;

    /**
     * The Step function that determines what action should a model.Robot or the model.Field do in a step.
     */
    public void Step();


}
