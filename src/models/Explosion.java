package models;

import gfx.Assets;
import interfaces.Killable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Explosion extends MovableObject implements Killable {
    private boolean isAlive;
    private              int animationFrame   = 0;
    private static final int EXPLOSION_VELOCITY = 0;
    private static final int EXPLOSION_WIDTH  = 256;
    private static final int EXPLOSION_HEIGHT = 256;

    public Explosion(int x, int y, BufferedImage image) {
        super(x - EXPLOSION_WIDTH / 4, y - EXPLOSION_HEIGHT / 4, image,EXPLOSION_VELOCITY);
        this.isAlive = true;
    }

    @Override
    public boolean getIsAlive() {
        return this.isAlive;
    }

    @Override
    public void render(Graphics graphics) {
        if (animationFrame == 32) {
            this.isAlive = false;
        } else {
            int offsetX = (animationFrame % 8) * EXPLOSION_WIDTH;
            int offsetY = 0;
            if (animationFrame > 23) {
                offsetY = 3 * EXPLOSION_HEIGHT;
            } else if (animationFrame > 15) {
                offsetY = 2 * EXPLOSION_HEIGHT;
            } else if (animationFrame > 7) {
                offsetY = EXPLOSION_HEIGHT;
            }
            this.setImage(Assets.explosion.crop(offsetX, offsetY, EXPLOSION_WIDTH, EXPLOSION_HEIGHT));
            animationFrame++;
        }
        super.render(graphics);
    }
}
