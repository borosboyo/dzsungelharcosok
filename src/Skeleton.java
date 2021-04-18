import java.io.*;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Skeleton {
    private Scanner input;
    private int crust;
    private boolean nearsun;

    private Settler s1;
    private Settler s2;
    private Robot r1;
    private Asteroid a1;
    private Asteroid a2;
    private Asteroid a3;
    private Asteroid a4;
    private Asteroid a5;
    private Ice ice;
    private Iron iron;
    private Uranium uran;
    private Coal coal;
    private Teleport t1;


    public Skeleton(){
        input = new Scanner(System.in);
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
                       Game.getInstance().field.AddAsteroid(a1);
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

    public void writeout(Game g){
        for(int i = 0; i < g.field.getSettlers().size(); i++) {
            Settler s = g.field.getSettlers().get(i);
            System.out.print("Settler {" + i + "," + s.getAsteroid() + "," + getNyersanyagok(s) + "," + getTelepotok(s));
        }
    }

    String getNyersanyagok(Settler s){
        String materials = new String();
        if(s.getInventory() == null)
            return "null";
        for(int i = 0; i < s.getInventory().size(); i++){
            materials += (s.getInventory().get(i).toString());
        }
        return materials;
    }

    String getTelepotok(Settler s){
        if(s.getTeleportlist() == null)
            return "null";
        String t = new String();
        for(int i = 0; i < s.getTeleportlist().size(); i++){
            t += s.getTeleportlist().get(i).toString();
        }
        return t;

    }


/*
    public static void main(String args[]){
        Skeleton s = new Skeleton();
        Game g = new Game();
        int exit = 1;
         do {
            g.readCommands();
            exit = s.EndTest(); //Az exit változót itt tuja módosítani a felhasználó
        }while (exit != 0);  /*Ha exit == 0, akkor a program befejezi a futását,
             *                  máskülönben újra a tesztek listája jelenik meg

    } */



    /**
     * A tesztek lefutását követően hívódik meg az a függvény.
     * Először kiírja a konzolra hogy vége a tesztnek, majd vár egy számot a felhasználótól
     * @return egy integer, amit a felhasználó ad meg.
     */
    public int EndTest(){
        System.out.println("**************************Test Ended**************************");
        System.out.println("Press 0 to exit, press another number to choose another test!");
        int exit = input.nextInt();
        return exit;
    }

    /**
     * A felhasználótól beolvas egy számot
     * @return a felhasználó által megadott szám
     */
    public int  inputNumber(){
        int number = input.nextInt();
        while(number > 23 || number < 1){
            System.out.println("The number was incorrect. Please enter a number between 1-23!");
            number = input.nextInt();
        }
        return number;
    }

    public void SetCrustthicknessforTest(int crust){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
                crust = input.nextInt();
                while (crust > 10 || crust < 0) {
                    System.out.println("The number was incorrect. Please enter a number between 0-10!)");
                    crust = input.nextInt();
                }
    }

    /**
     * Az esztétikus kiírást segítő függvény
     * @param functions a függvények neveit veszi át egy String[] tömbbe
     */
    public void WriteTest(String[] functions){
        System.out.print("\n");
        for(int i = 0; i < functions.length; i++){
            for(int j = 0; j < i; j++)
                System.out.print("|\t");
            System.out.print(functions[i]+ "\n");
        }
        System.out.print("\n");
    }

}
