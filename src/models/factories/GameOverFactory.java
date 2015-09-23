package models.factories;


import game.Engine;
import gfx.Assets;
import models.MenuButton;
import state.GameState;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameOverFactory {
    private static final BufferedImage PLAY_BUTTON_HOVERED_IMAGE = Assets.mainMenuPlay;
    private static final BufferedImage PLAY_BUTTON_INACTIVE_IMAGE = Assets.mainMenuInactivePlay;

    private static final BufferedImage EXIT_BUTTON_HOVERED_IMAGE = Assets.mainMenuExit;
    private static final BufferedImage EXIT_BUTTON_INACTIVE_IMAGE = Assets.mainMenuInactiveExit;

    private static final int IMAGE_WIDTH = 373;
    private static final int IMAGE_HEIGHT = 129;

    private static final int BUTTONS_Y = 400;

    private static final int DISTANCE_BETWEEN_BUTTONS = 10;

    public static List<MenuButton> generateMenuButtons() {
        List<MenuButton> menuButtons = new ArrayList<>();

        MenuButton playButton = new MenuButton(20,
                BUTTONS_Y,
                PLAY_BUTTON_HOVERED_IMAGE,
                PLAY_BUTTON_INACTIVE_IMAGE);
        MenuButton exitButton = new MenuButton(20 + (IMAGE_WIDTH + DISTANCE_BETWEEN_BUTTONS),
                BUTTONS_Y,
                EXIT_BUTTON_HOVERED_IMAGE,
                EXIT_BUTTON_INACTIVE_IMAGE);

        menuButtons.add(playButton);
        menuButtons.add(exitButton);

        return menuButtons;
    }
}
