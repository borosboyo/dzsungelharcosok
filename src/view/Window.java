package view;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Window extends JFrame {
    private MenuPanel menuPanel;
    private GamePanel game;

    public Window(){
        setTitle("Asteroidmining");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 200);
        setResizable(false);
        menuPanel = new MenuPanel();
        add(menuPanel);
        setVisible(true);

    }

    public static void main(String []args) throws IOException {
       Window window =  new Window();
       window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
        try {
            playSound("sound/sound.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playSound(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File(filename);
        AudioInputStream input = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(input);
        clip.start();
        clip.loop(99);
        float volume = 0.00f;
        setVolume(clip,volume);
    }

    public static void setVolume(Clip clip, float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}
