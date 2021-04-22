package view;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MenuPanel extends JPanel {

    Window window;

    private JPanel formPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private Image bg;

    private JButton startButton = new JButton();
    private JButton loadButton = new JButton();
    private JButton exitButton = new JButton();

    private JSpinner settlerSpinner;

    private JLabel settlerLabel;

    private ArrayList<ImageIcon> imageContainer = new ArrayList<ImageIcon>();

    public MenuPanel(Window _window){
        window = _window;
        setLayout(new GridLayout(2,1,0,10));
        setBorder(BorderFactory.createEmptyBorder(50,0,0,0));

        initImages();
        initBackGround();
        initPanels();
        initAllButtons();

        add(formPanel);
        add(buttonsPanel);
    }

    public void initBackGround(){
        bg = imageContainer.get(0).getImage();
    }


    public void initPanels(){
        formPanel.setOpaque(false);
        buttonsPanel.setOpaque(false);
    }

    public void initAllButtons(){
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0,100,0,100));

        initButton(startButton,2);
        initButton(loadButton,3);
        initButton(exitButton,4);

        buttonsPanel.add(startButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(exitButton);
    }

    public void initButton(JButton button, int imageIndex){
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setIcon(imageContainer.get(imageIndex));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setMargin(new Insets(0,0,0,0));
        button.setOpaque(false);
        button.setBorderPainted(false);
        //button.setContentAreaFilled(false);
        button.addActionListener(new ClickListener());
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

    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == startButton){
                window.switchToGame();
            }
        }
    }

}
