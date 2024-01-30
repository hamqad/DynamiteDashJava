package com.syntx.dynamitedash.graphics.gamestates;

import java.awt.Color;
import java.awt.Font;
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

public class WinScreen {
	
	static BufferedImage screen = null;
	static UIButton back;
	static String text;
	private static UIManager ui = Game.winManager;
	
	public static void setUp() {
		
		text = Game.winner.id +  " wins!";
		Game.playerCount  = 1;
		try {
			screen = ImageIO.read(new File("res/gamescreens/gameover.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		back = new UIButton(new Vector2i(49, 34), new Vector2i(54, 54), new UIActionListener() {
			public void perform() {
				System.out.println("done");
				Game.spawned = false;
				Game.state = Game.STATE.HOME;
			}
		}, false); 
		
		
		ui.addButton(back);
	}
	
	public static void render(Graphics g) {
		g.drawImage(screen, 0, 0, null);
		g.setColor(new Color(0xffe74c3c));
		g.setFont(new Font("Helvetica", Font.PLAIN, 124));
		g.drawString(text, 612, 744);

	}
	
}
