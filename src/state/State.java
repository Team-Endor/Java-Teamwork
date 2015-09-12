package state;

// This class defines the different states of the game e.g.
// Menu state
// Game state
// state end game

import com.sun.prism.Graphics;

public abstract class State {

    public abstract void tick();

    public abstract void render(Graphics g);
}
