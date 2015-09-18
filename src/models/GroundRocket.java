package models;

import java.awt.image.BufferedImage;

public class GroundRocket extends MovableObject {
    private Position position;

    public GroundRocket(int x, int y, BufferedImage image, int velocity, Position position) {
        super(x, y, image, velocity);
        this.position = position;
    }

    @Override
    public void tick() {
        if (this.position == Position.FromLeft) {
            this.move(this.getVelocity(), -this.getVelocity());
        }
        if (this.position == Position.FromCenter) {
            this.move(0, -this.getVelocity());
        }
        if (this.position == Position.FromRight) {
            this.move(-this.getVelocity(), -this.getVelocity());
        }
    }

    public enum Position {
        FromLeft,
        FromCenter,
        FromRight
    }
}
