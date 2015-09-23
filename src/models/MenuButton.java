package models;

import game.Engine;
import models.Object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton extends Object {
    private BufferedImage imageInactive;
    private BufferedImage imageHovered;
    private boolean       isHovered;

    public MenuButton(int x, int y, BufferedImage imageHovered, BufferedImage imageInactive) {
        super(x, y, imageInactive);
        this.imageInactive = imageInactive;
        this.imageHovered = imageHovered;
        this.setIsHovered(false);
    }

    public boolean isHovered() {
        return this.isHovered;
    }

    public void setIsHovered(boolean isHovered) {
        this.isHovered = isHovered;
    }

    @Override
    public void render(Graphics graphics) {
        if (this.isHovered()) {
            this.setImage(imageHovered);
        } else {
            this.setImage(imageInactive);
        }
        super.render(graphics);
    }
}
