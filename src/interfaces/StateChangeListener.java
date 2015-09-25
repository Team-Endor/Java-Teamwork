package interfaces;

import Events.StateChangeEvent;

public interface StateChangeListener {
    void changeState(StateChangeEvent event);
}
