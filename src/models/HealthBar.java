package models;

import gfx.Assets;
import models.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthBar extends Object {
    private static final int HEALTHBARBORDER_X   = 600;
    private static final int HEALTHBARBORDER_Y   = 50;
    private static final int HEALTHBAR_X         = 604;
    private static final int HEALTHBAR_Y         = 69;
    private static final int HEALTHBARNUMBER_X   = 658;
    private static final int HEALTHBARNUMBER_Y   = 82;
    private static final int HEALTHBAR_WIDTH_MAX = 162;

    private Player        player;
    private int           healthbarWidth;
    private BufferedImage healthbar;

    public HealthBar(Player player) {
        super(HEALTHBARBORDER_X, HEALTHBARBORDER_Y, Assets.healthbarBorder);

        this.healthbar = Assets.healthbar;
        this.player = player;
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        this.healthbarWidth = (int) (HEALTHBAR_WIDTH_MAX * (this.player.getHealth() / (double) Player.MaxHealth));
        graphics.drawImage(this.healthbar,
                HEALTHBAR_X,
                HEALTHBAR_Y,
                this.healthbarWidth,
                this.healthbar.getHeight(),
                null);
        Font font = new Font("Verdana", Font.BOLD, 12);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        String health = String.format("%d/%d", this.player.getHealth(), Player.MaxHealth);
        graphics.drawString(health, HEALTHBARNUMBER_X, HEALTHBARNUMBER_Y);
        graphics.setColor(Color.red);
    }
}
