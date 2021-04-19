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

    private Scanner console_read = new Scanner(System.in);

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
    Field field;


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

    public int menu(){
        boolean isCorrect = false;
        int re_num = 0;
        System.out.println("Asteroid game");
        System.out.println("New Game - Press 1");
        System.out.println("Load Game - Press 2");
        System.out.println("Tests - Press 3");
        System.out.println("Exit - Press 4");

        while(!isCorrect){
            int numb = console_read.nextInt();
            switch (numb){
                case 1:{
                    re_num = 1;
                    isCorrect = true;
                    break;
                }
                case 2:{
                    re_num = 2;
                    isCorrect = true;
                    break;
                }
                case 3:{
                    re_num = 3;
                    isCorrect = true;
                    break;
                }
                case 4:{
                    re_num = 4;
                    isCorrect = true;
                    break;
                }
                default: {
                    System.out.println("Hibás paraméter!");
                    isCorrect = false;
                    break;
                }
            }
        }
        return re_num;
    }


    public static void main(String []args) throws IOException {
        boolean new_game = true;
        boolean test = false;
        int state = 1;

        while(state < 5){
            state = game.menu();
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
                    //TODO::Biros tesztek futtatása itt
                   // test = true;
                  //  break;
                    Skeleton s = new Skeleton();
                    s.fileRead("test.txt");
                    s.writeout(Game.getInstance());
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
                continue;
            }

            if(!game.StartGame(new_game)){
                System.out.println("Nincs mentett jatek!");
                continue;
            }

            Skeleton s = new Skeleton();
            //s.writeout(game);
            int counter = 0;
            boolean menu = false;

            while(!game.EndGame() && !menu){
                s.writeout(game);
                menu = game.readCommands();
                counter++;
                if(counter == game.field.getSettlers().size()){
                    Timer.getInstance().Tick();
                    counter=0;
                }
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
    public void loadGame() throws IOException, ClassNotFoundException{ //TODO::ha nincs fájl, akkor térjen vissza a menübe
        final ObjectInputStream input = new ObjectInputStream(new FileInputStream("field_status"));
        System.out.println(input);
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

    /**
     * Parancsok olvasása a játékhoz
     */

    private Scanner console_read2 = new Scanner(System.in);

    public boolean readCommands(){
        boolean correct = false;
        boolean end = false;
        String in;
        String[] commands;
        Settler se = null;

        while(!correct){
            in = console_read2.nextLine();
            commands = in.split("[ !\"\\#$%&'*+,-./:;<=>?@\\[\\]^_`{|}~]+");

            if(commands.length < 2 && !commands[0].equals("savegame")){
                System.out.println("Helytelen bemenet!");
                correct = false;
                continue;
            }else if(commands.length > 1){
                for (int i = 0; i  < field.getSettlers().size(); i++){
                    if(Integer.parseInt(commands[1]) == field.getSettlers().get(i).getId()){
                        se = field.getSettlers().get(i);
                        correct = true;
                        break;
                    }
                    else{
                        correct = false;
                    }
                }
            }
            else if(commands[0].equals("savegame")){
                correct = true;
            }

            if(!correct){
                System.out.println("Helytelen bemenet!");
                continue;
            }

            switch (commands[0]){
                 case "move":{
                     if(commands.length != 3){
                         System.out.println("Helytelen parancs!");
                         correct = false;
                         break;
                     }
                     for(int i=0; i<field.getAsteroids().size(); i++){
                         if(Integer.parseInt(commands[2]) == field.getAsteroids().get(i).getId()){
                             System.out.println(se.getId());
                             System.out.println(se.getAsteroid().getid());
                             System.out.println(se.getAsteroid());
                             se.Move(field.getAsteroids().get(i));
                              correct = true;
                              System.out.println(se.getId());
                             System.out.println(se.getAsteroid().getid());
                             System.out.println(se.getAsteroid());
                              break;
                         }
                         else{
                             correct = false;
                         }
                     }
                     if(!correct){
                         System.out.println("Helytelen parancs!");
                     }
                     break;
                }
                case "drill":{
                    if(commands.length != 2){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se.Drill();
                    correct = true;
                    break;
                }
                case "mine":{
                    if(commands.length != 2){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se.Mine();
                    correct = true;
                    break;
                }
                case "useteleport":{
                    if(commands.length != 3){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    for(int i=0; i<se.getAsteroid().getTeleports().size(); i++){
                        if(Integer.parseInt(commands[2]) == se.getAsteroid().getTeleports().get(i).getId()){
                            se.UseTeleport(se.getAsteroid().getTeleports().get(i));
                            correct = true;
                            break;
                        }
                        else{
                            correct = false;
                        }
                    }
                    if(!correct){
                        System.out.println("Helytelen parancs!");
                    }
                    break;
                }
                case "placeteleport":{
                    if(commands.length != 2){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se.PlaceTeleport();
                    correct = true;
                    break;
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
                    break;
                }
                case "maketeleport":{
                    if(commands.length != 2){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se.MakeTeleport();
                    correct = true;
                    break;
                }
                case "buildrobot":{
                    if(commands.length != 2){
                        System.out.println("Helytelen parancs!");
                        correct = false;
                        break;
                    }
                    se.BuildRobot();
                    correct = true;
                    break;
                }
                case "savegame":{
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
                    end = true;
                    break;
                }
                default:{
                    correct = false;
                    System.out.println("Helytelen parancs!");
                    break;
                }
            }
        }
        if(end){
            return true;
        }
        return false;
    }
}
