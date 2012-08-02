package org.rs2server.rs2.content.misc;

import org.rs2server.rs2.ScriptManager;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.Mob.InteractionMode;

public class Tutors {

	public static void getRangedEquipment(Player player){
		if(player.tutorDelay > 0){
			player.getActionSender().sendMessage("You have to wait "+player.tutorDelay+" minutes before you can claim from a tutor again.");						
			return;
		}
		if(player.getInventory().freeSlots() < 2) {
			player.getActionSender().sendMessage("Your inventory is full");
			return;
		}
		if(player.tutorDelay == 0){
			player.tutorDelay = 30;
		if(HasItemCheck.hasTrainingBow(player) == false){
			player.getInventory().add(new Item(9704, 1));
			player.getActionSender().sendMessage("A training bow has been added into your inventory.");
			
		} 
		player.getInventory().add(new Item(9706, 30));
		player.getActionSender().sendMessage("30 Training arrows have been added to your inventory.");
		}
		return;
		
	}
	

	public static void getMagicEquipment(Player player){
		if(player.tutorDelay > 0){
			player.getActionSender().sendMessage("You have to wait "+player.tutorDelay+" minutes before you can claim from a tutor again.");							
			return;
		}
		if(player.getInventory().freeSlots() < 2) {
			player.getActionSender().sendMessage("Your inventory is full");
			return;
		}
		if(player.tutorDelay == 0 && player.getInventory().freeSlots() >= 2){
			player.tutorDelay = 30;
			player.getInventory().add(new Item(556, 30));
			player.getInventory().add(new Item(558, 30));
			player.getActionSender().sendMessage("30 Air runes have been added to your inventory.");
			player.getActionSender().sendMessage("30 Mind runes have been added to your inventory.");
			return;
		}
		return;
	} 

}
