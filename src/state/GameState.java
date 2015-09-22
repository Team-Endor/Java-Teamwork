package state;

import gfx.Assets;
import models.*;
import models.Backgrounds.MovingBackground;
import models.Enemies.Enemy;
import models.Enemies.GroundRocket;
import models.Object;
import models.Player.Player;
import models.factories.AirplanesFactory;
import models.factories.ExplosionsFactory;
import models.factories.GroundRocketsFactory;
import models.factories.PlayerFactory;
import physics.CollisionDetector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState extends State {
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 600;
    Random random = new Random();

    private StateManager stateManager;

    private double Velocity = 5;
    private int VelocityINT = 5;
    private int DistanceTraveled = 1;

    private Object background;
    private HealthBar healthBar;

    private static Player player;

    private List<Explosion> explosions;
    private List<Enemy> enemies;

    private List<Explosion> explosionsToRemove;
    private List<Enemy> enemiesToRemove;
    private GroundRocket testGroundRocketFromLeft, testGroundRocketFromCenter, testGroundRocketFromRight;

    public GameState(StateManager stateManager) {
        this.stateManager = stateManager;
        this.init();
    }

    public Player getPlayer() {
        return player;
    }

    public void init() {
        this.background = new MovingBackground(Assets.background);
        this.player = PlayerFactory.generatePlayer();
        this.healthBar = new HealthBar(this.getPlayer());

        this.explosions = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.explosionsToRemove = new ArrayList<>();
        this.enemiesToRemove = new ArrayList<>();

        this.enemies.add(AirplanesFactory.generateAirplane());
        this.enemies.add(AirplanesFactory.generateFighterPlane());

        this.testGroundRocketFromLeft = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromLeft);
        this.testGroundRocketFromCenter = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromCenter);
        this.testGroundRocketFromRight = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromRight);
    }

    @Override
    public void tick() {

        if (this.DistanceTraveled < 50000000) {
            int rand = random.nextInt(100);
            if (rand == 0 || rand == 1) {
                this.enemies.add(AirplanesFactory.generateAirplane());
            }
            if (rand == 5) {
                this.enemies.add(AirplanesFactory.generateFighterPlane());
            }
        }


        this.background.tick(this.VelocityINT);

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

        enemiesToRemove.clear();
        for (Enemy enemy : enemies) {
            enemy.tick(this.VelocityINT);

            if (CollisionDetector.intersects(this.player.getBoundingBox(), enemy.getBoundingBox())) {
                this.player.setHealth(this.player.getHealth() - 100);
                System.out.println(this.player.getHealth());
                this.explosions.add(ExplosionsFactory.createExplosion(enemy.getX(), enemy.getY()));
                enemy.setIsAlive(false);
            }

            if (!enemy.getIsAlive()) {
                enemiesToRemove.add(enemy);
            }
        }
        if (enemiesToRemove.size() > 0) {
            enemies.removeAll(enemiesToRemove);
        }


        this.player.tick((int) this.Velocity);
        this.testGroundRocketFromLeft.tick(this.VelocityINT);
        this.testGroundRocketFromCenter.tick(this.VelocityINT);
        this.testGroundRocketFromRight.tick(this.VelocityINT);

        this.healthBar.tick(this.VelocityINT);

        if (!this.player.getIsAlive()) {
            this.stateManager.setCurrentState(this.stateManager.getGameOverState());
        }

        this.Velocity += 0.01;
        this.VelocityINT = (int) Velocity;
        this.DistanceTraveled += this.VelocityINT;
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
        this.testGroundRocketFromLeft.render(graphics);
        this.testGroundRocketFromCenter.render(graphics);
        this.testGroundRocketFromRight.render(graphics);

        this.healthBar.render(graphics);
        graphics.drawString(String.format("DISTANCE: %d", this.DistanceTraveled), 608, 110);
    }
}
