package state;

// Checks currently in what state we are and what it has to be done!
public class StateManager {

    public static State currentState;

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State stateToSet) {
        currentState = stateToSet;
    }
}
