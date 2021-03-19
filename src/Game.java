import java.util.Random;

public class Game {

    //Singleton
    public static Game game = new Game();
    Field field = new Field();
    public void EndGame(){}
    public void StartGame(){ }
    public void WinGame() {}

    public static void main(String[] args) {
        System.out.println("Hello world");
    }


     Material RandomMaterial(){
        Random rand = new Random();
        Material mat = null;

        int r_num = rand.nextInt(5);
        switch (r_num){
            case 0 -> mat = new Uranium();
            case 1 -> mat = new Ice();
            case 2 -> mat = new Coal();
            case 3 -> mat = new Iron();
            case 4 -> mat = null;
        }

        return mat;
    }

}
