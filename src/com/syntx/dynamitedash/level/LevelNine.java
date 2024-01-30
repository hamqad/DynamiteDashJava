package com.syntx.dynamitedash.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.gate.Gate;
import com.syntx.dynamitedash.entity.mob.ShooterProLeft;
import com.syntx.dynamitedash.entity.mob.ShooterProRight;

public class LevelNine extends Level {
		
	public LevelNine (String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {  
		try {
			BufferedImage image = ImageIO.read(LevelNine.class.getResource(path)); // loading levels image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h , tiles, 0 , w);  // Converting image into an array of pixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! ");
		}
		
		add(new ShooterProRight(14, 25));
		add(new ShooterProRight(14, 29));
		add(new ShooterProRight(18, 32));
		add(new ShooterProRight(23, 32));
		add(new ShooterProRight(28, 32));
		
		add(new ShooterProLeft(34, 32));
		add(new ShooterProLeft(41, 32));
		add(new ShooterProLeft(47, 32));
		add(new ShooterProLeft(47, 29));
		add(new ShooterProLeft(47, 25));
		
		add(new Gate(31,27));
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
	
	

