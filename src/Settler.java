import java.util.ArrayList;

/**
 * The type Settler.
 */
public class Settler extends Entity implements IDrill, IMine, Steppable{


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
    public Settler(int id){
        super(id);
        inventory= new ArrayList<Material>();
        teleportlist= new ArrayList<Teleport>();
    }

    public void CompleteAction(){
        Timer.getInstance().setCounter(Timer.getInstance().getCounter()+1); //noveljuk a countert eggyel
        Timer.getInstance().Tick();

    }

    public void Move(Asteroid a) {
        if(this.getAsteroid().CheckNeighbour(a)){
            this.getAsteroid().RemoveEntity(this);
            a.Accept(this);
            this.setAsteroid(a);
            a.CheckBase();
            CompleteAction();
        }
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
        int uranium=0;  //Megnézzuk van e elég anyag az építéshez
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

        //Ha van elég csükkentjuk es letrehozzuk a robotot az aszteroidan
        if(iron>=1&&coal>=1&&uranium>=1){
            boolean deleteiron=true;
            boolean deletecoal=true;
            boolean deleteuranium=true;

            for (int k=0; k<inventory.size(); k++)
            {

                //Toroljuk a szukseges anyagokat
                if(inventory.get(k) instanceof Iron && deleteiron){
                    inventory.remove(k);
                    deleteiron=false;
                    k--;
                }
                else if(inventory.get(k) instanceof Coal && deletecoal){
                    inventory.remove(k);
                    deletecoal=false;
                    k--;
                }
                else if(inventory.get(k)  instanceof Uranium && deleteuranium){
                    inventory.remove(k);
                    deleteuranium=false;
                    k--;
                }

                if (deleteiron == false&& deletecoal == false&&deleteuranium == false){
                    break;
                }
            }

            Robot r= new Robot(1);   //TODO: retek id miatt itt átkell adni valamit, kikell talalni mi legyen
            r.setAsteroid(this.getAsteroid());
            this.getAsteroid().Accept(r);
            Timer.getInstance().AddSteppable(r);
            CompleteAction();
        }


    }

    /**
     * Make teleport.
     */
    public void MakeTeleport(){
        int iron=0;
        int ice=0;
        int uranium=0;  //Megnézzuk van e elég anyag az építéshez
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
        //Ha van elég csükkentjuk es letrehozzuk a teleportot és hozzáadjuk a teleportlisthez
        if(iron>=2&&ice>=1&&uranium>=1&&EnoughTeleportSpace()==true){ //ha egy vagy semennyi nincs benne akkor tud craftolni
            int deleteiron = 0; //kettot kell belole torolni ezert ezt igy csinalom
            boolean deleteice = true;
            boolean deleteuranium = true;

            for (int k=0; k<inventory.size(); k++) {

                if (inventory.get(k) instanceof Iron && deleteiron < 2) {
                    inventory.remove(k);
                    deleteiron++;
                    k--;

                } else if (inventory.get(k) instanceof Ice && deleteice) {
                    inventory.remove(k);
                    deleteice = false;
                    k--;
                } else if (inventory.get(k) instanceof Uranium && deleteuranium) {
                    inventory.remove(k);
                    deleteuranium = false;
                    k--;
                }

                if (deleteiron == 2&& !deleteice && !deleteuranium){
                    break;
                }
            }
            Teleport t= new Teleport(1);   //TODO: megin a retek id miatt kell valamit átadni....
            this.teleportlist.add(t);
            Timer.getInstance().AddSteppable(t);
            CompleteAction();
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

            CompleteAction();
        }
    }

    /**
     * Place material.
     */
    public void PlaceMaterial(){
        if(getAsteroid().getMaterial()==null && getAsteroid().getCrustThickness() == 0){
            getAsteroid().AddMaterial(inventory.get(inventory.size()-1));
            inventory.remove(0);

            CompleteAction();
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
        CompleteAction();
    }


    public boolean EnoughTeleportSpace(){
        if(teleportlist.isEmpty()){
            return true;
        }
        if(teleportlist.get(0).getAsteroids().size()==1){
            return true;
        }
        return false;
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public ArrayList<Material> getInventory() {
        return inventory;
    }

    public ArrayList<Teleport> getTeleportlist() {
        return teleportlist;
    }

    @Override
    public void Drill() {
        if(this.getAsteroid().getCrustThickness()>0){
            this.getAsteroid().DrilledBy();
        }
        CompleteAction();
    }

    public void Step(){
       for(Teleport t: teleportlist){   //megnezni hogy felrobbant e valamelyik es ha igen kitorolni
            if(t.getExploded()==true){
                teleportlist.remove(t);
            }
       }
    }


}
