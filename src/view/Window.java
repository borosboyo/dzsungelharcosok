package view;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

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
    }

}
