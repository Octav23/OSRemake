package org.rs2server.rs2.model.skills;

import java.util.Random;

import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.Constants;
import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.model.Player;

public class Crafting {
			
	private static final Animation LEATHER_CRAFTING = Animation.create(1249);
	private static final Random r = new Random();
	private static final Item THREAD = new Item(1734);
		//private static final Player player;
	
	public static void leatherCrafting(Player player, int levelReq, int amount, int leatherId, int leatherAmount, int itemToRecieve, double xpToReceive) {
		//player.getActionSender().sendCloseInterface();
		if (player.getSkills().getLevel(Skills.CRAFTING) >= levelReq) {
			if(player.getInventory().contains(1734)) {
				if(player.getInventory().contains(leatherId, leatherAmount)) {
					if(amount > player.getInventory().getCount(leatherId)) {
						amount = player.getInventory().getCount(leatherId);
					}
					player.getActionQueue().addAction(new LeatherCraftingAction(player, amount, leatherId, leatherAmount, itemToRecieve, xpToReceive));
				} else {
					player.getActionSender().sendMessage("You don't have enough hides.");
				}
			} else {
				Item item = new Item(itemToRecieve);
				player.getActionSender().sendMessage("You'll need some thread.");
			}
		} else {
			Item item = new Item(itemToRecieve);
			player.getActionSender().sendMessage("You need a Crafting level of " + levelReq + " in order to craft a " + item.getDefinition().getName() + ".");
		}
	}
	
	
	public enum DragonLeatherType {
		GREEN_DRAGON_LEATHER,
		BLUE_DRAGON_LEATHER,
		RED_DRAGON_LEATHER,
		BLACK_DRAGON_LEATHER,
	}
	
	/*public enum DragonLeatherType {
		GREEN_DRAGON_LEATHER(1753),
		BLUE_DRAGON_LEATHER(1751),
		RED_DRAGON_LEATHER(1749),
		BLACK_DRAGON_LEATHER(1747);
		
		private int id;
		
		private DragonLeatherType(int id) {
			this.id = id;
		}
		
		/**
		 * Gets the dragon leather id.
		 * @return The dragon leather id.
		 *//*
		public int toId() {
			return id;
		}
	}*/
	
   		
	public static boolean dragonLeatherCrafting(Player player, int button) {
		switch(player.getCraftingVariables().getLeatherType()) {

		
			//switch(button) {
			default: System.out.println("we clicked "+button+".");
		//}
		
	}
	return true;
}

	
	public static boolean hardLeatherBodies(Player player, int childClicked) {
		switch(childClicked) {
		case 6://Make 1.
			leatherCrafting(player, 28, 1, 1743, 1, 1131, 35);
			break;
		case 5://Make 5.
			leatherCrafting(player, 28, 5, 1743, 1, 1131, 35);
			break;
		case 4://Make x.
					System.out.println("we got here! aaaaaamamfgg");
			//player.getInterfaceState().openEnterAmountInterface(309);
			//player.setTemporaryAttribute("xHardLeatherBodiesLvlReq", 28);
			//player.getActionSender().sendAmountRequest();
			break;
		case 3://Make all.
			leatherCrafting(player, 28, player.getInventory().getCount(1131), 1743, 1, 1131, 35);
			break;
		}
		return false;
	}
	
