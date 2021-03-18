import java.util.ArrayList;

public class Timer {
    //Singleton
    public static Timer timer = new Timer();

    private ArrayList<Steppable> steppables;

    public void RemoveSteppable(Steppable s){
        steppables.remove(s);
    }

    public void Tick(){}

}
