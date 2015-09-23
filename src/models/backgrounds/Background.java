package models.backgrounds;

import java.awt.*;

import java.awt.image.BufferedImage;

public class Background extends models.Object {

    public Background(BufferedImage image) {
        super(0, 0, image);
    }

    public void render(Graphics g) {
        g.drawImage(this.getImage(), this.getX(), this.getY(), null);
    }
}
