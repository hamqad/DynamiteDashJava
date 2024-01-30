package com.syntx.dynamitedash.graphics.UI;

import java.awt.Color;		
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.syntx.dynamitedash.util.Vector2i;

public class UIPanel extends UIComponent{
	
	private List<UIComponent> components = new ArrayList<UIComponent>(); // A list of components on the panel 
	private Vector2i size; // The position and size of the panel
	private boolean rounded; // Weather the panel is rounded or not 
	
	public UIPanel(Vector2i position, Vector2i size, boolean rounded) {
		super(position);
		this.rounded = rounded;
		this.position = position;
		this.size = size;
		colour = new Color(0x7f95a5a6, true);
	}
	
	public void addComponent(UIComponent component) { // Adds a component to the panel
		component.init(this); 
		components.add(component);
	}
	
	public void update() { // Updates the panel
		for(UIComponent component : components) { // Updates every component inside the panel
			component.setOffset(position); // Updates the components position
			component.update(); // Updates the component
		}
	}
	
	public void render(Graphics g) { // Renders the panel
		g.setColor(colour); // Sets the colour to the one specified
		 if(rounded) g.fillRoundRect(position.x, position.y, size.x, size.y, 100, 200); // Fills a rounded rectangle at the panels specifications
		 else g.fillRect(position.x, position.y, size.x, size.y); // Fills a rectangle at the panels specifications
		for(UIComponent component : components) { // Renders every component inside the panel
			component.render(g); 
		}
	}
	
	
}

