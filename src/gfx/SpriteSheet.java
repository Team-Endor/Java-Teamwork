package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage img) {
        this.spriteSheet = img;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return this.spriteSheet.getSubimage(x, y, width, height);
    }
}
