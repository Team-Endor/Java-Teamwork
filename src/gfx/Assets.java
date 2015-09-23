package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static SpriteSheet   meteor;      // Every image has to have Field here!
    public static SpriteSheet   explosion;
    public static SpriteSheet   Airplanes;
    public static SpriteSheet   fighterPlane;
    public static BufferedImage backgroundSpace, backgroundSpaceAtmosphere, backgroundAtmosphere, backgroundGround;
    public static BufferedImage healthbar;
    public static BufferedImage healthbarBorder;
    public static BufferedImage groundRocketFromLeft, groundRocketFromRight, groundRocketFromCenter;

    public static BufferedImage mainMenuBackground;
    public static BufferedImage mainMenuPlay, mainMenuCredits, mainMenuSettings, mainMenuExit;
    public static BufferedImage mainMenuInactivePlay, mainMenuInactiveCredits, mainMenuInactiveSettings,
            mainMenuInactiveExit;

    public static BufferedImage gameWonBackground, gameLostBackground;
    public static BufferedImage endGamePlayAgain, endGameBackToMenu, endGameExit;
    public static BufferedImage endGameInactivePlayAgain, endGameInactiveBackToMenu, endGameInactiveExit;

    public static BufferedImage creditsBackground;

    public static void init() {
        meteor = new SpriteSheet(ImageLoader.loadImage("/images/meteor.png"));
        explosion = new SpriteSheet(ImageLoader.loadImage("/images/explosion.png"));
        Airplanes = new SpriteSheet(ImageLoader.loadImage("/images/airplanes-small.png"));
        fighterPlane = new SpriteSheet(ImageLoader.loadImage("/images/fighter-plane-small.png"));

        backgroundSpace = ImageLoader.loadImage("/images/backgrounds/1.png");
        backgroundSpaceAtmosphere = ImageLoader.loadImage("/images/backgrounds/2.png");
        backgroundAtmosphere = ImageLoader.loadImage("/images/backgrounds/3.jpg");
        backgroundGround = ImageLoader.loadImage("/images/backgrounds/4.jpg");

        healthbar = ImageLoader.loadImage("/images/HealthBar.png");
        healthbarBorder = ImageLoader.loadImage("/images/HealthbarBorder.png");

        SpriteSheet groundRocketsSheet = new SpriteSheet(ImageLoader.loadImage("/images/ground-rockets.png"));
        groundRocketFromRight = groundRocketsSheet.crop(0, 4, 34, 34);
        groundRocketFromCenter = groundRocketsSheet.crop(35, 0, 19, 39);
        groundRocketFromLeft = groundRocketsSheet.crop(55, 4, 34, 34);

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

        // load gameEnd screen elements
        gameLostBackground = ImageLoader.loadImage("/images/end-game-screen/gamelost-background.png");
        gameWonBackground = ImageLoader.loadImage("/images/end-game-screen/gamewon-background.png");

        endGamePlayAgain = ImageLoader.loadImage("/images/end-game-screen/endGamePlayAgain.png");
        endGameBackToMenu = ImageLoader.loadImage("/images/end-game-screen/endGameBackToMenu.png");
        endGameExit = ImageLoader.loadImage("/images/end-game-screen/endGameExit.png");

        endGameInactivePlayAgain = ImageLoader.loadImage("/images/end-game-screen/endGamePlayAgainInactive.png");
        endGameInactiveBackToMenu = ImageLoader.loadImage("/images/end-game-screen/endGameBackToMenuInactive.png");
        endGameInactiveExit = ImageLoader.loadImage("/images/end-game-screen/endGameExitInactive.png");

        //load credits background
        creditsBackground = ImageLoader.loadImage("/images/Credits.jpg");
    }
}
