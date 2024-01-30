package com.syntx.dynamitedash.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.gate.Gate;
import com.syntx.dynamitedash.entity.mob.Dummy;

public class LevelEight extends Level {
		
	public LevelEight (String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {  
		try {
			BufferedImage image = ImageIO.read(LevelEight.class.getResource(path)); // loading levels image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h , tiles, 0 , w);  // Converting image into an array of pixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! ");
		}
		
		add(new Dummy(26, 6));
		add(new Dummy(38, 6));
		add(new Dummy(43, 6));
		add(new Dummy(18, 10));
		add(new Dummy(24, 11));
		add(new Dummy(35, 11));
		add(new Dummy(36, 14));
		add(new Dummy(28, 15));
		add(new Dummy(18, 16));
		add(new Dummy(43, 16));
		add(new Dummy(37, 18));
		add(new Dummy(21, 19));
		add(new Dummy(43, 19));
		add(new Dummy(28, 21));
		add(new Dummy(24, 23));
		add(new Dummy(19, 24));
		add(new Dummy(41, 24));
		add(new Dummy(35, 25));
		add(new Dummy(41, 24));
		add(new Dummy(39, 26));
		add(new Dummy(30, 28));
		add(new Dummy(25, 29));
		add(new Dummy(34, 20));
		add(new Dummy(35, 29));
		add(new Dummy(36, 29));
		add(new Dummy(32, 30));
		add(new Dummy(26, 30));
		add(new Dummy(27, 29));
		add(new Dummy(32, 31));
		
		add(new Gate(45, 31));
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
	
	

