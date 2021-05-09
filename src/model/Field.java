package model;

import model.Objects.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Field.
 */
public class Field implements Steppable, Serializable {
    private final ArrayList<Asteroid> asteroids = new ArrayList();
    private final ArrayList<Settler> settlers = new ArrayList();
    private final ArrayList<Robot> robots = new ArrayList();
    private final ArrayList<Ufo> ufos = new ArrayList();

    private int sunstormcounter = 0;

    private double sqrt;
    private int side;
    private int turnsPerSunstorm= 10;

    /**
     * Field initialization (Add Asteroids, Settlers and Ufos)
     *
     * @param settlernumber number of the settlers in the game
     * @param maxthickness  max crust thickness
     */
    public void newField(int settlernumber, int maxthickness) {
        //alap aszteroidak
        asteroids.add(new Asteroid(0, 3, true, new Ice()));
        asteroids.add(new Asteroid(1, 3, true, new Coal()));
        asteroids.add(new Asteroid(2, 3, true, new Iron()));
        asteroids.add(new Asteroid(3, 3, true, new Uranium()));
        asteroids.add(new Asteroid(4, 3, true, null));

        //random aszteroidak, telepesenkent +10db
        for (int i = 5; i < settlernumber * 10 + 5; i++) {
            Random rand = new Random();
            asteroids.add(new Asteroid(i, rand.nextInt(maxthickness + 1), false, RandomMaterial()));
        }
        //koordináták számolása
        sqrt = Math.sqrt(settlernumber * 10 + 5);
        side = (int) Math.ceil(sqrt);
        int xunit = 200;    //x távolság az aszteroidák között
        int yunit = 150;    //y távolság közöttük
        for (int i = 0; i < side; i++) {    //y   sor
            for (int j = 0; j < side; j++) {    //x oszlop
                if (asteroids.size() > (i * (side)) + j) {
                    asteroids.get(i * (side) + j).setY(i * yunit + 20);
                    asteroids.get(i * (side) + j).setX(j * xunit + 20);
                }
            }
        }

        //szomszédok beállítása, egymástól lévő távolság alapján
        for (Asteroid a : asteroids) {
            for (Asteroid a2 : asteroids) {
                int diffX = Math.abs(a.getX() - a2.getX());
                int diffY = Math.abs(a.getY() - a2.getY());

                if (diffX <= xunit && diffY <= yunit && a != a2)
                    a.getNeigbours().add(a2);
            }
        }


        //csak tesztelés miatt
        Settler s2 = new Settler(11);
        s2.getInventory().add(new Ice());
        s2.getInventory().add(new Coal());
        s2.getInventory().add(new Iron());
        s2.getInventory().add(new Iron());
        s2.getInventory().add(new Uranium());
        asteroids.get(2).Accept(s2);
        settlers.add(s2);
        s2.setAsteroid(asteroids.get(2));

        Teleport tel = new Teleport(0);
        tel.getAsteroids().add(asteroids.get(2));
        tel.getAsteroids().add(asteroids.get(4));
        asteroids.get(2).addTeleport(tel);
        asteroids.get(4).addTeleport(tel);
        GTimer.getInstance().AddSteppable(tel);

        tel = new Teleport(4);
        tel.getAsteroids().add(asteroids.get(12));
        tel.getAsteroids().add(asteroids.get(5));
        asteroids.get(12).addTeleport(tel);
        asteroids.get(5).addTeleport(tel);
        GTimer.getInstance().AddSteppable(tel);

        Robot ro = new Robot(5);
        asteroids.get(2).Accept(ro);
        ro.setAsteroid(asteroids.get(2));
        GTimer.getInstance().AddSteppable(ro);

        Ufo ufo = new Ufo(5);
        asteroids.get(2).Accept(ufo);
        ufo.setAsteroid(asteroids.get(2));
        GTimer.getInstance().AddSteppable(ufo);
        //csak tesztelés miatt végea


        Random rand = new Random();
        int k = 0;
        //telepesek létrehozása, és ufok elhelyezése random asteroidára, ID-juk beállítása
        for (int i = 0; i < settlernumber; i++) {
            Settler s = new Settler(k);
            int randasteroid = rand.nextInt(asteroids.size());
            asteroids.get(randasteroid).Accept(s);
            settlers.add(s);
            s.setAsteroid(asteroids.get(randasteroid));
            k++;

            //4 telepesenként egy ufo
            if (i % 4 == 0) {
                Ufo u = new Ufo(k);
                randasteroid = rand.nextInt(asteroids.size());
                asteroids.get(randasteroid).Accept(u);
                u.setAsteroid(asteroids.get(randasteroid));
                GTimer.getInstance().AddSteppable(u);
                ufos.add(u);
                k++;
            }
        }
        //steppable-hez hozzáadjuk a telepeseket
        GTimer.getInstance().AddSteppable(this);
        for (Settler s : settlers) {
            GTimer.getInstance().AddSteppable(s);
        }
        SetNearSun();
    }


