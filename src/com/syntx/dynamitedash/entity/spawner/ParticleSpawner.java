package com.syntx.dynamitedash.entity.spawner;

import com.syntx.dynamitedash.entity.particle.Particle;
import com.syntx.dynamitedash.graphics.Sprite;
import com.syntx.dynamitedash.level.Level;

public class ParticleSpawner extends Spawner {
	
	private int life;
	
	public ParticleSpawner(int x, int y, int life, int amount, Level level, Sprite sprite, boolean collide) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		
		for(int i = 0; i < amount; i++) { // Loops through each element and adds the specified amount of particles to the level
			 level.add(new Particle(x, y, life, sprite, collide));
			}
		}
		
	}
	
