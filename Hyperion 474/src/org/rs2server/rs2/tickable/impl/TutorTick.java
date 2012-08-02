package org.rs2server.rs2.tickable.impl;

import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.Prayers;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.tickable.Tickable;


public class TutorTick extends Tickable {

	public TutorTick() {
		super(100);
	}
	
	public void execute() {
		for(Player p : World.getWorld().getPlayers()) {
			if(p != null) {
				checkMagic(p);
			}
		}
	}
	
	private void checkMagic(final Player player) {
		if(player.tutorDelay <= 0) {
			return;
		}
		if(player.tutorDelay > 0){
			player.tutorDelay -= 1;
		}
	}
	
}