	public static boolean leatherCraftingInterface(Player player, int buttonId) {
		switch(buttonId) {
		default:
		System.out.println("we clicked: "+buttonId);
		break;
		/*
		 * Normal gloves.
		 * */
		case 127://Craft 1.
			leatherCrafting(player, 1, 1, 1741, 1, 1059, 13.8);
			return true;
		case 126://Craft 5.
			leatherCrafting(player, 1, 5, 1741, 1, 1059, 13.8);
			return true;
		case 108://Craft All.
			leatherCrafting(player, 1, 28, 1741, 1, 1059, 13.8);
			return true;
		/*
		 * Armour
		 */
		case 125: //Craft 1.
			leatherCrafting(player, 14, 1, 1741, 1, 1129, 27);
			return true;
		case 124: //Craft 5.
			leatherCrafting(player, 14, 5, 1741, 1, 1129, 27);
			return true;
		case 114: //Craft All.
			leatherCrafting(player, 14, 28, 1741, 1, 1129, 27);
			return true;
		/*
		 * Boots
		 */
		case 129://Craft 1.
			leatherCrafting(player, 7, 1, 1741, 1, 1061, 16.3);
			return true;
		case 117://Craft 5.
			leatherCrafting(player, 7, 5, 1741, 1, 1061, 16.3);
			return true;
		case 109://Craft all
			leatherCrafting(player, 7, 28, 1741, 1, 1061, 16.3);
			return true;
			/*
			 * Vambs
			 */
		case 131://Craft 1
			leatherCrafting(player, 11, 1, 1741, 1, 1063, 22);
			return true;
		case 130://Craft 5.
			leatherCrafting(player, 11, 5, 1741, 1, 1063, 22);
			return true;
		case 110://Craft all
			leatherCrafting(player, 11, 28, 1741, 1, 1063, 22);
			return true;
			/*
			 * Chaps
			 */
		case 133://Craft 1.
			leatherCrafting(player, 18, 1, 1741, 1, 1095, 27);
			return true;
		case 132://Craft 5.
			leatherCrafting(player, 18, 5, 1741, 1, 1095, 27);
			return true;
		case 111://Craft all.
			leatherCrafting(player, 18, 28, 1741, 1, 1095, 27);
			return true;
			/*
			 * Coif
			 */
		case 135://Craft 1
			leatherCrafting(player, 38, 1, 1741, 1, 1169, 37);
			return true;
		case 134://Craft 5.
			leatherCrafting(player, 38, 5, 1741, 1, 1169, 37);
			return true;
		case 112://Craft 10.
			leatherCrafting(player, 38, 28, 1741, 1, 1169, 37);
			return true;
			/*
			 * Cowl
			 */
		case 137://Craft 1
			leatherCrafting(player, 9, 1, 1741, 1, 1167, 18.5);
			return true;
		case 136://Craft 5.
			leatherCrafting(player, 9, 5, 1741, 1, 1167, 18.5);
			return true;
		case 113://Craft 10.
			leatherCrafting(player, 9, 28, 1741, 1, 1167, 18.5);
			return true;
		}
		return false;
	}
		public static boolean tanningButtons(Player player, int buttonId) {
		switch(buttonId) {
		/*
		 * Soft leather.
		 */
		case 148: //tan 1.
			tan(player, 1, 1, 1739, 1741);
			return true;
		case 140: //tan 5
			tan(player, 5, 1, 1739, 1741);
			return true;
		case 132: //X
			//player.getInterfaceState().openEnterAmountInterface(324, 1741);
			//player.getActionSender().sendAmountRequest();
			/*player.setTemporaryAttribute("tanXDeletedItem", 1739);
			player.setTemporaryAttribute("tanXAddedItem", 1741);
			player.setTemporaryAttribute("tanXPayment", 1);*/
			return true;
		case 124: //All
			tan(player, player.getInventory().getCount(1739), 1, 1739, 1741);
			return true;
			/*
			 * Hard leather.
			 */
		case 149: //tan 1.
			tan(player, 1, 3, 1739, 1743);
			return true;
		case 141: //tan 5
			tan(player, 5, 3, 1739, 1743);
			return true;
		case 133: //X
		//	player.getInterfaceState().openEnterAmountInterface(324, 1743);
			/*player.getActionSender().sendAmountRequest();
			player.setTemporaryAttribute("tanXDeletedItem", 1739);
			player.setTemporaryAttribute("tanXAddedItem", 1743);
			player.setTemporaryAttribute("tanXPayment", 3);*/
			return true;
		case 125: //All
			tan(player, player.getInventory().getCount(1739), 3, 1739, 1743);
			return true;
			/*
			 * TODO: Snake skin.
			 */
			/*
			 * Green d' leather.
			 */
		case 152://tan 1.
			tan(player, 1, 20, 1753, 1745);
			return true;
		case 144://tan 5.
			tan(player, 5, 20, 1753, 1745);
			return true;
		case 136://tan x.
		//	player.getInterfaceState().openEnterAmountInterface(324, 1745);
			/*player.getActionSender().sendAmountRequest();
			player.setTemporaryAttribute("tanXDeletedItem", 1753);
			player.setTemporaryAttribute("tanXAddedItem", 1745);
			player.setTemporaryAttribute("tanXPayment", 20);*/
			return true;
		case 128://tan all.
			tan(player, player.getInventory().getCount(1753), 20, 1753, 1745);
			return true;
			/*
			 * Blue d' leather.
			 */
		case 153://tan 1.
			tan(player, 1, 20, 1751, 2505);
			return true;
		case 145://tan 5.
			tan(player, 5, 20, 1751, 2505);
			return true;
		case 137://tan x.
		//	player.getInterfaceState().openEnterAmountInterface(324, 2505);
			/*player.getActionSender().sendAmountRequest();
			player.setTemporaryAttribute("tanXDeletedItem", 1751);
			player.setTemporaryAttribute("tanXAddedItem", 2505);
			player.setTemporaryAttribute("tanXPayment", 20);*/
			return true;
		case 129://tan all.
			tan(player, player.getInventory().getCount(1751), 20, 1751, 2505);
			return true;
			/*
			 * Red d' leather.
			 */
		case 154://tan 1.
			tan(player, 1, 20, 1749, 2507);
			return true;
		case 146://tan 5.
			tan(player, 5, 20, 1749, 2507);
			return true;
		case 138://tan x.
			//player.getInterfaceState().openEnterAmountInterface(324, 2507);
			/*player.getActionSender().sendAmountRequest();
			player.setTemporaryAttribute("tanXDeletedItem", 1749);
			player.setTemporaryAttribute("tanXAddedItem", 2507);
			player.setTemporaryAttribute("tanXPayment", 20);*/
			return true;
		case 130://tan all.
			tan(player, player.getInventory().getCount(1749), 20, 1749, 2507);
			return true;	
			/*
			 * Black d' leather.
			 */
		case 155://tan 1.
			tan(player, 1, 20, 1747, 2509);
			return true;
		case 147://tan 5.
			tan(player, 5, 20, 1747, 2509);
			return true;
		case 139://tan x.
			//player.getInterfaceState().openEnterAmountInterface(324, 2509);
			/*player.getActionSender().sendAmountRequest();
			player.setTemporaryAttribute("tanXDeletedItem", 1747);
			player.setTemporaryAttribute("tanXAddedItem", 2509);
			player.setTemporaryAttribute("tanXPayment", 20);*/
			return true;
		case 131://tan all.
			tan(player, player.getInventory().getCount(1747), 20, 1747, 2509);
			return true;		
		}
		return false;
	}
	
