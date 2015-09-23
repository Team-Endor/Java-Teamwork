package models.backgrounds;

import java.awt.image.BufferedImage;

public abstract class BackgroundState {
	public BufferedImage image;

	public BackgroundState(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage() {
		return this.image;
	}
}
