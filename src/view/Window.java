package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private MenuPanel menuPanel;
    private GamePanel game;

    public Window(){
        setTitle("Asteroidmining");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 200);
        setResizable(false);
        menuPanel = new MenuPanel();
        add(menuPanel);
        setVisible(true);

    }

    public static void main(String []args) throws IOException {
       Window window =  new Window();
       window.setIconImage(ImageIO.read(new File("images/asteroid-icon.png")));
    }
}
