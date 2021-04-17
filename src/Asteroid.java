import java.util.ArrayList;
import java.util.Random;

/**
 * The type Asteroid.
 */
public class Asteroid {
    private long crustThickness;


    private int id;
    private  boolean nearSun;
    private  Material material;
    private ArrayList<Asteroid> neighbours = new ArrayList();
    private ArrayList<Teleport> teleports = new ArrayList();
    private ArrayList<Entity> entities = new ArrayList();

    /**
     * Initializes a new Asteroid.
     *
     * @param crustThickness the crust thickness
     * @param nearSun        the near sun
     * @param material       the material
     */
    public Asteroid(int id, long crustThickness, boolean nearSun, Material material) {
        this.crustThickness = crustThickness;
        this.nearSun = nearSun;
        this.material = material;
        this.id = id;
    }

    /**
     * Accepts entity.
     *
     * @param e the e
     */
    public void Accept(Entity e){
        this.entities.add(e);
    }

    /**
     * Add material.
     *
     * @param m the m
     */
    public void AddMaterial(Material m){
        this.material = m;
    }

    /**
     * Build teleport.
     *
     * @param t the t
     */

    public void BuildTeleport(Teleport t){
        teleports.add(t);
    }

    /**
     * Check base.
     */
    public void CheckBase(){
        if(Game.getInstance().field.CheckReqMat(this)){
            Game.getInstance().WinGame();
        }
    }

    /**
     * Check trigger.
     */
    public void CheckTrigger(){
        if(this.nearSun && this.crustThickness == 0){
            material.Trigger();
        }
    }

    /**
     * Check neighbour boolean.
     *
     * @param a the a
     * @return the boolean
     */
    public boolean CheckNeighbour(Asteroid a){
        for (Asteroid neighbour : neighbours) {
            if (a == neighbour)
                return true;
        }
        return false;
    }

    /**
     * Drilled by.
     */
    public void DrilledBy(){
        this.crustThickness--;
        CheckTrigger();
    }


    /**
     * Explode.
     */
    public void Explode(){
        Game.getInstance().field.RemoveAsteroid(this);

        for (Teleport teleport : teleports) {
            teleport.RemoveAsteroid(this);
            teleports.remove(teleport);
        }

        for (Entity entity : entities) {
            entity.Blow();
        }

        for (Asteroid neighbour : neighbours) {
            neighbour.RemoveNeighbour(this);
        }

        for (int i=0; i< neighbours.size(); i++){
            neighbours.remove(i);
        }

    }

    /**
     * Get random neighbour asteroid.
     *
     * @return the asteroid
     */
    public Asteroid GetRandomNeighbour() {
        if (neighbours.size() < 1) {
            return null;
        }

        Random rand = new Random();
        int r_index = rand.nextInt(neighbours.size());

        return  neighbours.get(r_index);
    }

    /**
     * Get random teleport teleport.
     *
     * @return the teleport
     */
    public Teleport GetRandomTeleport(){
        if (teleports.size() < 1) {
            return null;
        }
        Random rand = new Random();
        int r_index = rand.nextInt(teleports.size());

        return  teleports.get(r_index);
    }

    /**
     * Mined by material.
     *
     * @return the material
     */
    public Material MinedBy(){
        Material ret_material = material;
        if (ret_material != null && crustThickness == 0){
            RemoveMaterial();
            return ret_material;
        }
        return null;
    }

    /**
     * Removes entity.
     *
     * @param e the e
     */
    public void RemoveEntity(Entity e){
        entities.remove(e);
    }

    /**
     * Removes material.
     */
    public void RemoveMaterial(){
        material = null;
    }

    /**
     * Removes neighbour.
     *
     * @param a the a
     */
    public void RemoveNeighbour(Asteroid a){
        neighbours.remove(a);
    }

    /**
     * Sunstorm.
     */
    public void Sunstorm(){
        if (!nearSun){
            return;
        }
        if(crustThickness != 0 && material != null){
            for (Entity entity : entities) {
                entity.Die();
            }
        }
        for(Teleport t: teleports){
            t.HitBySunstorm(this);
        }
    }


    //ArrayList Getters Setters
    /**
     * Gets neigbours.
     *
     * @return the neigbours
     */
    public ArrayList<Asteroid> getNeigbours() {
        return neighbours;
    }

    /**
     * Sets neigbours.
     *
     * @param neigbours the neigbours
     */
    public void setNeigbours(ArrayList<Asteroid> neigbours) {
        this.neighbours = neigbours;
    }

    /**
     * Gets teleports.
     *
     * @return the teleports
     */
    public ArrayList<Teleport> getTeleports() {
        return teleports;
    }

    /**
     * Sets teleports.
     *
     * @param teleports the teleports
     */
    public void setTeleports(ArrayList<Teleport> teleports) {
        this.teleports = teleports;
    }

    public void addTeleport(Teleport t){
        this.teleports.add(t);
    }


    /**
     * Gets entities.
     *
     * @return the entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Sets entities.
     *
     * @param entities the entities
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    /**
     * Gets material.
     *
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets material.
     *
     * @param material the material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Gets crust thickness.
     *
     * @return the crust thickness
     */
    public long getCrustThickness() {
        return crustThickness;
    }

    /**
     * Sets crust thickness.
     *
     * @param crustThickness the crust thickness
     */
    public void setCrustThickness(long crustThickness) {
        this.crustThickness = crustThickness;
    }

    /**
     * Is near sun boolean.
     *
     * @return the boolean
     */
    public boolean isNearSun() {
        return nearSun;
    }

    /**
     * Sets near sun.
     *
     * @param nearSun the near sun
     */
    public void setNearSun(boolean nearSun) {
        this.nearSun = nearSun;
    }

    /**
     * Add neighbour.
     *
     * @param a the a
     */
    public void AddNeighbour(Asteroid a) {
        neighbours.add(a);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}