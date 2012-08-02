package org.rs2server.rs2.model;


public class Ladders {

	public static  void perform(Player player, String way, String type, int height){
		final int x = player.getLocation().getX();
		final int y = player.getLocation().getY();
		final int z = player.getLocation().getZ();
		
				if(way == "up"){
					player.setTeleportTarget(Location.create(x, y, height));
					player.getActionSender().sendMessage("You climb up the stairs.");
				} else {
					player.setTeleportTarget(Location.create(x, y, height));
					player.getActionSender().sendMessage("You climb down the stairs.");	
					return;				
				}
					player.playAnimation(Animation.create(828));
		
		return;
	}

	public static void actionOption1(Player player, final int id, GameObject obj){
		switch (id){
		
		/**
		 * Ladders
		 */
		case 1746:
			perform(player, "down", "ladder", 1);
		break;

		case 1747:
			perform(player, "up", "ladder", 1);
		break;
		
		/**
		 * Staircases
		 */
		case 1740:
			perform(player, "down", "stairs", 1);
			break;
		
		case 1738:
			perform(player, "up", "stairs", 1);
			break;

			
		/**
		 * Dialogue based stairs
		 */
		case 1739:
		case 1748:
			DialogueManager.openDialogue(player, 214);
		break;
		
		}
	}

	public static void actionOption2(Player player, final int id){
		switch (id){
		
		/**
		 * Ladders
		 */
		case 1748:
			perform(player, "up", "ladder", 2);
		break;
			

		/**
		 * Staircases
		 */
		case 1739:
			perform(player, "up", "stairs", 2);
		break;
		}		
	}

	public static void actionOption3(Player player, final int id){
		switch (id){

		/**
		 * Ladders
		 */
		case 1748:
			perform(player, "down", "ladder", 0);
		break;

		/**
		 * Staircases
		 */
		case 1739:
			perform(player, "down", "stairs", 0);
		break;
		
		}		
	}
		


	/**
	 * Trapdoors
	 * 
	 */
	public static void trapdoorsOption1(Player player, final int id, GameObject obj){
		switch (id){
		case 14880: /*Going downstairs*/
			player.playAnimation(Animation.create(828));
			player.setTeleportTarget(Location.create(player.getLocation().getX(), player.getLocation().getY() + 6400, 0));
			break;
			
		case 1755: /*Going upstairs*/
			player.playAnimation(Animation.create(828));
			player.setTeleportTarget(Location.create(player.getLocation().getX(), player.getLocation().getY() - 6400, 0));
			break;
			
		case 14879: /*Opening trapdoor*/
			player.playAnimation(Animation.create(827));
			World.getWorld().register(new GameObject(obj.getLocation(), 14880, 22, 0, false));
			break;
		
		}
	}
	
	
}
