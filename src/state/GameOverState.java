package state;

import gfx.Assets;
import models.MenuButton;
import models.backgrounds.Background;
import models.factories.GameOverFactory;
import models.factories.MainMenuFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class GameOverState extends State {

    private BufferedImage gameOver;
    private Background background;
    java.util.List<MenuButton> menuButtons;
    private int hoveredButtonIndex;

    public GameOverState() {
        this.init();
    }

    public int getHoveredButtonIndex() {
        return this.hoveredButtonIndex;
    }

    public void setHoveredButtonIndex(int hoveredButtonIndex) {
        this.hoveredButtonIndex = hoveredButtonIndex;
    }

    public int numberOfMenuButtons() {
        return this.menuButtons.size();
    }

    public void init() {
        this.gameOver = Assets.gameOver;
        this.background = new Background(Assets.mainMenuBackground);

        this.menuButtons = GameOverFactory.generateMenuButtons();

        this.hoveredButtonIndex = 0;
    }

    @Override
    public void tick() {
        for (int i = 0; i < menuButtons.size(); i++) {
            if (i == hoveredButtonIndex) {
                menuButtons.get(i).setIsHovered(true);
            } else {
                menuButtons.get(i).setIsHovered(false);
            }
        }

        for (MenuButton button : this.menuButtons) {
            button.tick(0);
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.background.render(graphics);
        graphics.drawImage(this.gameOver,310,210,null);

        for (MenuButton button : this.menuButtons) {
            button.render(graphics);
        }
    }


}
