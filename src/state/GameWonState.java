package state;

import gfx.Assets;
import models.backgrounds.Background;

public class GameWonState extends GameEndState {
    @Override
    public void init() {
        super.init();
        super.setBackground(new Background(Assets.gameWonBackground));
    }
}
