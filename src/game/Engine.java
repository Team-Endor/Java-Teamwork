package game;

import Constants.Constants;
import Events.StateChangeEvent;
import display.Display;
import gfx.Assets;
import interfaces.StateChangeListener;
import state.GameEndState;
import state.GameState;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Engine implements Runnable, StateChangeListener {
    public static final int    WINDOW_WIDTH  = 800;
    public static final int    WINDOW_HEIGHT = 600;
    public static       String WINDOW_TITLE  = "Team Endor Java Teamwork - The Meteor";

    private Thread  thread;
    private Display display;
    private boolean isRunning;

    private BufferStrategy bufferStrategy;
    private Graphics       graphics;

    private StateManager stateManager;
    private State        currentState;

    public Engine() {
        this.isRunning = false;
    }

    public Display getDisplay() {
        return this.display;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private void init() {
        this.display = new Display(this.WINDOW_TITLE, this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
        this.bufferStrategy = display.getCanvas().getBufferStrategy();

        if (this.bufferStrategy == null) {
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy = display.getCanvas().getBufferStrategy();
        }

        Assets.init();
        this.stateManager = new StateManager();
        this.currentState = stateManager.getCurrentState();
        this.display.getCanvas().addKeyListener(this.currentState);
        this.attachEvents();
    }

    private void tick() {

        this.currentState.tick();
    }

    private void render() {

        this.graphics = this.bufferStrategy.getDrawGraphics();

        // clear the last image of the player
        this.graphics.clearRect(0, 0, this.WINDOW_WIDTH, this.WINDOW_HEIGHT);

        // Begin drawing
        this.currentState.render(this.graphics);

        // End drawing
        this.bufferStrategy.show();
    }

    @Override
    public void run() {
        this.init();
        this.display.getCanvas().requestFocus();

        // we make settings, so the "ticks" are equal in time on all computers/ processors
        int fps = 30;                                   // frames per second
        double timePerTick = 1_000_000_000.0 / fps;     // how much milliseconds it have to wait before a tick; 1 000
        // 000 000 = 1 sec
        double deltaTime = 0;                           // how much time passed  between now and the last time it ticked
        long now;
        long lastTimeTicked = System.nanoTime();        // the value of current time
        // (years/months/days/hours/minutes/seconds) in nano seconds

        while (isRunning) {
            now = System.nanoTime();
            deltaTime += (now - lastTimeTicked) / timePerTick;
            lastTimeTicked = now;

            if (deltaTime >= 1) {
                this.tick();
                this.render();
                deltaTime--;
            }
        }

        this.stop();
        this.display.getFrame().dispose();
    }

    @Override
    public void changeState(StateChangeEvent event) {
        if(event.getTargetState().equals(Constants.EXIT_GAME_STATE)){
            this.setIsRunning(false);
            return;
        }
        if(event.getSource() instanceof GameEndState){
            this.stateManager.restartGame();
            this.stateManager.getState(Constants.GAME_STATE).addStateChangeListener(this);
        }

        //detaching old listener
        this.display.getCanvas().removeKeyListener(this.currentState);

        //setting state
        this.stateManager.setCurrentState(event.getTargetState());
        this.currentState = this.stateManager.getCurrentState();

        //attaching new listener
        this.display.getCanvas().addKeyListener(this.stateManager.getCurrentState());
    }

    private void attachEvents(){
        for (State state : this.stateManager.getStates()) {
            state.addStateChangeListener(this);
        }
    }

    public synchronized void start() {
        if (this.isRunning) {
            return;
        }
        this.isRunning = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public synchronized void stop() {
        if (!this.isRunning) {
            return;
        }
        this.isRunning = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
