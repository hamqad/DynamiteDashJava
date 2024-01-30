package com.syntx.dynamitedash.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.entity.gate.Gate;
import com.syntx.dynamitedash.entity.mob.ShooterProRight;
import com.syntx.dynamitedash.entity.mob.Zombie;

public class MPLevelTwo extends Level {
		
	public MPLevelTwo (String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {  
		try {
			BufferedImage image = ImageIO.read(MPLevelTwo.class.getResource(path)); // loading levels image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h , tiles, 0 , w);  // Converting image into an array of pixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! ");
		}
		
	add(new Zombie(3, 1));
	add(new Zombie(3, 16));
	
	add(new Zombie(5, 1));
	add(new Zombie(5, 16));
	
	add(new Zombie(7, 1));
	add(new Zombie(7, 16));
	
	add(new Zombie(9, 1));
	add(new Zombie(9, 16));
	
	add(new Zombie(11, 1));
	add(new Zombie(11, 16));
	
	add(new Zombie(13, 1));
	add(new Zombie(13, 16));
	
	add(new Zombie(15, 1));
	add(new Zombie(15, 16));
	
	add(new Zombie(17, 1));
	add(new Zombie(17, 16));
	
	add(new Zombie(19, 1));
	add(new Zombie(19, 16));
	
	add(new Zombie(21, 1));
	add(new Zombie(21, 16));
	
	add(new Zombie(23, 1));
	add(new Zombie(23, 16));
	
	add(new Zombie(25, 1));
	add(new Zombie(25, 16));
	
	add(new Zombie(27, 1));
	add(new Zombie(27, 16));
	
	add(new Zombie(29, 1));
	add(new Zombie(29, 16));
	
	add(new ShooterProRight(7, 18));
	add(new ShooterProRight(11, 18));
	add(new ShooterProRight(15, 18));
	add(new ShooterProRight(19, 18));
	add(new ShooterProRight(23, 18));
	add(new ShooterProRight(27, 18));
	
	add(new ShooterProRight(7, 32));
	add(new ShooterProRight(11, 32));
	add(new ShooterProRight(15, 32));
	add(new ShooterProRight(19, 32));
	add(new ShooterProRight(23, 32));
	add(new ShooterProRight(27, 32));
	
	add(new Gate(1, 25));
	add(new Gate(1, 26));
	
	
	
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
	
	

