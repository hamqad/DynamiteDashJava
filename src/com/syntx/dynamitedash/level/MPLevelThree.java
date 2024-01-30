package com.syntx.dynamitedash.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.spawner.RandomPowerUpSpawner;

public class MPLevelThree extends Level {
		
	public MPLevelThree (String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {  
		try {
			BufferedImage image = ImageIO.read(MPLevelThree.class.getResource(path)); // loading levels image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h , tiles, 0 , w);  // Converting image into an array of pixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! ");
		}
		
		new RandomPowerUpSpawner(this, 4, 57, 30);
	}
	protected void generateLevel() {
		for(int y = 0; y < 64; y++) {
			for(int x = 0; x < 64; x++) {
				getTile(x,y);
			}
		}
		tile_size = 16;
	}
	
	
	
		
		
}
	
	

