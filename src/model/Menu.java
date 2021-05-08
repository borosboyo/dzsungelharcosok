package model;

import java.io.IOException;

/**
 * The type Menu.
 */
public class Menu {
    private MenuState menuState = MenuState.LOADMENU;

    /**
     * Sets menu state.
     *
     * @param menuState the menu state
     */
    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    /**
     * Handles the different scenarios based on menu states.
     *
     * @param settler_numb the settler numb
     */
    public void menu_step(int settler_numb) {
        switch (menuState) {
            //New game starts
            case NEWGAME: {
                Game.getInstance().resetGame(settler_numb);
                Game.getInstance().setGameState(GameState.GAME);
                Game.getInstance().getMenu().setMenuState(MenuState.NEWGAME);
                break;
            }
            //The user loads a game.
            case LOADGAME:{
                Game.getInstance().field = null;
                try {
                    Game.getInstance().loadGame();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Game.getInstance().setGameState(GameState.GAME);
                Game.getInstance().getMenu().setMenuState(MenuState.LOADGAME);
                break;
            }
            //Loadmenu state
            case LOADMENU:{
                Game.getInstance().setGameState(GameState.MENU);
                Game.getInstance().getMenu().setMenuState(MenuState.LOADMENU);
                break;
            }
        }
    }
}
