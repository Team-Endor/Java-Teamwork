package state;

import gfx.Assets;
import models.MenuButton;
import models.backgrounds.Background;
import models.factories.GameEndScreenFactory;

import java.awt.*;

public abstract class GameEndState extends State {
    private int velocity = 5;

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

    private int getVelocity() {
        return velocity;
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

        for (MenuButton button : this.buttons) {
            button.tick(this.getVelocity());
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.background.render(graphics);

        for (MenuButton button : this.buttons) {
            button.render(graphics);
        }
    }
}
