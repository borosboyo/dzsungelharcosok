package model;

import view.Window;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * The model.Game singleton class that manages ending and starting a game.
 */
public class Game implements Serializable {
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
     * The model.Field that contains all the asteroids and settlers.
     */
    public Field field;
    private static boolean menu = false;
    public boolean isMenu() {
        return menu;
    }
    public boolean isNew_game() {
        return new_game;
    }
    static boolean new_game = true;

    /**
     * Main methods. This is where the program runs.
     * @param args
     * @throws IOException
     */
    public static void main(String []args) throws IOException {
        Window window = new Window();
        window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
        boolean test = false;
        menu = false;  //TODO::ezt át kell majd írni úgy hogy ne static legyen, hanem lehessen változtatni
        int state = 1;
        try {
            window.playSound("sound/menu.wav", 1f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        while(state < 5){

            /*state = game.menu();
            switch (state){
                case 1: {
                    new_game = true;
                    break;
                }
                case 2:{
                    new_game = false;
                    break;
                }
                case 3:{
                    test = true;
                    break;
                }
                case 4:{
                    return;
                }
                default:{
                    System.out.println("Hibás paraméter!");
                    break;
                }
            }
            if(test){
                game.test_menu();
                test = false;
                continue;
            }*/

            if(menu){
                continue;
            }

            if(!game.StartGame(new_game)){
                System.out.println("Nincs mentett jatek!");
                continue;
            }

            Skeleton s = new Skeleton();
            int counter = 0;

            /*
            try {
                window.playSound("sound/sound.wav", 0.1f);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }

             */

            while(!game.EndGame() && !menu){
                s.writeout(game);
                menu = game.step_gamer();
                counter++;
                if(counter == game.field.getSettlers().size()){
                    Timer.getInstance().Tick();
                    counter=0;
                }
                window.repaint();
            }
        }

    }
    /**
     * Starts the game.
     */
    public boolean StartGame(boolean isNew){
        if(isNew){
            field = new Field();
            field.newField(5, 3);
            return true;
        }
        else{
            try {
                loadGame();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(game.field == null){
                return false;
            }
        }
        return true;
    }
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
     *Load the saved game (if exists)
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadGame() throws IOException, ClassNotFoundException{ //TODO::ha nincs fájl, akkor térjen vissza a menübe
        final ObjectInputStream input = new ObjectInputStream(new FileInputStream("field_status"));
        System.out.println(input);
        field = (Field)input.readObject();
    }
    /**
     *Save (Serialization) the game.
     * @throws IOException
     */
    public void saveGame() throws IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("field_status"));
        output.writeObject(field);
    }
    /**
     * Scanner for the commands console reading.
     */
    private Scanner console_read2 = new Scanner(System.in);
    public boolean step_gamer(){
        String in;
        String[] commands;
        boolean end = false;
        boolean cor = false;

        while(!cor) {
            in = console_read2.nextLine();
            commands = in.split("[ !\"\\#$%&'*+,-./:;<=>?@\\[\\]^_`{|}~]+");

            switch (commands[0]){
                case "savegame": {
                    if (commands.length != 1) {
                        cor = false;
                        break;
                    }
                    try {
                        saveGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                case "exit" : {
                    if (commands.length != 1) {
                        cor = false;
                        break;
                    }
                    return true;
                }
                default:{
                    Proto pr = null;         //ez így nem jó
                    cor = pr.read_command(commands);
                    break;
                }
            }
        }
        return false;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }
    public void setNew_game(boolean new_game) {
        this.new_game = new_game;
    }
}