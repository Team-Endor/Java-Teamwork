package models.factories;

import java.util.Random;

import models.Airplane;

public class RandomEnemyFactory {
	private static final int ENEMY_TYPES_COUNT  = 2;
	
	private static Random random = new Random();

	public static Airplane generateEnemy() {
		int enemyType = random.nextInt(ENEMY_TYPES_COUNT);

		switch (enemyType) {
		case 0:
			return AirplanesFactory.generateAirplane();

		case 1:
			return AirplanesFactory.generateFighterPlane();

		default:
			// TODO: throw exception?
			return AirplanesFactory.generateAirplane();
		}
	}
}
