import java.util.ArrayList;

public class Field implements Steppable{
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Settler> settlers;

    @Override
    public void Step() { }

    public void SetSunStorm(){}
    public void SetNearSun(){}
    public void RemoveSettler(Settler s){}
    public void RemoveAsteroid(Asteroid a){}


}
