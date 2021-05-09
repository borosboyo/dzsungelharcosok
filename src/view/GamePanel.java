package view;

import model.*;
import model.Objects.Asteroid;
import model.Objects.Settler;
import model.Objects.Teleport;
import view.ObjectView.AsteroidView;
import view.ObjectView.EntityView;
import view.ObjectView.TeleportView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * The type Game panel.
 */
public class GamePanel extends JPanel {
    /**
     * The Window.
     */
    Window window;
    private final int unit; //TODO:ehhez arányosan kéne majd az x,y,width,high értkékeket beállítani (asteroida megkapja, a többi még nem)
    /**
     * The Selected asteroid.
     */
    Asteroid selectedAsteroid;
    /**
     * The Selected settler.
     */
    Settler selectedSettler;
    /**
     * The Selected teleport.
     */
    Teleport selectedTeleport;
    /**
     * The Entity view.
     */
    ArrayList<EntityView> entityView = new ArrayList<>();
    /**
     * The Teleport views.
     */
    ArrayList<TeleportView> teleportViews = new ArrayList<>();
    /**
     * The Buffered images.
     */
    ArrayList<BufferedImage> bufferedImages = new ArrayList<>(); //TODO:: ezt meg lehetne csinálni a toolkit image-el és akkor lehet jó lenne az is

    /**
     * Gets buffered images.
     *
     * @return the buffered images
     */
    public ArrayList<BufferedImage> getBufferedImages() {
        return bufferedImages;
    }

    /**
     * The list that containts the endgame icons.
     */
    private final ArrayList<ImageIcon> endGameIcons = new ArrayList<>();


    private boolean savedGame;

    /**
     * Sets saved game.
     *
     * @param savedGame the saved game
     */
    public void setSavedGame(boolean savedGame) {
        this.savedGame = savedGame;
    }

    /**
     * Initializes the images for the settler inventory and endgame images.
     *
     * @throws IOException the io exception
     */
    public void initImages() throws IOException {
        BufferedImage im;
        im = ImageIO.read(new File("images/gamepanel.png"));
        if (im != null)
            bufferedImages.add(im);

        im = ImageIO.read(new File("images/plainbackground.png"));
        if (im != null)
            bufferedImages.add(im);

        im = ImageIO.read(new File("images/win.png"));
        if (im != null)
            bufferedImages.add(im);

        im = ImageIO.read(new File("images/lose.png"));
        if (im != null)
            bufferedImages.add(im);

    }

    /**
     * Initializes endgame icons
     */
    public void initEndgameIcons() {
        endGameIcons.add(new ImageIcon("images/backtomenu.png"));
        endGameIcons.add(new ImageIcon("images/menuexit.png"));
    }

    /**
     * Button that appears at the end of the game and takes the user back to the main menu.
     */
    private final JButton menuButton = new JButton();

    /**
     * Button that appears at the end of the game and exits the game.
     */
    private final JButton exitButton = new JButton();

    /**
     * Gets menu button.
     *
     * @return the menu button
     */
    public JButton getMenuButton() {
        return menuButton;
    }

    /**
     * Gets exit button.
     *
     * @return the exit button
     */
    public JButton getExitButton() {
        return exitButton;
    }


    /**
     * Keyboard listener. Certain functions can only be accessed with the keyboard:
     * moving the map, drilling, mining etc..
     */
    private final KeyListenerClass keyListener;

    /**
     * Mouse listener. Used for the endgame buttons
     * and selecting certain drawable elements settlers, asteroids, teleport.
     */
    private final MouseListenerClass mouseListener;

    /**
     * The constructor for the panel. Initializes the endgame buttons and adds the required listeners.
     *
     * @param _window the window
     * @param unit    the unit
     */
    public GamePanel(Window _window, int unit) {
        this.window = _window;
        this.unit = unit;
        keyListener = new KeyListenerClass(window, selectedSettler);
        mouseListener = new MouseListenerClass(window, selectedSettler, selectedTeleport, selectedAsteroid);

        /**
         * Initialize images.
         */
        try {
            initImages();
            initEndgameIcons();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Set up the endgame buttons.
         */
        initButton(menuButton, 0);
        initButton(exitButton, 1);
        add(menuButton);
        add(exitButton);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(564 / 2, 0, 0, 0));

        /**
         * Set listeners required for any interaction.
         */
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
    }

