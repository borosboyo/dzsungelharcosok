public abstract class Entity {

    private Asteroid asteroid;

    public Asteroid getAsteroid() {
        return asteroid;
    }

    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    public abstract void Blow();

    public abstract void Die();

    public void Drill() {

    }

    public void Move(Asteroid a) {
        if(this.getAsteroid().CheckNeighbour(a)){
            this.getAsteroid().RemoveEntity(this);
            this.setAsteroid(a);
        }
    }

    public void UseTeleport(Teleport t) {
    }


}
