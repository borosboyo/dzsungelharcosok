import javax.lang.model.type.NullType;
import java.util.ArrayList;

/**
 * The type Settler.
 */
public class Settler extends Entity{


    /**
     * Settler's inventory
     */
    private ArrayList<Material> inventory;

    /**
     * Teleport list
     */
    private ArrayList<Teleport> teleportlist;


    /**
     * Instantiates a new Settler.
     */
    public Settler() {
        inventory= new ArrayList<Material>();
        teleportlist= new ArrayList<Teleport>();
    }

    /**
     * Settler dies when it gets blown away.
     */
    @Override
    public void Blow() {
        this.Die();
    }

    /**
     * Settler dies.
     */
    @Override
    public void Die(){
        getAsteroid().RemoveEntity(this);
        setAsteroid(null);
    }

    /**
     * Build robot.
     */
    public void BuildRobot(){
        int iron=0;
        int coal=0;
        int uranium=0;
        for (Material m : inventory)
        {
           if(m instanceof Iron){
               iron++;
           }
            if(m instanceof Coal){
                coal++;
            }
            if(m instanceof Uranium){
                uranium++;
            }
        }

        if(iron>=1&&coal>=1&&uranium>=1){
            for (Material m : inventory)
            {
                boolean deleteiron=true;
                boolean deletecoal=true;
                boolean deleteuranium=true;

                if(m instanceof Iron && deleteiron){
                    inventory.remove(m);
                    deleteiron=false;
                }
                if(m instanceof Coal && deletecoal){
                    inventory.remove(m);
                    deletecoal=false;
                }
                if(m instanceof Uranium && deleteuranium){
                    inventory.remove(m);
                    deleteuranium=false;
                }
            }

            Robot r= new Robot();
            r.setAsteroid(this.getAsteroid());
            this.getAsteroid().Accept(r);
        }

    }

    /**
     * Make teleport.
     */
    public void MakeTeleport(){
        int iron=0;
        int ice=0;
        int uranium=0;
        for (Material m : inventory)
        {
            if(m instanceof Iron){
                iron++;
            }
            if(m instanceof Ice){
                ice++;
            }
            if(m instanceof Uranium){
                uranium++;
            }
        }
        if(iron>=2&&ice>=1&&uranium>=1&&teleportlist.isEmpty()){
                for (Material m : inventory)
                {
                    int deleteiron=0;
                    boolean deleteice=true;
                    boolean deleteuranium=true;

                    if(m instanceof Iron && deleteiron<2){
                        inventory.remove(m);
                        deleteiron++;
                    }
                    if(m instanceof Coal && deleteice){
                        inventory.remove(m);
                        deleteice=false;
                    }
                    if(m instanceof Uranium && deleteuranium){
                        inventory.remove(m);
                        deleteuranium=false;
                    }
                }
            Teleport t= new Teleport();
            this.teleportlist.add(t);
        }
    }

    /**
     * Mine.
     */
    public void Mine(){
        if(getAsteroid().getCrustThickness()==0 && inventory.size()<=10){
            Material i=getAsteroid().MinedBy();
            inventory.add(i);
            getAsteroid().CheckBase();
        }
    }

    /**
     * Place material.
     */
    public void PlaceMaterial(){
        if(getAsteroid().getMaterial()==null && getAsteroid().getCrustThickness() == 0){
            getAsteroid().AddMaterial(inventory.get(inventory.size()-1));
            inventory.remove(0);
        }
    }

    /**
     * Place teleport.
     */
    public void PlaceTeleport(){
        getAsteroid().BuildTeleport(teleportlist.get(0));
        if(teleportlist.get(0).getAsteroids().size()==2){
            teleportlist.remove(0);
        }
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public ArrayList<Material> getInventory() {
        return inventory;
    }
}
