package model;

import view.Window;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Main {
    private static Window window = Game.getInstance().window;

    public static void main(String[] args) throws IOException {
        window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
        try {
            Game.getInstance().window.playSound("sound/menu.wav", 1f);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void menu_step() throws IOException {
        GameState state = Game.getInstance().getGameState();
        switch (state){
            case MENU : {

                break;
            }
            case GAME:{
                Game.getInstance().RunGame(true);
                break;
            }
            case TEST:{
                System.out.println("Test");
                break;
            }
        }
    }
}
