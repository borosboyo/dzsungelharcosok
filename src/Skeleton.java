import java.util.Scanner;

public class Skeleton {


    public static void main(String args[]){
        Skeleton s = new Skeleton();
        s.TestList();
        s.chooseTest();
    }
    public void TestList(){
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

    public int  inputNumber(){
        Scanner input = new Scanner(System.in);
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
            case 2:
                Settler_wins_game_with_move_test();
            case 3:

            case 4:

            case 5:

            case 6:

            case 7:

            case 8:

            case 9:

            case 10:

            case 11:

            case 12:

            case 13:

            case 14:

            case 15:

            case 16:

            case 17:

            case 18:

            case 19:

            case 20:

            case 21:

            case 22:

            case 23:

        }
    }


    public void WriteTest(String[] functions){
        System.out.print("\n");
        for(int i = 0; i < functions.length; i++){
            for(int j = 0; j < i; j++)
                System.out.print("|\t");
            System.out.print(functions[i]+ "\n");
        }
    }

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
    public void Settler_tries_to_move_on_not_neighbour(){}
    public void Robot_Moves(){}

}
