package view;

import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame {
    private Menu menu;
    private Game game;

    public Window(){
        menu = new Menu();
    }

    public static void main(String []args) throws IOException {
        new Window();
    }
}
