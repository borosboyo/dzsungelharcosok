package view;


import model.Game;
import model.MenuState;
import model.Objects.Asteroid;
import model.Objects.Settler;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Certain functions are only accessible with the keyboard so this class handles this functionality.
 */
class KeyListenerClass implements KeyListener {
    Window window;
    Settler selectedSettler;

    public void setSelectedSettler(Settler selectedSettler) {
        this.selectedSettler = selectedSettler;
    }

    public KeyListenerClass(Window window, Settler selectedSettler) {
        this.window = window;
        this.selectedSettler = selectedSettler;
    }

    /**
     * Stores how many sides the map has, so that the map border calculation is smooth.
     */
    int side = Game.getInstance().field.getSide();

    /**
     * Checks if the game has reached the upper map bound.
     *
     * @return the boolean
     */
    public boolean checkUp() {
        if (Game.getInstance().field.getAsteroids().get(0).getY() == 50)
            return true;
        return false;
    }

    /**
     * Checks if the game has reached the lower map bound.
     *
     * @return the boolean
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
     *
     * @return the boolean
     */
    public boolean checkLeft() {
        if (Game.getInstance().field.getAsteroids().get(0).getX() == 50)
            return true;
        return false;
    }

    /**
     * Checks if the game has reached the right map bound.
     *
     * @return the boolean
     */
    public boolean checkRight() {
        int idx = side - 1;
        int bound = side * 70;
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

    /**
     * Handles the keyboard events.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        /**
         * The player returns to the menu when the escape button is pressed.
         */
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
        /**
         * The player can move the map upwards with W.
         */
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (!checkUp())
                moveVertically(10);
        }
        /**
         * The player can move the map downwards with S.
         */
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (!checkDown())
                moveVertically(-10);
        }
        /**
         * The player can move the map left with A.
         */
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (!checkLeft())
                moveHorizontally(10);
        }
        /**
         * The player can move the map right with D.
         */
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (!checkRight())
                moveHorizontally(-10);
        }
        /**
         * The player can drill with the selected settler with F.
         */
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

        /**
         * The player can mine with the selected settler with E.
         */
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
        /**
         * The player can save the game with M.
         */
        if (e.getKeyCode() == KeyEvent.VK_M) {
            try {
                Game.getInstance().saveGame();
                window.getGamePanel().setSavedGame(true);
                //savedGame = true;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        /**
         * The player can place a teleport with the selected settler with C.
         */
        if (e.getKeyCode() == KeyEvent.VK_C) {
            //placeteleport
            if (selectedSettler == null || selectedSettler.isFinishedTurn())
                return;
            selectedSettler.PlaceTeleport();
        }

        /**
         * The player can place material with the selected settler with V.
         */
        if (e.getKeyCode() == KeyEvent.VK_V) {
            //placematerial
            if (selectedSettler == null || selectedSettler.isFinishedTurn())
                return;
            selectedSettler.PlaceMaterial();
        }

        /**
         * The player can make a teleport with the selected settler with T.
         */
        if (e.getKeyCode() == KeyEvent.VK_T) {
            //maketeleport
            if (selectedSettler == null || selectedSettler.isFinishedTurn())
                return;
            selectedSettler.MakeTeleport();
        }

        /**
         * The player can build a robot with the selected settler with R.
         */
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