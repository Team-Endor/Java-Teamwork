package state;

import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverState extends State{
    private BufferedImage image;

    public GameOverState(){
        this.init();
    }
    @Override
    public void init() {
        image = Assets.GameOver;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image,310,210,null);
    }
}
