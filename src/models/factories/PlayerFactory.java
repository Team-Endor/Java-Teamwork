package models.factories;

import gfx.Assets;
import models.player.Player;

import java.awt.image.BufferedImage;

public class PlayerFactory {
    private static final int METEOR_WIDTH  = 80;
    private static final int METEOR_HEIGHT = 110;

    private static final int           PLAYER_X        = 370;
    private static final int           PLAYER_Y        = 100;
    private static final BufferedImage PLAYER_IMAGE    = Assets.meteor.crop(0, 0, METEOR_WIDTH, METEOR_HEIGHT);
    private static final int           PLAYER_VELOCITY = 15;
    private static final int           PLAYER_HEALTH   = Player.MaxHealth;

    public static Player generatePlayer() {
        Player createdPlayer = new Player(PLAYER_X, PLAYER_Y, PLAYER_IMAGE, PLAYER_VELOCITY, PLAYER_HEALTH);

        return createdPlayer;
    }
}
