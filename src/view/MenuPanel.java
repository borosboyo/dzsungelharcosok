package view;

import model.Game;
import model.MenuState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton cancelButton = new JButton();

    private JSpinner settlerSpinner = new JSpinner();
    private JLabel settlerLabel = new JLabel();

    private ArrayList<ImageIcon> imageContainer = new ArrayList<ImageIcon>();

    public MenuPanel(Window _window){
        window = _window;
        setBorder(BorderFactory.createEmptyBorder(60,0,0,0));

        initImages();
        initBackGround();
        initButtonsPanel();
        initFormPanel();

        add(buttonsPanel);
        add(formPanel);
        buttonsPanel.setVisible(true);
        formPanel.setVisible(false);
    }

    public void initBackGround(){
        bg = imageContainer.get(0).getImage();
    }

    public void initButtonsPanel(){
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setOpaque(false);

        initButton(startButton,2);
        initButton(loadButton,3);
        initButton(exitButton,4);

        buttonsPanel.add(startButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(exitButton);
    }

    public void initFormPanel(){
        formPanel.setLayout(new GridLayout(3,2,0,10));
        formPanel.setOpaque(false);

        settlerLabel.setIcon(imageContainer.get(1));

        SpinnerNumberModel sModel = new SpinnerNumberModel(10,5,20,1);
        settlerSpinner.setModel(sModel);
        settlerSpinner.setOpaque(false);
        settlerSpinner.setSize(40,40);

        //Itt masik kep kell
        initButton(confirmButton, 2);
        initButton(cancelButton,4);

        formPanel.add(settlerLabel);
        formPanel.add(settlerSpinner);
        formPanel.add(confirmButton);
        formPanel.add(cancelButton);
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

    public void initImages() {
        imageContainer.add(new ImageIcon("images/menu.png"));
        imageContainer.add(new ImageIcon("images/menusettler.png"));
        imageContainer.add(new ImageIcon("images/menustartgame.png"));
        imageContainer.add(new ImageIcon("images/menuloadgame.png"));
        imageContainer.add(new ImageIcon("images/menuexit.png"));
    }

    public void askSettlers(){
        formPanel.setVisible(true);
        buttonsPanel.setVisible(false);
        window.repaint();
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
                askSettlers();
            }
            if(e.getSource() == loadButton){
                Game.getInstance().getMenu().setMenuState(MenuState.LOADGAME);
                Game.getInstance().getMenu().menu_step(0);
                window.switchToGame(0,true);
            }
            if(e.getSource() == exitButton){
                System.exit(0);
            }
            if(e.getSource() == confirmButton){
                Game.getInstance().getMenu().setMenuState(MenuState.NEWGAME);
                Game.getInstance().getMenu().menu_step((Integer) settlerSpinner.getValue());
                window.switchToGame((Integer) settlerSpinner.getValue(),false);
            }
            if(e.getSource() == cancelButton){
                Game.getInstance().getMenu().setMenuState(MenuState.LOADMENU);
                Game.getInstance().getMenu().menu_step(0);
                //TODO::Borosnak itt kell visszahoznia a sima menü panelt
            }
        }
    }

}
