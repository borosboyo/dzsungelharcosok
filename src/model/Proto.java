package model;

import java.io.IOException;
import java.util.Scanner;

public class Proto {


    /**
     * Scanner for the menu console reading.
     */
    private Scanner console_read = new Scanner(System.in);

    /**
     * Start menu
     * @return
     */
    public int menu(){
        boolean isCorrect = false;
        int re_num = 0;
        System.out.println("model.Asteroid game");
        System.out.println("New model.Game - Press 1");
        System.out.println("Load model.Game - Press 2");
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


    public void test_menu() throws IOException {
        Game game = Game.game;
        System.out.println("Melyik teszt esetet szeretnéd futtatni?");
        System.out.println("model.Entity moves - press 1");
        System.out.println("model.Entity tries to move on not neighbour - press 2");
        System.out.println("model.Entity tries to use unpaired teleport - press 3");
        System.out.println("model.Entity uses teleport - press 4");
        System.out.println("model.Robot drills ice - press 5");
        System.out.println("model.Robot drills non-trigger - press 6");
        System.out.println("model.Robot drills radioactice - press 7");
        System.out.println("model.Settler builds robot - press 8");
        System.out.println("model.Settler drills ice - press 9");
        System.out.println("model.Settler drills non-trigger - press 10");
        System.out.println("model.Settler drills radioactive - press 11");
        System.out.println("model.Settler makes teleport - press 12");
        System.out.println("model.Settler mines - press 13");
        System.out.println("model.Settler place material - press 14");
        System.out.println("model.Settler place teleport - press 15");
        System.out.println("model.Settler tries to build robot without materials - press 16");
        System.out.println("model.Settler tries to make teleport with full inventory - press 17");
        System.out.println("model.Settler tries to make teleport with no materials - press 18");
        System.out.println("model.Settler tries to mine on invalid asteroid - press 19");
        System.out.println("model.Settler tries to place material on invalid asteroid - press 20");
        System.out.println("model.Settler tries to mine with full inventory - press 21");
        System.out.println("model.Settler wins game with move - press 22");
        System.out.println("Special - press 23");
        System.out.println("Sunstorm on asteroid - press 24");
        System.out.println("model.Ufo mines - press 25");
        System.out.println("model.Ufo tries to mine on invalid asteroid - press 26");
        System.out.println("All settlers die - press 28");
        System.out.println("All test - press 29");
        Skeleton s = new Skeleton();
        int numb = console_read.nextInt();
        switch (numb){
            case 1:  {
                s.fileRead("EntityMoves.txt");
                s.writeToFile("EntityMoves");
                break;
            }
            case 2: {
                s.fileRead("EntityTriesToMoveOnNotNeighbour.txt");
                s.writeToFile("EntityTriesToMoveOnNotNeighbour");
                break;
            }
            case 3:  {
                s.fileRead("EntityTriesToUseUnpairedTeleport.txt");
                s.writeToFile("EntityTriesToUseUnpairedTeleport");
                break;
            }
            case 4: {
                s.fileRead("EntityUsesTeleport.txt");
                s.writeToFile("EntityUsesTeleport");
                break;
            }
            case 5: {
                s.fileRead("RobotDrillsIce.txt");
                s.writeToFile("RobotDrillsIce");
                s.writeout(game);
                break;

            }
            case 6: {
                s.fileRead("RobotDrillsNon-trigger.txt");
                s.writeToFile("RobotDrillsNon-trigger");
                s.writeout(game);
                break;
            }
            case 7: {
                s.fileRead("RobotDrillsRadioactive.txt");
                s.writeToFile("RobotDrillsRadioactive");
                s.writeout(game);
                break;
            }
            case 8: {
                s.fileRead("SettlerBuildsRobot.txt");
                s.writeToFile("SettlerBuildsRobot");
                break;
            }
            case 9:  {
                s.fileRead("SettlerDrillsIce.txt");
                s.writeToFile("SettlerDrillsIce");
                break;
            }
            case 10:{
                s.fileRead("SettlerDrillsNon-trigger.txt");
                s.writeToFile("SettlerDrillsNon-trigger");
                s.writeout(game);
                break;
            }
            case 11:{
                s.fileRead("SettlerDrillsRadioactive.txt");
                s.writeToFile("SettlerDrillsRadioactive");
                break;
            }
            case 12:{
                s.fileRead("SettlerMakesTeleport.txt");
                s.writeToFile("SettlerMakesTeleport");
                break;
            }
            case 13:{
                s.fileRead("SettlerMines.txt");
                s.writeToFile("SettlerMines");
                break;
            }
            case 14:{
                s.fileRead("SettlerPlaceMaterial.txt");
                s.writeToFile("SettlerPlaceMaterial");
                break;
            }
            case 15:{
                s.fileRead("SettlerPlaceTeleport.txt");
                s.writeToFile("SettlerPlaceTeleport");
                break;
            }
            case 16: {
                s.fileRead("SettlerTriesToBuildRobotWithoutMaterials.txt");
                s.writeToFile("SettlerTriesToBuildRobotWithoutMaterials");
                break;
            }
            case 17:{
                s.fileRead("SettlerTriesToMakeTeleportWithFullInventory.txt");
                s.writeToFile("SettlerTriesToMakeTeleportWithFullInventory");
                break;
            }
            case 18:{
                s.fileRead("SettlerTriesToMakeTeleportWithNoMaterials.txt");
                s.writeToFile("SettlerTriesToMakeTeleportWithNoMaterials");
                break;
            }
            case 19:{
                s.fileRead("SettlerTriesToMineOnInvalidAsteroid.txt");
                s.writeToFile("SettlerTriesToMineOnInvalidAsteroid");
                break;
            }
            case 21:{
                s.fileRead("SettlerTriesToMineWithFullyInventory.txt");
                s.writeToFile("SettlerTriesToMineWithFullyInventory");
                break;
            }
            case 20:{
                s.fileRead("SettlerTriesToPlaceMaterialOnInvalidAsteroid.txt");
                s.writeToFile("SettlerTriesToPlaceMaterialOnInvalidAsteroid");
                break;
            }
            case 22: {
                s.fileRead("SettlerWinsGameWithMove.txt");
                s.writeToFile("SettlerWinsGameWithMove");
                break;
            }
            case 23:{
                s.fileRead("Special.txt");
                s.writeToFile("Special");
                break;
            }
            case 24:{
                s.fileRead("SunstormOnAsteroid.txt");
                s.writeToFile("SunstormOnAsteroid");
                break;
            }
            case 25: {
                s.fileRead("UfoMines.txt");
                s.writeToFile("UfoMines");
                break;
            }
            case 26: {
                s.fileRead("UfoTriesToMineOnInvalidAsteroid.txt");
                s.writeToFile("UfoTriesToMineOnInvalidAsteroid");
                break;
            }
            case 28:{
                s.fileRead("AllSettlersDie.txt");
                s.writeToFile("AllSettlersDie");
                break;
            }
            case 29: {
                run_all(s);
            }
        }
    }

    public void run_all(Skeleton s) throws IOException {
        s.fileRead("EntityMoves.txt");
        s.writeToFile("EntityMoves");
     //   game = new Game();
        s.fileRead("EntityTriesToMoveOnNotNeighbour.txt");
        s.writeToFile("EntityTriesToMoveOnNotNeighbour");
     //   game = new Game();
        s.fileRead("EntityTriesToUseUnpairedTeleport.txt");
        s.writeToFile("EntityTriesToUseUnpairedTeleport");
       // game = new Game();
        s.fileRead("EntityUsesTeleport.txt");
        s.writeToFile("EntityUsesTeleport");
     //   game = new Game();
        s.fileRead("RobotDrillsIce.txt");
        s.writeToFile("RobotDrillsIce");
       // game = new Game();
        s.fileRead("RobotDrillsNon-trigger.txt");
        s.writeToFile("RobotDrillsNon-trigger");
      //  game = new Game();
        s.fileRead("RobotDrillsRadioactive.txt");
        s.writeToFile("RobotDrillsRadioactive");
       // game = new Game();
        s.fileRead("SettlerBuildsRobot.txt");
        s.writeToFile("SettlerBuildsRobot");
     //   game = new Game();
        s.fileRead("SettlerDrillsIce.txt");
        s.writeToFile("SettlerDrillsIce");
      //  game = new Game();
        s.fileRead("SettlerDrillsNon-trigger.txt");
        s.writeToFile("SettlerDrillsNon-trigger");
     //   game = new Game();
        s.fileRead("SettlerDrillsRadioactive.txt");
        s.writeToFile("SettlerDrillsRadioactive");
     //   game = new Game();
        s.fileRead("SettlerMakesTeleport.txt");
        s.writeToFile("SettlerMakesTeleport");
     //   game = new Game();
        s.fileRead("SettlerMines.txt");
        s.writeToFile("SettlerMines");
      // game = new Game();
        s.fileRead("SettlerPlaceMaterial.txt");
        s.writeToFile("SettlerPlaceMaterial");
    //    game = new Game();
        s.fileRead("SettlerPlaceTeleport.txt");
        s.writeToFile("SettlerPlaceTeleport");
      ///  game = new Game();
        s.fileRead("SettlerTriesToBuildRobotWithoutMaterials.txt");
        s.writeToFile("SettlerTriesToBuildRobotWithoutMaterials");
        //game = new Game();
        s.fileRead("SettlerTriesToMakeTeleportWithFullInventory.txt");
        s.writeToFile("SettlerTriesToMakeTeleportWithFullInventory");
     //   game = new Game();
        s.fileRead("SettlerTriesToMakeTeleportWithNoMaterials.txt");
        s.writeToFile("SettlerTriesToMakeTeleportWithNoMaterials");
    //    game = new Game();
        s.fileRead("SettlerTriesToMineOnInvalidAsteroid.txt");
        s.writeToFile("SettlerTriesToMineOnInvalidAsteroid");
      //  game = new Game();
        s.fileRead("SettlerTriesToMineWithFullyInventory.txt");
        s.writeToFile("SettlerTriesToMineWithFullyInventory");
        //game = new Game();
        s.fileRead("SettlerTriesToPlaceMaterialOnInvalidAsteroid.txt");
        s.writeToFile("SettlerTriesToPlaceMaterialOnInvalidAsteroid");
        //game = new Game();
        s.fileRead("SettlerWinsGameWithMove.txt");
        s.writeToFile("SettlerWinsGameWithMove");
       // Game.game = new Game();
        s.fileRead("Special.txt");
        s.writeToFile("Special");
      //  game = new Game();
        s.fileRead("SunstormOnAsteroid.txt");
        s.writeToFile("SunstormOnAsteroid");
       // game = new Game();
        s.fileRead("UfoMines.txt");
        s.writeToFile("UfoMines");
      //  game = new Game();
        s.fileRead("UfoTriesToMineOnInvalidAsteroid.txt");
        s.writeToFile("UfoTriesToMineOnInvalidAsteroid");
        /*game = new model.Game();
        s.fileRead("AllSettlersDie.txt");
        s.writeToFile("AllSettlersDie");*/
    }




    /**
     * Execute instructions received in an array
     * @param commands String block for the commands
     * @return
     */




}
