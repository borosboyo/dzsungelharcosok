package view;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The application window. Contains the GamePanel and MenuPanel.
 */
public class Window extends JFrame {
    private final MenuPanel menuPanel;
    private GamePanel gamePanel;
    private final ArrayList<Clip> clips = new ArrayList<>();

    /**
     * Initializes the window: sets up the basic properties and loads thee sounds.
     *
     * @throws UnsupportedAudioFileException the unsupported audio file exception
     * @throws IOException                   the io exception
     * @throws LineUnavailableException      the line unavailable exception
     */
    public Window() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setTitle("Asteroidmining");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 200);
        setResizable(false);
        menuPanel = new MenuPanel(this);
        add(menuPanel);
        setVisible(true);

        loadSounds("sound/menu.wav");
        loadSounds("sound/game_sound.wav");
        loadSounds("sound/drill.wav");
        loadSounds("sound/mine.wav");
        loadSounds("sound/teleport.wav");
    }

    /**
     * Loads the sounds for the menu and the game.
     */
    public void loadSounds(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Clip clip = AudioSystem.getClip();
        File file = new File(filename);
        AudioInputStream input = AudioSystem.getAudioInputStream(file);
        clip.open(input);
        clips.add(clip);
    }

    /**
     * Plays a sound.
     *
     * @param i      the
     * @param volume the volume
     * @param loop   the loop
     * @throws IOException                   the io exception
     * @throws UnsupportedAudioFileException the unsupported audio file exception
     * @throws LineUnavailableException      the line unavailable exception
     */
    public void playSound(int i, float volume, int loop) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        clips.get(i).setFramePosition(0);
        clips.get(i).start();
        clips.get(i).loop(loop);
        setVolume(i, volume);
    }

    /**
     * Stop a sound.
     *
     * @param i the
     */
    public void stopSound(int i) {
        clips.get(i).stop();
    }

    /**
     * Sets the volume for the sound.
     *
     * @param i      the index of the soundclip.
     * @param volume the volume
     */
    public void setVolume(int i, float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clips.get(i).getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    /**
     * Switch to game.
     */
    public void switchToGame() {
        remove(menuPanel);
        setSize(1024, 576);
        gamePanel = new GamePanel(this, 70);
        add(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        try {
            stopSound(0);
            playSound(1, 1.0f, 99);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switch to menu.
     */
    public void switchToMenu() {
        remove(gamePanel);
        setSize(600, 200);
        add(menuPanel);

        menuPanel.setFocusable(true);
        try {
            stopSound(1);
            playSound(0, 1.0f, 99);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets game panel.
     *
     * @return the game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
