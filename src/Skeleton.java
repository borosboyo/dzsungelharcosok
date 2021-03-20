import java.util.Scanner;

public class Skeleton {
    private Scanner input;
    Skeleton(){
        input = new Scanner(System.in);
    }

    public static void main(String args[]){
        Skeleton s = new Skeleton();
        int exit = 1;
         do {
            s.TestList();
            s.chooseTest();
            exit = s.EndTest();
        }while (exit != 0);

    }

    /**
     * A tesztek listája
     */
    public void TestList(){
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


    public void chooseTest(){
        int number = inputNumber();
        switch (number){
            case 1:
                Settler_Moves_test();
                break;
            case 2:
                Settler_wins_game_with_move_test();
                break;
            case 3:
                Settler_tries_to_move_on_not_neighbour();
                break;
            case 4:
                Robot_Moves();
                break;
            case 5:
                Settler_place_material();
                break;
            case 6:
                Settler_tries_to_place_material_on_full_asteroid();
                break;
            case 7:
                Settler_place_teleport();
                break;
            case 8:
                Settler_makes_teleport();
                break;
            case 9:
                Settler_uses_teleport();
                break;
            case 10:
                Settler_tries_to_use_unpaired_teleport();
                break;
            case 11:
                Robot_uses_teleport();
                break;
            case 12:
                Robot_tries_to_use_unpaired_teleport();
                break;
            case 13:
                Sunstorm_on_settler();
                break;
            case 14:
                Sunstorm_on_robot();
                break;
            case 15:
                Settler_builds_robot();
                break;
            case 16:
                Settler_mines();
                break;
            case 17:
                Settler_drills_non_trigger();
                break;
            case 18:
                Settler_drills_radioactive();
                break;
            case 19:
                Settler_drills_ice();
                break;
            case 20:
                All_settlers_die();
                break;
            case 21:
                Robot_drills_non_trigger();
                break;
            case 22:
                Robot_drills_radioactive();
                break;
            case 23:
                Robot_drills_ice();
                break;
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
     * Az konkrét tesztesetek függvényei
     */
    public void Settler_Moves_test(){

        String func[] = {
                "Settler.Move(Asteroid)",
                "Asteroid.CheckNeighbour()",
                "Asteroid.Remove(Settler)",
                "Asteroid.Accept(Settler)",
                "Asteroid.CheckBase()"
        };
        WriteTest(func);
    }

    public void Settler_wins_game_with_move_test(){
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

    public void Settler_tries_to_move_on_not_neighbour(){
        String func[] = {
                "Settler.Move(Asteroid)",
                "Asteroid.CheckNeighbour()",
        };
        WriteTest(func);
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

    public void Settler_place_material(){
        System.out.println("Current crustthickness? (Write a number between 0-10");
        int crust = input.nextInt();
        while(crust > 10 || crust < 0){
            System.out.println("The number was incorrect. Please enter a number between 0-10!)");
            crust = input.nextInt();
        }
        String func[];
        if(crust == 0)
        func = new String[]{
                "Settler.PlaceMaterial()",
                "Asteroid.CheckNeighbour()",
                "Robot.Move(Asteroid)",
                "Asteroid.Accept(Robot)",
                "Asteroid.Remove(Robot)"
        };
        else
            func = new String[]{
                    "Settler.PlaceMaterial()"};
        WriteTest(func);
    }

    public void Settler_tries_to_place_material_on_full_asteroid(){}
    public void Settler_place_teleport(){}
    public void Settler_makes_teleport(){}
    public void Settler_uses_teleport(){}
    public void Settler_tries_to_use_unpaired_teleport(){}
    public void Robot_uses_teleport(){}
    public void Robot_tries_to_use_unpaired_teleport(){}
    public void Sunstorm_on_settler(){}
    public void Sunstorm_on_robot(){}
    public void Settler_builds_robot(){}
    public void Settler_mines(){}
    public void Settler_drills_non_trigger(){}
    public void Settler_drills_radioactive(){}
    public void Settler_drills_ice(){}
    public void All_settlers_die(){}
    public void Robot_drills_non_trigger(){}
    public void Robot_drills_radioactive(){}
    public void Robot_drills_ice(){}




}
