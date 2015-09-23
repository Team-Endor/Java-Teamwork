package models.factories;

import game.Engine;
import gfx.Assets;
import models.MenuButton;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameEndScreenFactory {
    private static final BufferedImage PLAYAGAIN_BUTTON_HOVERED_IMAGE  = Assets.endGamePlayAgain;
    private static final BufferedImage PLAYAGAIN_BUTTON_INACTIVE_IMAGE = Assets.endGameInactivePlayAgain;

    private static final BufferedImage BACK_TO_MENU_BUTTON_HOVERED_IMAGE  = Assets.endGameBackToMenu;
    private static final BufferedImage BACK_TO_MENU_BUTTON_INACTIVE_IMAGE = Assets.endGameInactiveBackToMenu;

    private static final BufferedImage EXIT_BUTTON_HOVERED_IMAGE  = Assets.endGameExit;
    private static final BufferedImage EXIT_BUTTON_INACTIVE_IMAGE = Assets.endGameInactiveExit;

    private static final int IMAGE_WIDTH  = 320;
    private static final int IMAGE_HEIGHT = 72;

    private static final int BUTTONS_X = Engine.WINDOW_WIDTH / 2 - IMAGE_WIDTH / 2;

    private static final int DISTANCE_BETWEEN_BUTTONS = 10;

    public static List<MenuButton> generateEndGameButtons() {
        List<MenuButton> menuButtons = new ArrayList<>();

        MenuButton playAgainButton = new MenuButton(BUTTONS_X,
                290,
                PLAYAGAIN_BUTTON_HOVERED_IMAGE,
                PLAYAGAIN_BUTTON_INACTIVE_IMAGE);

        MenuButton backToMenu = new MenuButton(BUTTONS_X,
                290 + (IMAGE_HEIGHT + DISTANCE_BETWEEN_BUTTONS),
                BACK_TO_MENU_BUTTON_HOVERED_IMAGE,
                BACK_TO_MENU_BUTTON_INACTIVE_IMAGE);

        MenuButton exitButton = new MenuButton(BUTTONS_X,
                290 + 2 * (IMAGE_HEIGHT + DISTANCE_BETWEEN_BUTTONS),
                EXIT_BUTTON_HOVERED_IMAGE,
                EXIT_BUTTON_INACTIVE_IMAGE);

        menuButtons.add(playAgainButton);
        menuButtons.add(backToMenu);
        menuButtons.add(exitButton);

        return menuButtons;
    }
}