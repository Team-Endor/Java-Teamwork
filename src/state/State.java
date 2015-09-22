package state;

// This class defines the different states of the game e.g.
// Menu state
// Game state
// state end game

import java.awt.*;

public abstract class State {
    public abstract void init();

    public abstract void tick();

    public abstract void render(Graphics graphics);
}
