package models.factories;

import gfx.Assets;
import models.Airplane;

import java.awt.image.BufferedImage;

public class AirplaneFactory {
    private static final BufferedImage AIRPLANE_IMAGE    = Assets.airplane1;
    private static final int           AIRPLANE_VELOCITY = 10;

    public static Airplane generateAirplane(int x, int y) {
        Airplane createdAirplane = new Airplane(x, y, AIRPLANE_IMAGE,
                AIRPLANE_VELOCITY);

        return createdAirplane;
    }
}
