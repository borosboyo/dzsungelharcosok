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

}
