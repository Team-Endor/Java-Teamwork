package models.backgrounds;

import java.awt.image.BufferedImage;

public class MovingBackgroundState extends BackgroundState {
	private int altitude;
	private int startAltitude;
	private int endAltitude;

	public MovingBackgroundState(BufferedImage image, int startAltitude, int endAltitude) {
		super(image);

		this.startAltitude = startAltitude;
		this.endAltitude = endAltitude;
	}

	public int getOffsetY() {
		return this.altitude % this.image.getHeight();
	}

	public void updateAltitude(int altitude) {
		this.altitude = altitude;
	}

	public boolean isViewed(int altitude) {
		return (altitude <= this.startAltitude && altitude >= this.endAltitude);
	}
}
