package models.Backgrounds;

import models.MovableObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MovingBackground extends MovableObject {

    public MovingBackground(BufferedImage image) {
        super(0,0,image,0);

    }

    public void tick(int gameVelocity) {
        this.setY(this.getY() - gameVelocity);
        if (this.getY() < 0) {
            this.setY(this.getImage().getHeight());
        }
    }

    public void render(Graphics g) {
        g.drawImage(this.getImage(), this.getX(), this.getY(), null);
        g.drawImage(this.getImage(), this.getX(), this.getY() - this.getImage().getHeight(), null);
    }
}
