package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(){
        setLayout(new GridLayout(2,2,20,20));
        setBorder(BorderFactory.createEmptyBorder(50,75,50,75));
        setBackground(Color.lightGray);
    }
}
