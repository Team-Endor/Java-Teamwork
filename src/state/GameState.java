package state;

import gfx.Assets;
import models.*;
import models.backgrounds.ChangingBackground;
import models.backgrounds.MovingBackgroundState;
import models.enemies.Enemy;
import models.factories.ExplosionsFactory;
import models.factories.PlayerFactory;
import models.factories.RandomEnemyFactory;
import models.player.Player;
import physics.CollisionDetector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState extends State {
	private static final int MAX_ENEMIES_COUNT = 5;
	private static final int MIN_SPAWN_TIME = 20;
	private static final int SPAWN_INTERVAL = 20;

	private static final int SPACE_END = 10000;
	private static final int SPACE_STATRT = 8800;
	private static final int AIR_END = 8000;
	private static final int AIR_STATRT = 800;
	
	public static final int BOARD_WIDTH = 800;
	public static final int BOARD_HEIGHT = 600;

	private boolean isPaused;

	private static final Random random = new Random();

	private StateManager stateManager;

	private int nextEnemyTimer = 0;

	private double Velocity = 5;
	private int VelocityINT = 5;
	private int DistanceTraveled = 1;

	private ChangingBackground background;
	private HealthBar healthBar;

	private static Player player;

	private List<Explosion> explosions;
	private List<Explosion> explosionsToRemove;
	private List<Enemy> enemies;

	public GameState(StateManager stateManager) {
		this.stateManager = stateManager;
		this.init();
	}

	public Player getPlayer() {
		return player;
	}

	public boolean getIsPaused() {
		return isPaused;
	}

	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void init() {
		this.background = new ChangingBackground();// Assets.background);
		this.background.pushBackState(new MovingBackgroundState(Assets.backgroundSpace, SPACE_STATRT, SPACE_END));
		this.background.pushBackState(new MovingBackgroundState(Assets.backgroundSpaceAtmosphere, AIR_END, SPACE_STATRT));
		this.background.pushBackState(new MovingBackgroundState(Assets.backgroundAtmosphere, AIR_STATRT, AIR_END));
		this.background.pushBackState(new MovingBackgroundState(Assets.backgroundGround, 0, AIR_STATRT));

		this.player = PlayerFactory.generatePlayer();
		this.healthBar = new HealthBar(this.getPlayer());

		this.explosions = new ArrayList<>();
		this.enemies = new ArrayList<>();

		this.explosionsToRemove = new ArrayList<>();
	}

	@Override
	public void tick() {
		if (!getIsPaused()) {
			this.background.updateAltitude(SPACE_END - this.DistanceTraveled);

			explosionsToRemove.clear();
			for (Explosion explosion : explosions) {
				explosion.tick(this.VelocityINT);
				if (!explosion.getIsAlive()) {
					explosionsToRemove.add(explosion);
				}
			}
			if (explosionsToRemove.size() > 0) {
				explosions.removeAll(explosionsToRemove);
			}

			this.processCollision();

			this.removeDestoyedEnemies();

			this.spawnEnemy();

			this.player.tick((int) this.Velocity);

			this.healthBar.tick(this.VelocityINT);

			if (!this.player.getIsAlive()) {
				this.stateManager.setCurrentState(this.stateManager.getGameOverState());
			}

			if (this.DistanceTraveled >= SPACE_END) {
				// victory
				System.out.println("Victory");
				this.setIsPaused(true);
			}

			this.Velocity += 0.01;
			this.VelocityINT = (int) Velocity;
			this.DistanceTraveled += this.VelocityINT;
		}
	}

	@Override
	public void render(Graphics graphics) {
		// Begin drawing
		this.background.render(graphics);

		for (Explosion explosion : explosions) {
			explosion.render(graphics);
		}

		for (Enemy enemy : enemies) {
			enemy.render(graphics);
		}

		this.player.render(graphics);

		this.healthBar.render(graphics);
		graphics.drawString(String.format("DISTANCE: %d", this.DistanceTraveled), 608, 110);
	}

	private void processCollision() {
		for (int i = 0; i < this.enemies.size(); ++i) {
			Enemy enemy = this.enemies.get(i);

			enemy.tick(this.VelocityINT);

			if (CollisionDetector.intersects(this.player.getBoundingBox(), enemy.getBoundingBox())) {
				this.player.setHealth(this.player.getHealth() - 25);
				System.out.println(this.player.getHealth());
				this.explosions.add(ExplosionsFactory.createExplosion(enemy.getX(), enemy.getY()));
				enemy.setIsAlive(false);
			}

			// TODO: move isOnScreen method?
			if (!enemy.isOnScreen()) {
				enemy.setForDestruction();
			}
		}
	}

	private void removeDestoyedEnemies() {
		for (int i = 0; i < this.enemies.size(); ++i) {
			if (this.enemies.get(i).isSetForDestruction()) {
				this.enemies.remove(i);
				--i;
			}
		}
	}

	private void spawnEnemy() {
		if (this.enemies.size() < MAX_ENEMIES_COUNT) {
			--nextEnemyTimer;
			if (nextEnemyTimer <= 0) {
				this.enemies.add(RandomEnemyFactory.generateEnemy());

				nextEnemyTimer = MIN_SPAWN_TIME + this.random.nextInt(SPAWN_INTERVAL);
			}
		}
	}
}
