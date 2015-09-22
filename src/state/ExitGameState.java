package state;

import game.Engine;

import java.awt.*;

public class ExitGameState extends State {
    private Engine engine;

    public ExitGameState(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void init() {
    }

    @Override
    public void tick() {
        this.engine.setIsRunning(false);
    }

    @Override
    public void render(Graphics graphics) {
    }
}
