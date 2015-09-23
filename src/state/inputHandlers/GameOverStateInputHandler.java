package state.inputHandlers;


import state.GameOverState;
import state.StateManager;

import java.awt.event.KeyEvent;

public class GameOverStateInputHandler extends InputHandler {
    private int keyCode;

    public GameOverStateInputHandler(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();
        GameOverState currentState = this.getStateManager().getGameOverState();
        int numberOfMenuButtons = currentState.numberOfMenuButtons();

        if (this.keyCode == KeyEvent.VK_LEFT) {
            if (currentState.getHoveredButtonIndex() > 0) {
                currentState.setHoveredButtonIndex(currentState.getHoveredButtonIndex() - 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_RIGHT) {
            if (currentState.getHoveredButtonIndex() < numberOfMenuButtons - 1) {
                currentState.setHoveredButtonIndex(currentState.getHoveredButtonIndex() + 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_ENTER) {
            if (currentState.getHoveredButtonIndex() == 0) {
                this.getStateManager().getGameState().setIsPaused(false);
                this.getStateManager().setCurrentState(this.getStateManager().getGameState());
            }
            if (currentState.getHoveredButtonIndex() == 1) {
                this.getStateManager().setCurrentState(this.getStateManager().getExitGameState());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
