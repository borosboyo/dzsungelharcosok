/**
 * Abstract class Material.
 */
public abstract class Material {

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

    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }
}
