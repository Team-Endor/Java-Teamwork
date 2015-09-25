package state;

import Constants.Constants;
import gfx.Assets;
import models.backgrounds.Background;
import models.MenuButton;
import models.factories.MainMenuFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class MenuState extends State{
    private Background background;
    private int keyCode;

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
    }

    @Override
    public void render(Graphics graphics) {
        this.background.render(graphics);

        for (MenuButton button : this.menuButtons) {
            button.render(graphics);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyCode = e.getKeyCode();

        if (this.keyCode == KeyEvent.VK_UP) {
            if (this.getHoveredButtonIndex() > 0) {
                this.setHoveredButtonIndex(this.getHoveredButtonIndex() - 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_DOWN) {
            if (this.getHoveredButtonIndex() < this.menuButtons.size() - 1) {
                this.setHoveredButtonIndex(this.getHoveredButtonIndex() + 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_ENTER) {
            if (this.getHoveredButtonIndex() == 0) {
                this.fireStateChangeEvent(Constants.GAME_STATE);
            }
            if (this.getHoveredButtonIndex() == 1) {
                this.fireStateChangeEvent(Constants.CREDITS_STATE);
            }
            if (this.getHoveredButtonIndex() == 3) {
                this.fireStateChangeEvent(Constants.EXIT_GAME_STATE);
            }
        }
    }
}
