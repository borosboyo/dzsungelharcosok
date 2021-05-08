package view;

import model.Asteroid;
import model.Settler;
import model.Teleport;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * The type Mouse listener class.
 */
class MouseListenerClass implements MouseListener, ActionListener {
    Window window;
    Settler selectedSettler;
    Teleport selectedTeleport;
    Asteroid selectedAsteroid;

    public MouseListenerClass(Window window, Settler selectedSettler, Teleport selectedTeleport, Asteroid selectedAsteroid) {
        this.window = window;
        this.selectedSettler = selectedSettler;
        this.selectedTeleport = selectedTeleport;
        this.selectedAsteroid = selectedAsteroid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            //selectedSettler = null;
            window.getGamePanel().SelectedSettler(null);
            window.getGamePanel().checkboxSettler(e.getX(), e.getY());
        }

        if (e.getButton() == 3) {
            window.getGamePanel().checkboxAsteroidTeleport(e.getX(), e.getY());
            if (selectedSettler != null && selectedAsteroid != null && !selectedSettler.isFinishedTurn()) {
                selectedSettler.Move(selectedAsteroid);
                if (selectedSettler.isFinishedTurn()) {
                    window.repaint();
                    //selectedSettler = null;
                    window.getGamePanel().SelectedSettler(null);
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
                    //selectedSettler = null;
                    window.getGamePanel().SelectedSettler(null);
                }
            }
        }
        window.repaint();
        //selectedAsteroid = null;
        window.getGamePanel().SelectedAsteroid(null);
        //selectedTeleport = null;
        window.getGamePanel().SelectedTeleport(null);
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

    /**
     * Handles the buttons that show up, when the game ends.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * Returns to the main menu.
         */
        if (e.getSource() == window.getGamePanel().getMenuButton()) {
            window.switchToMenu();
        }
        /**
         * Exits the game.
         */
        if (e.getSource() == window.getGamePanel().getExitButton()) {
            System.exit(0);
        }
    }

    public void setSelectedSettler(Settler selectedSettler) {
        this.selectedSettler = selectedSettler;
    }

    public void setSelectedTeleport(Teleport selectedTeleport) {
        this.selectedTeleport = selectedTeleport;
    }

    public void setselectedAsteroid(Asteroid selectedAsteroid) {
        this.selectedAsteroid = selectedAsteroid;
    }
}