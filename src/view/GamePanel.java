package view;

import model.Asteroid;
import model.Field;
import model.Game;
import model.MenuState;

import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GamePanel extends JPanel {
    Window window;
    int unit; //TODO:ehhez arányosan kéne majd az x,y,width,high értkékeket beállítani (asteroida megkapja, a többi még nem)

    public GamePanel(Window _window, int unit) {
        this.window = _window;
        this.unit = unit;
        //TODO::ez így nem működik
        addKeyListener(new KeyListenerClass());
        addMouseListener(new MouseListenerClass());
    }


    Asteroid checkboxAsteroid(int x, int y) {
        Field fi = Game.getInstance().field;
        for (Asteroid asteroid : fi.getAsteroids()) {
            if (asteroid.getX() < x && x < asteroid.getX() + unit && asteroid.getY() < y && y < asteroid.getY() + unit) {
                return asteroid;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Field fi = Game.getInstance().field;

        this.setBackground(Color.DARK_GRAY);

        /**
         * Asteroids draw
         */
       fi.getAsteroids().stream().map(asteroid -> new AsteroidView(asteroid)).forEach(asteroidView -> asteroidView.draw(g, unit, 0, 0));

        //TODO::itt lehetne fontot is allitani es a szovegek helyet is megkene , mas szovegeket is leheten itt megadni, ha kell
        Font font = new Font(Font.SERIF, Font.PLAIN, (int) (20));
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Settler turn:", 2, 20);
        for (int i = 0; i < fi.getSettlers().size(); i++) {
            if (!fi.getSettlers().get(i).isFinishedTurn()) {
                g.drawString(String.valueOf(fi.getSettlers().get(i).getId()), 0, 40 + (i * 20));
            }
        }
    }


    class KeyListenerClass implements KeyListener {

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
                    window.playSound(4, 1.0f, 0); //TODO:ez nem itt lesz, hanem majd a useteleportnál
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                    int newY = a.getY() - 10;
                    a.setY(newY);
                }
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                    int newY = a.getY() + 10;
                    a.setY(newY);
                }
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                    int newX = a.getX() - 10;
                    a.setX(newX);
                }
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                    int newX = a.getX() + 10;
                    a.setX(newX);
                }
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_F) {
                try {
                    window.playSound(2, 1.0f, 0);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                try {
                    window.playSound(3, 1.0f, 0);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                //useteleport
            }
            if (e.getKeyCode() == KeyEvent.VK_C) {
                //placeteleport
            }
            if (e.getKeyCode() == KeyEvent.VK_V) {
                //placematerial
            }
            if (e.getKeyCode() == KeyEvent.VK_T) {
                //maketeleport
            }
            if (e.getKeyCode() == KeyEvent.VK_R) {
                //buildrobot
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    class MouseListenerClass implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX() + " " + e.getY());
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
    }


    //TODO::move -> két kattintás első az entiti második az aszteroidára
    //TODO:: useteleport -> space billentyű plusz aszteroida kiválasztása
}
