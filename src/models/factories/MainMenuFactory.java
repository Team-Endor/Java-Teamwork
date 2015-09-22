package models.factories;

import game.Engine;
import gfx.Assets;
import models.MenuButton;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MainMenuFactory {
    private static final BufferedImage PLAY_BUTTON_HOVERED_IMAGE  = Assets.mainMenuPlay;
    private static final BufferedImage PLAY_BUTTON_INACTIVE_IMAGE = Assets.mainMenuInactivePlay;

    private static final BufferedImage CREDITS_BUTTON_HOVERED_IMAGE  = Assets.mainMenuCredits;
    private static final BufferedImage CREDITS_BUTTON_INACTIVE_IMAGE = Assets.mainMenuInactiveCredits;

    private static final BufferedImage SETTINGS_BUTTON_HOVERED_IMAGE  = Assets.mainMenuSettings;
    private static final BufferedImage SETTINGS_BUTTON_INACTIVE_IMAGE = Assets.mainMenuInactiveSettings;

    private static final BufferedImage EXIT_BUTTON_HOVERED_IMAGE  = Assets.mainMenuExit;
    private static final BufferedImage EXIT_BUTTON_INACTIVE_IMAGE = Assets.mainMenuInactiveExit;

    private static final int IMAGE_WIDTH  = 373;
    private static final int IMAGE_HEIGHT = 129;

    private static final int BUTTONS_X = Engine.WINDOW_WIDTH / 2 - IMAGE_WIDTH / 2;

    private static final int DISTANCE_BETWEEN_BUTTONS = 10;

    public static List<MenuButton> generateMenuButtons() {
        List<MenuButton> menuButtons = new ArrayList<>();

        MenuButton playButton = new MenuButton(BUTTONS_X,
                20,
                PLAY_BUTTON_HOVERED_IMAGE,
                PLAY_BUTTON_INACTIVE_IMAGE);
        MenuButton creditsButton = new MenuButton(BUTTONS_X,
                20 + (IMAGE_HEIGHT + DISTANCE_BETWEEN_BUTTONS),
                CREDITS_BUTTON_HOVERED_IMAGE,
                CREDITS_BUTTON_INACTIVE_IMAGE);
        MenuButton settingsButton = new MenuButton(BUTTONS_X,
                20 + 2 * (IMAGE_HEIGHT + DISTANCE_BETWEEN_BUTTONS),
                SETTINGS_BUTTON_HOVERED_IMAGE,
                SETTINGS_BUTTON_INACTIVE_IMAGE);
        MenuButton exitButton = new MenuButton(BUTTONS_X,
                20 + 3 * (IMAGE_HEIGHT + DISTANCE_BETWEEN_BUTTONS),
                EXIT_BUTTON_HOVERED_IMAGE,
                EXIT_BUTTON_INACTIVE_IMAGE);

        menuButtons.add(playButton);
        menuButtons.add(creditsButton);
        menuButtons.add(settingsButton);
        menuButtons.add(exitButton);

        return menuButtons;
    }
}
