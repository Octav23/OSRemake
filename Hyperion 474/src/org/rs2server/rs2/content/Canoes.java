package org.rs2server.rs2.content;

import org.rs2server.rs2.Constants;
import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.action.Action.AnimationPolicy;
import org.rs2server.rs2.action.Action.CancelPolicy;
import org.rs2server.rs2.action.Action.StackPolicy;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.GameObject;
import org.rs2server.rs2.model.Location;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.model.skills.Woodcutting.Hatchet;
import org.rs2server.rs2.util.Misc;

public class Canoes {
	
	/**
	 * The hatchet we are using.
	 */
	private Hatchet hatchet;


	public static void chooseCanoeInterface(Player player){
		
		if(player.getSkills().getLevelForExperience(Skills.WOODCUTTING) >= 27){ //A Dugout
			player.getActionSender().sendInterfaceConfig(52, 6, false);
			player.getActionSender().sendInterfaceConfig(52, 32, true);
		}
		if(player.getSkills().getLevelForExperience(Skills.WOODCUTTING) >= 42){ //A Stable Dugout
			player.getActionSender().sendInterfaceConfig(52, 3, false);
			player.getActionSender().sendInterfaceConfig(52, 35, true);
		}
		if(player.getSkills().getLevelForExperience(Skills.WOODCUTTING) >= 57){ //A Waka
			player.getActionSender().sendInterfaceConfig(52, 13, false);
			player.getActionSender().sendInterfaceConfig(52, 29, true);
		}
		player.getActionSender().sendInterface(52, true);
		
		
	}
	
	public static void travelMap(Player player, String location, int canoe){
		//0 = Log, 1 = Dugout, 2 = Stable Dugout, 3 = Waka
		if(location == "Lumbridge"){
			player.getActionSender().sendInterfaceConfig(53, 50, false);
			player.getActionSender().sendInterfaceConfig(53, 11, true);
			switch (canoe){
			case 0:
				player.getActionSender().sendInterfaceConfig(53, 31, true); //Hide Wilderness
				player.getActionSender().sendInterfaceConfig(53, 38, true); //Edgeville
				player.getActionSender().sendInterfaceConfig(53, 15, true); //Barbarian village
				break;
			case 1:
				player.getActionSender().sendInterfaceConfig(53, 31, true); //Hide Wilderness
				player.getActionSender().sendInterfaceConfig(53, 38, true); //Edgeville
				break;
			case 2:
				player.getActionSender().sendInterfaceConfig(53, 31, true); //Hide Wilderness
				break;
				
			}
			
		}
		if(location == "Guild"){
			player.getActionSender().sendInterfaceConfig(53, 47, false);
			player.getActionSender().sendInterfaceConfig(53, 13, true);
			switch (canoe){
			case 0:
				player.getActionSender().sendInterfaceConfig(53, 31, true); //Hide Wilderness
				player.getActionSender().sendInterfaceConfig(53, 38, true); //Edgeville
				break;
			case 1:
				player.getActionSender().sendInterfaceConfig(53, 31, true); //Hide Wilderness
				break;
				
			}
			
		}
		if(location == "BarbarianVillage"){
			player.getActionSender().sendInterfaceConfig(53, 44, false);
			player.getActionSender().sendInterfaceConfig(53, 15, true);
			switch (canoe){
			case 0:
				player.getActionSender().sendInterfaceConfig(53, 31, true); //Hide Wilderness
				player.getActionSender().sendInterfaceConfig(53, 11, true); //Lumbridge
				break;
				
			}
			
		}
		if(location == "Edgeville"){
			player.getActionSender().sendInterfaceConfig(53, 36, false);
			player.getActionSender().sendInterfaceConfig(53, 38, true);
			switch (canoe){
			case 0:
				player.getActionSender().sendInterfaceConfig(53, 13, true); //Champion's Guild
				player.getActionSender().sendInterfaceConfig(53, 11, true); //Lumbridge
				break;
			case 1:
				player.getActionSender().sendInterfaceConfig(53, 11, true); //Lumbridge
				break;
				
			}
			
		}
		player.getActionSender().sendInterface(53, true);
		
	}
	public String stationLocation;
	public String state = "chop"; //chop, float, push, travel, map
	public String craftedCanoe = "log"; // Log, dugout, stableDugout, waka
	
	public void getStationLocation(Player player, GameObject obj){
		if(obj.getLocation().getX() == 3241 && obj.getLocation().getY() == 3235){ //Lumbridge
			stationLocation = "Lumbridge";
			
		}
		if(obj.getLocation().getX() == 3200 && obj.getLocation().getY() == 3341){ //Guild
			stationLocation = "Guild";
			
		}
		if(obj.getLocation().getX() == 3110 && obj.getLocation().getY() == 3409){ //Barbarian Village
			stationLocation = "BarbarianVillage";
			
		}
		if(obj.getLocation().getX() == 3130 && obj.getLocation().getY() == 3508){ //Edgeville
			stationLocation = "Edgeville";
			
		}
		
	}
	public void resetConfigs(Player player){
			player.getActionSender().sendConfig(674, 0);	
		
	}
	public boolean canHarvest(Player player) {
		for(Hatchet hatchet : Hatchet.values()) {
			if((player.getInventory().contains(hatchet.getId()) || player.getEquipment().contains(hatchet.getId()))
							&& player.getSkills().getLevelForExperience(Skills.WOODCUTTING) >= hatchet.getRequiredLevel()) {
				this.hatchet = hatchet;
				break;
			}
		}
		if(hatchet == null) {
			player.getActionSender().sendMessage("You do not have an axe that you can use.");
			return false;
		}
		return true;
	}
	
