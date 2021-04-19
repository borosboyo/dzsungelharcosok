import java.io.Serializable;

/**
 * Abstract class Material.
 */
public abstract class Material implements Serializable {

    /**
     * The Asteroid that contains the material inside it.
     */
    Asteroid asteroid;

    /**
     * Trigger of special materials in case of special events.
     */
    public abstract void Trigger();

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
}
