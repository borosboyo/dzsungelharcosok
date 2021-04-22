package view;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JPanel formPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private JLabel imageLabel;
    private Image bg;

    private JButton startButton = new JButton();
    private JButton loadButton = new JButton();
    private JButton exitButton = new JButton();

    private JSpinner settlerSpinner;

    private JLabel settlerLabel;

    public MenuPanel(){
        setLayout(new GridLayout(2,1,0,10));
        setBorder(BorderFactory.createEmptyBorder(50,75,0,75));
        initBackGround();
        initPanels();
        initButtons();
        add(formPanel);
        add(buttonsPanel);
    }

    public void initBackGround(){
        bg = new ImageIcon("images/menu.png").getImage();
    }


    public void initPanels(){
        formPanel.setOpaque(false);
        buttonsPanel.setOpaque(false);
    }

    public void initButtons(){
        buttonsPanel.add(startButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(exitButton);
    }

    public void initSettlerSpinnner(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg,0,0,null);
    }

}
