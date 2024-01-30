package com.syntx.dynamitedash.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;

import org.w3c.dom.ranges.RangeException;

import com.syntx.dynamitedash.util.Vector2i;

public class UIProgressBar extends UIComponent {
	
	private Vector2i size; // The size of the progress bar
	private double progress; // How much of the bar is full (0 to 1)	
	
	private Color foregroundColour; // The colour of the bar when complete

	public UIProgressBar(Vector2i position, Vector2i size) {
		super(position);
		this.size = size;
		
		foregroundColour = new Color(0xff00ff);
	}
	
	public void setProgress(double progress) {
		if(progress < 0.0 || progress > 1.0) {  // Throws an exception if the progress isn't between 0 and 1 
			throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Progress must be between 0.0 and 1.0!");
		}
		this.progress = progress;
	}
	

	public void setForegroundColour(int colour) { // Sets the foreground colour
		this.foregroundColour = new Color(colour);
	}
	
	public double getProgress() { // Returns the progress of the bar
		return progress;
	}
		
	public void update() {
		
	}
	
	public void render(Graphics g) { // Renders the progress bar onto the screen
		// Renders the part of the bar that is filled:
		g.setColor(colour); 
		g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);
		// Renders the part of the bar that is empty:
		g.setColor(foregroundColour);
		g.fillRect(position.x + offset.x, position.y + offset.y, (int) (progress * size.x), size.y);
		
	}
	
	
		
}
