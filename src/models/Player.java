package models;

import game.Engine;
import gfx.Assets;
import interfaces.Killable;

import java.awt.image.BufferedImage;

public class Player extends MovableObject implements Killable {
    private        boolean isAlive;
    private static Player  instance;
    private        int     health;
    private        int     totalHealth;
    private static final int METEOR_WIDTH   = 80;
    private static final int METEOR_HEIGHT  = 110;
    private static       int animationFrame = 0;

    private Player(int x, int y, BufferedImage image, int velocity, int totalHealth) {
        super(x, y, image, velocity);

        this.setTotalHealth(totalHealth);
        this.setCurrentHealth(totalHealth);
        this.isAlive = true;
    }

    public int getCurrentHealth() {
        return this.health;
    }

    public void setCurrentHealth(int currentHealth) {
        this.health = currentHealth;
    }

    public int getTotalHealth() {
        return this.totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }

    public static Player createInstance(int x, int y, BufferedImage image, int velocity, int health) {
        if (instance == null) {
            instance = new Player(x, y, image, velocity, health);
        }
        return instance;
    }

    @Override
    public void tick() {

        if (animationFrame < 5) {
            this.setImage(Assets.Meteor.crop(0, 0, METEOR_WIDTH, METEOR_HEIGHT));
        } else {
            this.setImage(Assets.Meteor.crop(METEOR_WIDTH, 0, METEOR_WIDTH, METEOR_HEIGHT));
        }
        animationFrame++;
        if (animationFrame == 10) {
            animationFrame = 0;
        }

        if (this.isMovingUp()) {
            this.move(0, -this.getVelocity());
        }

        if (this.isMovingDown()) {
            this.move(0, this.getVelocity());
        }

        if (this.isMovingLeft()) {
            if (this.getX() - this.getVelocity() >= 0) {
                this.move(-this.getVelocity(), 0);
            }
        }

        if (this.isMovingRight()) {
            if (this.getX() + this.getVelocity() < Engine.WINDOW_WIDTH - this.getImage().getWidth()) {
                this.move(this.getVelocity(), 0);
            }
        }
    }


    @Override
    public boolean getIsAlive() {
        return this.health > 0;
    }
}
