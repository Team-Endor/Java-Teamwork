package game;

import display.Display;
import gfx.Assets;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Engine implements Runnable {
    public static final int    WINDOW_WIDTH  = 800;
    public static final int    WINDOW_HEIGHT = 600;
    public static       String WINDOW_TITLE  = "Team Endor Java Teamwork - The Meteor";

    private Thread  thread;
    private boolean isRunning;
    private Display display;

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
            // Creates two buffers
            this.display.getCanvas().createBufferStrategy(2);
            this.bufferStrategy = display.getCanvas().getBufferStrategy();
        }

        Assets.init();

        this.stateManager = new StateManager(this);
    }

    private void tick() {
        this.currentState = stateManager.getCurrentState();
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
        double timePerTick = 1_000_000_000.0 / fps;     // how much milliseconds it have to wait before a tick; 1 000 000 000 = 1 sec
        double deltaTime = 0;                           // how much time passed  between now and the last time it ticked
        long now;
        long lastTimeTicked = System.nanoTime();        // the value of current time (years/months/days/hours/minutes/seconds) in nano seconds

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

    public synchronized void start() {
        if (this.isRunning) {                        // Check whether the game is already running, not to create a second thread
            return;
        }
        this.isRunning = true;
        this.thread = new Thread(this);              // on this thread the game runs
        this.thread.start();
    }

    public synchronized void stop() {
        if (!this.isRunning) {                      // Check whether the game is already stopped, not to create an exception
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
