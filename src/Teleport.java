import java.util.ArrayList;

public class Teleport {
    private ArrayList<Asteroid> asteroids;

    public void Transfer(Entity e){
        Asteroid a1 = null;
        Asteroid a2 = null;
        if (asteroids.size() == 2) {
            a1 = e.getAsteroid();
            if(asteroids.get(0).equals(a1)) {
                a2 = asteroids.get(1);
            }
            else { a2 = asteroids.get(0); }
        }
        a2.Accept(e);
        a1.Remove(e);
    }
    public void RemoveAsteroid(Asteroid a){}


}
