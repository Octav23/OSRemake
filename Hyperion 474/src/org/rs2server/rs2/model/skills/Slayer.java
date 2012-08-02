package org.rs2server.rs2.model.skills;


import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.util.Misc;

public class Slayer {
	
	/*
	 * 
	 * NOTE: You need ATLEAST 7 for each class. PLEASE DO NOT have less than 7 for each, or else the server WILL CRASH.
	 * 
	 */
	
	public int[] lowTasks = {5529, 100, 90, 118, 491, 181, 78};
	public int[] lowReqs = {12, 1, 5, 1, 8, 5, 6,};
	public String[] lowTaskNames = { "Yaks", "Goblins", "Skeletons", "Dwarves", "Ghosts", "Chaos Druids", "Giant Bats"
			};
	
	
	public int[] medTasks = {52,1587,134,82,127,119,117};
	public int[] medReqs = {45,30,40,45,40,40,40,};
	public String[] medTaskNames = { "Baby Blue Dragons", "Moss Giants", "Poison Spiders", "Lesser Demons", "Magical Axes", "Chaos Dwarves", "Hill Giants"
			};
	
	
	//TODO: REDO THESE PLEASE. ALSO RE-DO MED TASKS.
	public int[] highTasks = {5529, 100, 90, 118, 491, 181, 78};
	public int[] highReqs = {12, 1, 5, 1, 8, 5, 6,};
	public String[] highTaskNames = { "Yaks", "Goblins", "Skeletons", "Dwarves", "Ghosts", "Chaos Druids", "Giant Bats"
			};
	
	
	//CAUSES CRASHING FOR SOME REASON
	//public int[] highTasks = {1615,1618,5363,54,49,5529,82,83,834,8345};
	//public int[] highReqs = {85,50,90,65,65,65,60,30,39,34,};
	//public String[] highTaskNames = { "Abyssal Demons", "Blue Dragons", "Mithril Dragons", "Black Dragons", "Hellhounds", "Yaks", "Lesser Demons", "Baby Baby OOOH", "lolt", "lawl"
	//		};
	
	
	
	public void giveTask(final Player player) {
		if (player.getSkills().getCombatLevel() < 50)
			giveTask(1, player);
		else if (player.getSkills().getCombatLevel() >= 50 && player.getSkills().getCombatLevel() <= 90)
			giveTask(2, player);
		else if (player.getSkills().getCombatLevel() > 90 && player.getSkills().getCombatLevel() <= 126)
			giveTask(3, player);
		else
			giveTask(2, player);	
	}
	
	public void giveTask2(final Player player) {
		//Player player = (Player) player;
		for (int j = 0; j < lowTasks.length; j++) {
			if (lowTasks[j] == player.currentSlayerTask) {
				System.out.println("We already have a task.");
				//player.getActionSender().sendMessage("You already have an easy task... to kill " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + ".");
				return;		
			}	
		}
		giveTask(1, player);
	}
	
	public void giveTask(int taskLevel, final Player player) {
		int given = 0;
		int random = 0;
		String name = "";
		if (taskLevel == 1) {
			random = (int)(Math.random() * (lowTasks.length - 1));
			given = lowTasks[random];
			name = lowTaskNames[random];
		} else if (taskLevel == 2) {
			random = (int)(Math.random() * (medTasks.length - 1));
			given = medTasks[random];		
			name = medTaskNames[random];
		} else if (taskLevel == 3) {
			random = (int)(Math.random() * (highTasks.length - 1));
			given = highTasks[random];	
			name = highTaskNames[random];
		}
		if (!canDoTask(taskLevel,random, player)) {
			giveTask(taskLevel, player);
			return;
		}
		//Player player = player;
		player.currentSlayerTask = given;
		player.currentSlayerTaskAmount = Misc.random(15) + 15;
		player.currentSlayerTaskName = name;
		player.getActionSender().sendMessage("You have been assigned to kill " + player.currentSlayerTaskAmount + " " + player.currentSlayerTaskName + " as a slayer task.");
	
	}
	
	public boolean canDoTask(int taskLevel, int random, final Player player) {
		if (taskLevel == 1) {
			return player.getSkills().SLAYER >= lowReqs[random];		
		} else if (taskLevel == 2) {
			return player.getSkills().SLAYER >= medReqs[random];		
		} else if (taskLevel == 3) {
			return player.getSkills().SLAYER >= highReqs[random];			
		}
		return false;
	}
}