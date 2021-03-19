import java.util.ArrayList;

public class Asteroid {
    private long crustThickness;
    private  boolean nearSun;
    private  Material material;
    private ArrayList<Asteroid> neigbours;
    private ArrayList<Teleport> teleports;
    private ArrayList<Entity> entities;
    private Game game;


    public Asteroid(long crustThickness, boolean nearSun, Material material, ArrayList<Entity> entities) {
        this.crustThickness = crustThickness;
        this.nearSun = nearSun;
        this.material = material;
        this.entities = entities;
    }

    public void Accept(Entity e){
        this.entities.add(e);
    }

    public void AddMaterial(Material m){
        this.material = m;
    }

    //TODO: kitalálni, hogy mi alapján töltse fel az aszteroidákat
    public void AddRandomMaterial(){

    }

    public void BuildTeleport(Teleport t){
        teleports.add(t);
    }


    public void CheckBase(){
        boolean required = false;

        //TODO:nyeréshez szükséges dolgokat ki kell találni, vagy megnézni neten ha fent van.

        if(required){
            Game.game.WinGame();
        }
    }

    public void CheckTrigger(){
        if(this.nearSun && this.crustThickness == 0){
            material.Trigger();
        }
    }

    public boolean CheckNeighbour(Asteroid a){
        for (Asteroid neigbour : neigbours) {
            if (a == neigbour)
                return true;
        }
        return false;
    }

    public void DrilledBy(){
        this.crustThickness--;
        CheckTrigger();
    }

    public void Exlode(){

    }
    
    public Asteroid GetRandomNeighbour(){ return null; }
    public Teleport GetRandomTeleport(){return null;}
    public Material MinedBy(){return null;}
    public void RemoveEntity(Entity e){}
    public void RemoveMaterial(Material m){}
    public void Sunstorm(){}



    public ArrayList<Asteroid> getNeigbours() {
        return neigbours;
    }

    public void setNeigbours(ArrayList<Asteroid> neigbours) {
        this.neigbours = neigbours;
    }

    public ArrayList<Teleport> getTeleports() {
        return teleports;
    }

    public void setTeleports(ArrayList<Teleport> teleports) {
        this.teleports = teleports;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public long getCrustThickness() {
        return crustThickness;
    }

    public void setCrustThickness(long crustThickness) {
        this.crustThickness = crustThickness;
    }

    public boolean isNearSun() {
        return nearSun;
    }

    public void setNearSun(boolean nearSun) {
        this.nearSun = nearSun;
    }

}