	/**
	 * Initializes the tanning screen for the player.
	 * This needs to be redone, and light up when people have the correct itens in inventory.
	 */
	public static void sendTanningInterface(Player player) {
		player.getActionSender().sendInterface(324, false);
		player.getActionSender().sendString(324, 108, "Soft Leather");
		player.getActionSender().sendString(324, 109, "Hard Leather");
		player.getActionSender().sendString(324, 110, "Snakeskin");
		player.getActionSender().sendString(324, 111, "Snakeskin");
		player.getActionSender().sendString(324, 112, "Green d'hide");
		player.getActionSender().sendString(324, 113, "Blue d'hide");
		player.getActionSender().sendString(324, 114, "Red d'hide");
		player.getActionSender().sendString(324, 115, "Black d'hide");
		player.getActionSender().sendString(324, 116, "1 coin");
		player.getActionSender().sendString(324, 117, "3 coins");
		player.getActionSender().sendString(324, 118, "20 coins");
		player.getActionSender().sendString(324, 119, "15 coins");
		player.getActionSender().sendString(324, 120,"20 coins");
		player.getActionSender().sendString(324, 121, "20 coins");
		player.getActionSender().sendString(324, 122,"20 coins");
		player.getActionSender().sendString(324, 123,"20 coins");
		player.getActionSender().sendInterfaceModel(324, 100, 275, 1739);
		player.getActionSender().sendInterfaceModel(324, 101, 275, 1739);
		player.getActionSender().sendInterfaceModel(324, 102, 275, 7801);
		player.getActionSender().sendInterfaceModel(324, 103, 275, 6287);
		player.getActionSender().sendInterfaceModel(324, 104, 275, 1753);
		player.getActionSender().sendInterfaceModel(324, 105, 275, 1751);
		player.getActionSender().sendInterfaceModel(324, 106, 275, 1749);
		player.getActionSender().sendInterfaceModel(324, 107, 275, 1747);
	}
	
	
	public static void tan(Player player, int amount, int payment, int deletedItem, int addedItem) {
		Item iPayment = new Item(995, payment * amount);
		//player.getActionSender().sendCloseInterface();
		if(player.getInventory().contains(deletedItem)) {
			if (player.getInventory().hasItem(/*995, payment * amount*/iPayment)) {
				if(amount > player.getInventory().getCount(deletedItem)) {
					amount = player.getInventory().getCount(deletedItem);
				}
				player.getInventory().remove(iPayment);
				player.getInventory().remove(new Item(deletedItem, amount));
				player.getInventory().add(new Item(addedItem, amount));
			} else {
			player.getActionSender().sendMessage("You do not have enough coins.");
			} 
		} else {
			player.getActionSender().sendMessage("You don't have any more hides to continue tanning.");
		}
	}

	
	/**
	 * Call this event when you want to spin.
	 */
	private static class LeatherCraftingAction extends Action {
		public LeatherCraftingAction(Player player, int amount, int leatherId, int leatherAmount, int itemToRecieve, double xpToReceive) {
			super(player, 0);
			this.leather = new Item(leatherId, leatherAmount);
			this.itemToRecieve = new Item(itemToRecieve);
			this.exp = xpToReceive;
			this.amount = amount;
		}

