import java.io.*;
import java.util.Scanner;

/**
    * The Game singleton class that manages ending and starting a game.
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
     * The Field that contains all the asteroids and settlers.
     */
    Field field; //TODO: atadni hany settler es max keregvastagsag


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
        setWin(true);
        System.out.print("Settlers win!");
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadGame() throws IOException, ClassNotFoundException{
        final ObjectInputStream input = new ObjectInputStream(new FileInputStream("field_status"));
        field = (Field)input.readObject();
    }

    /**
     *
     * @throws IOException
     */
    public void saveGame() throws IOException{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("field_status"));
        output.writeObject(field);
    }

    //TODO::ezt nem tudom, hogy hova akarjuk majd írni és honna akarjuk meghívni (akkor kell meghívni amikor a játékos lépni akar)
    //TODO::kellene egy játék kiíró de az sztem ugyanaz lenne mint a file-os
    /**
     * Parancsok olvasása a játékhoz
     */
    private Scanner console_read = new Scanner(System.in);
    public void readCommands(){
        boolean correct = false;
        String in;
        String[] commands;
        Settler se;

        while(!correct){
            in = console_read.nextLine();
            commands = in.split("[ !\"\\#$%&'*+,-./:;<=>?@\\[\\]^_`{|}~]+");

            switch (commands[0]){
                 case "move":{
                     if(commands.length != 3 || Integer.parseInt(commands[1]) >= field.getSettlers().size() || Integer.parseInt(commands[2]) >= field.getAsteroids().size()){
                         System.out.println("Helytelen parancs!");
                         correct = false;
                         break;
                     }
                     se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                     se.Move(field.getAsteroids().get(Integer.parseInt(commands[2])));
                     correct = true;
                }
                case "drill":{
                    if(commands.length != 2 || Integer.parseInt(commands[1]) > field.getSettlers().size()){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                    se.Drill();
                    correct = true;
                }
                case "mine":{
                    if(commands.length != 2 || Integer.parseInt(commands[1]) > field.getSettlers().size()){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                    se.Mine();
                    correct = true;
                }
                case "useteleport":{
                    if(commands.length != 3 || Integer.parseInt(commands[1]) >= field.getSettlers().size() || Integer.parseInt(commands[2]) >= field.getSettlers().get(Integer.parseInt(commands[1])).getAsteroid().getTeleports().size()){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                    se.UseTeleport(se.getAsteroid().getTeleports().get(Integer.parseInt(commands[2])));
                    correct = true;
                }
                case "placeteleport":{
                    if(commands.length != 2 || Integer.parseInt(commands[1]) > field.getSettlers().size()){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                    se.PlaceTeleport();
                    correct = true;
                }
                case "placematerial":{
                    if(commands.length != 2 || Integer.parseInt(commands[1]) > field.getSettlers().size()){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                    se.PlaceMaterial();
                    correct = true;
                }
                case "maketeleport":{
                    if(commands.length != 2 || Integer.parseInt(commands[1]) > field.getSettlers().size()){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                    se.MakeTeleport();
                    correct = true;
                }
                case "buildrobot":{
                    if(commands.length != 2 || Integer.parseInt(commands[1]) > field.getSettlers().size()){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se =  field.getSettlers().get(Integer.parseInt(commands[1]));
                    se.BuildRobot();
                    correct = true;
                }
                case "savegame":{  //TODO::menuben lehet majd beolvasni (loadgame-et ott kell meghívni)
                    if(commands.length != 1){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    try {
                        saveGame();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    correct = true;
                }
                default:{
                    System.out.println("Helytelen parancs!");
                    correct = false;
                    break;
                }
            }
        }
    }
}
