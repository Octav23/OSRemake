package org.rs2server.rs2.content;

import java.util.Random;

import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.action.Action.AnimationPolicy;
import org.rs2server.rs2.action.Action.CancelPolicy;
import org.rs2server.rs2.action.Action.StackPolicy;
import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Entity;
import org.rs2server.rs2.model.GameObject;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.model.skills.Agility;
import org.rs2server.rs2.tickable.Tickable;
import org.rs2server.rs2.util.Misc;

public class EnvironmentHandler {
	

	public static void pickItem(final Player player, final Item item, final String message, final GameObject obj) {
		if(!player.canOption) {
			return;
		}
		
		if(!player.getInventory().hasRoomFor(item)) {
			player.getActionSender().sendMessage("You don't have enough inventory space.");
			return;
		}

		player.canOption = false;
		player.playAnimation(Animation.create(7270));
		player.getInventory().add(item);
		player.getActionSender().sendMessage(message);

		World.getWorld().submit(new Event(600) {
			public void execute() {
				player.canOption = true;
				stop();
			}
		});
		
		if(Misc.random(4) == 1){
			World.getWorld().register(new GameObject(obj.getLocation(), -1, 10, 0, false));
			
			World.getWorld().submit(new Tickable(12) {
				public void execute() {
					World.getWorld().register(new GameObject(obj.getLocation(), obj.getId(), 10, 0, false));
					this.stop();
			}
		});
			
		}
		
	}
		
	
	public static void actionOption1(Player player, final int id, GameObject obj){
		/**
		 * The random number generator.
		 */
		final Random random = new Random();
		
		switch (id){
		
		case 365:   // sacks
			switch (random.nextInt(17)) {

			case 1:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
            player.getActionSender().sendMessage("You search the sacks and find only dust...");
            break;
			case 2:
            player.getActionSender().sendMessage("You search the sacks and you find a needle! It's your lucky day!");
            player.getInventory().add(new Item(1733, 1));
            break;
			case 3:
            player.getActionSender().sendMessage("You search the sacks and a spider bites you! Ouch!");
            break;
			case 4:
            player.getActionSender().sendMessage("You search the sacks and you find an old cabbage! Yum!");
            player.getInventory().add(new Item(1963, 1));
            break;
			case 5:
            player.getActionSender().sendMessage("You search the sacks and you find nothing...");
            break;
			case 6:
            player.getActionSender().sendMessage("You search the sacks and realise you have better things to do.");
            break;
			case 7:
            player.getActionSender().sendMessage("You search the sacks and find a gold coin! Lucky coin!");
            player.getInventory().add(new Item(995, 1));
            break;
			case 8:
            player.getActionSender().sendMessage("You search the sacks in hope for thieving experience.");
            break;
			case 9:
            player.getActionSender().sendMessage("You search the sacks and realise you have better things to do.");
            break;
			
			}
		break;
		
		case 18493: //signpost
			player.getActionSender().sendMessage("North: Varrock");
			player.getActionSender().sendMessage("West: Lumbridge");
			player.getActionSender().sendMessage("East: Al Kharid");
			break;
			
		case 4671: //bookcase medium
			player.getActionSender().sendMessage("You search the bookcase and you find nothing of interest.");
			break;
			
		case 380://bookcase large
			player.getActionSender().sendMessage("You search the bookcase and you find nothing of interest.");
			break;
			
		case 1556: // sign? doesn't work.
			player.getActionSender().sendMessage("WARNING: Climb down at your own risk!");
			break;
			
		case 354:// crate
			player.getActionSender().sendMessage("You search the crate and you find nothing.");
			break;
		
		case 9611: // crate @ HAM dungeon
			player.getActionSender().sendMessage("You search the crate and you find nothing.");
			break;
		}
		
		
	}
	
	public static void actionOption2(Player player, final int id, GameObject obj){
		switch (id){
		
		case 1161: //Cabbages
			pickItem(player, new Item(1965), "You pick a cabbage.", obj);
			return;
			
		case 5583:
		case 5584: //Wheat
		case 5585:
		case 313:
			pickItem(player, new Item(1947), "You pluck some grain.", obj);
			return;				
			
		case 312: //Potatoes
			pickItem(player, new Item(1942), "You grab a potato.", obj);
			return;
			
		case 3366:
			pickItem(player, new Item(1957), "You grab an onion.", obj);
			return;

		case 2646:
			pickItem(player, new Item(1779), "You pluck some flax.", obj);
			return;
			
		}
	}
	
}
