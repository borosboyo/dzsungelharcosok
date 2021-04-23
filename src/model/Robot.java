package model;

import java.util.Random;

/**
 * The type Robot.
 */
public class Robot extends Entity implements IDrill {

    /**
     * Contructor of the Robot class
     *
     * @param id Robot Id
     */
    public Robot(int id){
        super(id);
    }

    /**
     * The robot gets blown away if the asteroid it is on explodes.
     */
    @Override
    public void Blow() {
        Move(getAsteroid().GetRandomNeighbour());
    }

    /**
     * The robot dies if it gets hit by sunstorm.
     */
    @Override
    public void Die() {
        getAsteroid().RemoveEntity(this);
        setAsteroid(null);
        Timer.getInstance().RemoveSteppable(this);
        Game.getInstance().field.getRobots().remove(this);
    }

    /**
     * The Step function of the robot.
     */
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

    /**
     * Drilling
     */
    @Override
    public void Drill() {
        if(this.getAsteroid().getCrustThickness()>0){
            this.getAsteroid().DrilledBy();
        }
    }
}
