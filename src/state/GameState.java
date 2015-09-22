package state;

import game.Background;
import game.Engine;
import gfx.Assets;
import gfx.HealthBar;
import models.*;
import models.factories.AirplanesFactory;
import models.factories.ExplosionsFactory;
import models.factories.GroundRocketsFactory;
import models.factories.PlayerFactory;
import physics.CollisionDetector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState extends State {
    private Background background;

    private static Player player;

    private List<Explosion> explosions;
    private List<Airplane>  airplanes;
    private FighterPlane    testFighterPlane;
    private GroundRocket    testGroundRocketFromLeft, testGroundRocketFromCenter, testGroundRocketFromRight;

    private boolean isPaused;

    public GameState() {
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
        this.setIsPaused(true);

        this.background = new Background(Assets.gameBackground);
        this.player = PlayerFactory.generatePlayer();

        this.explosions = new ArrayList<>();

        this.airplanes = new ArrayList<>();
        this.airplanes.add(AirplanesFactory.generateAirplane());
        this.airplanes.add(AirplanesFactory.generateFighterPlane());

        this.testGroundRocketFromLeft = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromLeft);
        this.testGroundRocketFromCenter = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromCenter);
        this.testGroundRocketFromRight = GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromRight);
    }

    @Override
    public void tick() {
        if(!isPaused) {
            this.background.tick();

            List<Explosion> explosionsToRemove = new ArrayList<>();
            for (Explosion explosion : explosions) {
                explosion.tick();
                if (!explosion.getIsAlive()) {
                    explosionsToRemove.add(explosion);
                }
            }
            explosions.removeAll(explosionsToRemove);

            List<Airplane> airplanesToRemove = new ArrayList<>();
            for (Airplane airplane : airplanes) {
                airplane.tick();

                if (CollisionDetector.intersects(this.player.getBoundingBox(), airplane.getBoundingBox())) {
                    this.player.setCurrentHealth(this.player.getCurrentHealth() - 25);
                    System.out.println(this.player.getCurrentHealth());
                    this.explosions.add(ExplosionsFactory.createExplosion(airplane.getX(), airplane.getY()));
                    airplane.setIsAlive(false);
                }

                if (!airplane.getIsAlive() || !airplane.isOnScreen()) {
                    airplanesToRemove.add(airplane);
                }
            }
            airplanes.removeAll(airplanesToRemove);

            this.player.tick();
            this.testGroundRocketFromLeft.tick();
            this.testGroundRocketFromCenter.tick();
            this.testGroundRocketFromRight.tick();

            if (!this.player.getIsAlive()) {
                System.out.print("You died!");
                // TODO What happens if player dies
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        // Begin drawing
        this.background.render(graphics);

        for (Explosion explosion : explosions) {
            explosion.render(graphics);
        }

        for (Airplane airplane : airplanes) {
            airplane.render(graphics);
        }

        this.player.render(graphics);
        this.testGroundRocketFromLeft.render(graphics);
        this.testGroundRocketFromCenter.render(graphics);
        this.testGroundRocketFromRight.render(graphics);

        int healthBarWidth = 690;
        int healthBarBottomOffset = 40;
        HealthBar.DrawHealthBar(this.player,
                healthBarWidth, graphics,
                (Engine.WINDOW_WIDTH - healthBarWidth) / 2,
                Engine.WINDOW_HEIGHT - healthBarBottomOffset);

        // End drawing
    }
}
