package game;

import gfx.Assets;

import java.awt.*;

import java.awt.image.BufferedImage;

public class Background {
	
	private int x;
	private int y;
		
	private BufferedImage backgroundImage;

	public Background() {
		this.x = 0;
		this.y = 0;
		
		this.backgroundImage = Assets.background;
	}
	
	public void tick() {
		this.y -= Game.VELOCITY;
		if (this.y < 0) {
			this.y = this.backgroundImage.getHeight();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(this.backgroundImage, this.x, this.y, null);
		g.drawImage(this.backgroundImage, this.x, this.y - this.backgroundImage.getHeight(), null);
	}
}
