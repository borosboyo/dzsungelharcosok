import java.util.ArrayList;
import java.util.Random;

/**
 * The type Field.
 */
public class Field implements Steppable {
    private ArrayList<Asteroid> asteroids = new ArrayList();
    private ArrayList<Settler> settlers = new ArrayList();

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
        int coal = 3;
        int ice = 3;
        int iron = 3;
        int uran = 3;

        /*
        ArrayList<Material> required_material = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            required_material.add(new Coal());
            required_material.add(new Ice());
            required_material.add(new Uranium());
            required_material.add(new Iron());
        }*/

        ArrayList<Material> settler_mat;

        for (Settler settler : settlers) {
            if (settler.getAsteroid() == a) {
                settler_mat = settler.getInventory();

                for (Material material : settler_mat) {
                    if (material instanceof Coal) {
                        coal--;
                    } else if (material instanceof Iron) {
                        iron--;
                    } else if (material instanceof Uranium) {
                        uran--;
                    } else if (material instanceof Ice) {
                        ice--;
                    }
                }
            }
        }

        if(coal == 0 && iron == 0 && uran == 0 && ice ==0){
            return true;
        }

        return false;
    }

    //TODO: az aszteroida paraméterében kell majd átadni
    Material RandomMaterial(){
        Random rand = new Random();
        Material mat = null;

        int r_num = rand.nextInt(5);
        switch (r_num){
            case 0 -> mat = new Uranium();
            case 1 -> mat = new Ice();
            case 2 -> mat = new Coal();
            case 3 -> mat = new Iron();
            case 4 -> mat = null;
        }

        return mat;
    }

    public void AddSettler(Settler s) {
        settlers.add(s);
    }
}
