package models.Player;

import gfx.Assets;
import interfaces.Killable;
import models.MovableObject;
import state.GameState;

import java.awt.image.BufferedImage;

public class Player extends MovableObject implements Killable{
    private boolean isAlive;
    private static Player instance;
    private int health;
    private static final int METEOR_WIDTH = 80;
    private static final int METEOR_HEIGHT = 110;
    private static int animationFrame = 0;

    public static final int MaxHealth = 100;

    private Player(int x, int y, BufferedImage image, int velocity, int health) {
        super(x, y, image, velocity);

        this.setHealth(health);
        this.isAlive = true;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public static Player createInstance(int x, int y, BufferedImage image, int velocity, int health) {
        if (instance == null) {
            instance = new Player(x, y, image, velocity, health);
        }
        return instance;
    }

    @Override
    public void tick(int gameVelocity) {

        if(animationFrame < 5){
            this.setImage(Assets.Meteor.crop(0, 0, METEOR_WIDTH, METEOR_HEIGHT));
        }else{
            this.setImage(Assets.Meteor.crop(METEOR_WIDTH, 0, METEOR_WIDTH, METEOR_HEIGHT));
        }
        animationFrame++;
        if(animationFrame == 10){
            animationFrame = 0;
        }

        if (this.isMovingUp()) {
            if(this.getY() - this.getVelocity() >= 0){
                this.move(0, -this.getVelocity());
            }
        }

        if (this.isMovingDown()) {
            if(this.getY() + this.getImage().getHeight() + this.getVelocity() < GameState.BOARD_HEIGHT){
                this.move(0, this.getVelocity());
            }
        }

        if (this.isMovingLeft()) {
            if (this.getX() - this.getVelocity() >= 0) {
                this.move(-this.getVelocity(), 0);
            }
        }

        if (this.isMovingRight()) {
            if (this.getX() + this.getVelocity() < GameState.BOARD_WIDTH - this.getImage().getWidth()) {
                this.move(this.getVelocity(), 0);
            }
        }
    }


    @Override
    public boolean getIsAlive() {
        return this.health > 0;
    }
}
