package com.syntx.dynamitedash.entity.spawner;

import java.util.Random;

import com.syntx.dynamitedash.entity.powerup.FastPlace;
import com.syntx.dynamitedash.entity.powerup.Box;
import com.syntx.dynamitedash.entity.powerup.Clone;
import com.syntx.dynamitedash.entity.powerup.ExtraBomb;
import com.syntx.dynamitedash.entity.powerup.Ghost;
import com.syntx.dynamitedash.entity.powerup.HealthPotion;
import com.syntx.dynamitedash.entity.powerup.MegaBomb;
import com.syntx.dynamitedash.entity.powerup.SpeedBoost;
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.level.tile.Tile;

public class PowerupSpawner {
	
Random random = new Random(); // Used for randomising the powerup

public PowerupSpawner(Level level) {
		for(int y = 1; y < level.height; y++) { // Loops through each element and adds the specified amount of particles to the level
			for(int x = 1; x < level.width; x++) {
				if(level.tiles[x + y * level.width] == Tile.col_level1_box) {
					int powerup = random.nextInt(25); // Holds a random value between 0 and 6. Each number represents a powerup
					if (powerup == 0) level.add(new FastPlace(x * 16, y * 16));
					if (powerup == 1) level.add(new Box(x * 16, y * 16));
					if (powerup == 2) level.add(new Clone(x * 16, y * 16));
					if (powerup == 3) level.add(new ExtraBomb(x * 16 , y * 16));
					if (powerup == 4) level.add(new Ghost(x * 16, y * 16));
					if (powerup == 5) level.add(new HealthPotion(x * 16 , y * 16));
					if (powerup == 6) level.add(new MegaBomb(x * 16, y * 16));
					if (powerup == 7) level.add(new SpeedBoost(x * 16, y * 16));
				}
			} 				
		}
	}
}
	
