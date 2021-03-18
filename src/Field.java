import java.util.ArrayList;

/**
 * The type Field.
 */
public class Field implements Steppable {
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Settler> settlers;

    @Override
    public void Step() {
        SetNearSun();
        SetSunStorm();
    }


    /**
     * Set sun storm.
     *
     * Set sunstorm for all asteroids
     */
    public void SetSunStorm() {
        //valamennyi tickenként
        for (Asteroid a : asteroids) {
            a.Sunstorm();
        }
    }

    /**
     * Set near sun.
     *
     * Set NearSun for specified asteroids and check if any asteroid is triggered
     */
    public void SetNearSun() {
        //valami alapján átállitja az asteroidáknál a nearsunt
        for (Asteroid a : asteroids) {
            a.CheckTrigger();
        }
    }

    /**
     * Remove settler.
     *
     * @param s the settler to remove
     */
    public void RemoveSettler(Settler s) {
        settlers.remove(s);
    }

    /**
     * Remove asteroid.
     *
     * @param a the asteroid to remove
     */
    public void RemoveAsteroid(Asteroid a) {
        asteroids.remove(a);
    }

}
