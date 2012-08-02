package org.rs2server.rs2.model.skills;

import java.util.Random;

import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.GameObject;
import org.rs2server.rs2.model.Graphic;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.ItemDefinition;
import org.rs2server.rs2.model.NPC;
import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.Constants;
import org.rs2server.rs2.model.Damage.Hit;
import org.rs2server.rs2.model.Damage.HitType;
import org.rs2server.rs2.model.region.Region;


public class Thieving {
	/**
	 * A static instance of the Random object provided by Sun.
	 * Use this as r.nextInt(maxVal);
	 */
	private static final Random r = new Random();
	
	private static final int[] THIEVING_STALLS = {
		4705, //fish stall..
	};
	
	private static final int[] THIEIVNG_STALL_LEVEL_REQUIREMENTS = {
		1, //fish stall..
	};
	
	private static final Item[][] THIEVING_STALL_LOOT = {
		{new Item(329), new Item(359), new Item(377), new Item(383), new Item(371), new Item(317), new Item(321), new Item(365), new Item(329), new Item(325), new Item(337), new Item(333)}, //Fish stall
	};
	
	private static final double[] THIEVING_EXPERIENCE = {
		16 * Constants.EXP_MODIFIER, //Fish stall.
	};
	
	public static boolean handleObjectClick(final Player player, final GameObject o) {
		if(o == null) {
			System.out.println("The fuck?");
			return false;
		}
		for(int index = 0; index < THIEVING_STALLS.length; index++) {
			if(THIEVING_STALLS[index] == o.getDefinition().getId()) {
				if(player.getSkills().getLevel(Skills.THIEVING) < THIEIVNG_STALL_LEVEL_REQUIREMENTS[index]) {
					player.getActionSender().sendMessage("You need a Thieving level of " + THIEIVNG_STALL_LEVEL_REQUIREMENTS[index] + " in order to steal from this stall.");
					return true;
				}
				final Region[] regions = World.getWorld().getRegionManager().getSurroundingRegions(o.getLocation());
				//player.getActionSender().sendMessage("You attempt to steal from the stall..");
								final int qIndex = index;
						Item loot = THIEVING_STALL_LOOT[qIndex][r.nextInt(THIEVING_STALL_LOOT[qIndex].length)];
					if(player.getInventory().add(loot)) {
						player.playAnimation(Animation.create(881));
						//for(final Region r : regions) {
								//for(final Player p : r.getPlayers()) {
								World.getWorld().register(new GameObject(o.getLocation(), 634, o.getType(), o.getDirection(), o.isLoadedInLandscape()));
								//}
							//}
						}
				final int fIndex = index;

					World.getWorld().submit(new Event(0) {
				
						public void execute() {
						Item loot = THIEVING_STALL_LOOT[fIndex][r.nextInt(THIEVING_STALL_LOOT[fIndex].length)];
						//if(player.getInventory().add(loot)) {
					/*for(final Region r : regions) {
								for(final Player p : r.getPlayers()) {
								World.getWorld().register(new GameObject(o.getLocation(), 634, o.getType(), o.getDirection(), o.isLoadedInLandscape()));
									//p.getActionSender().sendCreateObject(634, o.getType(), o.getDirection(), o.getLocation());
								}
							}*/
							//player.getActionSender().sendMessage("You successfully stole a " + loot.getDefinition().getName().toLowerCase() + ".");
							player.getSkills().addExperience(Skills.THIEVING, THIEVING_EXPERIENCE[fIndex]);
							World.getWorld().submit(new Event(3000) { //TODO: Array with the real delay?

								@Override
								public void execute() {
									for(final Region r : regions) {
										for(final Player p : r.getPlayers()) {
					World.getWorld().register(new GameObject(o.getLocation(), o.getId(), o.getType(), o.getDirection(), o.isLoadedInLandscape()));

										}
									}
									this.stop();
								}
								
							});
							this.stop();
						//} else {
							//player.getActionSender().sendMessage("...But quickly realize you have no space in your inventory.");
							//this.stop();
						//}
					}
					
				});
				return true;
			}
		}
		return false;
	}

}
