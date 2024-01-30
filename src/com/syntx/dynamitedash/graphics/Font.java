package com.syntx.dynamitedash.graphics;

public class Font {
	
	private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 16); // The sprite sheet of the font
	private static Sprite[] characters = Sprite.split(font); // An array of each individual character sprite from the font spritesheet 
	
	private static String charIndex =  "ABCDEFGHIJKLM" + // This is the order of the characters as represented in the characters spritesheet
										"NOPQRSTUVWXYZ" + //
										"abcdefghijklm" + //
										"nopqrstuvwxyz" + //
										"0123456789.,'" + //
										"'\"\";:!@$%()-+";
	
	public Font() {
		
	}
	
	public void render(String text, int x, int y, Screen screen) { // Renders the font to the screen at a specific position
		render(text, x, y, 0, 0, screen);
	}	

	public void render(String text, int x, int y, int colour, Screen screen) { // Renders the font to the screen at a specific position and a specific colour
		render(text, x, y, 0, colour, screen);	
	}
	
	
		public void render(String text, int x, int y, int spacing, int colour, Screen screen) { // Renders the font to the screen at a specific position, colour and spacing of the characters
		int xOffset = 0; // The amount the width of each character is offset by
		int line = 0; // indicates which line the text is on
		for(int i = 0; i < text.length(); i++) { // Loops through each character from the given text to output 
			xOffset += 16 + spacing;
			int yOffset = 0; // The amount the height of the character is offset by 
			char currentChar = text.charAt(i); // The current character
			if (currentChar == 'g' || currentChar == 'y' || currentChar == 'p' || currentChar == 'q' || currentChar == 'j' || currentChar == ',') yOffset = 2; // Drops the character slightly if need be 
			if (currentChar == '\n') {
				xOffset = 0; // Resets the x offset whenever a new line is created
				line++; // Creates a new line whenever the character is equal to \n
			}
			int index = charIndex.indexOf(currentChar); // Sets index to whichever index the current character corresponds to in the charIndex string
			if(index == -1) continue;
			screen.renderTextCharacter(x + xOffset, y + line * 20 + yOffset, characters[index], colour, false); // Prints the text that was given to be output from the corresponding text in the fonts spritesheet
		}
	}
	
}
