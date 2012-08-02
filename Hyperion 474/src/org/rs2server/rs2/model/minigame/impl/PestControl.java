package org.rs2server.rs2.model.minigame.impl;

import org.rs2server.rs2.model.Location;
import org.rs2server.rs2.model.NPC;
import org.rs2server.rs2.model.NPCDefinition;
import org.rs2server.rs2.model.World;

@SuppressWarnings("unused")
public class PestControl extends AbstractMinigame {
	
	public void init() {
		
	}
	
	/**
	 * Spawns the pest control NPCs.
	 */
	public void spawnNpcs() {
		NPCDefinition def = null;
		NPC[] npcList = {
				new NPC(def, Location.create(2659, 2592, 0), null, null, 1)
				};
		for(NPC npcs : npcList) {
			def = NPCDefinition.forId(npcs.getDefinition().getId());
			NPC generated = new NPC(def, npcs.getLocation(), npcs.getLocation(), npcs.getLocation(), 1);
			World.getWorld().getNPCs().add(npcs);
		}
	}
}