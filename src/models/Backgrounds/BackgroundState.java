package models.backgrounds;

import java.awt.image.BufferedImage;

public abstract class BackgroundState {
	public BufferedImage image;

	public BackgroundState(BufferedImage image) {
		this.image = image;
	}

	public int getY() {
		return 0;
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public abstract void tick();

	public abstract boolean isStateFinished();

	// HACK: the hacks are real :)
	public void swapped() {
	}
}
