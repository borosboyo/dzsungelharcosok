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
                continue; //TODO::Ez így nem jó
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
                menu = game.step_gamer();
                counter++;
                if(counter == game.field.getSettlers().size()){
                    Timer.getInstance().Tick();
                    counter=0;
                }
            }
        }

    }

    public void test_menu() throws IOException {
        System.out.println("Melyik teszt esetet szeretnéd futtatni?");
        System.out.println("Entity moves - press 1");
        System.out.println("Entity tries to move on not neighbour - press 2");
        System.out.println("Entity tries to use unpaired teleport - press 3");
        System.out.println("Entity uses teleport - press 4");
        System.out.println("Robot drills ice - press 5");
        System.out.println("Robot drills non-trigger - press 6");
        System.out.println("Robot drills radioactice - press 7");
        System.out.println("Settler builds robot - press 8");
        System.out.println("Settler drills ice - press 9");
        System.out.println("Settler drills non-trigger - press 10");
        System.out.println("Settler drills radioactive - press 11");
        System.out.println("Settler makes teleport - press 12");
        System.out.println("Settler mines - press 13");
        System.out.println("Settler place material - press 14");
        System.out.println("Settler place teleport - press 15");
        System.out.println("Settler tries to build robot without materials - press 16");
        System.out.println("Settler tries to make teleport with full inventory - press 17");
        System.out.println("Settler tries to make teleport with no materials - press 18");
        System.out.println("Settler tries to mine on invalid asteroid - press 19");
        System.out.println("Settler tries to place material on invalid asteroid - press 20");
        System.out.println("Settler tries to mine with full inventory - press 21");
        System.out.println("Settler wins game with move - press 22");
        System.out.println("Special - press 23");
        System.out.println("Sunstorm on asteroid - press 24");
        System.out.println("Ufo mines - press 25");
        System.out.println("Ufo tries to mine on invalid asteroid - press 26");
        System.out.println("All settlers die - press 28");
        Skeleton s = new Skeleton();
        int numb = console_read.nextInt();
        switch (numb){
            case 1:  s.fileRead("EntityMoves.txt");
            case 2:  s.fileRead("EntityTriesToMoveOnNotNeighbour.txt");
            case 3:  s.fileRead("EntityTriesToUseUnpairedTeleport.txt");
            case 5:  s.fileRead("EntityUsesTeleport.txt");
            case 6:  s.fileRead("RobotDrillsIce.txt");
            case 7:  s.fileRead("RobotDrillsNon-trigger.txt");
            case 8:  s.fileRead("RobotDrillsRadioactive.txt");
            case 9:  s.fileRead("SettlerBuildsRobot.txt");
            case 10:  s.fileRead("SettlerDrillsIce.txt");
            case 11:  s.fileRead("SettlerDrillsNon-trigger.txt");
            case 12: s.fileRead("SettlerDrillsRadioactive.txt");
            case 13: s.fileRead("SettlerMakesTeleport.txt");
            case 14: s.fileRead("SettlerMines.txt");
            case 15: s.fileRead("SettlerPlaceMaterial.txt");
            case 16: s.fileRead("SettlerPlaceTeleport.txt");
            case 17: s.fileRead("SettlerTriesToBuildRobotWithoutMaterials.txt");
            case 18: s.fileRead("SettlerTriesToMakeTeleportWithFullInventory.txt");
            case 19: s.fileRead("SettlerTriesToMakeTeleportWithNoMaterials.txt");
            case 20: s.fileRead("SettlerTriesToMineOnInvalidAsteroid.txt");
            case 21: s.fileRead("SettlerTriesToMineWithFullyInventory.txt");
            case 22: s.fileRead("SettlerTriesToPlaceMaterialOnInvalidAsteroid.txt");
            case 23: s.fileRead("SettlerWinsGameWithMove.txt");
            case 24: s.fileRead("Special.txt");
            case 25: s.fileRead("SunstormOnAsteroid.txt");
            case 26: s.fileRead("UfoMines.txt");
            case 27: s.fileRead("AllSettlersDie.txt");
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

        return false;
    }

    public boolean read_command(String[] commands){
        boolean correct = false;
        Settler se = null;

        if(commands.length < 2){
            System.out.println("Helytelen bemenet!");
            return false;
        }else{
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

        if(!correct){
            System.out.println("Helytelen bemenet!");
            return false;
        }

        switch (commands[0]){
            case "move":{
                if(commands.length != 3){
                    correct = false;
                    break;
                }
                for(int i=0; i<field.getAsteroids().size(); i++){
                    if(Integer.parseInt(commands[2]) == field.getAsteroids().get(i).getId()){
                        se.Move(field.getAsteroids().get(i));
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
                se.Drill();
                correct = true;
                break;
            }
            case "mine":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                se.Mine();
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
                correct = true;
                break;
            }
            case "placematerial":{
                if(commands.length != 2 || Integer.parseInt(commands[1]) > field.getSettlers().size()){
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
                    correct = false;
                    break;
                }
                se.MakeTeleport();
                correct = true;
                break;
            }
            case "buildrobot":{
                if(commands.length != 2){
                    correct = false;
                    break;
                }
                se.BuildRobot();
                correct = true;
                break;
            }
            default:{
                correct = false;
                break;
            }
        }

        if(!correct){
            System.out.println("Helytelen bemenet!");
            return false;
        }
        return true;
    }
}
