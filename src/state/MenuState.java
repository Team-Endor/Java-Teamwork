package state;

import gfx.Assets;
import models.backgrounds.Background;
import models.MenuButton;
import models.factories.MainMenuFactory;

import java.awt.*;
import java.util.List;

public class MenuState extends State {
    private int velocity = 5;

    private Background background;

    List<MenuButton> menuButtons;
    private int hoveredButtonIndex;

    public MenuState() {
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

    private int getVelocity() {
        return velocity;
    }

    public void init() {
        this.background = new Background(Assets.mainMenuBackground);

        this.menuButtons = MainMenuFactory.generateMenuButtons();

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
            button.tick(this.getVelocity());
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.background.render(graphics);

        for (MenuButton button : this.menuButtons) {
            button.render(graphics);
        }
    }
}
