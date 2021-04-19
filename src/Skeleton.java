import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


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

        while((line=br.readLine())!=null) {
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
                if (words[2].equals("null")) {
                    a.addTeleport(null);
                } else {
                    a.addTeleport(new Teleport(Integer.parseInt(words[2])));
                }
                szomszedok.add(words[7]);
                //TODO: szomszedok, entitásokat az entitásnal adja hozzá
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
                        if (words[2].equals("null")) {
                            a.addTeleport(null);
                        } else {
                            a.addTeleport(new Teleport(Integer.parseInt(words[2])));
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
        for(Asteroid a: Game.getInstance().field.getAsteroids()){
            cnt++;
            if(a.getId() == cnt){
                String[] szomsz_str = szomszedok.get(cnt-1).split("[;()]+");
              //  for(int i = 0; i < szomsz_str.length; i++){
                //    System.out.println(szomsz_str[i] + "..." + i);
                //}
                for(int ii = 1; ii < szomsz_str.length;ii++){
                    for(Asteroid a2: Game.getInstance().field.getAsteroids()){
                        if(a2.getId()  == Integer.parseInt(szomsz_str[ii])){
                            a.AddNeighbour(a2);
                        }
                    }
                }
            } else System.out.println("nem jo a sorrend");
        }
        for(int i = 0; i < parancs.length; i++){
            System.out.println(parancs[i]);
        }
        Game.getInstance().read_command(parancs);
        fr.close();
    }

    // A következő függvény a játék aktuális állapotás írja ki
    public void writeout(Game g){
        for(int i = 0; i < g.field.getSettlers().size(); i++) {
            Settler s = g.field.getSettlers().get(i);
            System.out.println("Settler {" + i + "," + s.getAsteroid().getId() + "," + getNyersanyagok(s) + "," + getTelepotok(s) + "}");
        }
        for(int i = 0; i < g.field.getRobots().size(); i++){
            Robot r = g.field.getRobots().get(i);
            int id = i +g.field.getSettlers().size();
            System.out.println("Robot {" + id + "," + r.getAsteroid() + "}");
        }
        for(int i = 0; i < g.field.getUfos().size(); i++){
            Ufo u = g.field.getUfos().get(i);
            int id = i+g.field.getSettlers().size() + g.field.getRobots().size();
            System.out.println("Ufo {" + id +g.field.getRobots().size() + "," + u.getAsteroid().getId() + "}");
        }

        for(int i = 0; i < g.field.getAsteroids().size(); i++){
            Asteroid a = g.field.getAsteroids().get(i);
            System.out.println("Asteroid {" + a.getid() + ",(" + getTelepotokA(a) + ")," + getNyersanyagA(a) + "," + a.getCrustThickness() + "," +
                    Nearsun(a) + ",(" + Szomszedok(a) + "),(" + Entitasok(a) + ")}");
        }
    }


    /**
     * A következő 7 függvény  a játék állípotának a kiiratását segíti
     */
    String getNyersanyagok(Settler s){
        String materials = new String();
        if(s.getInventory().size() == 0)
            return "null";
        for(int i = 0; i < s.getInventory().size(); i++){
            if (s.getInventory().get(i) != null){
                materials += (s.getInventory().get(i).getName());
                materials += ",";
            }
        }
        return materials;
    }

    String getTelepotok(Settler s){
        if(s.getTeleportlist().size() == 0)
            return "null";
        String t = new String();
        for(int i = 0; i < s.getTeleportlist().size(); i++){
            t += s.getTeleportlist().get(i).toString();
            if(s.getTeleportlist().size() - 1 > i)
                 t += ",";
        }
        return t;
    }

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

    String getNyersanyagA(Asteroid a){
        if(a.getMaterial() == null)
            return "null";
        return a.getMaterial().getName();
    }

    String Nearsun(Asteroid a){
        return a.isNearSun() ? "true" : "false";
    }

    String Szomszedok(Asteroid a){
       String sz = new String();
       if(a.getNeigbours().size() == 0)
           return "null";
        for(int i = 0; i < a.getNeigbours().size(); i++) {
            sz += a.getNeigbours().get(i).getid();
            if(a.getNeigbours().size() -  1 > i)
                sz += ",";
        }
        return sz;
    }

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
