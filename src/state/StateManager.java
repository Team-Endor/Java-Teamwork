package state;

import Constants.Constants;
import java.util.HashMap;
import java.util.Map;

public class StateManager {
    Map<String, State> states = new HashMap<>();


    private State currentState;

    public StateManager() {
        this.seedStates();
        this.setCurrentState(Constants.MENU_STATE);
    }

    private void seedStates(){
        this.states.put(Constants.GAME_STATE,new GameState());
        this.states.put(Constants.MENU_STATE,new MenuState());
        this.states.put(Constants.GAME_WON_STATE,new GameWonState());
        this.states.put(Constants.GAME_LOST_STATE,new GameLostState());
        this.states.put(Constants.CREDITS_STATE, new CreditsState());
    }

    public Iterable<State> getStates(){
        return this.states.values();
    }

    public State getState(String state){
        return this.states.get(state);
    }

    public void restartGame(){
        this.states.put(Constants.GAME_STATE, new GameState());
    }

    public State getCurrentState(){
        return this.currentState;
    }

    public void setCurrentState(String stateToSet) {
        this.currentState = this.states.get(stateToSet);
    }
}
