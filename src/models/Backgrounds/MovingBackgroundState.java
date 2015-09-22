package models.Backgrounds;

import java.awt.image.BufferedImage;

import gfx.Assets;

public class MovingBackgroundState extends BackgroundState {
	private int y;
	private int speedY;

	public MovingBackgroundState(BufferedImage image,
			int repeatCount,
			int speedY) {
		super(image);
		this.y = repeatCount * image.getHeight();
		this.speedY = Math.abs(speedY);
	}
	
	@Override
	public int getY() {
		return this.y % this.image.getHeight();
	}
	
	@Override
	public void tick() {
		this.y -= this.speedY;
	}

	@Override
	public boolean isStateFinished() {
		return (this.y <= 0);
	}
	
	@Override
	public void swapped() {
		this.y += this.image.getHeight();
	}
}
