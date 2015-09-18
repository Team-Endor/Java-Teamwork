package models.factories;

import gfx.Assets;
import models.Airplane;
import models.FighterPlane;

import java.awt.image.BufferedImage;

public class AirplanesFactory {
    private static final BufferedImage AIRPLANE_IMAGE    = Assets.airplane1;
    private static final int           AIRPLANE_VELOCITY = 3;

    private static final BufferedImage FIGHTER_PLANE_IMAGE    = Assets.fighterPlane;
    private static final int           FIGHTER_PLANE_VELOCITY = 12;

    public static Airplane generateAirplane(int x, int y) {
        Airplane createdAirplane = new Airplane(x, y, AIRPLANE_IMAGE, AIRPLANE_VELOCITY);

        return createdAirplane;
    }

    public static FighterPlane generateFighterPlane(int x, int y) {
        FighterPlane createdFighterPlane = new FighterPlane(x, y, FIGHTER_PLANE_IMAGE, FIGHTER_PLANE_VELOCITY);

        return createdFighterPlane;
    }
}
