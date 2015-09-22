package state.inputHandlers;

import models.Player.Player;
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
        if (this.keyCode == KeyEvent.VK_ESCAPE){
            this.getStateManager().setCurrentState(this.getStateManager().getMenuState());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
