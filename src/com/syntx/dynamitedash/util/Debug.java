package com.syntx.dynamitedash.util;

import com.syntx.dynamitedash.graphics.Screen;

// A static class used for debugging
public class Debug {

	private Debug() {
		
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, boolean fixed) { // Draws a rectangle to the screen
		screen.drawRect(x, y, width, height, 0xff0000, fixed);
	}
	
	public static void drawRect(Screen screen, int x, int y, int width, int height, int colour, boolean fixed) { // Draws a rectangle to the screen
		screen.drawRect(x, y, width, height, colour, fixed);
	}
	
}
