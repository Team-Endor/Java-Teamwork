package models;

import game.Game;
import interfaces.Movable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class MovableObject extends Object implements Movable {
    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isMovingLeft;
    private boolean isMovingRight;
    private int     velocity;

    protected MovableObject(int x, int y, BufferedImage image, int velocity) {
        super(x, y, image);
        this.setVelocity(velocity);

        this.setIsMovingUp(false);
        this.setIsMovingDown(false);
        this.setIsMovingLeft(false);
        this.setIsMovingRight(false);
    }

    @Override
    public boolean isMovingUp() {
        return this.isMovingUp;
    }

    public void setIsMovingUp(boolean isMovingUp) {
        this.isMovingUp = isMovingUp;
    }

    @Override
    public boolean isMovingDown() {
        return this.isMovingDown;
    }

    public void setIsMovingDown(boolean isMovingDown) {
        this.isMovingDown = isMovingDown;
    }

    @Override
    public boolean isMovingLeft() {
        return this.isMovingLeft;
    }

    public void setIsMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }

    @Override
    public boolean isMovingRight() {
        return this.isMovingRight;
    }

    public void setIsMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }

    @Override
    public int getVelocity() {
        return this.velocity;
    }

    protected void setVelocity(int velocity) {
        if (velocity <= 0) {
            throw new IllegalArgumentException();
        }

        this.velocity = velocity;
    }

    @Override
    public void tick() {
        if (this.isMovingUp()) {
            this.move(-Game.VELOCITY/2, -this.getVelocity());
        }

        if (this.isMovingDown()) {
            this.move(-Game.VELOCITY/2, this.getVelocity());
        }

        if (this.isMovingLeft()) {
            this.move(-this.getVelocity(), -Game.VELOCITY/2);
        }

        if (this.isMovingRight()) {
            this.move(this.getVelocity(), -Game.VELOCITY/2);
        }
    }

    @Override
    public void move(int deltaX, int deltaY) {
        this.setX(this.getX() + deltaX);
        this.setY(this.getY() + deltaY);
    }

    public boolean isOnScreen() {
        return this.getX() + this.getImage().getWidth() > 0 &&
                this.getX() < Game.WINDOW_WIDTH &&
                this.getY() + this.getImage().getHeight() > 0 &&
                this.getY() < Game.WINDOW_HEIGHT;
    }
}
