import java.io.*;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Skeleton implements Serializable{

    public Skeleton(){

    }
    private static String[] splitSentenceByWords(String str){

        //if string is empty or null, return empty array
        if(str == null || str.equals(""))
            return new String[0];

        String[] words = str.split("[ !\"\\#$%&'*+,-./:;<=>?@\\[\\]^_`{|}~]+");

        return words;
    }

    public void fileRead(String filename) throws IOException {
        File file=new File(filename);
        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        StringBuffer sb=new StringBuffer();
        String line;

        while((line=br.readLine())!=null)
        {
            String[] words = splitSentenceByWords(line);

                switch (words[1]){
                    case "Asteroid":{
                        long crust = Long.parseLong(words[5]);
                        boolean nearsun = Boolean.parseBoolean(words[6]);
                       Asteroid a = new Asteroid(Integer.parseInt(words[0]), crust, nearsun, null);
                       Game.getInstance().field.AddAsteroid(a);
                        switch (words[4]){
                            case "Iron": a.setMaterial(new Iron());
                            case "Coal": a.setMaterial(new Coal());
                            case "Ice":a.setMaterial(new Ice());
                            case "Uranium": a.setMaterial(new Uranium());
                        }
                        a.addTeleport(new Teleport(Integer.parseInt(words[1])));
                        //TODO: szomszedok, entitásokat az entitásnal adja hozzá
                    }
                    case "Settler":{
                        Settler s = new Settler(Integer.parseInt(words[0]));          //id
                        Asteroid a = Game.getInstance().field.getAsteroids().get(Integer.parseInt(words[1]));
                        s.setAsteroid(a);                                         //asteroida
                        a.getEntities().add(s);
                        //TODO: inventory, teleport

                    }
                    case "Robot":{
                        Robot r = new Robot(Integer.parseInt(words[0]));  //id
                        Asteroid a = Game.getInstance().field.getAsteroids().get(Integer.parseInt(words[1]));
                        r.setAsteroid(a);                                         //asteroida
                        a.getEntities().add(r);
                        //Inventory, teleport nincs neki
                    }

                    case "Ufo":{
                        Ufo u = new Ufo(Integer.parseInt(words[0]));              //id
                        Asteroid a = Game.getInstance().field.getAsteroids().get(Integer.parseInt(words[1]));
                        u.setAsteroid(a);                                         //asteroida
                        a.getEntities().add(u);

                    }
                }

            sb.append(line);
            sb.append("\n");
        }
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
            materials += (s.getInventory().get(i).getName());
            materials += ",";
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
