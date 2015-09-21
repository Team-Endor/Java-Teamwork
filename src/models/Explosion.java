package models;


import game.Game;
import gfx.Assets;
import interfaces.Killable;

import java.awt.image.BufferedImage;

public class Explosion extends Object implements Killable {

    private boolean isAlive;
    private              int animationFrame   = 0;
    private static final int EXPLOSION_WIDTH  = 256;
    private static final int EXPLOSION_HEIGHT = 256;

    public Explosion(int x, int y, BufferedImage image) {
        super(x - EXPLOSION_WIDTH / 4, y - EXPLOSION_HEIGHT / 4, image);
        this.isAlive = true;
    }

    @Override
    public void tick() {
        this.setY(this.getY() - Game.VELOCITY);
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
            this.setImage(Assets.Explosion.crop(offsetX, offsetY, EXPLOSION_WIDTH, EXPLOSION_HEIGHT));
            animationFrame++;
        }
    }

    @Override
    public boolean getIsAlive() {
        return this.isAlive;
    }
}
