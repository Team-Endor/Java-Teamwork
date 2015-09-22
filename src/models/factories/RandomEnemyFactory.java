package models.factories;

import java.util.Random;

import models.enemies.Enemy;
import models.enemies.GroundRocket;

public class RandomEnemyFactory {
	private static final int ENEMY_TYPES_COUNT  = 5;
	
	private static Random random = new Random();

	public static Enemy generateEnemy() {
		int enemyType = random.nextInt(ENEMY_TYPES_COUNT);

		switch (enemyType) {
		case 0:
			return AirplanesFactory.generateAirplane();

		case 1:
			return AirplanesFactory.generateFighterPlane();

		case 2:
			return GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromLeft);
			
		case 3:
			return GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromCenter);
			
		case 4:
			return GroundRocketsFactory.generateGroundRocket(GroundRocket.Position.FromRight);
			
		default:
			// TODO: throw exception?
			return AirplanesFactory.generateAirplane();
		}
	}
}
