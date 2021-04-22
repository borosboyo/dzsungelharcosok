package view;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MenuPanel extends JPanel {
    private JPanel formPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private JLabel imageLabel;
    private Image bg;

    private JButton startButton = new JButton();
    private JButton loadButton = new JButton();
    private JButton quitButton = new JButton();

    private JSpinner settlerSpinner;

    private JLabel settlerLabel;

    private ArrayList<ImageIcon> imageContainer = new ArrayList<ImageIcon>();

    public MenuPanel(){
        setLayout(new GridLayout(2,1,0,10));
        setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        initImages();
        initBackGround();
        initPanels();
        initButtons();

        add(formPanel);
        add(buttonsPanel, BorderLayout.CENTER);
    }

    public void initBackGround(){

        //bg = new ImageIcon("images/menu.png").getImage();
        bg = imageContainer.get(0).getImage();
    }


    public void initPanels(){
        //formPanel.setOpaque(false);
        //buttonsPanel.setOpaque(false);
    }

    public void initButtons(){
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));


        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setIcon(imageContainer.get(2));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setContentAreaFilled(true);
        startButton.setMargin(new Insets(0,0,0,0));
        buttonsPanel.add(startButton);
    }

    public void initSettlerSpinnner(){

    }

    public void initImages() {
        imageContainer.add(new ImageIcon("images/menu.png"));
        imageContainer.add(new ImageIcon("images/menusettler.png"));
        imageContainer.add(new ImageIcon("images/menustartgame.png"));
        imageContainer.add(new ImageIcon("images/menuloadgame.png"));
        imageContainer.add(new ImageIcon("images/menuexit.png"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg,0,0,null);
    }

}
