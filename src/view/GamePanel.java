package view;

import model.*;

import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
    Window window;
    private int unit; //TODO:ehhez arányosan kéne majd az x,y,width,high értkékeket beállítani (asteroida megkapja, a többi még nem)
    Asteroid selectedAsteroid;
    Settler selectedSettler;
    Teleport selectedTeleport;
    ArrayList<EntityView> entityView = new ArrayList<EntityView>();
    ArrayList<TeleportView> teleportViews = new ArrayList<TeleportView>();
    ArrayList<BufferedImage> bufferedImages = new ArrayList<BufferedImage>(); //TODO:: ezt meg lehetne csinálni a toolkit image-el és akkor lehet jó lenne az is

    public ArrayList<BufferedImage> getBufferedImages() {
        return bufferedImages;
    }

    public void initImages() throws IOException {
        BufferedImage im;
        im = ImageIO.read(new File("images/gamepanel.png"));
        if (im != null)
            bufferedImages.add(im);

    }

    private JButton menuButton = new JButton();
    private JButton exitButton = new JButton();

    /**
     * Keyboard listener. Certain functions can only be accessed with the keyboard:
     * moving the map, drilling, mining etc..
     */
    private KeyListenerClass keyListener = new KeyListenerClass();

    /**
     * Mouse listener. Used for the endgame buttons
     * and selecting certain drawable elements settlers, asteroids, teleport.
     */
    private MouseListenerClass mouseListener = new MouseListenerClass();

    /**
     * The constructor for the panel. Initializes the endgame buttons and adds the required listeners.
     */
    public GamePanel(Window _window, int unit) {
        this.window = _window;
        this.unit = unit;
        initButton(menuButton, "menu");
        initButton(exitButton, "exit");
        add(menuButton);
        add(exitButton);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(564 / 2, 0, 0, 0));

        try {
            initImages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(keyListener);
        addMouseListener(mouseListener);
    }

    /**
     * Initializes the buttons that appear when the game finishes.
     */
    public void initButton(JButton button, String text) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        //button.setIcon(imageContainer.get(imageIndex));
        //button.setBorder(BorderFactory.createEmptyBorder());
        //button.setMargin(new Insets(0, 0, 0, 0));
        //button.setOpaque(false);
        //button.setBorderPainted(false);
        //button.setContentAreaFilled(false);
        button.setVisible(false);
        button.setText(text);
        button.addActionListener(mouseListener);
    }

    public void finishGame() {
        if (Game.getInstance().EndGame() || Game.getInstance().isWin()) {
            removeKeyListener(keyListener);

        }
    }


    void checkboxAsteroidTeleport(int x, int y) {
        Field fi = Game.getInstance().field;
        Asteroid asteroid;
        Teleport teleport;

        for (int i = 0; i < fi.getAsteroids().size(); i++) {
            asteroid = fi.getAsteroids().get(i);
            if (asteroid.getX() < x && x < asteroid.getX() + unit && asteroid.getY() < y && y < asteroid.getY() + unit) {
                selectedAsteroid = asteroid;
                return;
            }
        }

        TeleportView telV;
        for (int i = 0; i < teleportViews.size(); i++) {
            telV = teleportViews.get(i);
            if (telV.getX() < x && x < telV.getX() + telV.getSize() && telV.getY() < y && y < telV.getY() + telV.getSize()) {
                selectedTeleport = telV.getTeleport();
                return;
            }
        }

        selectedAsteroid = null;
        selectedTeleport = null;
    }

    void checkboxSettler(int x, int y) {
        EntityView entV;
        for (int i = 0; i < entityView.size(); i++) {
            entV = entityView.get(i);
            if (entV.getX() < x && x < entV.getX() + entV.getSize() && entV.getY() < y && y < entV.getY() + entV.getSize()) {
                selectedSettler = (Settler) entV.getEntity();
                //selectedSettler.setSelected(true);
                window.repaint();
                return;
            }
        }
        //selectedSettler.setSelected(false);
        selectedSettler = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Field fi = Game.getInstance().field;

        this.setBackground(Color.DARK_GRAY);

        Font font = new Font(Font.SERIF, Font.PLAIN, (int) (20));
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

            g.drawImage(i, 824, -20, 200, 300, null);

            for (j = 0; j < selectedSettler.getInventory().size(); j++) {
                if (selectedSettler.getInventory().get(j) == null)
                    return;

                String s = new String("");
                s += selectedSettler.getInventory().get(j).getName();
                g.setColor(Color.WHITE);
                g.drawString(s, 860, 25 + j * 20);
            }

            for (int k = 0; k < selectedSettler.getTeleportlist().size(); k++) {
                if (selectedSettler.getTeleportlist().get(k) == null)
                    return;

                String s = new String("");
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

        if(fi.getSunstormcounter() == 1){
            g.setFont( new Font(Font.SERIF, Font.BOLD ,(int) (30)));
            g.setColor(Color.RED);
            g.drawString("SUNSTORM", window.getWidth()/3+10, 30);
        }
        window.repaint();
        g.setColor(Color.GRAY);
        g.drawLine(0, window.getHeight() - 60, window.getWidth(), window.getHeight() - 60);
        font = new Font(Font.SERIF, Font.BOLD, (int) (14));
        g.setFont(font);
        g.drawString("Up: W,  Down: S,  Left: A,  Right: D,    Drill: F,   Mine: E,   Placeteleport: C,   Placematerial: V,   Maketeleport: T,   BuildRobot: R,   Save: M", 40, window.getHeight() - 46);
    }


    /**
     * The type Key listener class.
     */
    class KeyListenerClass implements KeyListener {

        /**
         * Stores how many sides the map has, so that the map border calculation is smooth.
         */
        int side = Game.getInstance().field.getSide();

        /**
         * Checks if the game has reached the upper map bound.
         */
        public boolean checkUp() {
            if (Game.getInstance().field.getAsteroids().get(0).getY() == 50)
                return true;
            return false;
        }

        /**
         * Checks if the game has reached the lower map bound.
         */
        public boolean checkDown() {
            int idx = Game.getInstance().field.getAsteroids().size() - 1;
            int bound = side * 30;
            if (Game.getInstance().field.getAsteroids().get(idx).getY() == bound)
                return true;
            return false;
        }

        /**
         * Checks if the game has reached the left map bound.
         */
        public boolean checkLeft() {
            if (Game.getInstance().field.getAsteroids().get(0).getX() == 50)
                return true;
            return false;
        }

        /**
         * Checks if the game has reached the right map bound.
         */
        public boolean checkRight() {
            int idx = side - 1;
            int bound = side * -90;
            if (Game.getInstance().field.getAsteroids().get(idx).getX() == bound)
                return true;
            return false;
        }

        /**
         * Moves an asteroid vertically with a certain distance.
         *
         * @param distance the distance
         */
        public void moveVertically(int distance) {
            for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                int newY = a.getY() + distance;
                a.setY(newY);
            }
        }

        /**
         * Moves an asteroid horizontally with a certain distance.
         *
         * @param distance the distance
         */
        public void moveHorizontally(int distance) {
            for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                int newX = a.getX() + distance;
                a.setX(newX);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Game.getInstance().getMenu().setMenuState(MenuState.LOADMENU); //TODO::itt beállíthatjuk azt, hogyha a menübe visszalépünk esc-el, akkor mentse a játékot
                Game.getInstance().getMenu().menu_step(0);
                window.switchToMenu();
                try {
                    window.playSound(4, 0.6f, 0); //TODO:ez nem itt lesz, hanem majd a useteleportnál
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (!checkUp())
                    moveVertically(10);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (!checkDown())
                    moveVertically(-10);
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (!checkLeft())
                    moveHorizontally(10);
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (!checkRight())
                    moveHorizontally(-10);
            }
            if (e.getKeyCode() == KeyEvent.VK_F) {
                if (selectedSettler == null || selectedSettler.isFinishedTurn())
                    return;
                selectedSettler.Drill();
                if (selectedSettler.isFinishedTurn()) {
                    try {
                        window.playSound(2, 0.2f, 0);
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                if (selectedSettler == null || selectedSettler.isFinishedTurn())
                    return;
                selectedSettler.Mine();
                if (selectedSettler.isFinishedTurn()) {
                    try {
                        window.playSound(3, 0.2f, 0);
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                try {
                    Game.getInstance().saveGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_C) {
                //placeteleport
                if (selectedSettler == null || selectedSettler.isFinishedTurn())
                    return;
                selectedSettler.PlaceTeleport();
            }
            if (e.getKeyCode() == KeyEvent.VK_V) {
                //placematerial
                if (selectedSettler == null || selectedSettler.isFinishedTurn())
                    return;
                selectedSettler.PlaceMaterial();
            }
            if (e.getKeyCode() == KeyEvent.VK_T) {
                //maketeleport
                if (selectedSettler == null || selectedSettler.isFinishedTurn())
                    return;
                selectedSettler.MakeTeleport();
            }
            if (e.getKeyCode() == KeyEvent.VK_R) {
                //buildrobot
                if (selectedSettler == null || selectedSettler.isFinishedTurn())
                    return;
                selectedSettler.BuildRobot();
            }
            window.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    class MouseListenerClass implements MouseListener, ActionListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
                selectedSettler = null;
                checkboxSettler(e.getX(), e.getY());
            }

            if (e.getButton() == 3) {
                checkboxAsteroidTeleport(e.getX(), e.getY());
                if (selectedSettler != null && selectedAsteroid != null && !selectedSettler.isFinishedTurn()) {
                    selectedSettler.Move(selectedAsteroid);
                    if (selectedSettler.isFinishedTurn()) {
                        window.repaint();
                        selectedSettler = null;
                    }
                    return;
                }

                if (selectedSettler != null && selectedTeleport != null && !selectedSettler.isFinishedTurn()) {
                    selectedSettler.UseTeleport(selectedTeleport);
                    if (selectedSettler.isFinishedTurn()) {
                        try {
                            window.playSound(4, 0.4f, 0);
                        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                            ioException.printStackTrace();
                        }
                        window.repaint();
                        selectedSettler = null;
                    }
                }
            }
            window.repaint();
            selectedAsteroid = null;
            selectedTeleport = null;
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            /**
             * Handles the buttons that show up, when the game ends.
             * */
            if (e.getSource() == menuButton) {
                window.switchToMenu();
            }
            if (e.getSource() == exitButton) {
                System.exit(0);
            }
        }
    }


    //TODO::move -> két kattintás első az entiti második az aszteroidára
    //TODO:: useteleport -> space billentyű plusz aszteroida kiválasztása
}
