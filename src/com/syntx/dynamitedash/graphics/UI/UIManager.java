package com.syntx.dynamitedash.graphics.UI;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class UIManager {

	public List<UIPanel> panels = new ArrayList<UIPanel>(); // A list of panels on the user interface
	public List<UIButton> buttons = new ArrayList<UIButton>(); // A list of panels on the user interface
	boolean cleared = false;
	
	public UIManager() {
		
	}
	
	public void addPanel(UIPanel panel) { // Adds a panel to the user interface
		panels.add(panel);
	}
	
	public void addButton(UIButton button) { // Adds a panel to the user interface
		buttons.add(button);
	}
	
	public void clear() {
		panels.clear();
		buttons.clear();
	}
	
	public void update() { // Updates the user interface
		for(UIPanel panel : panels) { // Updates every panel on the user interface
			panel.update();
		}
		for(UIButton button : buttons) { // Updates every panel on the user interface
		//	System.out.println(SwingUtilities.isEventDispatchThread());
			button.update();
		}
}
	
	public void render(Graphics g) { // Renders the user interface using java's in built graphics renderer
		for(UIPanel panel : panels) { // Renders every panel on the user interface
			panel.render(g);
		}
		for(UIButton button : buttons) {
			button.render(g);
		}
	}
	
}

