package game;

import display.Display;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private int width;
    private int height;
    private String title;

    private Thread thread;
    private boolean isRunning;
    private Display display;

    private BufferStrategy bs;
    private java.awt.Graphics g;

    private SpriteSheet spriteSheet;
    private int spriteSheetWidth;
    private int spriteSheetHeight;
    private int spriteSheetPos;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.isRunning = false;
    }

    public void init() {
        display = new Display(this.title, this.width, this.height);
        this.spriteSheet = new SpriteSheet(ImageLoader.loadImage("/images/player.png"));
        this.spriteSheetWidth = 95;
        this.spriteSheetHeight = 130;
    }

    private void tick() {

    }

    private void render() {
        this.bs = display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(2);   // Creates two buffers
            this.bs = display.getCanvas().getBufferStrategy();
        }

        this.g = this.bs.getDrawGraphics();

        BufferedImage img = ImageLoader.loadImage("/images/sky.jpg");
        g.drawImage(img, 0, 0, null);                           // Load Background image


        g.drawImage(this.spriteSheet.crop(                      // Crop Spritesheet
                spriteSheetWidth * this.spriteSheetPos,         // This is called every time in the render
                0,
                spriteSheetWidth,
                spriteSheetHeight), 150, 200, null);


        this.bs.show();
        this.g.dispose();
    }

    @Override
    public void run() {
        init();

        while (isRunning) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tick();
            render();
            this.spriteSheetPos++;
            if (this.spriteSheetPos >= 7 ){                     // this needs to revert otherwise outside of boundaries
                this.spriteSheetPos = 0;
            }
        }

        stop();
    }

    public synchronized void start() {
        if (this.isRunning) {                         // Check whether the game is already running, not to create a second thread
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
