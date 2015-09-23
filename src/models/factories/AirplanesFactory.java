package models.factories;

import gfx.Assets;
import models.enemies.FighterPlane;
import models.enemies.PassangerPlane;
import state.GameState;

import java.awt.image.BufferedImage;
import java.util.Random;

public class AirplanesFactory {
    private static Random random = new Random();

    private static final int AIRPLANE_SPAWN_X = 600;
    private static final int AIRPLANE_SPAWN_Y = GameState.BOARD_HEIGHT;

    private static final int AIRPLANE_WIDTH  = 150;
    private static final int AIRPLANE_HEIGHT = 40;

    private static final int FIGHTERPLANE_WIDTH  = 105;
    private static final int FIGHTERPLANE_HEIGHT = 27;

    private static final BufferedImage AIRPLANE1LEFT_IMAGE = Assets.Airplanes.crop(0,
            0,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);
    private static final BufferedImage AIRPLANE2LEFT_IMAGE = Assets.Airplanes.crop(AIRPLANE_WIDTH,
            0,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);
    private static final BufferedImage AIRPLANE3LEFT_IMAGE = Assets.Airplanes.crop(0,
            AIRPLANE_HEIGHT,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);
    private static final BufferedImage AIRPLANE4LEFT_IMAGE = Assets.Airplanes.crop(AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);

    private static final BufferedImage AIRPLANE1RIGHT_IMAGE = Assets.Airplanes.crop(AIRPLANE_WIDTH * 2,
            0,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);
    private static final BufferedImage AIRPLANE2RIGHT_IMAGE = Assets.Airplanes.crop(AIRPLANE_WIDTH * 3,
            0,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);
    private static final BufferedImage AIRPLANE3RIGHT_IMAGE = Assets.Airplanes.crop(AIRPLANE_WIDTH * 2,
            AIRPLANE_HEIGHT,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);
    private static final BufferedImage AIRPLANE4RIGHT_IMAGE = Assets.Airplanes.crop(AIRPLANE_WIDTH * 3,
            AIRPLANE_HEIGHT,
            AIRPLANE_WIDTH,
            AIRPLANE_HEIGHT);

    private static final int AIRPLANE_VELOCITY = 3;

    private static final BufferedImage FIGHTER_PLANE_LEFT_IMAGE  = Assets.fighterPlane.crop(0,
            0,
            FIGHTERPLANE_WIDTH,
            FIGHTERPLANE_HEIGHT);
    private static final BufferedImage FIGHTER_PLANE_RIGHT_IMAGE = Assets.fighterPlane.crop(FIGHTERPLANE_WIDTH,
            0,
            FIGHTERPLANE_WIDTH,
            FIGHTERPLANE_HEIGHT);
    private static final int           FIGHTER_PLANE_VELOCITY    = 12;

    public static PassangerPlane generateAirplane() {

        int image = random.nextInt(8);
        BufferedImage airplaneImage = AIRPLANE1RIGHT_IMAGE;
        switch (image) {
            case 0:
                airplaneImage = AIRPLANE1LEFT_IMAGE;
                break;
            case 1:
                airplaneImage = AIRPLANE2LEFT_IMAGE;
                break;
            case 2:
                airplaneImage = AIRPLANE3LEFT_IMAGE;
                break;
            case 3:
                airplaneImage = AIRPLANE4LEFT_IMAGE;
                break;
            case 4:
                airplaneImage = AIRPLANE1RIGHT_IMAGE;
                break;
            case 5:
                airplaneImage = AIRPLANE2RIGHT_IMAGE;
                break;
            case 6:
                airplaneImage = AIRPLANE3RIGHT_IMAGE;
                break;
            case 7:
                airplaneImage = AIRPLANE4RIGHT_IMAGE;
                break;
        }

        int y = random.nextInt(AIRPLANE_SPAWN_Y) + AIRPLANE_SPAWN_Y;
        int x = 0;
        if (image < 4) {
            x = random.nextInt(AIRPLANE_SPAWN_X / 2) + AIRPLANE_SPAWN_X;
        } else {
            x = random.nextInt(AIRPLANE_SPAWN_X) - AIRPLANE_SPAWN_X / 2;
        }

        PassangerPlane createdAirplane = new PassangerPlane(x, y, airplaneImage, AIRPLANE_VELOCITY);
        if (image < 4) {
            createdAirplane.setIsMovingLeft(true);
        } else {
            createdAirplane.setIsMovingRight(true);
        }

        return createdAirplane;
    }

    public static FighterPlane generateFighterPlane() {

        int image = random.nextInt(2);
        BufferedImage airplaneImage = FIGHTER_PLANE_LEFT_IMAGE;
        switch (image) {
            case 0:
                airplaneImage = FIGHTER_PLANE_LEFT_IMAGE;
                break;
            case 1:
                airplaneImage = FIGHTER_PLANE_RIGHT_IMAGE;
                break;
        }

        int y = random.nextInt(AIRPLANE_SPAWN_Y) + AIRPLANE_SPAWN_Y;
        int x = 0;
        if (image < 1) {
            x = random.nextInt(AIRPLANE_SPAWN_X / 2) + AIRPLANE_SPAWN_X;

        } else {
            x = random.nextInt(AIRPLANE_SPAWN_X) - AIRPLANE_SPAWN_X / 2;
        }

        FighterPlane createdFighterPlane = new FighterPlane(x, y, airplaneImage, FIGHTER_PLANE_VELOCITY);

        if (image < 1) {
            createdFighterPlane.setIsMovingLeft(true);
        } else {
            createdFighterPlane.setIsMovingRight(true);
        }

        return createdFighterPlane;
    }
}