	public void clickCanoeInterface(Player player, int button){
		switch(button){
		case 24:
			craftedCanoe = "log";
			state = "push";
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 1);	
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 300);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 100000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 100000000);	
			}
			break;
		case 25:
			craftedCanoe = "dugout";
			state = "push";
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 2);					
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 600);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 150000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 110000000);	
			}
			break;
		case 26:
			craftedCanoe = "stableDugout";
			state = "push";
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 3);					
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 800);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 200000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 130000000);	
			}
			break;
		case 27:
			craftedCanoe = "waka";
			state = "push";
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 4);					
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 1100);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 300000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 150000000);	
			}
			break;
			
		case 37:
			player.getActionSender().sendMessage("You need atleast a woodcutting level of 27 to make this canoe.");
			break;
		case 38:
			player.getActionSender().sendMessage("You need atleast a woodcutting level of 42 to make this canoe.");
			break;
		case 39:
			player.getActionSender().sendMessage("You need atleast a woodcutting level of 57 to make this canoe.");
			break;
		}
		player.getActionSender().removeAllInterfaces();
	}
	
	public void clickTravelInterface(Player player, int button){
		switch(button){
		case 11:
		case 32: //Lumbridge
			player.setTeleportTarget(Location.create(3240, 3243, 0));
			break;
		case 56:
		case 13:
		case 33: //Guild
			player.setTeleportTarget(Location.create(3195, 3345, 0));
			break;
		case 15:
		case 34: //Barbarian village
			player.setTeleportTarget(Location.create(3109, 3415, 0));
			break;
		case 38:
		case 39: //Edgeville
			player.setTeleportTarget(Location.create(3128, 3500, 0));
			break;
		case 35: //Wilderness
			player.setTeleportTarget(Location.create(3141, 3796, 0));
			break;
		}
		player.getActionSender().removeAllInterfaces();
		state = "chop";
	}
	
	public void pushObject(Player player, GameObject obj){
		player.playAnimation(Animation.create(7270));		
	

				
		if(craftedCanoe == "log"){
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 11);	
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 3000);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 750000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 200000000);	
			}
		}
		if(craftedCanoe == "dugout"){
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 12);	
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 3300);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 800000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 210000000);	
			}
		}
		if(craftedCanoe == "stableDugout"){
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 13);	
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 3500);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 900000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 230000000);	
			}
		}
		if(craftedCanoe == "waka"){
			if(stationLocation == "Lumbridge"){
				player.getActionSender().sendConfig(674, 14);	
			}
			if(stationLocation == "Guild"){
				player.getActionSender().sendConfig(674, 3800);	
			}
			if(stationLocation == "BarbarianVillage"){
				player.getActionSender().sendConfig(674, 950000);	
			}
			if(stationLocation == "Edgeville"){
				player.getActionSender().sendConfig(674, 240000000);	
			}
		}
		state = "travel";
	}
	
	
	public void showTravelMap(Player player){
		int canoeId = 0;
		if(craftedCanoe == "log"){
			canoeId = 0;			
		}
		if(craftedCanoe == "dugout"){
			canoeId = 1;	
		}
		if(craftedCanoe == "stableDugout"){
			canoeId = 2;	
		}
		if(craftedCanoe == "waka"){
			canoeId = 3;			
		}
		travelMap(player, stationLocation, canoeId);
	}

	public void handleObject(Player player, GameObject obj){
		if(player.getSkills().getLevelForExperience(Skills.WOODCUTTING) < 12){
			player.getActionSender().sendMessage("You need atleast a woodcutting level of 12 to chop the canoe station.");
			return;
		}
		getStationLocation(player, obj);
		
		if(state == "chop"){
			
			if(canHarvest(player) == false){
				return;
			}
			
		chopCanoe(player, obj);
		player.playAnimation(hatchet.getAnimation());
		}
		
		if(state == "float"){
			chooseCanoeInterface(player);
			return;
		}
		if(state == "push"){
			pushObject(player, obj);
			return;
		}
		if(state == "travel"){
			showTravelMap(player);
			return;
			
		}
	}
	
	public void chopCanoe(final Player player, GameObject obj){
		
	
		player.getActionQueue().addAction(new Action(player, 3) {
			@Override
			public void execute() {

				if(stationLocation == "Lumbridge"){
					player.getActionSender().sendConfig(674, 10);					
				}
				if(stationLocation == "Guild"){
					player.getActionSender().sendConfig(674, 2600);	
					
				}
				if(stationLocation == "BarbarianVillage"){
					player.getActionSender().sendConfig(674, 700000);	
					
				}
				if(stationLocation == "Edgeville"){
					player.getActionSender().sendConfig(674, 180000000);	
					
				}
					getMob().playAnimation(Animation.create(-1));
					state = "float";
					this.stop();	
				
			}
			@Override
			public AnimationPolicy getAnimationPolicy() {
				return AnimationPolicy.RESET_NONE;
			}
			@Override
			public CancelPolicy getCancelPolicy() {
				return CancelPolicy.ALWAYS;
			}
			@Override
			public StackPolicy getStackPolicy() {
				return StackPolicy.NEVER;
				}									
			});		
		}
	
	
	
	
}
