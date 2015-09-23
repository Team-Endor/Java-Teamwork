package state.inputHandlers;

import state.GameEndState;
import state.GameState;
import state.State;
import state.StateManager;

import java.awt.event.KeyEvent;

public class GameEndScreenInputHandler extends InputHandler{
    private int keyCode;

    public GameEndScreenInputHandler(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();
        State currentState = this.getStateManager().getCurrentState();
        GameEndState currentGameEndState;
        if(currentState == this.getStateManager().getGameLostState()){
            currentGameEndState = this.getStateManager().getGameLostState();
        } else {
            currentGameEndState = this.getStateManager().getGameWonState();
        }

        int numberOfMenuButtons = currentGameEndState.numberOfButtons();

        if (this.keyCode == KeyEvent.VK_UP) {
            if (currentGameEndState.getHoveredButtonIndex() > 0) {
                currentGameEndState.setHoveredButtonIndex(currentGameEndState.getHoveredButtonIndex() - 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_DOWN) {
            if (currentGameEndState.getHoveredButtonIndex() < numberOfMenuButtons - 1) {
                currentGameEndState.setHoveredButtonIndex(currentGameEndState.getHoveredButtonIndex() + 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_ENTER) {
            if (currentGameEndState.getHoveredButtonIndex() == 0) {
                GameState newGameState = new GameState(this.getStateManager());
                this.getStateManager().setGameState(newGameState);
                this.getStateManager().setCurrentState(this.getStateManager().getGameState());
            }
            if (currentGameEndState.getHoveredButtonIndex() == 1) {
                this.getStateManager().getGameState().setIsPaused(true);
                this.getStateManager().setCurrentState(this.getStateManager().getMenuState());
            }
            if (currentGameEndState.getHoveredButtonIndex() == 2) {
                this.getStateManager().setCurrentState(this.getStateManager().getExitGameState());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
