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
        //ha szomszédos akkor mozog
        if(this.getAsteroid().CheckNeighbour(a)){
            //mozgás
            this.getAsteroid().RemoveEntity(this);
            a.Accept(this);
            this.setAsteroid(a);

            //check hogy megynerték-e
            a.CheckBase();
        }
        //különben nem mozog
    }

    public void UseTeleport(Teleport t) {
    }


}
