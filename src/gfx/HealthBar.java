package gfx;

import models.Player;

import java.awt.*;

public class HealthBar {
    public static void DrawHealthBar(Player player, int totalBarLength, Graphics graphics, int positionX, int positionY) {
        //start drawing health bar frame
        graphics.drawImage(Assets.healthBarSeparator, positionX, positionY, null);

        for(int i = 0; i <= totalBarLength; i++){
            graphics.drawImage(Assets.healthBarFrame, positionX + i, positionY, null);
        }

        graphics.drawImage(Assets.healthBarSeparator, positionX + totalBarLength + 1, positionY, null);
        //end drawing health bar frame

        //start drawing health bar fill
        double percentageFull = ((double) player.getCurrentHealth() / player.getTotalHealth()) * totalBarLength;

        for (int i = 0; i < percentageFull; i++) {
            graphics.drawImage(Assets.healthBarFiller, positionX + i + 1, positionY, null);
        }
        //end drawing health bar fill
    }
}
