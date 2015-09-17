package models;

import java.awt.image.BufferedImage;

public class Airplane extends MovableObject {
    public Airplane(int x, int y, BufferedImage image, int velocity) {
        super(x, y, image, velocity);
    }

    @Override
    public void tick() {
        this.move(3, -1);
    }
}
