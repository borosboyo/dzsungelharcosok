import javax.swing.plaf.ActionMapUIResource;
import java.util.ArrayList;

/**
 * The type Entity.
 */
public abstract class Entity {

    /**
     * The asteroid that the entity is on
     */
    private Asteroid asteroid;
    private int id;


    public Entity(int id) {
        this.id = id;
    }

    /**
     * Returns the asteroid that the Entity stands on.
     *
     * @return the asteroid
     */
    public Asteroid getAsteroid() {
        return asteroid;
    }

    /**
     * Sets asteroid.
     *
     * @param asteroid the asteroid
     */
    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    /**
     * Blow.
     *
     * The entity gets blown away if the asteroid explodes.
     */
    public abstract void Blow();

    /**
     * Die.
     *
     * The Entity dies.
     *
     */
    public abstract void Die();
/*
    /**
     * Drill.
     *
     * Entity drills the crust of the asteroid.

    public void Drill() {
        if(this.getAsteroid().getCrustThickness()>0){
            this.getAsteroid().DrilledBy();
        }
    }
    */
    /**
     * Move.
     *
     * @param a the asteroid that the entity will move onto
     */
    public void Move(Asteroid a) {
        if(this.getAsteroid().CheckNeighbour(a)){
            this.getAsteroid().RemoveEntity(this);
            a.Accept(this);
            this.setAsteroid(a);
            a.CheckBase();
        }
    }

    /**
     * Use teleport.
     *
     * @param t the teleport that will be used.
     */
    public void UseTeleport(Teleport t) {
        t.Transfer(this);
    }


}
