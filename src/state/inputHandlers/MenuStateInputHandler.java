package state.inputHandlers;

import state.StateManager;

import java.awt.event.KeyEvent;

public class MenuStateInputHandler extends InputHandler{
    private int    keyCode;

    public MenuStateInputHandler(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyCode = e.getKeyCode();

        if (this.keyCode == KeyEvent.VK_UP) {
        }
        if (this.keyCode == KeyEvent.VK_DOWN) {
        }
        if (this.keyCode == KeyEvent.VK_LEFT) {
        }
        if (this.keyCode == KeyEvent.VK_RIGHT) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();

        if (this.keyCode == KeyEvent.VK_UP) {
        }
        if (this.keyCode == KeyEvent.VK_DOWN) {
        }
        if (this.keyCode == KeyEvent.VK_LEFT) {
        }
        if (this.keyCode == KeyEvent.VK_RIGHT) {
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
