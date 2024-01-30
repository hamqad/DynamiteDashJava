package com.syntx.dynamitedash.graphics.gamestates;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.Game;

public class LoadingScreen {
	
	static BufferedImage screen = null;
	static int time = 0;
	static String path = "";
	
	public static void setUp() {
	}
	
	public static void update() {
		if (time == 0) {
			path = "res/gamescreens/dynamite1.png";
			try {
				screen = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
		if (time == 15) {
			path = "res/gamescreens/dynamite2.png";
			try {
				screen = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (time == 30) {
			path = "res/gamescreens/dynamite3.png";
			try {
				screen = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (time == 45) {
			path = "res/gamescreens/dynamite4.png";
			try {
				screen = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (time == 60) {
			path = "res/gamescreens/dynamite5.png";
			try {
				screen = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (time == 75) {
			path = "res/gamescreens/dynamite6.png";
			try {
				screen = ImageIO.read(new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		if (time == 90) {
			Game.state = Game.STATE.HOME;
		}
		time++;
	}
	
	public static void render(Graphics g) {
			g.drawImage(screen, 0, 0, null);
	}	
}
