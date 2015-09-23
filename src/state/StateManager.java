package state;

import display.Display;
import game.Engine;
import state.inputHandlers.*;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class StateManager {
    Map<String, InputHandler> inputHandlers = new HashMap<>();

    private Engine engine;

    private GameState     gameState;
    private MenuState     menuState;
    private GameWonState  gameWonState;
    private GameLostState gameLostState;
    private CreditsState  creditsState;
    private ExitGameState exitGameState;

    private GameStateInputHandler     gameStateInputHandler;
    private MenuStateInputHandler     menuStateInputHandler;
    private GameEndScreenInputHandler gameEndStateInputHandler;
    private CreditsStateInputHandler  creditsStateInputHandler;

    private State currentState;

    public StateManager(Engine engine) {
        this.engine = engine;

        this.gameState = new GameState(this);
        this.menuState = new MenuState();
        this.gameWonState = new GameWonState();
        this.gameLostState = new GameLostState();
        this.creditsState = new CreditsState();
        this.exitGameState = new ExitGameState(this.engine);

        this.gameStateInputHandler = new GameStateInputHandler(this);
        this.menuStateInputHandler = new MenuStateInputHandler(this);
        this.gameEndStateInputHandler = new GameEndScreenInputHandler(this);
        this.creditsStateInputHandler = new CreditsStateInputHandler(this);

        this.inputHandlers.put("GameState", this.gameStateInputHandler);
        this.inputHandlers.put("MenuState", this.menuStateInputHandler);
        this.inputHandlers.put("ExitGameState", null);
        this.inputHandlers.put("GameWonState", this.gameEndStateInputHandler);
        this.inputHandlers.put("GameLostState", this.gameEndStateInputHandler);
        this.inputHandlers.put("CreditsState", this.creditsStateInputHandler);

        this.setCurrentState(this.menuState);
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public ExitGameState getExitGameState() {
        return this.exitGameState;
    }

    public MenuState getMenuState() {
        return this.menuState;
    }

    public CreditsState getCreditsState() {
        return this.creditsState;
    }

    public GameWonState getGameWonState() {
        return gameWonState;
    }

    public GameLostState getGameLostState() {
        return gameLostState;
    }

    public State getCurrentState() {
        return this.currentState;
    }

    public Engine getEngine() {
        return this.engine;
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

        display.getCanvas().addKeyListener(this.inputHandlers.get(stateToSet.getClass().getSimpleName()));
    }
}
