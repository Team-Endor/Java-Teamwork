package state.inputHandlers;

import models.Player;
import state.StateManager;

import java.awt.event.KeyEvent;

public class GameStateInputHandler extends InputHandler {
    private int    keyCode;

    public GameStateInputHandler(StateManager stateManager) {
        super(stateManager);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyCode = e.getKeyCode();
        Player player = super.getStateManager().getGameState().getPlayer();

        if (this.keyCode == KeyEvent.VK_UP) {
            player.setIsMovingUp(true);
        }
        if (this.keyCode == KeyEvent.VK_DOWN) {
            player.setIsMovingDown(true);
        }
        if (this.keyCode == KeyEvent.VK_LEFT) {
            player.setIsMovingLeft(true);
        }
        if (this.keyCode == KeyEvent.VK_RIGHT) {
            player.setIsMovingRight(true);
        }
        if (this.keyCode == KeyEvent.VK_ESCAPE) {
            player.setIsMovingUp(false);
            player.setIsMovingDown(false);
            player.setIsMovingLeft(false);
            player.setIsMovingRight(false);

            this.getStateManager().getGameState().setIsPaused(true);
            this.getStateManager().setCurrentState(this.getStateManager().getMenuState());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();
        Player player = super.getStateManager().getGameState().getPlayer();

        if (this.keyCode == KeyEvent.VK_UP) {
            player.setIsMovingUp(false);
        }
        if (this.keyCode == KeyEvent.VK_DOWN) {
            player.setIsMovingDown(false);
        }
        if (this.keyCode == KeyEvent.VK_LEFT) {
            player.setIsMovingLeft(false);
        }
        if (this.keyCode == KeyEvent.VK_RIGHT) {
            player.setIsMovingRight(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
