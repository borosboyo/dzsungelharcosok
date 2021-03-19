import java.util.ArrayList;
import java.util.Random;

public class Asteroid {
    private long crustThickness;
    private  boolean nearSun;
    private  Material material;
    private ArrayList<Asteroid> neigbours;
    private ArrayList<Teleport> teleports;
    private ArrayList<Entity> entities;


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

    public void BuildTeleport(Teleport t){
        teleports.add(t);
    }

    //TODO: Sztem ezt érdemes egy fájlból csinálni mint random, vagy ha random akkor lehet érdemes átírni a random részét
    public void AddRandomMaterial(){
        Random rand = new Random();

        int r_num = rand.nextInt(4);
        switch (r_num){
            case 0 -> material = new Uranium();
            case 1 -> material = new Ice();
            case 2 -> material = new Coal();
            case 3 -> material = new Iron();
        }
    }

    //TODO: újragondolni a játék megnyerésének ellenőrzését
    public void CheckBase(){
        boolean required = false;

        ArrayList<Material> required_material = new ArrayList<>();
        //Ez így nagyon ronda. Szerintem másik megoldás kéne.
        for (int i = 0; i < 3; i++){
            required_material.add(new Coal());
            required_material.add(new Ice());
            required_material.add(new Uranium());
            required_material.add(new Iron());
        }

        /*
        for(int i= 0; i < settlers.size(); i++){
            settler.getInventory().
        }

        required = true;
         */


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

    public Material MinedBy(){
        Material ret_material = material;
        if (ret_material != null && crustThickness == 0){
            CheckBase();
            RemoveMaterial();
            return ret_material;
        }
        return null;
    }

    public void Sunstorm(){
        if(crustThickness == 0 && material != null){
            for (Entity entity : entities) {
                entity.Die();
            }
        }
    }

    public void Explode(){
        Game.game.field.RemoveAsteroid(this);

        for (Teleport teleport : teleports) {
            teleport.RemoveAsteroid(this);
        }

        for (Entity entity : entities) {
            entity.Blow();
        }

        for (Asteroid neighbour : neigbours) {
            neighbour.RemoveNeighbour(this);
        }

    }

    public void RemoveEntity(Entity e){
        entities.remove(e);
    }

    public void RemoveMaterial(){
        material = null;
    }

    public void RemoveNeighbour(Asteroid a){
        neigbours.remove(a);
    }

    public Asteroid GetRandomNeighbour() {
        if (neigbours.size() < 1) {
            return null;
        }

        Random rand = new Random();
        int r_index = rand.nextInt(neigbours.size());

        return  neigbours.get(r_index);
    }

    public Teleport GetRandomTeleport(){
        if (teleports.size() < 1) {
            return null;
        }
        Random rand = new Random();
        int r_index = rand.nextInt(teleports.size());

        return  teleports.get(r_index);
    }



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