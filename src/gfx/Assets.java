package gfx;

// This class is created to make loading of the images faster, "init" from Game class is here

import java.awt.image.BufferedImage;


public class Assets {
    private static final int METEOR_WIDTH    = 78;
    private static final int METEOR_HEIGHT   = 67;
    private static final int AIRPLANE_WIDTH  = 146;
    private static final int AIRPLANE_HEIGHT = 39;

    public static BufferedImage playerMeteor, airplane1;        // Every image has to have Field here!
    public static BufferedImage background;
    public static BufferedImage fighterPlane;
    public static BufferedImage groundRocketFromLeft, groundRocketFromRight, groundRocketFromCenter;

    public static void init() {
        SpriteSheet meteorSheet = new SpriteSheet(ImageLoader.loadImage("/images/asteroids.png"));
        playerMeteor = meteorSheet.crop(10, 246, METEOR_WIDTH, METEOR_HEIGHT);

        SpriteSheet airplaneSheet = new SpriteSheet(ImageLoader.loadImage("/images/airplanes-small.png"));
        airplane1 = airplaneSheet.crop(154, 49, AIRPLANE_WIDTH, AIRPLANE_HEIGHT);

        background = ImageLoader.loadImage("/images/Sky.jpg");

        fighterPlane = ImageLoader.loadImage("/images/fighter-plane-small.png");

        SpriteSheet groundRocketsSheet = new SpriteSheet(ImageLoader.loadImage("/images/ground-rockets.png"));
        groundRocketFromRight = groundRocketsSheet.crop(0, 4, 34, 34);
        groundRocketFromCenter = groundRocketsSheet.crop(35, 0, 19, 39);
        groundRocketFromLeft = groundRocketsSheet.crop(55, 4, 34, 34);
    }
}
