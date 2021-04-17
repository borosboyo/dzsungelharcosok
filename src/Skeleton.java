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

    public void fileRead() throws IOException {
        File file=new File("Demo.txt");    //creates a new file instance
        FileReader fr=new FileReader(file);   //reads the file
        BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
        StringBuffer sb=new StringBuffer();    //constructs a string buffer with no characters
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

                    }
                    case "Settler":{
                        Settler s = new Settler(Integer.parseInt(words[0]));
                        Asteroid a = Game.getInstance().field.getAsteroids().get(Integer.parseInt(words[1]));
                        s.setAsteroid(a);
                        a.getEntities().add(s);


                    }
                    case "Robot":
                    case "Ufo":
                }

            sb.append(line);      //appends line to string buffer
            sb.append("\n");     //line feed
        }
        fr.close();    //closes the stream and release the resources
    }



    public static void main(String args[]){
        Skeleton s = new Skeleton();
        int exit = 1;
         do {
            s.TestList();
            s.chooseTest();
            exit = s.EndTest(); //Az exit változót itt tuja módosítani a felhasználó
        }while (exit != 0);  /*Ha exit == 0, akkor a program befejezi a futását,
                                máskülönben újra a tesztek listája jelenik meg*/

    }

    /**
     * A tesztek listája, amit a konzolra kiír ez a függvény
     */
    public void TestList(){
        setUp();
        System.out.print("\n***************************New Test***************************");
        System.out.print(
                "Please select a test!\n" +
                        "Press 1 to test Settler Moves\n" +
                        "Press 2 to test Settler wins game with move\n" +
                        "Press 3 to test Settler tries to move on not neighbour\n" +
                        "Press 4 to test Robot moves\n" +
                        "Press 5 to test Settler place material \n" +
                        "Press 6 to test Settler tries to place material on full asteroid\n" +
                        "Press 7 to test Settler place teleport\n" +
                        "Press 8 to test Settler makes teleport\n" +
                        "Press 9 to test Settler use teleport\n" +
                        "Press 10 to test Settler tries to use unpaired teleport\n" +
                        "Press 11 to test Robot uses teleport\n" +
                        "Press 12 to test Robot tries to use unpaired teleport\n" +
                        "Press 13 to test Sunstorm on settler\n" +
                        "Press 14 to test Sunstorm on robot\n" +
                        "Press 15 to test Settler builds robot\n" +
                        "Press 16 to test Settler mines\n" +
                        "Press 17 to test Settler drills non-trigger\n" +
                        "Press 18 to test Settler drills radioactive\n" +
                        "Press 19 to test Settler drills ice\n" +
                        "Press 20 to test All settlers die\n" +
                        "Press 21 to test Robot drills non-trigger\n" +
                        "Press 22 to test Robot drills radioactive\n" +
                        "Press 23 to test Robot drills ice\n"
        );
    }

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

    /**
     * Ez a függvény a felhasználótól kér egy számot, majd ennek a függvényében meghívja az adott tesztet.
     */
    public void chooseTest(){
        int number = inputNumber();
        switch (number){
            case 1:
                Settler_Moves();
                Settler_Moves_Test();
                break;
            case 2:
                Settler_wins_game_with_move();
                Settler_wins_game_with_move_test();
                break;
            case 3:
                Settler_tries_to_move_on_not_neighbour();
                Settler_tries_to_move_on_not_neighbour_test();
                break;
            case 4:
                Robot_Moves();
                Robot_Moves_test();
                break;
            case 5:
                Settler_place_material();
                Settler_place_material_test();
                break;
            case 6:
                Settler_tries_to_place_material_on_full_asteroid();
                Settler_tries_to_place_material_on_full_asteroid_test();
                break;
            case 7:
                Settler_place_teleport();
                Settler_place_teleport_test();
                break;
            case 8:
                Settler_makes_teleport();
                Settler_makes_teleport_test();
                break;
            case 9:
                Settler_uses_teleport();
                Settler_uses_teleport_test();
                break;
            case 10:
                Settler_tries_to_use_unpaired_teleport();
                Settler_tries_to_use_unpaired_teleport_test();
                break;
            case 11:
                Robot_uses_teleport();
                Robot_uses_teleport_test();
                break;
            case 12:
                Robot_tries_to_use_unpaired_teleport();
                Robot_tries_to_use_unpaired_teleport_test();
                break;
            case 13:
                Sunstorm_on_settler();
                Sunstorm_on_settler_test();
                break;
            case 14:
                Sunstorm_on_robot();
                Sunstorm_on_robot_test();
                break;
            case 15:
                Settler_builds_robot();
                Settler_builds_robot_test();
                break;
            case 16:
                Settler_mines();
                //Settler_mines_test();
                break;
            case 17:
                Settler_drills_non_trigger();
                Settler_drills_non_trigger_test();
                break;
            case 18:
                Settler_drills_radioactive();
                //Settler_drills_radioactive_test();
                break;
            case 19:
                Settler_drills_ice();
                Settler_drills_ice_test();
                break;
            case 20:
                All_settlers_die();
                break;
            case 21:
                Robot_drills_non_trigger();
                Robot_drills_non_trigger_test();
                break;
            case 22:
                Robot_drills_radioactive();
                Robot_drills_radioactive_test();
                break;
            case 23:
                Robot_drills_ice();
                Robot_drills_ice_test();
                break;
        }
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

    /**
     * Junit test set up.
     */
    @Before
    public void setUp(){
        ice = new Ice();
        coal = new Coal();
        iron = new Iron();
        uran = new Uranium();
        s1 = new Settler(1);
        s2 = new Settler(2);
        r1= new Robot();

        a1 = new Asteroid(1,3,false, ice);
        ice.setAsteroid(a1);
        a2 = new Asteroid(2,3,false, coal);
        ice.setAsteroid(a2);
        a3 = new Asteroid(3,3,false, iron);
        ice.setAsteroid(a3);
        a4 = new Asteroid(4,3,false, uran);
        ice.setAsteroid(a4);
        a5 = new Asteroid(5,0,false, null);
        ice.setAsteroid(a5);


        a1.Accept(s1);
        s1.setAsteroid(a1);

        a1.Accept(r1);
        r1.setAsteroid(a1);

        a2.Accept(s2);
        s2.setAsteroid(a2);

        Game.getInstance().field.AddAsteroid(a1);
        Game.getInstance().field.AddAsteroid(a2);
        Game.getInstance().field.AddAsteroid(a3);
        Game.getInstance().field.AddAsteroid(a4);
        Game.getInstance().field.AddAsteroid(a5);

        Game.getInstance().field.AddSettler(s1);
        Game.getInstance().field.AddSettler(s2);

        a1.AddNeighbour(a2);
        a2.AddNeighbour(a1);
        a2.AddNeighbour(a3);
        a3.AddNeighbour(a1);
        a4.AddNeighbour(a5);
        a5.AddNeighbour(a4);
        a5.AddNeighbour(a1);
        a1.AddNeighbour(a5);

        t1 = new Teleport();
        t1.getAsteroids().add(a1);
        t1.getAsteroids().add(a2);
    }
    
    /**
     * Az konkrét tesztesetek függvényei következnek.
     * A "Use_Case()" formátumú függvények a konzolon való megjelenést biztosítják.
     * A "Use_Case_Test()" formátumú függvények a tartalmi tesztelést hajtják végre.
     */

    public void Settler_Moves(){
        String func[] = {
                "Settler.Move(Asteroid)",
                "Asteroid.CheckNeighbour()",
                "Asteroid.Remove(Settler)",
                "Asteroid.Accept(Settler)",
                "Asteroid.CheckBase()"
        };
        WriteTest(func);
    }

    /**
     * Settler moves junit test.
     */
    @Test
    public void Settler_Moves_Test(){
        a1.getEntities().clear();
        a1.getNeigbours().clear();
        a2.getEntities().clear();
        a2.getNeigbours().clear();

        a1.Accept(s1);
        s1.setAsteroid(a1);
        a2.Accept(s2);
        s2.setAsteroid(a2);

        a1.getNeigbours().add(a2);
        a2.getNeigbours().add(a1);


        Assert.assertTrue(s1.getAsteroid() == a1);
        Assert.assertTrue(s2.getAsteroid() == a2);
        Assert.assertTrue(a1.getEntities().get(0) == s1);
        Assert.assertTrue(a2.getEntities().get(0) == s2);
        s1.Move(a2);
        Assert.assertTrue(a1.getEntities().size() == 0);
        Assert.assertTrue(a2.getEntities().size() == 2);
        Assert.assertTrue(a2.getEntities().get(0) == s2);
        Assert.assertTrue(a2.getEntities().get(1) == s1);
        Assert.assertTrue(s1.getAsteroid() == a2);
        Assert.assertTrue(s2.getAsteroid() == a2);
    }

    public void Settler_wins_game_with_move(){
        String func[] = {
                "Settler.Move(Asteroid)",
                "Asteroid.CheckNeighbour()",
                "Asteroid.Remove(Settler)",
                "Asteroid.Accept(Settler)",
                "Asteroid.CheckBase()",
                "Game.Wingame()"
        };
        WriteTest(func);
    }

    /**
     * Settker wins game with move junit test.
     */
    @Test
    public void Settler_wins_game_with_move_test(){
        s1.getInventory().add(ice);
        s1.getInventory().add(ice);
        s1.getInventory().add(iron);
        s1.getInventory().add(iron);
        s1.getInventory().add(coal);
        s1.getInventory().add(coal);
        s1.getInventory().add(uran);
        s1.getInventory().add(uran);

        s2.getInventory().add(ice);
        s2.getInventory().add(iron);
        s2.getInventory().add(coal);
        s2.getInventory().add(uran);

        Assert.assertEquals(8, s1.getInventory().size());
        Assert.assertEquals(4, s2.getInventory().size());

        s1.Move(a2);

        Assert.assertSame(a2.getEntities().get(0), s2);
        Assert.assertSame(a2.getEntities().get(1), s1);

        Game.getInstance().field.CheckReqMat(a2);

    }


    public void Settler_tries_to_move_on_not_neighbour(){
        String func[] = {
                "Settler.Move(Asteroid)",
                "Asteroid.CheckNeighbour()",
        };
        WriteTest(func);
    }

    /**
     * Settler tries to move on not neighbour junit test.
     */
    @Test
    public void Settler_tries_to_move_on_not_neighbour_test(){
        a1.RemoveNeighbour(a2);
        a2.RemoveNeighbour(a1);
        s1.setAsteroid(a1);

        int a2entitysize = a2.getEntities().size();
        Assert.assertTrue(s1.getAsteroid()==a1);
        s1.Move(a2);
        Assert.assertTrue(s1.getAsteroid()==a1);
        Assert.assertEquals(a2entitysize,a2.getEntities().size());
    }

    public void Robot_Moves(){
        String func[] = {
                "Robot.Step()",
                "Asteroid.CheckNeighbour()",
                "Robot.Move(Asteroid)",
                "Asteroid.Accept(Robot)",
                "Asteroid.Remove(Robot)"
        };
        WriteTest(func);
    }

    /**
     * Robot moves junit test.
     */
    @Test
    public void Robot_Moves_test(){
        Asteroid ast= r1.getAsteroid();
        r1.Move(a2);
        Assert.assertEquals(a2, r1.getAsteroid());
        Assert.assertNotEquals(ast, r1.getAsteroid());
    }

    public void Settler_place_material(){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
        int crust = input.nextInt();
        while(crust > 10 || crust < 0){
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        String func[];
        if(crust == 0)
        func = new String[]{
                "Settler.PlaceMaterial()",
                "Asteroid.Placeinside()",
                "Asteroid.Addmaterial(Material)",
                "Settler.RemoveInventory(Material)"
        };
        else
            func = new String[]{
                    "Settler.PlaceMaterial()"};
        WriteTest(func);
    }

    /**
     * Settler place material junit test.
     */
    @Test
    public void Settler_place_material_test(){

        a1.setCrustThickness(0);
        a1.RemoveMaterial();
        Assert.assertEquals(null, a1.getMaterial());
        s1.getInventory().add(iron);
        System.out.println(s1.getInventory().get(0));
        int inventorysize= s1.getInventory().size();
        s1.PlaceMaterial();
        Assert.assertTrue(iron == a1.getMaterial());
        Assert.assertEquals(inventorysize-1, s1.getInventory().size());

    }

    public void Settler_tries_to_place_material_on_full_asteroid(){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
        int crust = input.nextInt();
        while(crust > 10 || crust < 0){
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        String func[];
        if(crust == 0)
            func = new String[]{
                    "Settler.PlaceMaterial()",
                    "Asteroid.PlaceInside()",
            };
        else
            func = new String[]{
                    "Settler.PlaceMaterial()"};
        WriteTest(func);
    }

    /**
     * Settler tries to place material on full asteroid junit test.
     */
    @Test
    public void Settler_tries_to_place_material_on_full_asteroid_test(){
        a1.setCrustThickness(0);
        a1.setMaterial(new Iron());
        Material mat= a1.getMaterial();
        s1.getInventory().add(new Ice());
        int inventorysize= s1.getInventory().size();
        s1.PlaceMaterial();
        Assert.assertEquals(mat, a1.getMaterial());
        Assert.assertEquals(inventorysize, s1.getInventory().size());

    }

    public void Settler_place_teleport(){
        String func[] = {
                "Settler.PlaceTeleport()",
                "Asteroid.BuildTeleport()",
                "Teleport.AddAsteroid(Asteroid)"
        };
        WriteTest(func);
    }

    /**
     * Settler place teleport junit test.
     */
    @Test
    public void Settler_place_teleport_test(){
        Teleport t2= new Teleport();
        s1.getTeleportlist().add(t2);
        s1.getTeleportlist().get(0).getAsteroids().add(s1.getAsteroid());

        Assert.assertEquals(1, s1.getTeleportlist().size());

        s1.Move(a2);
        s1.getTeleportlist().get(0).getAsteroids().add(s1.getAsteroid());
        s1.getTeleportlist().remove(0);

        Assert.assertEquals(0, s1.getTeleportlist().size());

        Assert.assertEquals(a1, t2.getAsteroids().get(0));
        Assert.assertEquals(a2, t2.getAsteroids().get(1));
    }

    public void Settler_makes_teleport(){
        System.out.println("Collect all materials? (0: no, else: yes");
        int collect = input.nextInt();
        String func[];
        if(collect != 0)
        func = new String[] {
                "Settler.MakeTeleport()",
                "Teleport.Teleport()",
                "Settler.AddTeleport(Teleport)"
        };
        else
            func = new String[]{
                    "Settler.MakeTeleport()"
            };
        WriteTest(func);
    }

    /**
     * Settler makes teleport junit test.
     */
    @Test
    public void Settler_makes_teleport_test(){
        Assert.assertEquals(0, s1.getInventory().size());

        Iron iron2 = new Iron();
        Iron iron3 = new Iron();
        s1.getInventory().add(iron2);
        s1.getInventory().add(iron);
        s1.getInventory().add(ice);
        s1.getInventory().add(uran);
        s1.getInventory().add(iron3);

        Assert.assertEquals(5, s1.getInventory().size());

        s1.MakeTeleport();

        Assert.assertEquals(1, s1.getInventory().size());
        Assert.assertEquals(1, s1.getTeleportlist().size());
    }

    public void Settler_uses_teleport(){
        String func[] = {
                "Settler.UseTeleport(Teleport)",
                "Teleport.Transfer(Settler)",
                "Asteroid.Accept(Settler)",
                "Asteroid.Remove(Settler)"
        };
        WriteTest(func);
    }

    /**
     * Settler uses teleport junit test.
     */
    @Test
    public void Settler_uses_teleport_test(){
        a1.getEntities().clear();
        a1.getEntities().add(s1);
        a2.getEntities().clear();
        Assert.assertSame(a1.getEntities().get(0), s1);
        Assert.assertEquals(1, a1.getEntities().size());
        Assert.assertEquals(0, a2.getEntities().size());
       // Assert.assertSame(a2.getEntities().get(0), s2);
        Assert.assertSame(s1.getAsteroid(), a1);
      //  Assert.assertSame(s2.getAsteroid(), a2);

        s1.UseTeleport(t1);

     //   Assert.assertEquals(1, a2.getEntities().size());
      //  Assert.assertEquals(0, a1.getEntities().size());
      //  Assert.assertEquals(1, a2.getEntities().size());

       Assert.assertSame(s1.getAsteroid(), a2);

    }

    public void Settler_tries_to_use_unpaired_teleport(){

        String func[] = {
                "Settler.UseTeleport(Teleport)",
                "Teleport.Transfer(Settler)"
        };
        WriteTest(func);
    }

    /**
     * Settler tries to use unpaired teleport junit test.
     */
    @Test
    public void Settler_tries_to_use_unpaired_teleport_test(){
        Assert.assertSame(a1.getEntities().get(0), s1);
        Assert.assertSame(a2.getEntities().get(0), s2);
        Assert.assertSame(s1.getAsteroid(), a1);
        Assert.assertSame(s2.getAsteroid(), a2);
        t1.getAsteroids().remove(1);

        s1.UseTeleport(t1);

        Assert.assertSame(a1.getEntities().get(0), s1);
        Assert.assertSame(a2.getEntities().get(0), s2);
        Assert.assertSame(s1.getAsteroid(), a1);
        Assert.assertSame(s2.getAsteroid(), a2);
    }

    public void Robot_uses_teleport(){
        String func[] = {
                "Robot.UseTeleport(Teleport)",
                "Teleport.Transfer(Robot)",
                "Asteroid.Accept(Robot)",
                "Asteroid.Remove(Robot)"
        };
        WriteTest(func);
    }

    /**
     * Robot uses teleport junit test.
     */
    @Test
    public void Robot_uses_teleport_test(){
        a1.getEntities().clear();
        a1.getEntities().add(r1);
        a2.getEntities().clear();
        Assert.assertSame(a1.getEntities().get(0), r1);
        Assert.assertEquals(1, a1.getEntities().size());
        Assert.assertEquals(0, a2.getEntities().size());
        // Assert.assertSame(a2.getEntities().get(0), s2);
        Assert.assertSame(r1.getAsteroid(), a1);
        //  Assert.assertSame(s2.getAsteroid(), a2);

        r1.UseTeleport(t1);

        //   Assert.assertEquals(1, a2.getEntities().size());
        //  Assert.assertEquals(0, a1.getEntities().size());
        //  Assert.assertEquals(1, a2.getEntities().size());

        Assert.assertSame(r1.getAsteroid(), a2);
        Assert.assertSame(s2.getAsteroid(), a2);

    }

    public void Robot_tries_to_use_unpaired_teleport(){
        String func[] = {
                "Robot.UseTeleport(Teleport)",
                "Teleport.Transfer(Robot)"
        };
        WriteTest(func);
    }

    /**
     * Robot tries to use unpaired teleport junit test.
     */
    @Test
    public void Robot_tries_to_use_unpaired_teleport_test(){
        a1.getEntities().clear();
        a1.getEntities().add(r1);
        a2.getEntities().clear();
        Assert.assertSame(a1.getEntities().get(0), r1);
        Assert.assertSame(r1.getAsteroid(), a1);
        t1.getAsteroids().remove(1);

        r1.UseTeleport(t1);

        Assert.assertSame(a1.getEntities().get(0), r1);
        Assert.assertSame(r1.getAsteroid(), a1);

    }

    public void Sunstorm_on_settler(){
        System.out.println("Is the asteroid empty? (0: no, else: yes)");
        int empty = input.nextInt();
        String func[];
        if(empty == 0)
        func = new String[] {
                "Asteroid.SunStorm()",
                "Settler.Die()",
                "Asteroid.Remove(Settler)",
                "Field.RemoveSettler()"
        };
        else
            func = new String[]{
                    "Asteroid.SunStorm()"
            };
        WriteTest(func);
    }

    /**
     * Sunstorm on settler junit test.
     */
    @Test
    public void Sunstorm_on_settler_test(){
        a1.setCrustThickness(0);
        a1.setMaterial(null);
        a1.Accept(s1);
        a1.Sunstorm();
        Assert.assertNotNull(s1);
        Assert.assertEquals(s1.getAsteroid(), a1);
    }

    public void Sunstorm_on_robot(){
        System.out.println("Is the asteroid empty? (0: no, else: yes)");
        int empty = input.nextInt();
        String func[];
        if(empty == 0)
            func = new String[] {
                    "Asteroid.SunStorm()",
                    "Robot.Die()",
                    "Asteroid.Remove(Robot)"
            };
        else
            func = new String[]{
                    "Asteroid.SunStorm()"
            };
        WriteTest(func);
    }

    /**
     * Sunstorm on robot junit test
     */
    @Test
    public void Sunstorm_on_robot_test(){
        a1.setCrustThickness(0);
        a1.setMaterial(null);
        a1.Accept(r1);
        a1.Sunstorm();
        Assert.assertNotNull(s1);
        Assert.assertEquals(r1.getAsteroid(), a1);
    }

    public void Settler_builds_robot(){
        System.out.println("Does the settler have enough materials? (0: no, else: yes)");
        int empty = input.nextInt();
        String func[];
        if(empty == 0)
            func = new String[] {
                    "Settler.BuildRobot()"
            };
        else
            func = new String[]{
                    "Settler.BuildRobot()",
                    "Robot.Robot()",
                    "Robot.SetAsteroid(Asteroid)",
                    "Asteroid.Accept(Robot)"
            };
        WriteTest(func);
    }

    /**
     * Settler builds robot junit test.
     */
    @Test
    public void Settler_builds_robot_test(){
        Assert.assertEquals(0, s1.getInventory().size());

        int entitiesonPlanet= s1.getAsteroid().getEntities().size();

        Iron iron2 = new Iron();
        Coal coal2 = new Coal();
        s1.getInventory().add(coal2);
        s1.getInventory().add(iron2);
        s1.getInventory().add(iron);

        s1.getInventory().add(uran);

        Assert.assertEquals(4, s1.getInventory().size());

        s1.BuildRobot();

        Assert.assertEquals(1, s1.getInventory().size());
        Assert.assertEquals(entitiesonPlanet+1, s1.getAsteroid().getEntities().size());

    }

    public void Settler_mines(){
        System.out.println("Do the settlers collect the required materials? (0: no, else: yes)");
        int empty = input.nextInt();
        String func[];
        if(empty != 0)
            func = new String[] {
                    "Settler.Mine()",
                    "Asteroid.MinedBy()",
                    "Asteroid.RemoveMaterial()",
                    "Settlers.AddInventory(Material)",
                    "Asteroid.CheckBase()",
                    "Game.Wingame()"
            };
        else
            func = new String[]{
                    "Settler.Mine()",
                    "Asteroid.MinedBy()",
                    "Asteroid.RemoveMaterial()",
                    "Settlers.AddInventory(Material)",
                    "Asteroid.CheckBase()"
            };
        WriteTest(func);
    }

    /**
     * Settler Mines test
     */
    @Test
    public void Settler_mines_test(){
        a1.setMaterial(iron);
        a1.Accept(s1);
        a1.Accept(s2);
        s1.getInventory().add(iron);
        s1.getInventory().add(iron);
        s1.getInventory().add(uran);
        s1.getInventory().add(uran);
        s1.getInventory().add(uran);
        s2.getInventory().add(ice);
        s2.getInventory().add(ice);
        s2.getInventory().add(ice);
        s2.getInventory().add(coal);
        s2.getInventory().add(coal);
        s1.Mine();
        Assert.assertNotNull(s1.getInventory());
        Assert.assertNull(a1.getMaterial());
        Assert.assertTrue(Game.getInstance().isWin());

    }



    public void Settler_drills_non_trigger() {
        this.SetCrustthicknessforTest(crust);
        String func[];
        if (crust == 0) {
            func = new String[]{
                    "Settler.Drill()",
                    "Asteroid.DrilledBy()"
            };
        }
        else
            func = new String[]{
                    "Settler.Drill()"
            };
    }

    /**
     * Settler drills non trigger junit test.
     */
    @Test
    public void Settler_drills_non_trigger_test(){
        a1.getEntities().clear();
        a1.Accept(s1);
        s1.setAsteroid(a1);
        a1.setCrustThickness(crust);
        a1.setNearSun(nearsun);
        a1.RemoveMaterial();
        a1.AddMaterial(coal);
        ice.setAsteroid(a1);

        Assert.assertEquals(crust, a1.getCrustThickness());
        Assert.assertSame(coal, a1.getMaterial());

        s1.Drill();

        if(crust > 0){
            Assert.assertEquals(crust, a1.getCrustThickness() + 1);
            Assert.assertSame(a1.getMaterial(), coal);
        }else
        if(crust == 0) {
            Assert.assertEquals(crust, a1.getCrustThickness());
            Assert.assertSame(a1.getMaterial(), coal);
        }
    }

    public void Settler_drills_radioactive(){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
        crust = input.nextInt();
        while (crust > 10 || crust < 0) {
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        System.out.println("Is the asteorid nearsun? (0: no, else: yes)");
        int near = input.nextInt();
        if(near == 0)
            nearsun = false;
        else
             nearsun = true;

        String func[];
        if (nearsun && crust == 1) {
            func = new String[]{
                    "Settler.Drill()",
                    "Asteroid.DrilledBy()",
                    "Uranium.Trigger()",
                    "Asteroid.Exolode()",
                    "Settler.Blow()"
            };
        } else {
            if (crust > 0)
                func = new String[]{
                        "Settler.Drill()",
                        "Asteroid.DrilledBy()"
                };
            else
                func = new String[]{
                        "Settler.Drill()"
                };
        }
        WriteTest(func);
    }

    /**
     * Settler drills radioactive junit test.
     */
    @Test
    public void Settler_drills_radioactive_test(){
        a1.setCrustThickness(3);
        nearsun = false;
        s1.Drill();
        Assert.assertEquals(a1.getCrustThickness(), 2);
        a1.setCrustThickness(1);
        a1.setMaterial(uran);
        nearsun = true;
        s1.Drill();
        a1.DrilledBy();
        Assert.assertNull(a1);
    }

    public void Settler_drills_ice(){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
        crust = input.nextInt();
        while (crust > 10 || crust < 0) {
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        System.out.println("Is the asteorid nearsun? (0: no, else: yes)");
        int near = input.nextInt();
        if(near == 0)
            nearsun = false;
        else
            nearsun = true;
        String func[];
        if (nearsun && crust == 1) {
            func = new String[]{
                    "Settler.Drill()",
                    "Asteroid.DrilledBy()",
                    "Uranium.Trigger()",
                    "Asteroid.RemoveMaterial()",
                    "Settler.Blow()"
            };
        } else {
            if (crust > 0)
                func = new String[]{
                        "Settler.Drill()",
                        "Asteroid.DrilledBy()"
                };
            else
                func = new String[]{
                        "Settler.Drill()"
                };
        }
        WriteTest(func);
    }

    /**
     * Settler drills ice junit test.
     */
    @Test
    public void Settler_drills_ice_test(){
        a1.getEntities().clear();
        a1.Accept(s1);
        s1.setAsteroid(a1);
        a1.setCrustThickness(crust);
        a1.setNearSun(nearsun);
        a1.RemoveMaterial();
        a1.AddMaterial(ice);
        ice.setAsteroid(a1);

        Assert.assertEquals(crust, a1.getCrustThickness());
        Assert.assertSame(ice, a1.getMaterial());

        s1.Drill();


        if(nearsun && crust == 1){
            Assert.assertNull(a1.getMaterial());
            Assert.assertFalse(crust != a1.getCrustThickness() + 1);
        }else
            if(crust > 0){
                Assert.assertEquals(crust, a1.getCrustThickness() + 1);
                Assert.assertSame(a1.getMaterial(), ice);
        }else
            if(crust == 0){
                Assert.assertEquals(crust, a1.getCrustThickness());
                Assert.assertSame(a1.getMaterial(), ice);
        }
    }


    public void All_settlers_die(){
        String func[] = {
                "Field.RemoveSettler(Settler)",
                "Game.EndGame()"
        };
        WriteTest(func);
    }


    public void Robot_drills_non_trigger(){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
        crust = input.nextInt();
        while (crust > 10 || crust < 0) {
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        String func[];
        if (crust != 0)
            func = new String[]{
                    "Robot.Drill()",
                    "Asteroid.DrilledBy()"
            };
        else
            func = new String[]{
                    "Robot.Drill()"
            };
        WriteTest(func);
    }

    /**
     * Robot drills non-trigger junit test.
     */
    @Test
    public void Robot_drills_non_trigger_test(){
        a1.getEntities().clear();
        a1.Accept(r1);
        r1.setAsteroid(a1);
        a1.setCrustThickness(crust);
        a1.setNearSun(nearsun);
        a1.RemoveMaterial();
        a1.AddMaterial(coal);
        ice.setAsteroid(a1);

        Assert.assertEquals(crust, a1.getCrustThickness());
        Assert.assertSame(coal, a1.getMaterial());

        r1.Drill();

        if(crust > 0){
            Assert.assertEquals(crust, a1.getCrustThickness() + 1);
            Assert.assertSame(a1.getMaterial(), coal);
        }else
            if(crust == 0) {
                Assert.assertEquals(crust, a1.getCrustThickness());
                Assert.assertSame(a1.getMaterial(), coal);
        }
    }


    public void Robot_drills_radioactive(){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
        crust = input.nextInt();
        while (crust > 10 || crust < 0) {
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        System.out.println("Is the asteorid nearsun? (0: no, else: yes)");
        int near = input.nextInt();
        if(near == 0)
            nearsun = false;
        else
            nearsun = true;
        String func[];
        if (nearsun && crust == 1)
            func = new String[]{
                    "Robot.Drill()",
                    "Asteroid.DrilledBy()",
                    "Uranium.Trigger()",
                    "Asteroid.Explode()",
                    "Robot.Blow()"
            };
        else {
            if (crust > 0)
                func = new String[]{
                        "Robot.Drill()",
                        "Asteroid.DrilledBy()"
                };
            else
                func = new String[]{
                        "Robot.Drill()"
                };
        }
        WriteTest(func);
    }

    /**
     * Robot drills radioactive junit test.
     */
    @Test
    public void Robot_drills_radioactive_test(){
        a1.getEntities().clear();
        a1.Accept(s1);
        a1.getNeigbours().clear();
        a1.getNeigbours().add(a2);
        a1.getNeigbours().add(a3);
        s1.setAsteroid(a1);
        a1.Accept(r1);
        r1.setAsteroid(a1);
        a1.setCrustThickness(crust);
        a1.setNearSun(nearsun);
        a1.RemoveMaterial();
        a1.AddMaterial(uran);
        uran.setAsteroid(a1);

        Assert.assertEquals(crust, a1.getCrustThickness());
        Assert.assertSame(uran, a1.getMaterial());
        Assert.assertTrue(a1.getNeigbours().size() == 2);

        r1.Drill();



        if(nearsun && crust == 1){
            //
        }else
        if(crust > 0){
            Assert.assertEquals(crust, a1.getCrustThickness() + 1);
            Assert.assertSame(a1.getMaterial(), uran);
        }else
        if(crust == 0){
            Assert.assertEquals(crust, a1.getCrustThickness());
            Assert.assertSame(a1.getMaterial(), uran);
        }
    }


    public void Robot_drills_ice(){
        System.out.println("Current crustthickness? (Write a number between 0-10)");
        crust = input.nextInt();
        while (crust > 10 || crust < 0) {
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        System.out.println("Is the asteorid nearsun? (0: no, else: yes)");
        int near = input.nextInt();
        if(near == 0)
            nearsun = false;
        else
            nearsun = true;
        String func[];
        if (nearsun && crust == 1) {
            func = new String[]{
                    "Robot.Drill()",
                    "Asteroid.DrilledBy()",
                    "Uranium.Trigger()",
                    "Asteroid.RemoveMaterial()",
                    "Robot.Blow()"
            };
        } else {
            if (crust != 0)
                func = new String[]{
                        "Robot.Drill()",
                        "Asteroid.DrilledBy()"
                };
            else
                func = new String[]{
                        "Robot.Drill()"
                };
        }
        WriteTest(func);
    }

    /**
     * Robot drill ice junit test.
     */
    @Test
    public void Robot_drills_ice_test() {
        a1.getEntities().clear();
        a1.Accept(r1);
        r1.setAsteroid(a1);
        a1.setCrustThickness(crust);
        a1.setNearSun(nearsun);
        a1.RemoveMaterial();
        a1.AddMaterial(ice);
        ice.setAsteroid(a1);

        Assert.assertEquals(crust, a1.getCrustThickness());
        Assert.assertSame(ice, a1.getMaterial());

        r1.Drill();


        if (nearsun && crust == 1) {
            Assert.assertNull(a1.getMaterial());
            Assert.assertFalse(crust != a1.getCrustThickness() + 1);
        } else if (crust > 0) {
            Assert.assertEquals(crust, a1.getCrustThickness() + 1);
            Assert.assertSame(a1.getMaterial(), ice);
        } else if (crust == 0) {
            Assert.assertEquals(crust, a1.getCrustThickness());
            Assert.assertSame(a1.getMaterial(), ice);
        }
    }
}
