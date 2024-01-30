package com.syntx.dynamitedash.graphics.gamestates;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.syntx.dynamitedash.Game;
import com.syntx.dynamitedash.graphics.UI.UIActionListener;
import com.syntx.dynamitedash.graphics.UI.UIButton;
import com.syntx.dynamitedash.graphics.UI.UIManager;
import com.syntx.dynamitedash.level.Level;
import com.syntx.dynamitedash.util.Vector2i;

public class GameOverScreen {
	
	static BufferedImage screen = null;
	static UIButton back;
	private static UIManager ui = Game.gameOverManager;
	public static void setUp() {
	
		try {
			screen = ImageIO.read(new File("res/gamescreens/gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		back = new UIButton(new Vector2i(49, 34), new Vector2i(54, 54), new UIActionListener() {
			public void perform() {
				Game.spawned = false;
				Game.state = Game.STATE.SINGLEPLAYER;
			}
		}, false); 
		
		
		ui.addButton(back);
	}
	
	public static void render(Graphics g) {
		g.drawImage(screen, 0, 0, null);

	}
	
}
