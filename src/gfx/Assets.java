package gfx;

// This class is created to make loading of the images faster, "init" from Game class is here

import java.awt.*;
import java.awt.image.BufferedImage;


public class Assets {

    public static SpriteSheet Meteor;      // Every image has to have Field here!
    public static SpriteSheet Explosion;
    public static SpriteSheet Airplanes;
    public static SpriteSheet FighterPlane;
    public static BufferedImage background;
    public static BufferedImage groundRocketFromLeft, groundRocketFromRight, groundRocketFromCenter;
    public static BufferedImage healthBarSeparator, healthBarFrame, healthBarFiller;

    public static void init() {
        Meteor = new SpriteSheet(ImageLoader.loadImage("/images/meteor4.png"));
        Explosion = new SpriteSheet(ImageLoader.loadImage("/images/explosion.png"));
        Airplanes = new SpriteSheet(ImageLoader.loadImage("/images/airplanes-small.png"));
        FighterPlane = new SpriteSheet(ImageLoader.loadImage("/images/fighter-plane-small.png"));

        background = ImageLoader.loadImage("/images/Sky.jpg");

        SpriteSheet groundRocketsSheet = new SpriteSheet(ImageLoader.loadImage("/images/ground-rockets.png"));
        groundRocketFromRight = groundRocketsSheet.crop(0, 4, 34, 34);
        groundRocketFromCenter = groundRocketsSheet.crop(35, 0, 19, 39);
        groundRocketFromLeft = groundRocketsSheet.crop(55, 4, 34, 34);

        healthBarSeparator = ImageLoader.loadImage("/images/health-bar/health-bar-separator.png");
        healthBarFrame = ImageLoader.loadImage("/images/health-bar/health-bar-frame.png");
        healthBarFiller = ImageLoader.loadImage("/images/health-bar/health-bar-filler.png");
    }
}
