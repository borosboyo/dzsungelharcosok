package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Teleport.
 */
public class Teleport implements Steppable, Serializable {
    int id;
    private boolean firstCrazy = false;
    private boolean secondCrazy = false;
    private boolean exploded = false;
    private final ArrayList<Asteroid> asteroids = new ArrayList<>();

    /**
     * Constructor of the Teleport
     *
     * @param id Teleport Id
     */
    public Teleport(int id) {
        this.id = id;
    }

    /**
     * Id getter
     * @return with the teleport Id
     */
    public int getId() {
        return id;
    }

    public boolean getExploded(){
        return exploded;
    }

    public void HitBySunstorm(Asteroid a){
        if (asteroids.size() > 0 && asteroids.get(0) == a) {
            firstCrazy = true;
        }
        if (asteroids.size() > 1 && asteroids.get(1) == a) {
            secondCrazy = true;
        }
        Step();
    }

    /**
     * Transfer.
     * If the teleport's pair is placed on another asteroid, then the entity is transfered to it.
     *
     * @param e the entity using the teleport
     */
    public void Transfer(Entity e) {
        if (e.getAsteroid() != asteroids.get(0) && e.getAsteroid() != asteroids.get(1))
            return;

        if (asteroids.size() == 2) {
            if (asteroids.get(0).equals(e.getAsteroid())) {
                asteroids.get(1).Accept(e);
                asteroids.get(0).getEntities().remove(e);
                e.setAsteroid(asteroids.get(1));
            } else {
                asteroids.get(0).Accept(e);
                asteroids.get(1).getEntities().remove(e);
                e.setAsteroid(asteroids.get(0));
            }

            if (e instanceof Settler) {
                Settler se = (Settler) e;
                se.setFinishedTurn(true);
            }
        }
    }

    /**
     * Remove asteroid.
     *
     * @param a the asteroid to remove
     */
    public void RemoveAsteroid(Asteroid a){

        asteroids.remove(a);    //ez kell ha csak ez egyiknek kell megsemmisulnie
        exploded=true;          // a settler tudja hogy ki kell torolni a tarolojabol
        if(asteroids.get(0)!=null){     //ha van párja letéve (=marad egy masik asteroid is benne) kitorli rola magat
            asteroids.get(0).getTeleports().remove(this);
        }
        asteroids.clear();          // torli az osszes asteroidat hogy biztos ne maradjon semmi benne
    }

    /**
     * Gets asteroids.
     *
     * @return the asteroids
     */
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }


    /**
     *The Step function of the teleport.
     */
    @Override
    public void Step() {

        if (firstCrazy) {
            asteroids.set(0, asteroids.get(0).GetRandomNeighbour());
        }
        if (secondCrazy) {
            asteroids.set(1, asteroids.get(1).GetRandomNeighbour());
        }
    }
}
