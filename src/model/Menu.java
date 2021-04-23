package model;

import java.io.IOException;

public class Menu {
    private MenuState menuState = MenuState.LOADMENU;

    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    public void menu_step(int settler_numb){
        switch (menuState){
            case NEWGAME:{
                Game.getInstance().resetGame(settler_numb);
                Game.getInstance().setGameState(GameState.GAME);
                Game.getInstance().getMenu().setMenuState(MenuState.NEWGAME);
                break;
            }
            case LOADGAME:{
                try {
                    Game.getInstance().loadGame();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Game.getInstance().setGameState(GameState.GAME);
                Game.getInstance().getMenu().setMenuState(MenuState.LOADGAME);
                break;
            }
            case LOADMENU:{
                Game.getInstance().setGameState(GameState.MENU);
                Game.getInstance().getMenu().setMenuState(MenuState.LOADMENU);
                break;
            }
        }
    }
}
