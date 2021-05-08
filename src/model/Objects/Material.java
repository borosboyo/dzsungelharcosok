package model.Objects;

import java.io.Serializable;

/**
 * Abstract class model.Material.
 */
public abstract class Material implements Serializable {

    /**
     * The model.Asteroid that contains the material inside it.
     */
    Asteroid asteroid;

    /**
     * Trigger of special materials in case of special events.
     */
    public abstract void Trigger();

    /**
     * Name getter
     * @return with the model.Material name
     */
    public abstract String getName();

    /**
     * Aszteroid getter
     * @return with the model.Asteroid
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
