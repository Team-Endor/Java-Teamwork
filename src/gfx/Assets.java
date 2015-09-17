package gfx;

// This class is created to make loading of the images faster, "init" from Game class is here

import java.awt.image.BufferedImage;


public class Assets {
    private static final int METEOR_WIDTH = 78;
    private static final int METEOR_HEIGHT = 67;
    private static final int AIRPLANE_WIDTH = 146;
    private static final int AIRPLANE_HEIGHT = 39;

    public static BufferedImage playerMeteor, airplane1;        // Every image has to have Field here!
	public static BufferedImage background;

    public static void init() {
        SpriteSheet meteorSheet = new SpriteSheet(ImageLoader.loadImage("/images/asteroids.png"));
        playerMeteor = meteorSheet.crop(10, 246, METEOR_WIDTH, METEOR_HEIGHT);

        SpriteSheet airplaneSheet = new SpriteSheet(ImageLoader.loadImage("/images/airplanes-small.png"));
        airplane1 = airplaneSheet.crop(154, 49, AIRPLANE_WIDTH, AIRPLANE_HEIGHT);
        
        background = ImageLoader.loadImage("/images/Sky.Original.jpg");
    }
}
