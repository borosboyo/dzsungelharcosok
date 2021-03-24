import java.util.Random;

public class Ufo extends Entity implements Steppable{
      private ArrayList<Material> inventory;

    @Override
    public void Step() {
        Random rand = new Random();
        long crust = getAsteroid().getCrustThickness();
        Teleport teleport = getAsteroid().GetRandomTeleport();

        if ((crust == 0) && (getAsteroid().getMaterial() != null) ) {  //Ha tud mine-ol különben mozog 
         mine();
        } else {
            if ((teleport.getAsteroids().size() == 2) && (rand.nextInt(100) < 30)) {
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
}
