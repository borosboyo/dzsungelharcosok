package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private MenuPanel menuPanel = new MenuPanel();
    private GamePanel game;

    public Window(){
        setTitle("Asteroidmining");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 400);
        setResizable(false);
        menuPanel.setLayout(new GridLayout(2,2,20,20));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50,75,50,75));
        menuPanel.setBackground(Color.lightGray);
        add(menuPanel);
        setVisible(true);
    }

    public static void main(String []args) throws IOException {
        new Window();
    }
}
