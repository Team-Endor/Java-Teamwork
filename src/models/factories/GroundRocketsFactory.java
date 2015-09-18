package models.factories;

import gfx.Assets;
import models.GroundRocket;

import java.awt.image.BufferedImage;

public class GroundRocketsFactory {
    private static final BufferedImage GROUND_ROCKET_FROM_LEFT_IMAGE   = Assets.groundRocketFromLeft;
    private static final BufferedImage GROUND_ROCKET_FROM_CENTER_IMAGE = Assets.groundRocketFromCenter;
    private static final BufferedImage GROUND_ROCKET_FROM_RIGHT_IMAGE  = Assets.groundRocketFromRight;
    private static final int           GROUND_ROCKETS_VELOCITY         = 12;

    private static final int GROUND_ROCKETS_Y            = 600;
    private static final int GROUND_ROCKET_FROM_LEFT_X   = 0;
    private static final int GROUND_ROCKET_FROM_CENTER_X = 390;
    private static final int GROUND_ROCKET_FROM_RIGHT_X  = 800;

    public static GroundRocket generateGroundRocket(GroundRocket.Position position) {
        if (position == GroundRocket.Position.FromLeft) {
            return new GroundRocket(
                    GROUND_ROCKET_FROM_LEFT_X,
                    GROUND_ROCKETS_Y,
                    GROUND_ROCKET_FROM_LEFT_IMAGE,
                    GROUND_ROCKETS_VELOCITY,
                    GroundRocket.Position.FromLeft);
        }
        if (position == GroundRocket.Position.FromCenter) {
            return new GroundRocket(GROUND_ROCKET_FROM_CENTER_X,
                    GROUND_ROCKETS_Y,
                    GROUND_ROCKET_FROM_CENTER_IMAGE,
                    GROUND_ROCKETS_VELOCITY,
                    GroundRocket.Position.FromCenter);
        }
        if (position == GroundRocket.Position.FromRight) {
            return new GroundRocket(
                    GROUND_ROCKET_FROM_RIGHT_X,
                    GROUND_ROCKETS_Y,
                    GROUND_ROCKET_FROM_RIGHT_IMAGE,
                    GROUND_ROCKETS_VELOCITY,
                    GroundRocket.Position.FromRight);
        }

        return null;
    }
}
