package org.rs2server.rs2.content.misc;

import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.NPC;
import org.rs2server.rs2.model.NPCDefinition;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.tickable.Tickable;

public class SheepShear {
	
	public Item SHEAR = new Item(1735);
	
	public Item WOOL = new Item(1737);

	private final Player player;
	
	public SheepShear(Player player) {
		this.player = player;
	}
	
	public void shearSheep(final NPC sheep) {
		if(!player.getInventory().hasItem(SHEAR)) {
			player.getActionSender().sendMessage("You don't have any shears!");
			return;
		}
		player.playAnimation(Animation.create(893));
			World.getWorld().submit(new Tickable(2) {
				public void execute() {
					if(sheep == null){
						return;
					}
					player.getInventory().add(WOOL);
					sheep.transform(42);
					player.getActionSender().sendMessage("You shear the sheep.");
					World.getWorld().submit(new Event(15000) {
						public void execute() {
							if(sheep == null){
								return;
							}
							sheep.transform(43);
							stop();
						}
					});
					stop();
				}
			});
	}
}
