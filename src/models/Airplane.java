package models;

import interfaces.Killable;

import java.awt.image.BufferedImage;

public abstract class Airplane extends MovableObject implements Killable{
    private boolean isAlive;

    protected Airplane(int x, int y, BufferedImage image, int velocity) {
        super(x, y, image, velocity);
        this.isAlive = true;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public boolean getIsAlive() {
        return this.isAlive;
    }
}
