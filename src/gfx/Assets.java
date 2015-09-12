package gfx;

// This class is created to make loading of the images faster, "init" from Game class is here

import java.awt.image.BufferedImage;
import java.util.HashMap;


public class Assets {
    private static final int width = 95;
    private static final int height = 130;

    public static BufferedImage player1, player2;        // Every image has to have Field here!

    public static void init() {                           // No constructor they are all static!
        SpriteSheet spSheet =
                new SpriteSheet(ImageLoader.loadImage("/images/player.png"));

        player1 = spSheet.crop(0, 0, width, height);
        player2 = spSheet.crop(width * 2, height * 2, width, height);
    }
}
