package com.syntx.dynamitedash.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.gate.Gate;
import com.syntx.dynamitedash.entity.mob.Zombie;

public class LevelThree extends Level {
		
	public LevelThree (String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {  
		try {
			BufferedImage image = ImageIO.read(LevelThree.class.getResource(path)); // loading levels image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h , tiles, 0 , w);  // Converting image into an array of pixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! ");
		}
		
		add(new Zombie(29, 15));
		add(new Zombie(30, 17));
		add(new Gate(37, 16));
		
	}
	protected void generateLevel() {
	//	for(int y = 0; y < 64; y++) {
	//		for(int x = 0; x < 64; x++) {
	//			getTile(x,y);
	//		}
	//	}
	//	tile_size = 16;
	}
	
	
	
		
		
}
	
	

