package model;

import view.Window;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        Window window = new Window();
        window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
        try {
            window.playSound(0, 1f, 99);
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }


        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                if(Game.game.getGameState() == GameState.GAME){
                    Game.game.step();
                    window.repaint();
                }
            }
        }, 1000, 1000/120); //TODO:ezeket az értékeket lehet átkell majd állítani
    }
}
