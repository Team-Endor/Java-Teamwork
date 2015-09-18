package game;

import display.Display;
import gfx.Assets;
import models.Airplane;
import models.FighterPlane;
import models.GroundRocket;
import models.Player;
import models.factories.AirplanesFactory;
import models.factories.GroundRocketsFactory;
import models.factories.PlayerFactory;
import physics.CollisionDetector;
import state.State;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public static final int    WINDOW_WIDTH  = 800;
    public static       int    WINDOW_HEIGHT = 600;
    public static       String WINDOW_TITLE  = "The Meteor";

    private Thread  thread;
    private boolean isRunning;
    private Display display;

    private BufferStrategy bs;
    private Graphics       g;

    private InputHandler ih;

    private State        currentState;
    private Background   background;

    private Player       player;
    private Airplane     testAirplane;
    private FighterPlane testFighterPlane;
    private GroundRocket testGroundRocketFromLeft, testGroundRocketFromCenter, testGroundRocketFromRight;

    public Game() {
        this.isRunning = false;
    }

    public void init() {
        this.display = new Display(this.WINDOW_TITLE, this.WINDOW_WIDTH, this.WINDOW_HEIGHT);

        Assets.init();
        this.background = new Background();
        this.player = PlayerFactory.generatePlayer();
        this.testAirplane = AirplanesFactory.generateAirplane(0, 400);
        this.testFighterPlane = AirplanesFactory.generateFighterPlane(800, 200);
        this.testGroundRocketFromLeft = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromLeft);
        this.testGroundRocketFromCenter = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromCenter);
        this.testGroundRocketFromRight = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromRight);

        this.ih = new InputHandler(this.display, this.player);

        // this.currentState = StateManager.getCurrentState();      // gets the current state; to set it use: StateManager.setCurrentState(new MenuState());

//        this.enemyRectangle = new Rectangle(500, 100, 20, 150);
    }

    private void tick() {
        this.background.tick();

        this.player.tick();
        this.testAirplane.tick();
        this.testFighterPlane.tick();
        this.testGroundRocketFromLeft.tick();
        this.testGroundRocketFromCenter.tick();
        this.testGroundRocketFromRight.tick();

        if (CollisionDetector.intersects(this.player.getBoundingBox(), this.testAirplane.getBoundingBox())) {
            this.player.setHealth(this.player.getHealth() - 50);
        }

        if (!this.player.isAlive()) {
            System.out.print("You died!");
            stop();
        }
    }

    private void render() {
        this.bs = display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);         // Creates two buffers
            this.bs = display.getCanvas().getBufferStrategy();
        }

        this.g = this.bs.getDrawGraphics();
        this.g.clearRect(0, 0, this.WINDOW_WIDTH, this.WINDOW_HEIGHT);              // clear the last image of the player

        // Begin drawing
        this.background.render(g);

        this.player.render(this.g);
        this.testAirplane.render(this.g);
        this.testFighterPlane.render(this.g);
        this.testGroundRocketFromLeft.render(this.g);
        this.testGroundRocketFromCenter.render(this.g);
        this.testGroundRocketFromRight.render(this.g);
//        this.g.setColor(Color.red);
//        this.g.fillRect(
//                this.enemyRectangle.x,
//                this.enemyRectangle.y,
//                this.enemyRectangle.width,
//                this.enemyRectangle.height);

        // End drawing

        this.bs.show();
        this.g.dispose();
    }

    @Override
    public void run() {
        init();

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
                tick();
                render();
                deltaTime--;
            }
        }

        stop();
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