		@Override
		public void execute() {
				//mob.getActionSender().sendInterface(-1, true);
				if(!mob.getInventory().hasItem(THREAD)) {
					mob.getActionSender().sendMessage("You've run out of thread to use.");
					this.stop();
					return;
				}
				if(!mob.getInventory().hasItem(leather)) {
					mob.getActionSender().sendMessage("You don't have enough hides to continue.");
					this.stop();
					return;
				}
			World.getWorld().submit(new Event(250) {
				public void execute() {
				this.setDelay(5000);//DOESN'T DO ANYTHING ATM.
				mob.playAnimation(LEATHER_CRAFTING);
				/**
				 * Deletes one piece of thread randomly.
				 */
				if(r.nextInt(3) == 0) {
					mob.getInventory().remove(THREAD);
	    		}
				mob.getInventory().remove(leather);
				mob.getInventory().add(itemToRecieve);
				mob.getSkills().addExperience(Skills.CRAFTING, exp * Constants.EXP_MODIFIER);
				if(--amount <= 0){
					this.stop();
				}
			}
		});
	}
		
		private int amount;
		private final Item leather;
		private final Item itemToRecieve;
		private final double exp;
					@Override
	public CancelPolicy getCancelPolicy() {
		return CancelPolicy.ALWAYS;
	}

	@Override
	public StackPolicy getStackPolicy() {
		return StackPolicy.NEVER;
	}

	@Override
	public AnimationPolicy getAnimationPolicy() {
		return AnimationPolicy.RESET_ALL;
	}
		
	
}
	


	
	private static int[][] GEM_INFORMATION =  {
			{1629, 1613, 16, 25, 892},
			{1627, 1611, 13, 20, 891},
			{1625, 1609, 1,  15, 890}, 
			{1623, 1607, 20, 50, 889}, 
			{1621, 1605, 27, 67, 888},
			{1619, 1603, 34, 85, 887},
			{1617, 1601, 43, 107, 886},
			{1631, 1615, 55, 137, 885}, 
			{6571, 6573, 67, 167, 2717}
	};

	
	public static boolean handleItemOnItem(Player player, int itemUsed, int usedWith) {
		for (int h = 0; h < GEM_INFORMATION.length; h++) {
			if ((itemUsed == 1755 && usedWith == GEM_INFORMATION[h][0]) || (usedWith == 1755 && itemUsed == GEM_INFORMATION[h][0])) {
				if (player.getSkills().getLevel(Skills.CRAFTING) < GEM_INFORMATION[h][2]) {
					player.getActionSender().sendMessage("You need a level of "+GEM_INFORMATION[h][2]+" to craft that!");
					break;
				}
				player.getActionSender().sendMessage("You craft the gem.");
				player.playAnimation(Animation.create(GEM_INFORMATION[h][4]));
				player.getInventory().remove(new Item(GEM_INFORMATION[h][0])); //Deletes the neededgem
				player.getInventory().add(new Item(GEM_INFORMATION[h][1], 1)); //Gives item
				player.getSkills().addExperience(Skills.CRAFTING, GEM_INFORMATION[h][3] * Constants.EXP_MODIFIER - 65); //Gives EXP
				break;
			}
		}
		/*
		 * Soft leather.
		 */
		if (itemUsed == 1733 && usedWith == 1741 || usedWith == 1733 && itemUsed == 1741) {
			//player.resetSkilling();
			player.getCraftingVariables().setCrafting(true);
			player.getActionSender().sendInterface(154, true);
			return true;
		}
		/*
		 * Hard leather.
		 */
		/*if (itemUsed == 1733 && usedWith == 1743 || usedWith == 1733 && itemUsed == 1743) {
			//player.resetSkilling();
			//player.getCraftingVariables().setCrafting(true);
			player.getActionSender().sendChatboxInterface(309);
			//player.getActionSender().sendInterfaceModel(309, 2, 200, 1131);
			//Crafting.hardLeatherBodies(player, childClicked);
			//System.out.println("we got here! aammfgg");
			return true;
		}*/
		
		return false;
		}
	
}
