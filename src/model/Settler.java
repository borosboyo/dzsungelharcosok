package model;

import java.util.ArrayList;

/**
 * The type Settler.
 */
public class Settler extends Entity implements IDrill, IMine{

    /**
     * Settler's inventory
     */
    private final ArrayList<Material> inventory;
    /**
     * Teleport list
     */
    private final ArrayList<Teleport> teleportlist;

    private boolean finishedTurn = false;
    private boolean selected = false;


    /**
     * Instantiates a new Settler.
     */
    public Settler(int id) {
        super(id);
        inventory = new ArrayList<>();
        teleportlist = new ArrayList<>();
    }

    /**
     * @param a the asteroid that the entity will move onto
     */
    public void Move(Asteroid a) {      //TODO::entityben is van move, ha nem lenne setFinishedTurn akkor jó lenne
        if (this.getAsteroid().CheckNeighbour(a) && !finishedTurn) {
            this.getAsteroid().RemoveEntity(this);
            a.Accept(this);
            this.setAsteroid(a);
            setFinishedTurn(true);
            a.CheckBase();
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
        Game.getInstance().field.getSettlers().remove(this);
        setAsteroid(null);
    }

    /**
     * Build robot.
     */
    public void BuildRobot() { //TODO:: Az inventory ellenőrzést és csökkentést ha összetudnán vonni itt és a maketeleportban akkkor szebb lenne a kód
        int iron = 0;
        int coal = 0;
        int uranium = 0;  //Megnézzuk van e elég anyag az építéshez
        for (Material m : inventory) {
            if (m instanceof Iron) {
                iron++;
            }
            if (m instanceof Coal) {
                coal++;
            }
            if (m instanceof Uranium) {
                uranium++;
            }
        }

        //Ha van elég csükkentjuk es letrehozzuk a robotot az aszteroidan
        if (iron >= 1 && coal >= 1 && uranium >= 1 && !finishedTurn) {
            boolean deleteiron = true;
            boolean deletecoal = true;
            boolean deleteuranium = true;

            for (int k = 0; k < inventory.size(); k++) {

                //Toroljuk a szukseges anyagokat
                if (inventory.get(k) instanceof Iron && deleteiron) {
                    inventory.remove(k);
                    deleteiron = false;
                    k--;
                } else if (inventory.get(k) instanceof Coal && deletecoal) {
                    inventory.remove(k);
                    deletecoal = false;
                    k--;
                } else if (inventory.get(k) instanceof Uranium && deleteuranium) {
                    inventory.remove(k);
                    deleteuranium=false;
                    k--;
                }

                if (!deleteiron && !deletecoal && !deleteuranium) {
                    break;
                }
            }


            int id = AddCorrectID(0);
            Robot r = new Robot(id);

            r.setAsteroid(this.getAsteroid());
            this.getAsteroid().Accept(r);
            GTimer.getInstance().AddSteppable(r);
            Game.getInstance().field.getRobots().add(r);
            setFinishedTurn(true);
        }


    }

    /**
     * Make teleport.
     */
    public void MakeTeleport(){
        int iron=0;
        int ice=0;
        int uranium=0;  //Megnézzuk van e elég anyag az építéshez
        for (Material m : inventory) {
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
        if (iron >= 2 && ice >= 1 && uranium >= 1 && EnoughTeleportSpace() && !finishedTurn) { //ha egy vagy semennyi nincs benne akkor tud craftolni
            int deleteiron = 0; //kettot kell belole torolni ezert ezt igy csinalom
            boolean deleteice = true;
            boolean deleteuranium = true;

            for (int k = 0; k < inventory.size(); k++) {

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

                if (deleteiron == 2 && !deleteice && !deleteuranium) {
                    break;
                }
            }

            int id = AddCorrectID(1);
            Teleport t = new Teleport(id);
            this.teleportlist.add(t);
            GTimer.getInstance().AddSteppable(t);
            setFinishedTurn(true);
        }
    }

    /**
     * Mine.
     */
    public void Mine() {
        if (getAsteroid().getCrustThickness() == 0 && inventory.size() <= 10 && !finishedTurn) {
            Material i = getAsteroid().MinedBy();
            if (i == null)
                return;

            inventory.add(i);
            setFinishedTurn(true);
            getAsteroid().CheckBase();
        }
    }

    /**
     * Place material.
     */
    public void PlaceMaterial() {
        if (getAsteroid().getMaterial() == null && getAsteroid().getCrustThickness() == 0 && !finishedTurn) {
            getAsteroid().AddMaterial(inventory.get(inventory.size() - 1));
            inventory.remove(inventory.get(inventory.size() - 1));
            setFinishedTurn(true);
        }
    }

    /**
     * Place teleport.
     */
    public void PlaceTeleport(){
        getAsteroid().BuildTeleport(teleportlist.get(0));
        if (teleportlist.get(0).getAsteroids().size() == 2 && !finishedTurn) {
            teleportlist.remove(0);
        }
        setFinishedTurn(true);
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
        if (this.getAsteroid().getCrustThickness() > 0 && !finishedTurn) {
            this.getAsteroid().DrilledBy();
            setFinishedTurn(true);
        }

    }

    /**
     * The Step function of the Settler.
     */
    @Override
    public void Step(){
        for (int i = 0; i < teleportlist.size(); i++){
            if(teleportlist.get(i).getExploded()) {
                teleportlist.remove(teleportlist.get(i));
            }
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public int AddCorrectID(int ent) {
        boolean correct_id = false;
        int id = -1;

//        while(!correct_id){ //TODO:: ez sztem nem kell majd
//            id++;
//            for (int i = 0; i  < Game.getInstance().field.getSettlers().size(); i++){ //TODO::ez csak akkor kell ha az entitásoknak nem lehet ugyanaz az id-je, sztem a consoleos rész kiszedése miatt már nem kell
//                if (id != Game.getInstance().field.getSettlers().get(i).getId()){
//                    correct_id = true;
//                    break;
//                }
//            }
//            if(!correct_id){
//                for (int i = 0; i  < Game.getInstance().field.getRobots().size(); i++){
//                    if (id != Game.getInstance().field.getRobots().get(i).getId()){
//                        correct_id = true;
//                        break;
//                    }
//                }
//            }
//            if(!correct_id) {
//                for (int i = 0; i < Game.getInstance().field.getRobots().size(); i++) { //TODO::ez csak akkor kell ha az entitásoknak nem lehet ugyanaz az id-je, sztem a consoleos rész kiszedése miatt már nem kell
//                    if (id != Game.getInstance().field.getUfos().get(i).getId()) {
//                        correct_id = true;
//                        break;
//                    }
//                }
//            }
//        }

        ArrayList<Asteroid> asteroids = Game.getInstance().field.getAsteroids();
        while (!correct_id) {
            id++;

            if (ent == 0) { //TODO::ez csak akkor kell ha kellenek robot id-k, ha nem kell akkor paraméter sem kell
                for (int i = 0; i < Game.getInstance().field.getRobots().size(); i++) {
                    if (id != Game.getInstance().field.getRobots().get(i).getId()) {
                        correct_id = true;
                        break;
                    }
                }
            } else if (ent == 1) {
                for (int i = 0; i < asteroids.size(); i++) {
                    for (int j = 0; j < asteroids.get(i).getTeleports().size(); i++) {
                        if (id != asteroids.get(i).getTeleports().get(j).getId()) {
                            correct_id = true;
                            break;
                        }
                    }
                }
            }
        }

        return id;
    }

}
