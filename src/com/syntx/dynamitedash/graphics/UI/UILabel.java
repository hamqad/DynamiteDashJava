package com.syntx.dynamitedash.graphics.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.syntx.dynamitedash.util.Vector2i;

public class UILabel extends UIComponent {
	
	public String text; // The text that the label will display
	private Font font; // The font the text will use
	public boolean dropShadow; // Weather the text should have a shadow or not

	public UILabel(Vector2i position, String text) { // Creates a label at a position with the specified text
		super(position);
		font = new Font("Helvetica", Font.PLAIN, 32); 
		this.text = text;
		colour = new Color(0xffe74c3c);
	}
	
	public UILabel setFont(Font font) { // Sets the font and returns the label
		this.font = font;
		return this;
	}

	
	public void render(Graphics g) { // Renders the label
		if(dropShadow) { // Creates a black shadow
		g.setFont(font);
		g.setColor(Color.black); 
		g.drawString(text, position.x + offset.x + 1, position.y + offset.y + 1);
		}
		// Renders the font onto the screen:
		g.setColor(colour);
		g.setFont(font);
		g.drawString(text, position.x + offset.x, position.y + offset.y);
	
	}
	
}
