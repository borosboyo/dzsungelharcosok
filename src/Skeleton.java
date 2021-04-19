import java.io.*;
import java.util.ArrayList;

public class Skeleton implements Serializable{

    public Skeleton(){

    }
    private static String[] splitSentenceByWords(String str){

        //if string is empty or null, return empty array
        if(str == null || str.equals(""))
            return new String[0];

        String[] words = str.split("[ ,\\[\\]^_`{|}~]+");

        return words;
    }

    public void fileRead(String filename) throws IOException {
        File file=new File(filename);
        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        StringBuffer sb=new StringBuffer();
        String line;
        int lines =0;
        String[] parancs = new String[5];
        Game.getInstance().field = new Field();
        ArrayList<String> szomszedok = new ArrayList();

        if(filename.equals("special.txt")){
            Uranium u = new Uranium();
            u.setExponation(2);
            Asteroid a = new Asteroid(1, 0, false, u);
            Game.getInstance().field.AddAsteroid(a);
            u.setAsteroid(a);
            a.addTeleport(new Teleport(1));
            Asteroid a2 = new Asteroid(2, 4, false, null);
            Game.getInstance().field.AddAsteroid(a2);
            a.addTeleport(new Teleport(2));
            Asteroid a3 = new Asteroid(3, 0, false, null);
            Game.getInstance().field.AddAsteroid(a3);
            a.addTeleport(new Teleport(1));
            Settler s = new Settler(1);
            s.setAsteroid(a);
            Robot r = new Robot(2);
            r.setAsteroid(a3);
            Teleport t2 = new Teleport(2);
            t2.getAsteroids().add(a2);
            s.getTeleportlist().add(t2);
            Teleport t1 = new Teleport(1);
            t1.getAsteroids().add(a);
            t1.getAsteroids().add(a3);
            a.getNeigbours().add(a2);
            a3.getNeigbours().add(a2);
            a2.getNeigbours().add(a);
            a2.getNeigbours().add(a3);
           for(int i = 0; i < 3; i++){
               line = br.readLine();
               String[] words = splitSentenceByWords(line);
               Game.getInstance().read_command(words);
               sb.append(line);
               sb.append("\n");
           }

        } else {

            while ((line = br.readLine()) != null) {
                lines++;
                String[] words = splitSentenceByWords(line);
                //  for (int i = 0; i < words.length; i++)
                //        System.out.println(words[i] + "..." +  i);
                if (lines == 1) {
                    parancs = words;
                } else {
                    if (words[3].equals("Uranium") && words[0].equals("Asteroid")) {
                        long crust = Long.parseLong(words[5]);
                        boolean nearsun = Boolean.parseBoolean(words[6]);
                        Asteroid a = new Asteroid(Integer.parseInt(words[1]), crust, nearsun, null);
                        Game.getInstance().field.AddAsteroid(a);
                        Uranium u = new Uranium();
                        u.setExponation(Integer.parseInt(words[4]));
                        a.setMaterial(u);
                        u.setAsteroid(a);
                        if (words[2].equals("null")) {
                            a.addTeleport(null);
                        } else {
                            Teleport t = new Teleport(Integer.parseInt(words[2]));
                            a.addTeleport(t);
                            t.getAsteroids().add(a);
                        }
                        szomszedok.add(words[7]);
                    } else {
                        switch (words[0]) {
                            case "Asteroid" -> {
                                long crust = Long.parseLong(words[4]);
                                boolean nearsun = Boolean.parseBoolean(words[5]);
                                Asteroid a = new Asteroid(Integer.parseInt(words[1]), crust, nearsun, null);
                                Game.getInstance().field.AddAsteroid(a);
                                switch (words[3]) {
                                    case "Iron" -> a.setMaterial(new Iron());
                                    case "Coal" -> a.setMaterial(new Coal());
                                    case "Ice" -> a.setMaterial(new Ice());
                                    //case "Uranium": a.setMaterial(new Uranium());
                                }
                                a.getMaterial().setAsteroid(a);
                                if (words[2].equals("null")) {
                                    a.addTeleport(null);
                                } else {
                                    Teleport t = new Teleport(Integer.parseInt(words[2]));
                                    a.addTeleport(t);
                                    t.getAsteroids().add(a);
                                }
                                szomszedok.add(words[6]);
                            }
                            case "Settler" -> {
                                Settler s = new Settler(Integer.parseInt(words[1]));          //id
                                //System.out.println( words[0]+ ", "+ words[1] + ", "+words[2]+", " + words[3]);
                                Asteroid a = Game.getInstance().field.getAsteroids().get(Integer.parseInt(words[2]));
                                s.setAsteroid(a);                                         //asteroida
                                a.getEntities().add(s);
                                String[] inv_str = words[3].split("[;()]+");
                                String[] tel_str = words[4].split("[;()]+");

                                for (int ii = 1; ii < inv_str.length; ii++) {       //inventory
                                    switch (inv_str[ii]) {
                                        case "Iron" -> s.getInventory().add(new Iron());
                                        case "Ice" -> s.getInventory().add(new Ice());
                                        case "Uranium" -> s.getInventory().add(new Uranium());
                                        case "Coal" -> s.getInventory().add(new Coal());
                                        case "null" -> s.getInventory().add(null);
                                    }
                                }

                                for (int ii = 1; ii < tel_str.length; ii++) {                     //teleports
                                    s.getTeleportlist().add(new Teleport(Integer.parseInt(tel_str[ii])));
                                }
                                Game.getInstance().field.AddSettler(s);
                            }
                            case "Robot" -> {
                                Robot r = new Robot(Integer.parseInt(words[1]));  //id
                                Asteroid a = Game.getInstance().field.getAsteroids().get(Integer.parseInt(words[2]));
                                r.setAsteroid(a);                                         //asteroida
                                a.getEntities().add(r);
                                //Inventory, teleport nincs neki
                            }

                            case "Ufo" -> {
                                Ufo u = new Ufo(Integer.parseInt(words[1]));              //id
                                Asteroid a = Game.getInstance().field.getAsteroids().get(Integer.parseInt(words[2]));
                                u.setAsteroid(a);                                         //asteroida
                                a.getEntities().add(u);
                            }
                        }
                    }
                    sb.append(line);
                    sb.append("\n");
                }
            }
            int cnt = 0;
            // System.out.println(szomszedok.size());
            for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                cnt++;
                if (a.getId() == cnt) {
                    String[] szomsz_str = szomszedok.get(cnt - 1).split("[;()]+");
                    //  for(int i = 0; i < szomsz_str.length; i++){
                    //    System.out.println(szomsz_str[i] + "..." + i);
                    //}
                    for (int ii = 1; ii < szomsz_str.length; ii++) {
                        for (Asteroid a2 : Game.getInstance().field.getAsteroids()) {
                            if (a2.getId() == Integer.parseInt(szomsz_str[ii])) {
                                a.AddNeighbour(a2);
                            }
                        }
                    }
                } else System.out.println("nem jo a sorrend");
            }
            Game.getInstance().read_command(parancs);
        }
        fr.close();
    }

    // A következő függvény a játék aktuális állapotás írja ki
    public void writeout(Game g){
        /**
         * Settler tulajdonságainak a kiírása a konzolra, a dokumentációban részletesen le van írva
         */
        for(int i = 0; i < g.field.getSettlers().size(); i++) {
            Settler s = g.field.getSettlers().get(i);
            if(s.getAsteroid() != null){
                System.out.println("Settler {" + s.getId() + "," + s.getAsteroid().getId() + "," + getNyersanyagok(s) + "," + getTelepotok(s) + "}");
            }
        }

        /**
         * Robot tulajdonságainak kiírása a konzolra
         */
        for(int i = 0; i < g.field.getRobots().size(); i++){
            Robot r = g.field.getRobots().get(i);
            int id = i +g.field.getSettlers().size();
            if(r.getAsteroid() != null) {
                System.out.println("Robot {" + id + "," + r.getAsteroid() + "}");
            }
        }

        /**
         *Ufo állapotának kiírása a konzolra
         */
        for(int i = 0; i < g.field.getUfos().size(); i++){
            Ufo u = g.field.getUfos().get(i);
            int id = i+g.field.getSettlers().size() + g.field.getRobots().size() + i;
            System.out.println("Ufo {" + id + "," + u.getAsteroid().getId() + "}");
        }

        /**
         * Aszteroida állapotának a kiírása
         */
        for(int i = 0; i < g.field.getAsteroids().size(); i++){
            Asteroid a = g.field.getAsteroids().get(i);
            System.out.println("Asteroid {" + a.getId() + ",(" + getTelepotokA(a) + ")," + getNyersanyagA(a) + "," + a.getCrustThickness() + "," +
                    Nearsun(a) + ",(" + Szomszedok(a) + "),(" + Entitasok(a) + ")}");
        }
    }


    /**
     * A következő 7 függvény  a játék állípotának a kiiratását segíti
     */
    /**
     *
     * @param s A settrel, melynek a nyersanyagjai érdekelnek
     * @return s-nél lévő nyersanyagok egy Stringbe, vesszővel elválasztva. A nyersanyagok zárójelbe vannak.
     */
    String getNyersanyagok(Settler s){

        if(s.getInventory().size() == 0)
            return "null";
        String materials = new String("(");
        for(int i = 0; i < s.getInventory().size(); i++){
            if (s.getInventory().get(i) != null){
                materials += (s.getInventory().get(i).getName());
                if(i < s.getInventory().size() - 1)
                    materials += ",";
            }
        }
        materials += ")";
        return materials;
    }

    /**
     *
     * @param s Settler, aminek a telepeseit szeretnénk lekérdezni
     * @return egy String mely kiírja a settler inventoryjába lévő teleportokat veszzővel elválasztva. Zárójelben van az egész String
     */
    String getTelepotok(Settler s){
        if(s.getTeleportlist().size() == 0)
            return "null";
        String t = new String("(");
        for(int i = 0; i < s.getTeleportlist().size(); i++){
            t += s.getTeleportlist().get(i).toString();
            if(s.getTeleportlist().size() - 1 > i)
                 t += ",";
        }
        t+=")";
        return t;
    }

    /**
     * Az a aszterodán lévő teleportokat teszi egy Stringbe
     * @param a
     * @return A teleportok listája egy Stringbe. Az elemek vesszővel vannak elválasztva.
     */
    String getTelepotokA(Asteroid a){
        if(a.getTeleports() == null)
            return "null";
        String t = new String();
        for(int i = 0; i < a.getTeleports().size(); i++){
            t += a.getTeleports().get(i).toString();
            t += ",";
        }
        return t;
    }

    /**
     * Az a aszteroidán lévő nyersanyagot adja vissza egy Stringként
     * @param a
     * @return
     */
    String getNyersanyagA(Asteroid a){
        if(a.getMaterial() == null)
            return "null";
        return a.getMaterial().getName();
    }

    /**
     * Egy Stringet ad vissza, ami az a aszteroida napközeli állapotpot jellemzi
     * @param a
     * @return "true" - amennyiben az aszteroida napközelben van
     *         "false" - amennyiben az aszteroida naptávolban helyezkedik el
     */
    String Nearsun(Asteroid a){
        return a.isNearSun() ? "true" : "false";
    }

    /**
     *
     * @param a Aszteroida, melynek a szomszédaira vagyunk kíváncsiak
     * @return Az a aszteroida szomszédjait adja vissza egy Stringként. Egyes elemek vesszővel vannak elválasztva.
     */
    String Szomszedok(Asteroid a){
       if(a.getNeigbours().size() == 0)
           return "null";
       String sz = new String();
       for(int i = 0; i < a.getNeigbours().size(); i++) {
           sz += a.getNeigbours().get(i).getId();
           if(a.getNeigbours().size() -  1 > i)
               sz += ",";
       }
       return sz;
    }


    /**
     *
     * @param a
     * @return Egy String, ami az aszteroidán lévő entitások azonosítóit tartalmazza, vesszővel elválasztva
     */
    String Entitasok(Asteroid a){
        if(a.getEntities() == null)
            return "null";
        String e = new String();
        for(int i = 0; i < a.getEntities().size(); i++){
            e+= a.getEntities().get(i).getId();
            if(a.getEntities().size() - 1 > i)
                e+= ",";
        }

        return e;
    }

}
