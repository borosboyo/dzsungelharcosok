package view;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Window extends JFrame {
    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    public Window(){
        setTitle("Asteroidmining");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 200);
        setResizable(false);
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(10);
        add(menuPanel);
        setVisible(true);
    }

    public static void main(String []args) throws IOException {
       Window window =  new Window();
       window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
    }

    private static Clip clip;

    public static void playSound(String filename, float volume) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        clip = AudioSystem.getClip();
        clip.stop();
        File file = new File(filename);
        AudioInputStream input = AudioSystem.getAudioInputStream(file);
        clip.open(input);
        clip.start();
        clip.loop(99);
        setVolume(clip,volume);
    }

    public static void setVolume(Clip clip, float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public void switchToGame(){
        remove(menuPanel);
        setSize(800,600);
        add(gamePanel);
        repaint();
    }
}
