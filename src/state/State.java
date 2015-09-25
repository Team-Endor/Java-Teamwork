package state;

import Events.StateChangeEvent;
import interfaces.StateChangeListener;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class State implements KeyListener{
    private List<StateChangeListener> listeners = new ArrayList<>();

    public abstract void init();

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public synchronized void addStateChangeListener( StateChangeListener listener ) {
        this.listeners.add(listener);
    }

    public synchronized void removeStateChangeListener( StateChangeListener listener ) {
        this.listeners.remove(listener);
    }

    protected synchronized void fireStateChangeEvent(String targetState) {
        StateChangeEvent state = new StateChangeEvent( this, targetState );
        Iterator listenersIterator = this.listeners.iterator();
        while( listenersIterator.hasNext() ) {
            ((StateChangeListener)listenersIterator.next()).changeState(state);
        }
    }
}
