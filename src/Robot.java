import java.io.ObjectInputStream;
import java.util.Random;

/**
 * The type Robot.
 */
public class Robot extends Entity implements Steppable {

    @Override
    public void Blow() {
        Move(getAsteroid().GetRandomNeighbour());
    }

    @Override
    public void Die() {
        getAsteroid().RemoveEntity(this);
        Timer.getInstance().RemoveSteppable(this);
    }

    @Override
    public void Step() {
        Random rand = new Random();
        long crust = getAsteroid().getCrustThickness();
        Teleport teleport = getAsteroid().GetRandomTeleport();

        if ((teleport.getAsteroids().size() == 2) && (rand.nextInt(100) < 30)) {  //Ha van szomszédja az aszteroidának és 30% akkor teleportál
                UseTeleport(teleport);
        } else {
            if (crust != 0) {
                Drill();
            } else {
                Move(getAsteroid().GetRandomNeighbour());
            }
        }
    }
}
