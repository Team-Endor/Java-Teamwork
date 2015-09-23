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
    private static final int MIN_SPAWN_TIME    = 20;
    private static final int SPAWN_INTERVAL    = 20;

    private static final int SPACE_END   = 130000;
    private static final int SPACE_START = 102600;
    private static final int AIR_END     = 102000;
    private static final int AIR_START   = 600;

    public static final int BOARD_WIDTH  = 800;
    public static final int BOARD_HEIGHT = 600;

    private boolean isPaused;
    private boolean hasWon;

    private static final Random random = new Random();

    private StateManager stateManager;

    private int    nextEnemyTimer;
    private double Velocity;
    private int    VelocityINT;
    private int    DistanceLeft;

    private ChangingBackground background;
    private HealthBar          healthBar;

    private static Player player;

    private List<Explosion> explosions;
    private List<Explosion> explosionsToRemove;
    private List<Enemy>     enemies;
    private List<Enemy>     enemiesToRemove;

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

    public boolean getHasWon() {return this.hasWon;}

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void init() {
        this.background = new ChangingBackground();// Assets.background);
        this.background.pushBackState(new MovingBackgroundState(Assets.backgroundSpace, SPACE_END, SPACE_START));
        this.background.pushBackState(new MovingBackgroundState(Assets.backgroundSpaceAtmosphere, SPACE_START, AIR_END));
        this.background.pushBackState(new MovingBackgroundState(Assets.backgroundAtmosphere, AIR_END, AIR_START));
        this.background.pushBackState(new MovingBackgroundState(Assets.backgroundGround, AIR_START, 0));

        this.player = PlayerFactory.generatePlayer();
        this.healthBar = new HealthBar(this.getPlayer());

        this.explosions = new ArrayList<>();
        this.enemies = new ArrayList<>();

        this.enemiesToRemove = new ArrayList<>();
        this.explosionsToRemove = new ArrayList<>();

        this.nextEnemyTimer = 0;
        this.Velocity = 5;
        this.VelocityINT = 5;
        this.DistanceLeft = 150000;
    }

    @Override
    public void tick() {
        if (!getIsPaused()) {
            this.background.updateAltitude(this.DistanceLeft);

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

            this.processEnemies();

            this.removeDestoyedEnemies();

            this.spawnEnemy();

            this.player.tick((int) this.Velocity);

            if (!this.player.getIsAlive()) {
                this.stateManager.setCurrentState(this.stateManager.getGameLostState());
            }

            if (this.DistanceLeft <= AIR_START) {
                // victory
                this.hasWon = true;
                this.setIsPaused(true);
                this.stateManager.setCurrentState(this.stateManager.getGameWonState());
            }

            this.Velocity += 0.01;
            this.VelocityINT = (int) Velocity;
            this.DistanceLeft -= this.VelocityINT;
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
        graphics.drawString(String.format("DISTANCE: %d", this.DistanceLeft), 608, 110);
    }

    private void processEnemies() {
        for (int i = 0; i < this.enemies.size(); ++i) {
            Enemy enemy = this.enemies.get(i);

            enemy.tick(this.VelocityINT);

            if (CollisionDetector.intersects(this.player.getBoundingBox(), enemy.getBoundingBox())) {
                this.player.setHealth(this.player.getHealth() - 25);
                System.out.println(this.player.getHealth());
                this.explosions.add(ExplosionsFactory.createExplosion(enemy.getX(), enemy.getY()));
                enemy.setIsAlive(false);
            }

            if (!enemy.getIsAlive()) {
                this.enemiesToRemove.add(enemy);
            }
        }
    }

    private void removeDestoyedEnemies() {
        if (enemiesToRemove.size() > 0) {
            this.enemies.removeAll(enemiesToRemove);
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