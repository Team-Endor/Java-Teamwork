package state.inputHandlers;

import state.*;

import java.awt.event.KeyEvent;

public class CreditsStateInputHandler extends InputHandler {
    private int keyCode;

    public CreditsStateInputHandler(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();

        if (this.keyCode == KeyEvent.VK_ENTER) {
            this.getStateManager().setCurrentState(this.getStateManager().getMenuState());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
