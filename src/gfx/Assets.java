package gfx;

import java.awt.image.BufferedImage;


public class Assets {

    public static SpriteSheet Meteor;      // Every image has to have Field here!
    public static SpriteSheet Explosion;
    public static SpriteSheet Airplanes;
    public static SpriteSheet FighterPlane;
    public static BufferedImage background;
    public static BufferedImage healthbar;
    public static BufferedImage healthbarBorder;
    public static BufferedImage GameOver;
    public static BufferedImage Menu;
    public static BufferedImage exitButton;
    public static BufferedImage playButton;
    public static BufferedImage groundRocketFromLeft, groundRocketFromRight, groundRocketFromCenter;

    public static void init() {
        Meteor = new SpriteSheet(ImageLoader.loadImage("/images/meteor.png"));
        Explosion = new SpriteSheet(ImageLoader.loadImage("/images/explosion.png"));
        Airplanes = new SpriteSheet(ImageLoader.loadImage("/images/airplanes-small.png"));
        FighterPlane = new SpriteSheet(ImageLoader.loadImage("/images/fighter-plane-small.png"));

        background = ImageLoader.loadImage("/images/Sky.jpg");
        healthbar = ImageLoader.loadImage("/images/HealthBar.png");
        healthbarBorder = ImageLoader.loadImage("/images/HealthbarBorder.png");

        GameOver = ImageLoader.loadImage("/images/GameOver.png");
        Menu = ImageLoader.loadImage("/images/menu.png");

        playButton = ImageLoader.loadImage("/images/play.png");
        exitButton = ImageLoader.loadImage("/images/exit.png");

        SpriteSheet groundRocketsSheet = new SpriteSheet(ImageLoader.loadImage("/images/ground-rockets.png"));
        groundRocketFromRight = groundRocketsSheet.crop(0, 4, 34, 34);
        groundRocketFromCenter = groundRocketsSheet.crop(35, 0, 19, 39);
        groundRocketFromLeft = groundRocketsSheet.crop(55, 4, 34, 34);
    }
}
