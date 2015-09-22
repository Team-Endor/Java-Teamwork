package models.backgrounds;

import java.awt.*;
import java.util.Queue;
import java.util.LinkedList;

public class ChangingBackground {
	private Queue<BackgroundState> states;

	private BackgroundState currentState, nextState;

	public ChangingBackground() {
		this.states = new LinkedList<>();
	}

	public void pushBackState(BackgroundState state) {
		this.states.add(state);

		if (this.states.size() == 1) {
			this.currentState = state;
			this.nextState = state;
		}
	}

	public boolean isFinnished() {
		return this.nextState.isStateFinished();
	}

	// FIX !!!!
	public void tick() {
		this.currentState.tick();
		if (this.currentState != this.nextState) {
			this.nextState.tick();
		}
		
		if (this.currentState.isStateFinished()) {
			this.currentState = this.nextState;
			this.currentState.swapped();
			
			this.nextState = this.states.poll();
			if (this.nextState == null) {
				this.nextState = this.currentState;
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(this.currentState.getImage(), 0,
				this.currentState.getY() - this.currentState.getImage().getHeight(), null);
		g.drawImage(this.nextState.getImage(), 0, this.nextState.getY(), null);
	}
}
