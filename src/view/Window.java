package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private Menu menu = new Menu();
    private Game game;

    public Window(){
        setTitle("Minesweeper");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        menu.setLayout(new GridLayout(2,2,20,20));
        menu.setBorder(BorderFactory.createEmptyBorder(50,75,50,75));
        menu.setBackground(Color.lightGray);
        add(menu);
        setVisible(true);
    }

    public static void main(String []args) throws IOException {
        new Window();
    }
}
