package com.syntx.dynamitedash.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();  
	
	public RandomLevel(int width, int height) {  
		super(width, height);

	}
	
	protected void generateLevel() {    // Loops through each tile and sets it to a random number representing a random tile
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tilesInt[x+y*width] = random.nextInt(4);
			}
		}
	}
	
	
	
}
