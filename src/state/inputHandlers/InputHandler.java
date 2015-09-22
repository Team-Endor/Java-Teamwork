package state.inputHandlers;

import state.StateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class InputHandler implements KeyListener {
    private StateManager stateManager;

    protected InputHandler(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void keyTyped(KeyEvent e);
}
