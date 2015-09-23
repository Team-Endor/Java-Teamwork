package models.factories;

import java.util.Random;

import models.enemies.Enemy;

public class RandomEnemyFactory {
	private static final int Cases = 4;

	private static Random random = new Random();

	public static Enemy generateEnemy() {
		int enemyType = random.nextInt(Cases);

		switch (enemyType) {
			case 0:
				return AirplanesFactory.generateAirplane();

			case 1:
				return AirplanesFactory.generateAirplane();

			case 2:
				return AirplanesFactory.generateFighterPlane();

			case 3:
				return GroundRocketsFactory.generateGroundRocket();

			default:
				return AirplanesFactory.generateAirplane();
		}
	}
}
