package game;

import display.Display;
import models.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private Player player;

    public InputHandler(Display display, Player player) {
        display.getCanvas().addKeyListener(this);
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            this.player.setIsMovingUp(true);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            this.player.setIsMovingDown(true);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            this.player.setIsMovingLeft(true);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            this.player.setIsMovingRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            this.player.setIsMovingUp(false);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            this.player.setIsMovingDown(false);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            this.player.setIsMovingLeft(false);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            this.player.setIsMovingRight(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
