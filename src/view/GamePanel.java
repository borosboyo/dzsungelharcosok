package view;

import model.*;

import java.awt.event.*;
import java.awt.event.KeyEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GamePanel extends JPanel {
    Window window;
    private int unit; //TODO:ehhez arányosan kéne majd az x,y,width,high értkékeket beállítani (asteroida megkapja, a többi még nem)
    Asteroid selectedAsteroid;
    Settler selectedSettler;
    Teleport selectedTeleport;

    public GamePanel(Window _window, int unit) {
        this.window = _window;
        this.unit = unit;
        //TODO::ez így nem működik
        addKeyListener(new KeyListenerClass());
        addMouseListener(new MouseListenerClass());
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
            for (int j = 0; j < asteroid.getTeleports().size(); j++) {
                teleport = asteroid.getTeleports().get(j);
                if (asteroid.getX() < x && x < asteroid.getX() + unit && asteroid.getY() < y && y < asteroid.getY() + unit) {
                    selectedTeleport = teleport;
                    return;
                }
            }
        }
        selectedAsteroid = null;
        selectedTeleport = null;
    }

//    void checkboxSettler(int x, int y) {
//        Field fi = Game.getInstance().field;
//        Settler settler;
//        for (int i = 0; i < fi.getSettlers().size(); i++){
//            settler = fi.getSettlers().get(i);
//            if (settler.getX() < x && x < settler.getX() + unit && settler.getY() < y && y < settler.getY() + unit) {
//                selectedSettler =  settler;
//                return;
//            }
//        }
//        selectedSettler = null;
//    }

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

        g.setColor(Color.GRAY);
        g.drawLine(0, window.getHeight() - 60, window.getWidth(), window.getHeight() - 60);
        font = new Font(Font.SERIF, Font.BOLD, (int) (14));
        g.setFont(font);
        g.drawString("Up: W,  Down: S,  Left: A,  Right: D,    Drill: F,   Mine: E,   Useteleport: Space,   Placeteleport: C,   Placematerial: V,   Maketeleport: T,   BuildRobot: T,   Save: M", 3, window.getHeight() - 46);
    }


    class KeyListenerClass implements KeyListener {

        public boolean checkEdge(int bound) {
            for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                if (a.getX() == bound)
                    return true;
            }
            return false;
        }

        public void moveVertically(int distance) {
            for (Asteroid a : Game.getInstance().field.getAsteroids()) {
                int newY = a.getY() + distance;
                a.setY(newY);
            }
        }

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
                    window.playSound(4, 1.0f, 0); //TODO:ez nem itt lesz, hanem majd a useteleportnál
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (!checkEdge(0))
                    moveVertically(-10);
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (!checkEdge(0))
                    moveVertically(10);
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (!checkEdge(1024))
                    moveHorizontally(-10);
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (!checkEdge(50))
                    moveHorizontally(10);
                window.repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_F) {
                if (selectedSettler == null)
                    return;
                selectedSettler.Drill();
                try {
                    window.playSound(2, 1.0f, 0);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                if (selectedSettler == null)
                    return;
                selectedSettler.Mine();
                try {
                    window.playSound(3, 1.0f, 0);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                try {
                    Game.getInstance().saveGame();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                //useteleport
                if (selectedSettler == null && selectedAsteroid == null)
                    return;
                //TODO
            }
            if (e.getKeyCode() == KeyEvent.VK_C) {
                //placeteleport
                if (selectedSettler == null)
                    return;
                selectedSettler.PlaceTeleport();
            }
            if (e.getKeyCode() == KeyEvent.VK_V) {
                //placematerial
                if (selectedSettler == null)
                    return;
                selectedSettler.PlaceMaterial();
            }
            if (e.getKeyCode() == KeyEvent.VK_T) {
                //maketeleport
                if (selectedSettler == null)
                    return;
                selectedSettler.MakeTeleport();
            }
            if (e.getKeyCode() == KeyEvent.VK_R) {
                //buildrobot
                if (selectedSettler == null)
                    return;
                selectedSettler.BuildRobot();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    class MouseListenerClass implements java.awt.event.MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {


            if (e.getButton() == 1) {
                selectedSettler = null;
//            checkboxSettler(e.getX(), e.getY());
            }

            if (e.getButton() == 3) {
                selectedAsteroid = null;
                selectedTeleport = null;

                checkboxAsteroidTeleport(e.getX(), e.getY());
                if (selectedSettler != null && selectedAsteroid != null) {
                    selectedSettler.Move(selectedAsteroid);
                }
                if (selectedSettler != null && selectedTeleport != null) {
                    selectedSettler.UseTeleport(selectedTeleport);
                }
            }
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
