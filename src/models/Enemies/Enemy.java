package models.Enemies;

import interfaces.Killable;
import models.MovableObject;
import state.GameState;

import java.awt.image.BufferedImage;

public abstract class Enemy extends MovableObject implements Killable{
    private boolean isAlive;

    protected Enemy(int x, int y, BufferedImage image, int velocity) {
        super(x, y, image, velocity);
        this.isAlive = true;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    @Override
    public void tick(int gameVelocity) {
        super.tick(gameVelocity);
        if(this.getY() < 0){
            this.setIsAlive(false);
        }
    }

    @Override
    public boolean getIsAlive() {
        return this.isAlive;
    }
}
