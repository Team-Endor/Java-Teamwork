package state;

import game.Engine;
import gfx.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MenuState extends State {
    private StateManager stateManager;
    private BufferedImage background;
    private BufferedImage playButton;
    private BufferedImage exitButton;
    private JPanel panel;

    public MenuState(StateManager stateManager) {
        this.init();
        this.stateManager = stateManager;
    }

    public void init() {
        this.background = Assets.Menu;
        this.playButton = Assets.playButton;
        this.exitButton = Assets.exitButton;

        panel = new JPanel();
        panel.setSize(800, 600);
        panel.setLayout(null);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon(this.background));
        background.setLocation(0, 0);
        background.setSize(800, 600);


        JButton play = new JButton();
        play.setSize(this.playButton.getWidth(), this.playButton.getHeight());
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setBackground(new Color(0, 0, 0, 1));
        play.setIcon(new ImageIcon(playButton));
        play.setLocation(250, 250);
        play.setActionCommand("a");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.setCurrentState(stateManager.getGameState());
            }
        });


        JButton exit = new JButton();
        exit.setLocation(350, 350);
        exit.setSize(this.exitButton.getWidth(), this.exitButton.getHeight());
        exit.setBorder(BorderFactory.createEmptyBorder());
        exit.setBackground(new Color(0, 0, 0, 1));
        exit.setIcon(new ImageIcon(exitButton));
        exit.setActionCommand("b");
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                stateManager.getEngine().stop();
            }
        });


        panel.add(play);
        panel.add(exit);
        panel.add(background);

    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {


        this.panel.paint(graphics);

    }
}
