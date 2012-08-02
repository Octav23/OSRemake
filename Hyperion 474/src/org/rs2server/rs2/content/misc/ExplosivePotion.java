package org.rs2server.rs2.content.misc;

import org.rs2server.rs2.model.*;
import org.rs2server.rs2.tickable.Tickable;

/**
 * For explosive potions.
 * @author Canownueasy
 *
 */
public class ExplosivePotion {
	
	public static void execute(final Player player, final int slot) {
		player.getInventory().remove(new Item(4045), slot);
		player.playAnimation(Animation.create(7270));
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				player.forceChat("Ow! The liquid exploded!");
				player.getActionSender().sendMessage("The glass shatters and the liquid ignites.");
				player.inflictDamage(new Hit(15), null);
				player.playGraphics(Graphic.create(571));
				this.stop();
			}
		});
	}

}
