package state;

import java.awt.*;

public abstract class State {
    public abstract void init();

    public abstract void tick();

    public abstract void render(Graphics graphics);
}
