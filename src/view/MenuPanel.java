package view;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JPanel formPanel;
    private JPanel buttonsPanel;

    private JLabel imageLabel;

    private JButton startButton;
    private JButton loadButton;
    private JButton exitButton;

    private JSpinner settlerSpinner;

    private JLabel settlerLabel;

    public MenuPanel(){
        setLayout(new GridLayout(2,1,0,10));
        setBorder(BorderFactory.createEmptyBorder(50,75,0,75));
        setBackground(Color.GRAY);

        initPanels();
        add(formPanel);
        add(buttonsPanel);

    }

    public void initPanels(){
        formPanel = new JPanel();
        buttonsPanel = new JPanel();
    }

    public void initSettlerSpinnner(){

    }

    public void initBackGround(){
    }
}
