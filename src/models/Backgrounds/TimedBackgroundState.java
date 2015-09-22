package models.backgrounds;

import java.awt.image.BufferedImage;

public class TimedBackgroundState extends BackgroundState {
	private int ticksCount;

	public TimedBackgroundState(BufferedImage image, int ticksCount) {
		super(image);
		this.ticksCount = ticksCount;
	}

	@Override
	public void tick() {
		this.ticksCount--;
	}

	@Override
	public boolean isStateFinished() {
		return (this.ticksCount <= 0);
	}
}
