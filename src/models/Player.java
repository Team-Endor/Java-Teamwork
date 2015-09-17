package models;

import display.Display;
import game.Game;
import interfaces.Killable;

import java.awt.image.BufferedImage;

public class Player extends MovableObject implements Killable {
    private static Player instance;
    private        int    health;

    private Player(int x, int y, BufferedImage image, int velocity, int health) {
        super(x, y, image, velocity);

        this.setHealth(health);
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() > 0;
    }

    public static Player createInstance(int x, int y, BufferedImage image, int velocity, int health) {
        if (instance == null) {
            instance = new Player(x, y, image, velocity, health);
        }
        return instance;
    }

    @Override
    public void tick() {
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
            if (this.getX() + this.getVelocity() < Game.WINDOW_WIDTH - this.getImage().getWidth()) {
                this.move(this.getVelocity(), 0);
            }
        }
    }
}
