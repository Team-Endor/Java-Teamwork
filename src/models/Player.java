package models;

import interfaces.Killable;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MovableObject implements Killable {
    private static Player instance;
    private int health;

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

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.getX(), this.getY(), null);
    }

    public static Player createInstance(int x, int y, BufferedImage image, int velocity, int health) {
        if(instance==null) {
            instance = new Player(x, y, image, velocity, health);
        }
        return instance;
    }
}
