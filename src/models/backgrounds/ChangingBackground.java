package models.backgrounds;

import java.awt.*;
import java.util.List;

import state.GameState;

import java.util.ArrayList;

public class ChangingBackground {
	private List<MovingBackgroundState> states;

	private MovingBackgroundState currentState, nextState;

	public ChangingBackground() {
		this.states = new ArrayList<>();
	}

	public void pushBackState(MovingBackgroundState state) {
		this.states.add(state);

		if (this.states.size() == 1) {
			this.currentState = state;
			this.nextState = state;
		}
	}

	public void updateAltitude(int altitude) {
		if (!this.currentState.isViewed(altitude)) {
			for (MovingBackgroundState movingBackgroundState : this.states) {
				if (movingBackgroundState.isViewed(altitude)) {
					this.currentState = movingBackgroundState;
				}
			}
		}

		this.currentState.updateAltitude(altitude);

		altitude -= GameState.BOARD_HEIGHT;
		if (!this.nextState.isViewed(altitude)) {
			for (MovingBackgroundState movingBackgroundState : this.states) {
				if (movingBackgroundState.isViewed(altitude)) {
					this.nextState = movingBackgroundState;
				}
			}
		}

		this.nextState.updateAltitude(altitude);
	}

	public void render(Graphics g) {
		g.drawImage(this.currentState.getImage(), 0, this.currentState.getOffsetY() - GameState.BOARD_HEIGHT, null);
		g.drawImage(this.nextState.getImage(), 0, this.nextState.getOffsetY(), null);
	}
}
