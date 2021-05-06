package model;

import view.Window;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {//TODO:: ha nincsen consolose-os kezelés, !vagy rendes idő szerinti léptetés! akkor ez feleslegessé vált, a maint akkor át lehet rakni a game-be sztem

    public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        Window window = new Window();
        window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
        try {
            window.playSound(0, 1f, 99);
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }


//        java.util.Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run(){
//                if(Game.game.getGameState() == GameState.GAME){
//                    Game.game.step();
//                    window.repaint();
//                }
//            }
//        }, 1000, 1000/30); //TODO:ezeket az értékeket lehet átkell majd állítani


        //TODO::ez akkor kell ha rendes idő szerint is akarjuk léptetni a léptetős dolgokat, Szerintem nagyon epikus ha nem csak azután lépnek miután az összes settler lelépte a sajátját, ha kíváncsi vagy rá akkor kommentezd ki ;)
//        java.util.Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run(){
//                if(Game.game.getGameState() == GameState.GAME){
//                    GTimer.getInstance().Tick();
//                    window.repaint();
//                }
//            }
//        }, 1000, 10000); //TODO:ezeket az értékeket lehet átkell majd állítani
    }
}
