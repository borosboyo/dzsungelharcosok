import java.util.ArrayList;

/**
 * Timer singleton class that determines when the Field and Robot steps.
 */
public class Timer {
    /**
     * The ArrayList that stores all the steppable objects of the game.
     */
    private ArrayList<Steppable> steppables;
    private int counter=0;
    private int settlernumber=0;

    /**
     * The constant timer.
     */
    public static Timer timer = new Timer();

    /**
     * Private constructor so the Timer stays singleton.
     */
    private Timer(){ steppables = new ArrayList();}

    public int getCounter() {
        return counter;
    }

    public void setCounter(int i){
        counter=i;
    }

    public void setSettlernumber(int i){
        settlernumber=i;
    }

    /**
     * Get the only instance of timer.
     *
     * @return the timer
     */
    public static Timer getInstance(){
        return timer;
    }

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
