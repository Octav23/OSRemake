package org.rs2server.rs2.tickable.impl;

import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.tickable.*;

public class WorldSave extends Tickable {


	/**
	*
	*	The larger this number is, the more time it takes to save.
	*	Used to be 5. That's like every 5 seconds.
	*	Saving too much = lots of lag || Saving too little = players whine.
	*
	*/
	public WorldSave() {
		super(5);
	}
	
	public void execute() {
		for(final Player p : World.getWorld().getPlayers()) {
			if(p != null) {
				World.getWorld().save(p);
			}
		}
	}

}
