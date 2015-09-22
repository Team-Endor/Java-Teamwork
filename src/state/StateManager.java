package state;

import display.Display;
import game.Engine;
import state.inputHandlers.GameStateInputHandler;
import state.inputHandlers.InputHandler;
import state.inputHandlers.MenuStateInputHandler;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class StateManager {
    Map<State, InputHandler> inputHandlers = new HashMap<>();

    private Engine engine;

    private GameState gameState;
    private MenuState menuState;

    private GameStateInputHandler gameStateInputHandler;
    private MenuStateInputHandler menuStateInputHandler;

    private State currentState;

    private boolean shouldChangeState;

    public StateManager(Engine engine) {
        this.engine = engine;
        this.shouldChangeState = false;

        this.gameState = new GameState();
        this.menuState = new MenuState();

        this.gameStateInputHandler = new GameStateInputHandler(this);
        this.menuStateInputHandler = new MenuStateInputHandler(this);

        inputHandlers.put(this.gameState,this.gameStateInputHandler);
        inputHandlers.put(this.menuState,this.menuStateInputHandler);

        this.setCurrentState(this.gameState);
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public MenuState getMenuState() {
        return this.menuState;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(State stateToSet) {
        this.currentState = stateToSet;

        Display display = this.engine.getDisplay();
        KeyListener[] allKeyListeners = display.getCanvas().getKeyListeners();
        if (allKeyListeners != null) {
            for (KeyListener keyListener : allKeyListeners) {
                display.getCanvas().removeKeyListener(keyListener);
            }
        }

        display.getCanvas().addKeyListener(inputHandlers.get(stateToSet));
    }

    public boolean getShouldChangeState() {
        return this.shouldChangeState;
    }

    public void setShouldChangeState(boolean shouldChangeState) {
        this.shouldChangeState = shouldChangeState;
    }
}
