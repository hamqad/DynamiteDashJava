package com.syntx.dynamitedash.entity.spawner;

import java.util.Random;

import com.syntx.dynamitedash.entity.powerup.Box;
import com.syntx.dynamitedash.entity.powerup.Clone;
import com.syntx.dynamitedash.entity.powerup.ExtraBomb;
import com.syntx.dynamitedash.entity.powerup.FastPlace;
import com.syntx.dynamitedash.entity.powerup.Ghost;
import com.syntx.dynamitedash.entity.powerup.HealthPotion;
import com.syntx.dynamitedash.entity.powerup.MegaBomb;
import com.syntx.dynamitedash.entity.powerup.SpeedBoost;
import com.syntx.dynamitedash.level.Level;

public class RandomPowerUpSpawner {
	
	Random random = new Random(); // Used for randomising the powerup
	
	public RandomPowerUpSpawner(Level level, int start, int x, int y) {
			for(int yy = start; yy < y; yy++) { // Loops through each element and adds the specified amount of particles to the level
				for(int xx = start; xx < x; xx++) {
					int powerup = random.nextInt(25); // Holds a random value between 0 and 6. Each number represents a powerup
					if (powerup == 0) level.add(new FastPlace(xx * 16, yy * 16));
					if (powerup == 1) level.add(new Box(xx * 16, yy * 16));
					if (powerup == 2) level.add(new Clone(xx * 16, yy * 16));
					if (powerup == 3) level.add(new ExtraBomb(xx * 16 , yy * 16));
					if (powerup == 4) level.add(new Ghost(xx * 16, yy * 16));
					if (powerup == 5) level.add(new HealthPotion(xx * 16, yy * 16));
					if (powerup == 6) level.add(new MegaBomb(xx * 16, yy * 16));
					if (powerup == 7) level.add(new SpeedBoost(xx * 16, yy * 16));
				} 				
			}
		}
	}
