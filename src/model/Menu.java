package model;

import java.io.IOException;

public class Menu {
    private boolean new_game = true;

    public boolean isState_menu() {
        return new_game;
    }

    public void setNew_game(boolean new_game) {
        this.new_game = new_game;
    }

    public void menu_step(){
        if(new_game){
            Game.getInstance().resetGame();
            Game.getInstance().setGameState(GameState.GAME);
            Game.getInstance().getMenu().setNew_game(true);
        }
        else{
            try {
                Game.getInstance().loadGame();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Game.getInstance().setGameState(GameState.GAME);
            Game.getInstance().getMenu().setNew_game(true);
        }
    }
}
