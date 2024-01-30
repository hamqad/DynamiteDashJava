package com.syntx.dynamitedash.entity.spawner;

import com.syntx.dynamitedash.entity.Entity;
import com.syntx.dynamitedash.entity.particle.Particle;
import com.syntx.dynamitedash.level.Level;

public abstract class Spawner extends Entity {

	public enum Type {   // Specifies what the spawner spawns
		MOB, PARTICLE, POWERUP;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {  // Places a spawner at the coordinates x and y with a specified type and amount that needs to be spawned
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
}
