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
    private Menu menu = new Menu();
    private GameState gameState = GameState.MENU;

    public Menu getMenu() {
        return menu;
    }

    public GameState getGameState() {
        return gameState;
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
     * The model.Field that contains all the asteroids and settlers.
     */
    public Field field;

    public boolean isNew_game() {
        return new_game;
    }
    static boolean new_game = true;

    Skeleton s = new Skeleton();
    int counter = 0;


    public void step(){
        if(game.EndGame()){
            game.gameState = GameState.MENU;
            return;
        }

       s.writeout(game);

       if(game.step_gamer()){
           //Ha hamis akkor menube vissza
       }

       System.out.println(counter);
        if(counter == game.field.getSettlers().size()){
            Timer.getInstance().Tick();
            counter=0;

            for(int i = 0; i<game.field.getSettlers().size(); i++){
                game.field.getSettlers().get(i).setFinishedTurn(false);
            }
        }
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
                    cor = read_command(commands);
                    break;
                }
            }
        }
        counter++;
        return false;
    }

    public void resetGame(){ //TODO::elég ennyi?
        field = null;
        field = new Field();
        field.newField(5, 3);
    }

    public void setNew_game(boolean new_game) {
        this.new_game = new_game;
    }

    public boolean read_command(String[] commands){
        boolean correct = false;
        int enti = 0;
        Settler se = null;
        Ufo uf = null;
        Robot ro = null;

        if(commands[0] == null){
            return false;
        }

        if(commands.length < 2){
            System.out.println("Helytelen bemenet!....1");
            return false;
        }else{
            for (int i = 0; i  < Game.getInstance().field.getSettlers().size(); i++){
                if(Integer.parseInt(commands[1]) == Game.getInstance().field.getSettlers().get(i).getId()){
                    se = Game.getInstance().field.getSettlers().get(i);
                    enti = 0;
                    correct = true;
                    break;
                }
            }
            if(!correct){
                for (int i = 0; i  < Game.getInstance().field.getRobots().size(); i++){
                    if(Integer.parseInt(commands[1]) == Game.getInstance().field.getRobots().get(i).getId()){
                        ro = Game.getInstance().field.getRobots().get(i);
                        enti = 1;
                        correct = true;
                        break;
                    }
                }
            }
            if(!correct) {
                for (int i = 0; i < Game.getInstance().field.getUfos().size(); i++) {
                    if (Integer.parseInt(commands[1]) == Game.getInstance().field.getUfos().get(i).getId()) {
                        uf = Game.getInstance().field.getUfos().get(i);
                        enti = 2;
                        correct = true;
                        break;
                    }
                }
            }
        }

        if(!correct){
            System.out.println("Helytelen bemenet!.....2");
            return false;
        }

        switch (commands[0]){
            case "move":{
                if(commands.length != 3){
                    correct = false;
                    break;
                }
                for(int i=0; i<Game.getInstance().field.getAsteroids().size(); i++){
                    if(Integer.parseInt(commands[2]) == Game.getInstance().field.getAsteroids().get(i).getId()){
                        if(enti == 0){
                            se.Move(Game.getInstance().field.getAsteroids().get(i));
                            se.setFinishedTurn(true);
                        }
                        if(enti == 1){
                            ro.Move(Game.getInstance().field.getAsteroids().get(i));
                        }
                        if(enti == 2){
                            uf.Move(Game.getInstance().field.getAsteroids().get(i));
                        }
                        correct = true;
                        break;
                    }
                    else{
                        correct = false;
                    }
                }
                break;
            }
            case "drill":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                if(enti == 0){
                    se.Drill();
                    se.setFinishedTurn(true);
                }
                if(enti == 1){
                    ro.Drill();
                }
                correct = true;
                break;
            }
            case "mine":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                if (enti ==0){
                    se.Mine();
                    se.setFinishedTurn(true);
                }
                if(enti == 1){
                    uf.Mine();
                }
                correct = true;
                break;
            }
            case "useteleport":{
                if(commands.length != 3){
                    correct = false;
                    break;
                }
                for(int i=0; i<se.getAsteroid().getTeleports().size(); i++){
                    if(Integer.parseInt(commands[2]) == se.getAsteroid().getTeleports().get(i).getId()){
                        se.UseTeleport(se.getAsteroid().getTeleports().get(i));
                        se.setFinishedTurn(true);
                        correct = true;
                        break;
                    }
                    else{
                        correct = false;
                    }
                }
                break;
            }
            case "placeteleport":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                se.PlaceTeleport();
                se.setFinishedTurn(true);
                correct = true;
                break;
            }
            case "placematerial":{
                if(commands.length != 2 || Integer.parseInt(commands[1]) > Game.getInstance().field.getSettlers().size()){
                    correct = false;
                    break;
                }
                se.PlaceMaterial();
                se.setFinishedTurn(true);
                correct = true;
                break;
            }
            case "maketeleport":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                se.MakeTeleport();
                se.setFinishedTurn(true);
                correct = true;
                break;
            }
            case "buildrobot":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                se.BuildRobot();
                se.setFinishedTurn(true);
                correct = true;
                break;
            }
            case "sunstorm":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                Game.getInstance().field.SetSunStorm();
                correct = true;
                break;
            }
            default:{
                correct = false;
                break;
            }
        }

        if(!correct){
            System.out.println("Helytelen bemenet!......3");
            return false;
        }
        return true;
    }
}