import java.util.ArrayList;

/**
 * The type Teleport.
 */
public class Teleport {
    private ArrayList<Asteroid> asteroids;

    /**
     * Transfer.
     *
     * @param e the entity using the teleport
     */
    public void Transfer(Entity e){
        Asteroid CurrentAsteroid = null;
        Asteroid DestAsteroid = null;
        if (asteroids.size() == 2) {
            CurrentAsteroid = e.getAsteroid();
            if(asteroids.get(0).equals(CurrentAsteroid)) {
                DestAsteroid = asteroids.get(1);
            }
            else { DestAsteroid = asteroids.get(0); }
        }
        DestAsteroid.Accept(e);
        CurrentAsteroid.Remove(e);
    }

    /**
     * Remove asteroid.
     *
     * @param a the asteroid to remove
     */
    public void RemoveAsteroid(Asteroid a){
        asteroids.remove(a);
    }

    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void setAsteroids(ArrayList<Asteroid> asteroids) {
        this.asteroids = asteroids;
    }


}
