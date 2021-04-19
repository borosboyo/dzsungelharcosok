import java.util.ArrayList;
import java.util.Random;

/**
 * The type Ufo.
 */
public class Ufo extends Entity implements IMine {
    private ArrayList<Material> inventory = new ArrayList();

    /**
     * Constructor of the Ufo
     * @param id the Ifo Id
     */
    public Ufo(int id){
        super(id);
    }

    /**
     * The Step function of the ufo.
     */
    @Override
    public void Step() {
        Random rand = new Random();
        long crust = getAsteroid().getCrustThickness();
        Teleport teleport = getAsteroid().GetRandomTeleport();

        if ((crust == 0) && (getAsteroid().getMaterial() != null)) {  //Ha tud mine-ol különben mozog
            Mine();
        } else {
            if ((teleport != null && teleport.getAsteroids().size() == 2) && (rand.nextInt(100) < 30)) {
                UseTeleport(teleport);
            } else {
                Move(getAsteroid().GetRandomNeighbour());
            }
        }
    }

    /**
     * The ufo gets blown away if the asteroid it is on explodes.
     */
    @Override
    public void Blow() {
        Move(getAsteroid().GetRandomNeighbour());
    }

    /**
     * The ufo dies if it gets hit by sunstorm.
     */
    @Override
    public void Die() {
        getAsteroid().RemoveEntity(this);
        setAsteroid(null);
        Timer.getInstance().RemoveSteppable(this);
    }

    /**
     * Material mining.
     */
    public void Mine() {
        if (getAsteroid().getCrustThickness() == 0) {
            Material i = getAsteroid().MinedBy();
            inventory.add(i);
        }
    }

}
