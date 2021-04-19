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

    /**
     * Name getter
     * @return with the Material name
     */
    public abstract String getName();

    /**
     * Aszteroid getter
     * @return with the Asteroid
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
}
