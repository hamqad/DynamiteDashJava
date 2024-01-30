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

public class MPPlayerSelectScreen {
	
	static BufferedImage screen = null;
	static UIButton one, two, three, back;
	private static UIManager ui = Game.mpPlayerSelectManager;
	
	public static void setUp() {
		
		try {
			screen = ImageIO.read(new File("res/gamescreens/multiplayerplayer.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	// Home:
		one = new UIButton(new Vector2i(641, 466), new Vector2i(213, 213), new UIActionListener() {
			public void perform() {
				Game.playerCount = 1;
				Game.state = Game.STATE.MPLEVELSELECT;
				//ui.clear();
			}
		}, false);
		
		
		two = new UIButton(new Vector2i(947, 466), new Vector2i(213, 213), new UIActionListener() {
			public void perform() {
				Game.playerCount = 2;
				Game.state = Game.STATE.MPLEVELSELECT;
				//ui.clear();
			}
		}, false);
		
		
		three = new UIButton(new Vector2i(797, 753), new Vector2i(213, 213), new UIActionListener() {
			public void perform() {
				Game.playerCount = 3;
				Game.state = Game.STATE.MPLEVELSELECT;
				//ui.clear();
			}
		}, false);
		
		back = new UIButton(new Vector2i(49, 34), new Vector2i(54, 54), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.HOME;
			}
		}, false); 
		
	// College:
	/*		one = new UIButton(new Vector2i(542, 394), new Vector2i(180, 180), new UIActionListener() {
			public void perform() {
				Game.playerCount = 1;
				Game.state = Game.STATE.MPLEVELSELECT;
				//ui.clear();
			}
		}, false);
		
		
		two = new UIButton(new Vector2i(801, 394), new Vector2i(180, 180), new UIActionListener() {
			public void perform() {
				Game.playerCount = 2;
				Game.state = Game.STATE.MPLEVELSELECT;
				//ui.clear();
			}
		}, false);
		
		
		three = new UIButton(new Vector2i(674, 673), new Vector2i(180, 180), new UIActionListener() {
			public void perform() {
				Game.playerCount = 3;
				Game.state = Game.STATE.MPLEVELSELECT;
				//ui.clear();
			}
		}, false);
		
		back = new UIButton(new Vector2i(41, 29), new Vector2i(46, 46), new UIActionListener() {
			public void perform() {
				Game.state = Game.STATE.HOME;
			}
		}, false); */
		
		
		ui.addButton(one);
		ui.addButton(two);
		ui.addButton(three);
		ui.addButton(back);
	}
	
	public static void render(Graphics g) {
		g.drawImage(screen, 0, 0, null);
		ui.render(g);

	}
	
}
