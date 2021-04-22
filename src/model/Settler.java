package model;

import java.util.ArrayList;

/**
 * The type model.Settler.
 */
public class Settler extends Entity implements IDrill, IMine{


    /**
     * model.Settler's inventory
     */
    private ArrayList<Material> inventory;
    /**
     * model.Teleport list
     */
    private ArrayList<Teleport> teleportlist;

    private boolean finishedTurn = false;

    /**
     * Instantiates a new model.Settler.
     */
    public Settler(int id){
        super(id);
        inventory= new ArrayList<Material>();
        teleportlist= new ArrayList<Teleport>();
    }


    /**
     *
     * @param a the asteroid that the entity will move onto
     */
    public void Move(Asteroid a) {
        if(this.getAsteroid().CheckNeighbour(a)){
            this.getAsteroid().RemoveEntity(this);
            a.Accept(this);
            this.setAsteroid(a);
            a.CheckBase();

        }
    }

    /**
     * model.Settler dies when it gets blown away.
     */
    @Override
    public void Blow() {
        this.Die();
    }

    /**
     * model.Settler dies.
     */
    @Override
    public void Die(){
        getAsteroid().RemoveEntity(this);
        Game.getInstance().field.getSettlers().remove(this);
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

            boolean correct_id = false;
            int id = -1;

            while(!correct_id){
                id++;
                for (int i = 0; i  < Game.getInstance().field.getSettlers().size(); i++){
                    if (id != Game.getInstance().field.getSettlers().get(i).getId()){
                        correct_id = true;
                        break;
                    }
                }
                if(!correct_id){
                    for (int i = 0; i  < Game.getInstance().field.getRobots().size(); i++){
                        if (id != Game.getInstance().field.getRobots().get(i).getId()){
                            correct_id = true;
                            break;
                        }
                    }
                }
                if(!correct_id) {
                    for (int i = 0; i  < Game.getInstance().field.getUfos().size(); i++){
                        if (id != Game.getInstance().field.getUfos().get(i).getId()){
                            correct_id = true;
                            break;
                        }
                    }
                }
            }

            Robot r= new Robot(id);

            r.setAsteroid(this.getAsteroid());
            this.getAsteroid().Accept(r);
            Timer.getInstance().AddSteppable(r);
            Game.getInstance().field.getRobots().add(r);
            System.out.println(id + " " + r + " "+ r.getAsteroid());
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
            inventory.remove(inventory.get(inventory.size()-1));

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
     * Check the leleport list size
     * @return true if there is still room in it
     */
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

    /**
     * model.Teleport arraylist getter
     * @return with teleport list
     */
    public ArrayList<Teleport> getTeleportlist() {
        return teleportlist;
    }


    public boolean isFinishedTurn() {
        return finishedTurn;
    }

    public void setFinishedTurn(boolean finishedTurn) {
        this.finishedTurn = finishedTurn;
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

    /**
     * The Step function of the model.Settler.
     */
    @Override
    public void Step(){
       for (int i = 0; i < teleportlist.size(); i++){
            if(teleportlist.get(i).getExploded()==true){
                teleportlist.remove(teleportlist.get(i));
            }
       }
    }


}
