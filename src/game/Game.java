package game;

import display.Display;
import gfx.Assets;
import gfx.ImageLoader;
import models.Player;
import models.factories.PlayerFactory;
import physics.CollisionDetector;
import state.State;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private int    width;
    private int    height;
    private String title;

    private Thread  thread;
    private boolean isRunning;
    private Display display;

    private BufferStrategy bs;
    private Graphics       g;

    private InputHandler ih;

    private State     currentState;
    private Player    player;
    private Rectangle enemyRectangle;


    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isRunning = false;
    }

    public void init() {
        this.display = new Display(this.title, this.width, this.height);

        Assets.init();
        this.player = PlayerFactory.generatePlayer();

        this.ih = new InputHandler(this.display, this.player);

        // this.currentState = StateManager.getCurrentState();      // gets the current state; to set it use: StateManager.setCurrentState(new MenuState());

        this.enemyRectangle = new Rectangle(500, 100, 20, 150);
    }

    private void tick() {
        this.player.tick();
        if (CollisionDetector.intersects(this.player.getBoundingBox(), this.enemyRectangle)) {
            this.player.setHealth(this.player.getHealth() - 50);
        }

        if (!this.player.isAlive()) {
            System.out.print("You dead, bro!");
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
        this.g.clearRect(0, 0, this.width, this.height);              // clear the last image of the player

        BufferedImage img = ImageLoader.loadImage("/images/sky.jpg");
        g.drawImage(img, 0, 0, null);                               // Load Background image

        // Begin drawing

        this.player.render(this.g);
        this.g.setColor(Color.red);
        this.g.fillRect(
                this.enemyRectangle.x,
                this.enemyRectangle.y,
                this.enemyRectangle.width,
                this.enemyRectangle.height);

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
