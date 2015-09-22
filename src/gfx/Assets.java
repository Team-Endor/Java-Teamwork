package gfx;

// This class is created to make loading of the images faster, "init" from Engine class is here

import java.awt.image.BufferedImage;

public class Assets {

    public static SpriteSheet   meteor;      // Every image has to have Field here!
    public static SpriteSheet   explosion;
    public static SpriteSheet   airplanes;
    public static SpriteSheet   fighterPlane;
    public static BufferedImage gameBackground;
    public static BufferedImage groundRocketFromLeft, groundRocketFromRight, groundRocketFromCenter;
    public static BufferedImage healthBarSeparator, healthBarFrame, healthBarFiller;
    public static BufferedImage mainMenuBackground;
    public static BufferedImage mainMenuPlay, mainMenuCredits, mainMenuSettings, mainMenuExit;
    public static BufferedImage mainMenuInactivePlay, mainMenuInactiveCredits, mainMenuInactiveSettings, mainMenuInactiveExit;

    public static void init() {
        meteor = new SpriteSheet(ImageLoader.loadImage("/images/meteor4.png"));
        explosion = new SpriteSheet(ImageLoader.loadImage("/images/explosion.png"));
        airplanes = new SpriteSheet(ImageLoader.loadImage("/images/airplanes-small.png"));
        fighterPlane = new SpriteSheet(ImageLoader.loadImage("/images/fighter-plane-small.png"));

        gameBackground = ImageLoader.loadImage("/images/Sky.jpg");

        SpriteSheet groundRocketsSheet = new SpriteSheet(ImageLoader.loadImage("/images/ground-rockets.png"));
        groundRocketFromRight = groundRocketsSheet.crop(0, 4, 34, 34);
        groundRocketFromCenter = groundRocketsSheet.crop(35, 0, 19, 39);
        groundRocketFromLeft = groundRocketsSheet.crop(55, 4, 34, 34);

        healthBarSeparator = ImageLoader.loadImage("/images/health-bar/health-bar-separator.png");
        healthBarFrame = ImageLoader.loadImage("/images/health-bar/health-bar-frame.png");
        healthBarFiller = ImageLoader.loadImage("/images/health-bar/health-bar-filler.png");

        // load main menu items
        mainMenuBackground = ImageLoader.loadImage("/images/main-menu/background.png");

        mainMenuPlay = ImageLoader.loadImage("/images/main-menu/play.png");
        mainMenuCredits = ImageLoader.loadImage("/images/main-menu/credits.png");
        mainMenuSettings = ImageLoader.loadImage("/images/main-menu/settings.png");
        mainMenuExit = ImageLoader.loadImage("/images/main-menu/exit.png");

        mainMenuInactivePlay = ImageLoader.loadImage("/images/main-menu/play-inactive.png");
        mainMenuInactiveCredits = ImageLoader.loadImage("/images/main-menu/credits-inactive.png");
        mainMenuInactiveSettings = ImageLoader.loadImage("/images/main-menu/settings-inactive.png");
        mainMenuInactiveExit = ImageLoader.loadImage("/images/main-menu/exit-inactive.png");

    }
}
