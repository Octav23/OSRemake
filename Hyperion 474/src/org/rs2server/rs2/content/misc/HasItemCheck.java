package org.rs2server.rs2.content.misc;



import java.io.FileNotFoundException;

import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.GroundItem;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.Location;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.container.Bank;
import org.rs2server.rs2.model.container.Equipment;
import org.rs2server.rs2.util.Misc;


public class HasItemCheck {

	public boolean ownsCape(Player player) {
		if(player.getInventory().hasItem(new Item(2413))
				|| player.getInventory().hasItem(new Item(2412))
				|| player.getInventory().hasItem(new Item(2414))) {
			player.getActionSender().sendMessage("You already own a cape...");
			return true;
		}

		for(int j = 0; j < Bank.SIZE; j++) {
			if(player.getBank().hasItem(new Item(2412)) || player.getBank().hasItem(new Item(2413))
					|| player.getBank().hasItem(new Item(2414))) {
				player.getActionSender().sendMessage("You already own a cape...");
				return true;
			}
		}

		if(player.getEquipment().contains(2412) || player.getEquipment().contains(2413) ||
				player.getEquipment().contains(2414)) {
			player.getActionSender().sendMessage("You already own a cape...");
			return true;
		}

		return false;
	}

	public static boolean hasTrainingSword(Player player) {
		if(player.getInventory().hasItem(new Item(9703))) {
			return true;
		}

		for(int j = 0; j < Bank.SIZE; j++) {
			if(player.getBank().hasItem(new Item(9703))) {
				return true;
			}
		}

		if(player.getEquipment().contains(9703)) {
			return true;
		}

		return false;
	}

	public static boolean hasTrainingShield(Player player) {
		if(player.getInventory().hasItem(new Item(9704))) {
			return true;
		}

		for(int j = 0; j < Bank.SIZE; j++) {
			if(player.getBank().hasItem(new Item(9704))) {
				return true;
			}
		}

		if(player.getEquipment().contains(9704)) {
			return true;
		}

		return false;
	}
	
	public static boolean hasTrainingBow(Player player) {
		if(player.getInventory().hasItem(new Item(9705))) {
			return true;
		}

		for(int j = 0; j < Bank.SIZE; j++) {
			if(player.getBank().hasItem(new Item(9705))) {
				return true;
			}
		}

		if(player.getEquipment().contains(9705)) {
			return true;
		}

		return false;
	}
	
	public static boolean hasBronzeAxe(Player player) {
		if(player.getInventory().hasItem(new Item(1351))) {
			return true;
		}

		for(int j = 0; j < Bank.SIZE; j++) {
			if(player.getBank().hasItem(new Item(1351))) {
				return true;
			}
		}

		if(player.getEquipment().contains(1351)) {
			return true;
		}

		return false;
	}

	public static boolean hasTinderbox(Player player) {
		if(player.getInventory().hasItem(new Item(590))) {
			return true;
		}

		for(int j = 0; j < Bank.SIZE; j++) {
			if(player.getBank().hasItem(new Item(590))) {
				return true;
			}
		}

		if(player.getEquipment().contains(590)) {
			return true;
		}

		return false;
	}
	
	public void init() throws FileNotFoundException {
		
	}


}