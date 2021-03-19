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

    boolean CheckReqMat(Asteroid a){
        boolean required = false;

        ArrayList<Material> required_material = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            required_material.add(new Coal());
            required_material.add(new Ice());
            required_material.add(new Uranium());
            required_material.add(new Iron());
        }


        for(int i= 0; i < settlers.size(); i++){
            if(settlers.get(i).getAsteroid() == a){
                settlers.get(i).getInventory();
            }
        }


        if(required){
            Game.game.WinGame();
        }

        return false;
    }


}
