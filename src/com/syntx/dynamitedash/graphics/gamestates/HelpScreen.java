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
import com.syntx.dynamitedash.util.Vector2i;

public class HelpScreen {
	
	static BufferedImage screen = null;
	static UIButton back;
	private static UIManager ui = Game.helpManager;
	
	public static void setUp() {
		try {
			screen = ImageIO.read(new File("res/gamescreens/help.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	// Home:
		back = new UIButton(new Vector2i(49, 34), new Vector2i(54, 54), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.HOME;
			}
		}, false);  
		
	// College:	
	/*	back = new UIButton(new Vector2i(41, 29), new Vector2i(46, 46), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.HOME;
			}
		}, false);   */
		
		
		ui.addButton(back);
	} 
	
	public static void render(Graphics g) {

		g.drawImage(screen, 0, 0, null);
		ui.render(g);
	}
	
}
