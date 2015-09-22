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

    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();

        if (this.keyCode == KeyEvent.VK_ESCAPE){
            this.getStateManager().setCurrentState(this.getStateManager().getGameState());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
