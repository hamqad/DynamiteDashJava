package com.syntx.dynamitedash.graphics.UI;

import java.awt.Color;
import java.awt.Graphics;

import com.syntx.dynamitedash.util.Vector2i;

public class UIComponent {
	
	public Vector2i  position, size;  // The position and size of the component
	protected Vector2i offset; // The offset of the component
	public Color colour; // The colour of the component
	protected UIPanel panel; // The Panel that the component exists in
	
	public boolean active = true; // Weather the component is active or not 
	
	public UIComponent(Vector2i position) {
		this.position = position;
		offset = new Vector2i();
	}
	
	public UIComponent(Vector2i position, Vector2i size) {
		this.position = position;
		this.size = size;
		offset = new Vector2i();
	}
	
	void init(UIPanel Panel) { // Sets the panel to the current panel
		this.panel = panel;
	}
	
	public UIComponent setColour (int colour) { // Sets the colour and returns the component 
		this.colour = new Color(colour);
		return this;
	}
	
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
	public Vector2i getAbsolutePosition() { // Returns the position of the component
		return new Vector2i(position).add(offset);
	}

	void setOffset(Vector2i offset) { // Sets the offset of the component
		this.offset = offset;
	}
	
}