    /**
     * Game state stepping
     */
    @Override
    public void Step() {
        SetNearSun();
        sunstormcounter++;
        //10 körönként napvihar
        if (sunstormcounter == turnsPerSunstorm) {
            SetSunStorm();
            sunstormcounter = 0;
        }
    }

    /**
     * Set sun storm.
     * <p>
     * Set sunstorm for all nearsun asteroids
     */
    public void SetSunStorm() {
        for (Asteroid a : asteroids) {
            if (a.isNearSun()) {
                a.Sunstorm();
            }
        }
    }

    /**
     * Set near sun.
     * <p>
     * Set NearSun for specified asteroids and check if any asteroid is triggered
     */
    public void SetNearSun() {
        Random rnd = new Random();
        //beállítja az összeset naptávolira
        for (Asteroid a : asteroids) {
            a.setNearSun(false);
        }
        //telepesek számától függően kiválaszt néhány aszteroidát, és beállítja napközelire őket és
        // a szomszédjaikat
        for (int i = 0; i < settlers.size() / 2; i++) {
            int id = rnd.nextInt(asteroids.size());
            Asteroid a = asteroids.get(id);
            //beállítjuk a kiválasztottat
            a.setNearSun(true);
            a.CheckTrigger();
            //és a szomszédjait
            for (Asteroid a2 : a.getNeigbours()) {
                a2.setNearSun(true);
                a2.CheckTrigger();
            }
        }
    }

    /**
     * Remove settler.
     *
     * @param s the settler to remove
     */
    public void RemoveSettler(Settler s) {
        settlers.remove(s);
    }

    /**
     * Remove asteroid.
     *
     * @param a the asteroid to remove
     */
    public void RemoveAsteroid(Asteroid a) {
        asteroids.remove(a);
    }


    /**
     * Check if the settlers on an asteroid have all the required materials for the win.
     *
     * @param a the asteroid on we check if the required materials are on.
     * @return the boolean
     */
    public boolean CheckReqMat(Asteroid a) {
        int coal = 3;
        int ice = 3;
        int iron = 3;
        int uran = 3;

        ArrayList<Material> settler_mat;

        for (Settler settler : settlers) {
            if (settler.getAsteroid() == a) {
                settler_mat = settler.getInventory();

                for (Material material : settler_mat) {
                    if (material instanceof Coal) {
                        coal--;
                    } else if (material instanceof Iron) {
                        iron--;
                    } else if (material instanceof Uranium) {
                        uran--;
                    } else if (material instanceof Ice) {
                        ice--;
                    }
                }
            }
        }

        return coal == 0 && iron == 0 && uran == 0 && ice == 0;
    }

    /**
     * Generate a random material material.
     *
     * @return the random material
     */
    Material RandomMaterial() {
        Random rand = new Random();
        Material mat = null;

        int r_num = rand.nextInt(5);
        switch (r_num) {
            case 0: {
                mat = new Uranium();
                break;
            }
            case 1: {
                mat = new Ice();
                break;
            }
            case 2: {
                mat = new Coal();
                break;
            }
            case 3: {
                mat = new Iron();
                break;
            }
            case 4: {
                mat = null;
                break;
            }
        }

        return mat;
    }

    /**
     * Asteroid arraylist getter
     *
     * @return with the asteroids list
     */
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    /**
     * Settler arraylist getter
     *
     * @return with the settlers list
     */
    public ArrayList<Settler> getSettlers() {
        return settlers;
    }


    /**
     * Gets sqrt.
     *
     * @return the sqrt
     */
    public double getSqrt() {
        return sqrt;
    }

    /**
     * Gets side.
     *
     * @return the side
     */
    public int getSide() {
        return side;
    }


    /**
     * Robot arraylist getter.
     *
     * @return with the robots list
     */
    public ArrayList<Robot> getRobots() {
        return robots;
    }

    /**
     * Ufo arraylist getter
     *
     * @return with the ufos list
     */
    public ArrayList<Ufo> getUfos() {
        return ufos;
    }

    /**
     * Add settler.
     *
     * @param s the settler to add.
     */
    public void AddSettler(Settler s) {
        settlers.add(s);
    }

    /**
     * Add asteroid.
     *
     * @param a the asteroid to add.
     */
    public void AddAsteroid(Asteroid a) {
        asteroids.add(a);
    }

    /**
     * Gets sunstormcounter.
     *
     * @return the sunstormcounter
     */
    public int getSunstormcounter() {
        return sunstormcounter;
    }

    public int getTurnsPerSunstorm() {
        return turnsPerSunstorm;
    }
    /**
     * Sets sunstormcounter.
     *
     * @param sunstormcounter the sunstormcounter
     */
    public void setSunstormcounter(int sunstormcounter) {
        this.sunstormcounter = sunstormcounter;
    }
}
