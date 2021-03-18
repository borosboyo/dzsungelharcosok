/**
 * The type Robot.
 */
public class Robot extends Entity implements Steppable{

    @Override
    public void Blow() {
        Asteroid a1 = getAsteroid();
        Move(a1.GetRandomNeighbour());
    }

    @Override
    public void Die() {


    }

    @Override
    public void Step() { }


}
