package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type model.Field.
 */
public class Field implements Steppable, Serializable {
    private final ArrayList<Asteroid> asteroids = new ArrayList();
    private final ArrayList<Settler> settlers = new ArrayList();
    private final ArrayList<Robot> robots = new ArrayList();
    private final ArrayList<Ufo> ufos = new ArrayList();
    private int sunstormcounter=0;

    /**
     * model.Field initialization (Add Asteroids, Settlers and Ufos)
     * @param settlernumber number of the settlers in the game
     * @param maxthickness max crust thickness
     */
    public void newField(int settlernumber, int maxthickness){
        //alap aszteroidak
        asteroids.add(new Asteroid(0 , 3, true, new Ice()));
        asteroids.add(new Asteroid(1 , 3, true, new Coal()));
        asteroids.add(new Asteroid(2 , 3, true, new Iron()));
        asteroids.add(new Asteroid(3 , 3, true, new Uranium()));
        asteroids.add(new Asteroid(4 , 3, true, null));

        //random aszteroidak, telepesenkent +10db
        for(int i=5; i<settlernumber*10+5; i++){
            Random rand=new Random();
            asteroids.add(new Asteroid(i ,rand.nextInt(10000000)%maxthickness, rand.nextInt(10000000)%2==0, RandomMaterial()));
        }

        //akkor lesznek szomszedok ha az idjuk osszege oszthato 4el

        for(Asteroid a: asteroids){
            a.setX(0);
            a.setY(0);
            for(int i=0; i<settlernumber*10+5; i++){
                if((a.getId()+i)%4==0){
                    a.getNeigbours().add(asteroids.get(i));
                }
            }
        }
        int x = 0;
        for (Asteroid a : asteroids) {
            x++;
            int y = 0;
            for (Asteroid a2 : asteroids) {
                y++;
                if (a.getNeigbours().contains(a2)) {



                }
            }
        }

        Random rand=new Random();
        int k=0;

        for(int i=0; i<settlernumber; i++){
            Settler s= new Settler(k);
            int randasteroid=rand.nextInt(asteroids.size());
            asteroids.get(randasteroid).Accept(s);
            settlers.add(s);
            s.setAsteroid(asteroids.get(randasteroid));
            k++;

            if(i%5==0){
                Ufo u= new Ufo(k);
                randasteroid=rand.nextInt(asteroids.size());
                asteroids.get(randasteroid).Accept(u);
                u.setAsteroid(asteroids.get(randasteroid));
                Timer.getInstance().AddSteppable(u);
                ufos.add(u);
                k++;
            }
        }

        Timer.getInstance().setSettlernumber(settlernumber);
        Timer.getInstance().AddSteppable(this);
        for(Settler s: settlers){
            Timer.getInstance().AddSteppable(s);
        }
    }

    /**
     * model.Game state stepping
     */
    @Override
    public void Step() {
        SetNearSun();
        sunstormcounter++;
        if(sunstormcounter==10){
            SetSunStorm();
            sunstormcounter=0;
        }
    }

    /**
     * Set sun storm.
     * <p>
     * Set sunstorm for all asteroids
     */
    public void SetSunStorm() {
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
        Random rnd= new Random();
        for (Asteroid a : asteroids) {
            if(a.getId()%(rnd.nextInt(asteroids.size()-1)+1)==0) {
                a.setNearSun(true);
                a.CheckTrigger();
            }else{
                a.setNearSun(false);
            }
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

    /**
     * model.Asteroid arraylist getter
     * @return with the asteroids list
     */
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    /**
     * model.Settler arraylist getter
     * @return with the settlers list
     */
    public ArrayList<Settler> getSettlers() {
        return settlers;
    }

    /**
     * model.Robot arraylist getter.
     * @return with the robots list
     */
    public ArrayList<Robot> getRobots() {
        return robots;
    }

    /**
     * model.Ufo arraylist getter
     * @return with the ufos list
     */
    public ArrayList<Ufo> getUfos() {
        return ufos;
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
