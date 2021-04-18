import java.util.ArrayList;
import java.util.Random;

/**
 * The type Field.
 */
public class Field implements Steppable {

    private final ArrayList<Asteroid> asteroids = new ArrayList();
    private final ArrayList<Settler> settlers = new ArrayList();
    private final ArrayList<Settler> ufos = new ArrayList();
    private int counter=0;

    public Field(int settlernumber, int maxthickness){
        //alap aszteroidak
        asteroids.add(new Asteroid(1 , 3, true, new Ice()));
        asteroids.add(new Asteroid(2 , 3, true, new Coal()));
        asteroids.add(new Asteroid(3 , 3, true, new Iron()));
        asteroids.add(new Asteroid(4 , 3, true, new Uranium()));
        asteroids.add(new Asteroid(5 , 3, true, null));

        //random aszteroidak, telepesenkent +10db
        for(int i=6; i<settlernumber*10+6; i++){
            Random rand=new Random();
            Material randmaterial;

            switch(rand.nextInt(10000000)%5){
                case 0: randmaterial=null;
                case 1: randmaterial=new Ice();
                case 2: randmaterial=new Coal();
                case 3: randmaterial=new Iron();
                case 4: randmaterial=new Uranium();
                default: randmaterial=null;
            }

            asteroids.add(new Asteroid(i ,rand.nextInt(10000000)%maxthickness, rand.nextInt(10000000)%2==0, randmaterial));
        }

        //akkor lesznek szomszedok ha az idjuk osszege oszthato 4el

        for(Asteroid a: asteroids){
            for(int i=0; i<settlernumber*10+6; i++){
                if((a.getid()+i)%4==0){
                    a.getNeigbours().add(asteroids.get(i));
                }
            }
        }

        Random rand=new Random();
        int k=0;

        for(int i=0; i<settlernumber; i++){
            Settler s= new Settler(i);
            int randasteroid=rand.nextInt(asteroids.size());
            asteroids.get(randasteroid).Accept(s);
            s.setAsteroid(asteroids.get(randasteroid));


            if(i%5==0){
                Ufo u= new Ufo(k);
                randasteroid=rand.nextInt(asteroids.size());
                asteroids.get(randasteroid).Accept(u);
                u.setAsteroid(asteroids.get(randasteroid));
                Timer.getInstance().AddSteppable(u);

                k++;
            }
        }

        Timer.getInstance().setSettlernumber(settlernumber);
        Timer.getInstance().AddSteppable(this);
        for(Settler s: settlers){
            Timer.getInstance().AddSteppable(s);
        }

    }


    @Override
    public void Step() {
        SetNearSun();
        SetSunStorm();
    }

    /**
     * Set sun storm.
     * <p>
     * Set sunstorm for all asteroids
     */
    public void SetSunStorm() {
        //valamennyi tickenként
        for (Asteroid a : asteroids) {
            if(a.isNearSun()) {
                a.Sunstorm();
            }
        }
    }

    /**
     * Set near sun.
     * <p>
     * Set NearSun for specified asteroids and check if any asteroid is triggered
     */
    public void SetNearSun() {

        //TODO: valami alapján truera vagy falsera állítani
        for (Asteroid a : asteroids) {
            a.setNearSun(true);
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


    /**
     * Check if the settlers on an asteroid have all the required materials for the win.
     *
     * @param a the asteroid on we check if the required materials are on.
     * @return the boolean
     */
    boolean CheckReqMat(Asteroid a){
        int coal = 3;
        int ice = 3;
        int iron = 3;
        int uran = 3;

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

        return coal == 0 && iron == 0 && uran == 0 && ice == 0;
    }

    /**
     * Generate a random material material.
     *
     * @return the random material
     */
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


    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public ArrayList<Settler> getSettlers() {
        return settlers;
    }


    /**
     * Add settler.
     *
     * @param s the settler to add.
     */
    public void AddSettler(Settler s) {
        settlers.add(s);
    }

    /**
     * Add asteroid.
     *
     * @param a the asteroid to add.
     */
    public void AddAsteroid(Asteroid a) {asteroids.add(a);}
}
