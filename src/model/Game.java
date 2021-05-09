package model;

import view.Window;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;

/**
 * The Game singleton class that manages ending and starting a game.
 */
public class Game implements Serializable {
    private final Menu menu = new Menu();
    private GameState gameState = GameState.MENU;

    public Menu getMenu() {
        return menu;
    }


    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Boolean that indicates that the Settlers have won the game yet.
     */
    private boolean win = false;

    /**
     * Is win boolean.
     *
     * @return the boolean
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Sets win.
     *
     * @param win the win
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * The constant game.
     */
    public static Game game = new Game();

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
    public Field field;


    /**
     * Ends the game if the Settlers lose.
     */
    public boolean EndGame(){
        if(field.getSettlers().size() < 1){
            System.out.print("Settlers lose!");
            return true;
        }
        return false;
    }

    /**
     * Ends the game if the Settlers collected the required materials and won the game.
     */
    public void WinGame() {
        setWin(true);
        System.out.print("Settlers win!");
    }

    /**
     * Load the saved game (if exists)
     *
     * @throws IOException            wrong input file
     * @throws ClassNotFoundException Serializable wrong
     */
    public void loadGame() throws IOException, ClassNotFoundException {
        final ObjectInputStream input = new ObjectInputStream(new FileInputStream("field_status"));
        field = (Field) input.readObject();
    }

    /**
     *Save (Serialization) the game.
     * @throws IOException wrong file name
     */
    public void saveGame() throws IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("field_status"));
        output.writeObject(field);
    }


    public void resetGame(int settler_numb) {
        field = null;
        field = new Field();
        field.newField(settler_numb, 4);
    }


    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        Window window = new Window();
        window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
        try {
            window.playSound(0, 1f, 99);
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }


        //TODO::ez akkor kell ha rendes idő szerint is akarjuk léptetni a léptetős dolgokat, Szerintem nagyon epikus ha nem csak azután lépnek miután az összes settler lelépte a sajátját, ha kíváncsi vagy rá akkor kommentezd ki ;)
//        java.util.Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run(){
//                if(Game.game.getGameState() == GameState.GAME){
//                    GTimer.getInstance().Tick();
//                    window.repaint();
//                }
//            }
//        }, 1000, 10000); //TODO:ezeket az értékeket lehet átkell majd állítani
    }

}