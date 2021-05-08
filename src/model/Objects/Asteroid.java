package model.Objects;

import model.Game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Asteroid.
 */
public class Asteroid implements Serializable {
    private long crustThickness;
    private final int id;
    private int x, y;
    private boolean nearSun;
    private Material material;
    private final ArrayList<Asteroid> neighbours = new ArrayList<>();
    private final ArrayList<Teleport> teleports = new ArrayList();
    private final ArrayList<Entity> entities = new ArrayList();

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

        if (material != null) {
            material.setAsteroid(this);
        }
        this.id = id;
    }

    /**
     * Accepts entity.
     *
     * @param e the e
     */
    public void Accept(Entity e) {
        this.entities.add(e);
    }

    /**
     * Add material.
     *
     * @param m the m
     */
    public void AddMaterial(Material m) {
        this.material = m;
    }

    /**
     * Build teleport.
     *
     * @param t the t
     */

    public void BuildTeleport(Teleport t) {
        t.getAsteroids().add(this);
        teleports.add(t);
    }

    /**
     * Check base.
     */
    public void CheckBase() {
        if (Game.getInstance().field.CheckReqMat(this)) {
            Game.getInstance().WinGame();
        }
    }

    /**
     * Check trigger.
     */
    public void CheckTrigger() {
        if (this.nearSun && this.crustThickness == 0 && this.material != null) {
            material.Trigger();
        }
    }

    /**
     * Check neighbour boolean.
     *
     * @param a the a
     * @return the boolean
     */
    public boolean CheckNeighbour(Asteroid a) {
        for (Asteroid neighbour : neighbours) {
            if (a == neighbour)
                return true;
        }
        return false;
    }

    /**
     * Drilled by.
     */
    public void DrilledBy() {
        this.crustThickness--;
        CheckTrigger();
    }


    /**
     * Explode.
     */
    public void Explode() {
        Game.getInstance().field.RemoveAsteroid(this);

        for (int i = 0; i < teleports.size(); i++) {
            teleports.get(i).RemoveAsteroid(this);
            teleports.remove(teleports.get(i));
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).Blow();
        }

        for (int i = 0; i < neighbours.size(); i++) {
            neighbours.get(i).RemoveNeighbour(this);
        }

        for (int i = 0; i < neighbours.size(); i++) {
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

        return neighbours.get(r_index);
    }

    /**
     * Get random teleport teleport.
     *
     * @return the teleport
     */
    public Teleport GetRandomTeleport() {
        if (teleports.size() < 1) {
            return null;
        }
        Random rand = new Random();
        int r_index = rand.nextInt(teleports.size());

        return teleports.get(r_index);
    }

    /**
     * Mined by material.
     *
     * @return the material
     */
    public Material MinedBy() {
        Material ret_material = material;
        if (ret_material != null && crustThickness == 0) {
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
    public void RemoveEntity(Entity e) {
        entities.remove(e);
    }

    /**
     * Removes material.
     */
    public void RemoveMaterial() {
        material = null;
    }

    /**
     * Removes neighbour.
     *
     * @param a the a
     */
    public void RemoveNeighbour(Asteroid a) {
        neighbours.remove(a);
    }

    /**
     * Sunstorm.
     */
    public void Sunstorm() {
        if (!nearSun) {
            return;
        }
        if (crustThickness != 0 && material != null) {
            for (int i = 0; i < entities.size(); i++) {
                entities.get(i).Die();
            }
        }

        for (int i = 0; i < teleports.size(); i++) {
            if (teleports.get(i) != null) {
                teleports.get(i).HitBySunstorm(this);
            }
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
     * Gets teleports.
     *
     * @return the teleports
     */
    public ArrayList<Teleport> getTeleports() {
        return teleports;
    }


    public void addTeleport(Teleport t) {
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
     * Getter ID
     *
     * @return with Asteroid id
     */
    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}