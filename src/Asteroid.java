import java.util.ArrayList;

public class Asteroid {
    private ArrayList<Asteroid> neigbours;
    private ArrayList<Teleport> teleports;
    private ArrayList<Entity> entities;
    private  Material material;
    private long crustThickness;
    private  boolean nearSun;

    public void Accept(Entity e){ }
    public void AddMaterial(Material m){}
    public void AddRandomMaterial(Material m){}
    public void BuildTeleport(Teleport t){ }
    public void CheckBase(){}
    public void CheckTrigger(){}
    public void DrilledBy(){}
    public void Exlode(){}
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