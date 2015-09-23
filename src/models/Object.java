package models;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Object {
    private int x;
    private int y;

    private BufferedImage image;

    protected Object(int x, int y, BufferedImage image) {
        this.setX(x);
        this.setY(y);
        this.setImage(image);
    }

    public int getX() {
        return this.x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    protected void setImage(BufferedImage image) {
        this.image = image;
    }

    public Rectangle getBoundingBox() {
        Rectangle currentBoundingBox = new Rectangle(this.getX(),
                this.getY(),
                this.getImage().getWidth(),
                this.getImage().getHeight());

        return currentBoundingBox;
    }

    public void render(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.getX(), this.getY(), null);
    }
}
