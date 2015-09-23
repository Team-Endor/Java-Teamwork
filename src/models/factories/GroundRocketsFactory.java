package models.factories;

import gfx.Assets;
import models.enemies.GroundRocket;

import java.awt.image.BufferedImage;
import java.util.Random;

public class GroundRocketsFactory {
    private static       Random        random                          = new Random();
    private static final BufferedImage GROUND_ROCKET_FROM_LEFT_IMAGE   = Assets.groundRocketFromLeft;
    private static final BufferedImage GROUND_ROCKET_FROM_CENTER_IMAGE = Assets.groundRocketFromCenter;
    private static final BufferedImage GROUND_ROCKET_FROM_RIGHT_IMAGE  = Assets.groundRocketFromRight;
    private static final int           GROUND_ROCKETS_VELOCITY         = 12;

    private static final int GROUND_ROCKETS_Y            = 600;
    private static final int GROUND_ROCKET_FROM_CENTER_X = 390;

    public static GroundRocket generateGroundRocket() {
        int position = random.nextInt(3);
        if (position == 0) {
            int start = random.nextInt(GROUND_ROCKET_FROM_CENTER_X);
            GroundRocket rocket = new GroundRocket(start,
                    GROUND_ROCKETS_Y,
                    GROUND_ROCKET_FROM_LEFT_IMAGE,
                    GROUND_ROCKETS_VELOCITY);
            rocket.setIsMovingRight(true);
            rocket.setIsMovingUp(true);

            return rocket;
        }
        if (position == 1) {
            int start = random.nextInt(GROUND_ROCKET_FROM_CENTER_X) + GROUND_ROCKET_FROM_CENTER_X / 2;
            GroundRocket rocket = new GroundRocket(start,
                    GROUND_ROCKETS_Y,
                    GROUND_ROCKET_FROM_CENTER_IMAGE,
                    GROUND_ROCKETS_VELOCITY);
            rocket.setIsMovingUp(true);

            return rocket;
        }
        if (position == 2) {
            int start = random.nextInt(GROUND_ROCKET_FROM_CENTER_X) + GROUND_ROCKET_FROM_CENTER_X;
            GroundRocket rocket = new GroundRocket(start,
                    GROUND_ROCKETS_Y,
                    GROUND_ROCKET_FROM_RIGHT_IMAGE,
                    GROUND_ROCKETS_VELOCITY);
            rocket.setIsMovingLeft(true);
            rocket.setIsMovingUp(true);

            return rocket;
        }

        return null;
    }
}
