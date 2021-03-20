import java.util.Random;

/**
 * The Game singleton class that manages ending and starting a game.
 */
public class Game {

    /**
     * The constant game.
     */
    private static Game game = new Game();

    /**
     * Private constructor so the class is Singleton.
     */
    private Game(){ }

    /**
     * Get the only instance of timer.
     *
     * @return the game.
     */
    public static Game getInstance(){
        return game;
    }

    /**
     * The Field that contains all the asteroids and settlers.
     */
    Field field = new Field();


    /**
     * Ends the game if the Settlers lose.
     */
    public void EndGame(){
        System.out.print("Settlers lose!");
    }


    /**
     * Starts the game.
     */
    public void StartGame(){

    }

    /**
     * Ends the game if the Settlers collected the required materials and won the game.
     */
    public void WinGame() {
        System.out.print("Settlers win!");
    }

}
