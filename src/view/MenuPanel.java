package view;

import model.Game;

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
    private JButton confirmButton = new JButton();

    private JSpinner settlerSpinner;
    private JLabel settlerLabel;

    private ArrayList<ImageIcon> imageContainer = new ArrayList<ImageIcon>();

    public MenuPanel(Window _window){
        window = _window;
        setBorder(BorderFactory.createEmptyBorder(60,0,0,0));

        initImages();
        initBackGround();
        initPanels();
        initAllButtons();

        add(buttonsPanel);
    }

    public void initBackGround(){
        bg = imageContainer.get(0).getImage();
    }


    public void initPanels(){
        buttonsPanel.setOpaque(false);
    }

    public void initAllButtons(){
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));

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
        button.setContentAreaFilled(false);
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
                Game.getInstance().setNew_game(true);
                Game.getInstance().setMenu(false);
                window.switchToGame(false);
            }
            if(e.getSource() == loadButton){
                Game.getInstance().setNew_game(false);
                Game.getInstance().setMenu(false);
                window.switchToGame(false);
                window.switchToGame((Integer) settlerSpinner.getValue(),true);
            }
            if(e.getSource() == exitButton){
                System.exit(0);
            }

        }
    }

}
