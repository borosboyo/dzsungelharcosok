package view;

import model.Ufo;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Window extends JFrame {
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private  ArrayList<Clip> clips = new ArrayList();

    public Window() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setTitle("Asteroidmining");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 200);
        setResizable(false);
        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this, 10);
        add(menuPanel);
        setVisible(true);

        loadSounds("sound/menu.wav");
        loadSounds("sound/game_sound.wav");
    }



    private void loadSounds(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip = AudioSystem.getClip();
        File file = new File(filename);
        AudioInputStream input = AudioSystem.getAudioInputStream(file);
        clip.open(input);
        clips.add(clip);
    }

    public  void playSound(int i, float volume) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        clips.get(i).start();
        clips.get(i).loop(99);
        setVolume(i,volume);
    }

    public void stopSound(int i){
        clips.get(i).stop();
    }

    public void setVolume(int i, float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clips.get(i).getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public void switchToGame(){
        remove(menuPanel);
        setSize(1024,576);
        add(gamePanel);
        try {
            stopSound(0);
            playSound(1,1.0f);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        repaint();
    }
}
