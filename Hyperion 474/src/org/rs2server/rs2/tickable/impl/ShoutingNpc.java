package org.rs2server.rs2.tickable.impl;

import org.rs2server.rs2.content.HAMHideout;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.NPC;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.tickable.Tickable;
import org.rs2server.rs2.util.Misc;


public class ShoutingNpc extends Tickable {
	
	
	public ShoutingNpc() {
		super(4);
	}
	public int sheepsShouted = 0;
	
	public void sheep(){
		sheepsShouted += 1;
		for(NPC npc : World.getWorld().getNPCs()) {
			if(npc.getDefinition().getId() == 43){
				if(sheepsShouted > 1){
					sheepsShouted = 0;
					return;
				}
				if(Misc.random(32) == 1 && sheepsShouted <= 1){
					npc.forceChat("Baa");
				}
			}
		}
	}
	public void execute() {
		HAMHideout.hamSpeech();
		sheep();
		
	}
	

}
