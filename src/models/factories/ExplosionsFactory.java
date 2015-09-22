package models.factories;

import gfx.Assets;
import models.Explosion;

import java.awt.image.BufferedImage;

public class ExplosionsFactory {
    private static final int           EXPLOSION_WIDTH  = 256;
    private static final int           EXPLOSION_HEIGHT = 256;
    private static final BufferedImage EXPLOSION_IMAGE  = Assets.explosion.crop(0,
            0,
            EXPLOSION_WIDTH,
            EXPLOSION_HEIGHT);

    public static Explosion createExplosion(int x, int y) {
        Explosion explosion = new Explosion(x, y, EXPLOSION_IMAGE);

        return explosion;
    }
}
