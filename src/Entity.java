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

        if(this.getAsteroid().getCrustThickness()>0){
            this.getAsteroid().DrilledBy(); //drilled by csökkenti a réteget eggyel és ha kell hívja meg a triggert
        }
    }

    public  void Move(Asteroid a) {
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
        t.Transfer(this);
    }


}
