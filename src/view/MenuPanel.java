package view;

import model.Game;
import model.MenuState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * MenuPanel in the Window
 */
public class MenuPanel extends JPanel {

    /**
     * The host Window.
     */
    private final Window window;

    /**
     * The first panel where the user can select Start, Load or Exit.
     */
    private final JPanel buttonsPanel = new JPanel();

    /**
     * The second panel that appears when the player selects Start.
     * This is where the user can select the number of settlers in the game.
     */
    private final JPanel formPanel = new JPanel();

    /**
     * The background image.
     */
    private Image bg;

    /**
     * Button that hides the buttonsPanel and reveals the formPanel
     */
    private final JButton startButton = new JButton();

    /**
     * Button that loads the saved game.
     */
    private final JButton loadButton = new JButton();

    /**
     * Exit button.
     */
    private final JButton exitButton = new JButton();

    /**
     * The user can confirm the number of settlers selected and start the game with this button.
     */
    private final JButton confirmButton = new JButton();

    /**
     * Takes the user back from the formPanel to the buttonsPanel.
     */
    private final JButton cancelButton = new JButton();

    /**
     * The spinner where the user can select the number of settlers.
     */
    private final JSpinner settlerSpinner = new JSpinner();

    /**
     * Holds the image "Settlers:" in the formPanel.
     */
    private final JLabel settlerLabel = new JLabel();

    /**
     * List of images used in the MenuPanel.
     */
    private final ArrayList<ImageIcon> imageContainer = new ArrayList<>();

    /**
     * The constructor of the MenuPanel.
     * Initializes the following things: images, the background, the panel for the starting option buttons,
     * the form panel that appaers when the user tries to start the game.
     *
     * @param _window the window
     */
    public MenuPanel(Window _window) {
        window = _window;
        setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));

        initImages();
        initBackGround();
        initButtonsPanel();
        initFormPanel();

        add(buttonsPanel);
        add(formPanel);
        buttonsPanel.setVisible(true);
        formPanel.setVisible(false);
    }

    /**
     * Initializes the background image.
     */
    public void initBackGround() {
        bg = imageContainer.get(0).getImage();
    }

    /**
     * Initializes the first buttons panel where the user can selec Start, Load or Exit.
     * Starts the initialization of the individual buttons.
     */
    public void initButtonsPanel() {
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
        buttonsPanel.setOpaque(false);

        initButton(startButton, 2);
        initButton(loadButton, 3);
        initButton(exitButton, 4);

        buttonsPanel.add(startButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(exitButton);
    }

    /**
     * Initializes the form panel that appears when the player selects Start.
     * In this panel the user can select the number of settlers in the game.
     */
    public void initFormPanel() {
        formPanel.setLayout(new GridLayout(3, 2, 0, 10));
        formPanel.setOpaque(false);

        settlerLabel.setIcon(imageContainer.get(1));

        SpinnerNumberModel sModel = new SpinnerNumberModel(10, 5, 20, 1);
        settlerSpinner.setModel(sModel);

        initButton(confirmButton, 5);
        initButton(cancelButton, 6);

        formPanel.add(settlerLabel);
        formPanel.add(settlerSpinner);
        formPanel.add(confirmButton);
        formPanel.add(cancelButton);
    }

    /**
     * Initializes a certain button with a certain image.
     *
     * @param button     the button
     * @param imageIndex the image index
     */
    public void initButton(JButton button, int imageIndex) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setIcon(imageContainer.get(imageIndex));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(new ClickListener());
    }

    /**
     * Loads all the images used in the menu.
     */
    public void initImages() {
        imageContainer.add(new ImageIcon("images/menu.png"));
        imageContainer.add(new ImageIcon("images/menusettler.png"));
        imageContainer.add(new ImageIcon("images/menustartgame.png"));
        imageContainer.add(new ImageIcon("images/menuloadgame.png"));
        imageContainer.add(new ImageIcon("images/menuexit.png"));
        imageContainer.add(new ImageIcon("images/menuconfirm.png"));
        imageContainer.add(new ImageIcon("images/menucancel.png"));
    }

    /**
     * Paints the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
    }

    /**
     * ClickListener class that handles the actions that happen if a certain button is clicked on the panel.
     */
    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            /**
             * Hides buttonsPanel and shows formPanel.
             */
            if (e.getSource() == startButton) {
                formPanel.setVisible(true);
                buttonsPanel.setVisible(false);
            }

            /**
             * Loads the saved game.
             */
            if (e.getSource() == loadButton) {
                Game.getInstance().getMenu().setMenuState(MenuState.LOADGAME);
                Game.getInstance().getMenu().menu_step(0);
                if (Game.getInstance().field != null) {
                    window.switchToGame();
                }
            }

            /**
             * Exit button.
             */
            if (e.getSource() == exitButton) {
                System.exit(0);
            }

            /**
             * Confirms the number of settlers selected in the spinner and starts the game.
             */
            if (e.getSource() == confirmButton) {
                Game.getInstance().getMenu().setMenuState(MenuState.NEWGAME);
                Game.getInstance().getMenu().menu_step((Integer) settlerSpinner.getValue());
                window.switchToGame();
                formPanel.setVisible(false);
                buttonsPanel.setVisible(true);
            }

            /**
             * Takes back the user form the formPanel to the buttonsPanel.
             */
            if (e.getSource() == cancelButton) {
                Game.getInstance().getMenu().setMenuState(MenuState.LOADMENU);
                Game.getInstance().getMenu().menu_step(0);
                buttonsPanel.setVisible(true);
                formPanel.setVisible(false);
            }
        }
    }

}
