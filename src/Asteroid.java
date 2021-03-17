import java.util.ArrayList;

public class Asteroid {
    private ArrayList<Asteroid> neigbours;
    private ArrayList<Teleport> teleports;
    private ArrayList<Entity> entities;
    private  Material material;
    private long cristThickness;
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
    public void Remove(Entity e){}
    public void RemoveMaterial(Material m){}
    public void Sunstorm(){}




}