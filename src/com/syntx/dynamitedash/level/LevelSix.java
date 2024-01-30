package com.syntx.dynamitedash.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.gate.Gate;
import com.syntx.dynamitedash.entity.mob.Zombie;

public class LevelSix extends Level {
		
	public LevelSix (String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {  
		try {
			BufferedImage image = ImageIO.read(LevelSix.class.getResource(path)); // loading levels image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h , tiles, 0 , w);  // Converting image into an array of pixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! ");
		}
		
		add(new Zombie(22, 9));
		add(new Zombie(24, 9));
		add(new Zombie(26, 9));
		add(new Zombie(28, 9));
		add(new Zombie(30, 9));
		add(new Zombie(32, 9));
		add(new Zombie(34, 9));
		add(new Zombie(36, 9));
		add(new Zombie(38, 9));
		
		add(new Zombie(23, 25));
		add(new Zombie(25, 25));
		add(new Zombie(27, 25));
		add(new Zombie(29, 25));
		add(new Zombie(31, 25));
		add(new Zombie(33, 25));
		add(new Zombie(35, 25));
		add(new Zombie(37, 25));
		add(new Zombie(39, 25));
		
		add(new Zombie(42, 7));
		add(new Zombie(41, 28));
		add(new Zombie(38, 28));
		add(new Zombie(32, 27));
		add(new Zombie(25, 28));
		add(new Zombie(20, 19));
		add(new Gate(19, 15));
		add(new Gate(19, 19));
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
	
	

