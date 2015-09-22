package models.Enemies;

import interfaces.Destroyable;
import interfaces.Killable;
import models.MovableObject;

import java.awt.image.BufferedImage;

public abstract class Enemy extends MovableObject implements Killable, Destroyable {
	private boolean isAlive;
	private boolean toDestroy;

	protected Enemy(int x, int y, BufferedImage image, int velocity) {
		super(x, y, image, velocity);
		this.isAlive = true;
		this.toDestroy = false;
	}

	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public void tick(int gameVelocity) {
		super.tick(gameVelocity);
		if (this.getY() < 0) {
			this.setIsAlive(false);
		}
	}

	@Override
	public boolean getIsAlive() {
		return this.isAlive;
	}

	@Override
	public void setForDestruction() {
		this.toDestroy = true;
	}

	@Override
	public boolean isSetForDestruction() {
		// TODO: remove the check for isAlive if we have death animation
		return this.toDestroy || !this.isAlive;
	}
}