    /**
     * Selected settler.
     *
     * @param selectedSettler the selected settler
     */
    public void SelectedSettler(Settler selectedSettler) {
        this.selectedSettler = selectedSettler;
        keyListener.setSelectedSettler(selectedSettler);
        mouseListener.setSelectedSettler(selectedSettler);
    }

    /**
     * Selected teleport.
     *
     * @param selectedTeleport the selected teleport
     */
    public void SelectedTeleport(Teleport selectedTeleport) {
        this.selectedTeleport = selectedTeleport;
        mouseListener.setSelectedTeleport(selectedTeleport);
    }

    /**
     * Selected asteroid.
     *
     * @param selectedAsteroid the selected asteroid
     */
    public void SelectedAsteroid(Asteroid selectedAsteroid) {
        this.selectedAsteroid = selectedAsteroid;
        mouseListener.setselectedAsteroid(selectedAsteroid);
    }

    /**
     * Initializes the buttons that appear when the game finishes.
     *
     * @param button     the button
     * @param imageIndex the image index
     */
    public void initButton(JButton button, int imageIndex) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setIcon(endGameIcons.get(imageIndex));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setVisible(false);
        button.addActionListener(mouseListener);
    }

    /**
     * The panel that only appears when the game finishes.
     *
     * @param g the g
     */
    public void finishGame(Graphics g) {

        BufferedImage i;

        /**
         * The game ends.
         */
        if (Game.getInstance().EndGame() || Game.getInstance().isWin()) {
            removeKeyListener(keyListener);
            menuButton.setVisible(true);
            exitButton.setVisible(true);
            i = bufferedImages.get(1);
            g.drawImage(i, 350, 175, 300, 200, null);

        }

        /**
         * The settlers win.
         */
        if (!Game.getInstance().EndGame() && Game.getInstance().isWin()) {
            i = bufferedImages.get(2);
            g.drawImage(i, 425, 160, 150, 150, null);
        }
        /**
         * The settlers lose.
         */
        if (Game.getInstance().EndGame() && !Game.getInstance().isWin()) {
            i = bufferedImages.get(3);
            g.drawImage(i, 405, 140, 200, 200, null);

        }

    }


    /**
     * Checkbox asteroid teleport.
     *
     * @param x the x
     * @param y the y
     */
    void checkboxAsteroidTeleport(int x, int y) {
        Field fi = Game.getInstance().field;
        Asteroid asteroid;

        for (int i = 0; i < fi.getAsteroids().size(); i++) {
            asteroid = fi.getAsteroids().get(i);
            if (asteroid.getX() < x && x < asteroid.getX() + unit && asteroid.getY() < y && y < asteroid.getY() + unit) {
                //selectedAsteroid = asteroid;
                SelectedAsteroid(asteroid);
                return;
            }
        }

        TeleportView telV;
        for (int i = 0; i < teleportViews.size(); i++) {
            telV = teleportViews.get(i);
            if (telV.getX() < x && x < telV.getX() + telV.getSize() && telV.getY() < y && y < telV.getY() + telV.getSize()) {
                //selectedTeleport = telV.getTeleport();
                SelectedTeleport(telV.getTeleport());
                return;
            }
        }

        //selectedAsteroid = null;
        SelectedAsteroid(null);
        //selectedTeleport = null;
        SelectedTeleport(null);
    }

    /**
     * Checkbox settler.
     *
     * @param x the x
     * @param y the y
     */
    void checkboxSettler(int x, int y) {
        EntityView entV;
        for (int i = 0; i < entityView.size(); i++) {
            entV = entityView.get(i);
            if (entV.getX() < x && x < entV.getX() + entV.getSize() && entV.getY() < y && y < entV.getY() + entV.getSize()) {
                //selectedSettler = (Settler) entV.getEntity();
                SelectedSettler((Settler) entV.getEntity());
                //selectedSettler.setSelected(true);
                window.repaint();
                return;
            }
        }
        //selectedSettler.setSelected(false);
        //selectedSettler = null;
        SelectedSettler(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        Field fi = Game.getInstance().field;

        this.setBackground(Color.DARK_GRAY);

        Font font = new Font(Font.SERIF, Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(Color.GRAY);
        g.drawString("Settler turn:", 2, 20);

        int count = 0;
        for (int i = 0; i < fi.getSettlers().size(); i++) {
            if (!fi.getSettlers().get(i).isFinishedTurn()) {
                g.drawString(String.valueOf(fi.getSettlers().get(i).getId()), 0, 40 + (i * 20));
                count++;
            }
        }
        if (count == 0) {
            GTimer.getInstance().Tick();
            for (int i = 0; i < fi.getSettlers().size(); i++) {
                fi.getSettlers().get(i).setFinishedTurn(false);
                g.drawString(String.valueOf(fi.getSettlers().get(i).getId()), 0, 40 + (i * 20));
            }
        }

        /**
         * Asteroids draw
         */
        entityView.clear();
        teleportViews.clear();

        int j;

        if (selectedSettler != null) {
            for (Settler s : Game.getInstance().field.getSettlers()) {
                if (s != selectedSettler)
                    s.setSelected(false);
                selectedSettler.setSelected(true);
            }
        } else {
            for (Settler s : Game.getInstance().field.getSettlers()) {
                s.setSelected(false);
            }
        }
        fi.getAsteroids().stream().map(asteroid -> new AsteroidView(asteroid, entityView, teleportViews)).forEach(asteroidView -> asteroidView.draw(g, unit, 0, 0));

        if (selectedSettler != null) {
//            Toolkit t = Toolkit.getDefaultToolkit();
//            Image i = t.getImage("images/gamepanel.png");
            BufferedImage i = bufferedImages.get(0);
            String s;

            g.drawImage(i, 824, -20, 200, 300, null);

            for (j = 0; j < selectedSettler.getInventory().size(); j++) {
                if (selectedSettler.getInventory().get(j) == null)
                    return;

                s = "";
                s += selectedSettler.getInventory().get(j).getName();
                g.setColor(Color.WHITE);
                g.drawString(s, 860, 25 + j * 20);
            }

            for (int k = 0; k < selectedSettler.getTeleportlist().size(); k++) {
                if (selectedSettler.getTeleportlist().get(k) == null)
                    return;

                s = "";
                s += "Teleport[";
                if (selectedSettler.getTeleportlist().get(k).getAsteroids().size() == 0) {
                    s += "2]";
                } else {
                    s += "1]";
                }
                g.setColor(Color.WHITE);
                g.drawString(s, 860, 25 + j * 20);
            }
        }
        if(fi.getSunstormcounter() == fi.getTurnsPerSunstorm()){
            g.setFont(new Font(Font.SERIF, Font.BOLD, 30));
            g.setColor(Color.RED);
            g.drawString("SUNSTORM", window.getWidth() / 3 + 10, 30);
        }
        window.repaint();
        g.setColor(Color.GRAY);
        g.drawLine(0, window.getHeight() - 60, window.getWidth(), window.getHeight() - 60);
        font = new Font(Font.SERIF, Font.BOLD, 14);
        g.setFont(font);
        g.drawString("Up: W,  Down: S,  Left: A,  Right: D,    Drill: F,   Mine: E,   Placeteleport: C,   Placematerial: V,   Maketeleport: T,   BuildRobot: R,   Save: M,   Exit: ESC", 33, window.getHeight() - 46);

        if (savedGame) {
            g.setColor(Color.GRAY);
            font = new Font(Font.SERIF, Font.BOLD, 30);
            g.setFont(font);
            g.drawString("Game Saved!", 100, 30);
            savedGame = false;
        }

        finishGame(g);
    }
}
