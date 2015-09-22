package models;

import interfaces.Destroyable;
import interfaces.Killable;

import java.awt.image.BufferedImage;

public abstract class Airplane extends MovableObject implements Killable, Destroyable {
	private boolean isAlive;
	private boolean toDestroy;

	protected Airplane(int x, int y, BufferedImage image, int velocity) {
		super(x, y, image, velocity);
		this.isAlive = true;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public boolean getIsAlive() {
		return this.isAlive;
	}

	@Override
	public void destroy() {
		this.toDestroy = true;
	}

	@Override
	public boolean toDestory() {
		// TODO: remove the check for isAlive if we have death animation
		return this.toDestroy || !this.isAlive;
	}
}
