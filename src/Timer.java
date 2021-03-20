import java.util.ArrayList;

/**
 * Timer singleton class that determines when the Field and Robot steps.
 */
public class Timer {
    /**
     * The constant timer.
     */
    private static Timer timer = new Timer();

    /**
     * Private constructor so the Timer stays singleton.
     */
    private Timer(){}

    /**
     * Get the only instance of timer.
     *
     * @return the timer
     */
    public static Timer getInstance(){
        return timer;
    }

    /**
     * The ArrayList that stores all the steppable objects of the game.
     */
    private ArrayList<Steppable> steppables;

    /**
     * Add an object to the list.
     *
     * @param s the object that implements the Steppable interface.
     */
    public void AddSteppable(Steppable s){
        steppables.add(s);
    }

    /**
     * Remove an object from the list.
     *
     * @param s the object that implements the Steppable interface.
     */
    public void RemoveSteppable(Steppable s){
        steppables.remove(s);
    }

    /**
     * Tick that happens every X seconds or rounds.
     */
    public void Tick(){
        for (Steppable steppable : steppables) {
            steppable.Step();
        }
    }

}
