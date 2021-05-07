package model;

import java.io.Serializable;

/**
 * The type Entity.
 */
public abstract class Entity implements Steppable, Serializable {

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
     * Id Getter
     * @return with the material Id
     */
    public int getId(){
        return id;
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
     * <p>
     * The Entity dies.
     */
    public abstract void Die();

    /**
     * Move.
     *
     * @param a the asteroid that the entity will move onto
     */
    public void Move(Asteroid a) {
        if (this.getAsteroid().CheckNeighbour(a)) {
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
