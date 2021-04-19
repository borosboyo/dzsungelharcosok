import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Entity implements IMine {
    private ArrayList<Material> inventory = new ArrayList();

    public Ufo(int id){
        super(id);
    }

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

    @Override
    public void Blow() {
        Move(getAsteroid().GetRandomNeighbour());
    }

    @Override
    public void Die() {
        getAsteroid().RemoveEntity(this);
        setAsteroid(null);
        Timer.getInstance().RemoveSteppable(this);
    }

    public void Mine() {
        if (getAsteroid().getCrustThickness() == 0) {
            Material i = getAsteroid().MinedBy();
            inventory.add(i);
        }
    }

}
