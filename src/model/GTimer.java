package model;

import java.util.ArrayList;

/**
 * Timer singleton class that determines when the Field and Robot steps.
 */
public class GTimer {//TODO:: ha nincsen main akkor ezt vissza lehet írni Timer-re
    /**
     * The ArrayList that stores all the steppable objects of the game.
     */
    private final ArrayList<Steppable> steppables;

    /**
     * The constant timer.
     */
    public static GTimer GTimer = new GTimer();

    /**
     * Private constructor so the Timer stays singleton.
     */
    private GTimer() {
        steppables = new ArrayList();
    }

    /**
     * Get the only instance of timer.
     *
     * @return the timer
     */
    public static GTimer getInstance() {
        return GTimer;
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
