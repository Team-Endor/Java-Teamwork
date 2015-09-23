package state.inputHandlers;

import state.GameState;
import state.MenuState;
import state.StateManager;

import java.awt.event.KeyEvent;

public class MenuStateInputHandler extends InputHandler {
    private int keyCode;

    public MenuStateInputHandler(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();
        MenuState currentMenuState = this.getStateManager().getMenuState();
        int numberOfMenuButtons = currentMenuState.numberOfMenuButtons();

        if (this.keyCode == KeyEvent.VK_UP) {
            if (currentMenuState.getHoveredButtonIndex() > 0) {
                currentMenuState.setHoveredButtonIndex(currentMenuState.getHoveredButtonIndex() - 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_DOWN) {
            if (currentMenuState.getHoveredButtonIndex() < numberOfMenuButtons - 1) {
                currentMenuState.setHoveredButtonIndex(currentMenuState.getHoveredButtonIndex() + 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_ENTER) {
            if (currentMenuState.getHoveredButtonIndex() == 0) {
                if(!this.getStateManager().getGameState().getPlayer().getIsAlive()){
                    GameState newGameState = new GameState(this.getStateManager());
                    this.getStateManager().setGameState(newGameState);
                    this.getStateManager().setCurrentState(this.getStateManager().getGameState());
                } else {
                    this.getStateManager().getGameState().setIsPaused(false);
                    this.getStateManager().setCurrentState(this.getStateManager().getGameState());
                }
            }
            if (currentMenuState.getHoveredButtonIndex() == 1) {
                this.getStateManager().setCurrentState(this.getStateManager().getCreditsState());
            }
            if (currentMenuState.getHoveredButtonIndex() == 3) {
                this.getStateManager().setCurrentState(this.getStateManager().getExitGameState());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
