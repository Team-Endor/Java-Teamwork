package state;

import Constants.Constants;
import gfx.Assets;
import models.MenuButton;
import models.backgrounds.Background;
import models.factories.GameEndScreenFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GameEndState extends State{
    private int keyCode;
    private Background background;

    java.util.List<MenuButton> buttons;
    private int hoveredButtonIndex;

    public GameEndState() {
        this.init();
    }

    protected void setBackground(Background background) {
        this.background = background;
    }

    public int getHoveredButtonIndex() {
        return this.hoveredButtonIndex;
    }

    public void setHoveredButtonIndex(int hoveredButtonIndex) {
        this.hoveredButtonIndex = hoveredButtonIndex;
    }

    public int numberOfButtons() {
        return this.buttons.size();
    }

    public void init() {
        this.background = new Background(Assets.gameWonBackground);

        this.buttons = GameEndScreenFactory.generateEndGameButtons();

        this.hoveredButtonIndex = 0;
    }

    @Override
    public void tick() {
        for (int i = 0; i < this.buttons.size(); i++) {
            if (i == hoveredButtonIndex) {
                this.buttons.get(i).setIsHovered(true);
            } else {
                this.buttons.get(i).setIsHovered(false);
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.background.render(graphics);

        for (MenuButton button : this.buttons) {
            button.render(graphics);
        }
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
            if (this.getHoveredButtonIndex() < this.buttons.size() - 1) {
                this.setHoveredButtonIndex(this.getHoveredButtonIndex() + 1);
            }
        }
        if (this.keyCode == KeyEvent.VK_ENTER) {
            if (this.getHoveredButtonIndex() == 0) {
               this.fireStateChangeEvent(Constants.GAME_STATE);
            }
            if (this.getHoveredButtonIndex() == 1) {
                this.fireStateChangeEvent(Constants.MENU_STATE);
            }
            if (this.getHoveredButtonIndex() == 2) {
                this.fireStateChangeEvent(Constants.EXIT_GAME_STATE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
