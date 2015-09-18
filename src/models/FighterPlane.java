package models;

import java.awt.image.BufferedImage;

public class FighterPlane extends MovableObject {
    public FighterPlane(int x, int y, BufferedImage image, int velocity) {
        super(x, y, image, velocity);
    }

    @Override
    public void tick() {
        this.move(-this.getVelocity(), 0);
    }
}
