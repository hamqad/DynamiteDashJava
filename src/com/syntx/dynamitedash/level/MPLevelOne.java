package com.syntx.dynamitedash.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MPLevelOne extends Level {
		
	public MPLevelOne (String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {  
		try {
			BufferedImage image = ImageIO.read(MPLevelOne.class.getResource(path)); // loading levels image
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w*h];
			image.getRGB(0, 0, w, h , tiles, 0 , w);  // Converting image into an array of pixels
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! ");
		}
		
	//	add(new Chaser(16, 3));
	//	add(new Dummy(10, 3));
	//	add(new ChaserPro(17, 3));
	//	add(new Dummy(10, 3));
	//	add(new MegaBomb(20, 20));
	//	add(new ExtraBomb(40, 40));
	//	add(new SpeedBoost(60, 60));
	//	add(new HealthPotion(70, 70));
	//	add(new AddPlaceRate(100, 100));
	//	add(new Box(200, 100));
	//	add(new Shooter(10,10));
		
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
	
	

