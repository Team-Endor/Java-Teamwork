package gfx;

// This class is created to make loading of the images faster, "init" from Game class is here

import java.awt.image.BufferedImage;


public class Assets {
    private static final int width           = 95;
    private static final int height          = 130;
    private static final int AIRPLANE_WIDTH  = 146;
    private static final int AIRPLANE_HEIGHT = 39;

    public static BufferedImage player1, player2, airplane1;        // Every image has to have Field here!

    public static void init() {
        SpriteSheet spSheet = new SpriteSheet(ImageLoader.loadImage("/images/player.png"));

        player1 = spSheet.crop(0, 0, width, height);
        player2 = spSheet.crop(width * 2, height * 2, width, height);

        SpriteSheet airplaneSheet = new SpriteSheet(ImageLoader.loadImage("/images/airplanes-small.png"));
        airplane1 = airplaneSheet.crop(154, 49, AIRPLANE_WIDTH, AIRPLANE_HEIGHT);
    }
}
