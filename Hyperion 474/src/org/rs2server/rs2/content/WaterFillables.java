package org.rs2server.rs2.content;

import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.action.Action.AnimationPolicy;
import org.rs2server.rs2.action.Action.CancelPolicy;
import org.rs2server.rs2.action.Action.StackPolicy;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.tickable.Tickable;


public class WaterFillables {

	public void start(final Player player){
	}
	/**
	 * Checks if water filled item is able to empty
	 * @param player The player.
	 * @param itemId The item id.
	 * @param itemSlot The item slot.
	 * @return True if we filled an item with water, false if not. 
	 */
	public static boolean checkForItemEmpty(Player player, int itemId, int itemSlot) {
			for(int index = 0; index < WATER_CONTAINERS_UNFILLED.length; index++) {
				if(WATER_CONTAINERS_FILLED[index].getId() == itemId) {
					player.getInventory().remove(WATER_CONTAINERS_FILLED[index]);
					player.getActionSender().sendMessage("You empty the " + WATER_CONTAINERS_FILLED[index].getDefinition().getName().toLowerCase() + "."); 
					player.getInventory().add(WATER_CONTAINERS_UNFILLED[index]);
					break;
				}
			}
			return false;
		}
	
	public static boolean playerStoppedFilling = false;
	
	/**
	 * Checks if some items is fillable with water, and fills them.
	 * @param player The player.
	 * @param itemId The item id.
	 * @param itemSlot The item slot.
	 * @param objectId The object id.
	 * @return True if we filled an item with water, false if not. 
	 */
	public static boolean checkForWaterFillingItemAndObjects(final Player player, final int itemId, int itemSlot, final int objectId) {

		player.getActionQueue().addAction(new Action(player, 2) {
			@Override
			public void execute() {
				boolean checkForWaterFilling = false;
				for(int id : WATER_CONTAINING_OBJECTS) {
					if(id == objectId) {
						checkForWaterFilling = true;
						break;
					}
				}
				if(checkForWaterFilling) {
					checkForWaterFilling = false;
					for(int index = 0; index < WATER_CONTAINERS_UNFILLED.length; index++) {
						if(WATER_CONTAINERS_UNFILLED[index].getId() == itemId) {
							if(player.getInventory().contains(WATER_CONTAINERS_UNFILLED[index].getId()) == false){
								this.stop();
								return;
						}
							player.getInventory().remove(WATER_CONTAINERS_UNFILLED[index]);
							player.getActionSender().sendMessage("You fill the " + WATER_CONTAINERS_UNFILLED[index].getDefinition().getName().toLowerCase() + " with water."); 
							player.getInventory().add(WATER_CONTAINERS_FILLED[index]);
							player.playAnimation(Animation.create(832));
							checkForWaterFilling = true;
							break;
						} else if(WATER_CONTAINERS_FILLED[index].getId() == itemId) {
							player.getActionSender().sendMessage("The " + WATER_CONTAINERS_FILLED[index].getDefinition().getName().toLowerCase() + " is already full.");
							this.stop();
							checkForWaterFilling = true;
							break;
						}
					}
				}
			}
			@Override
			public AnimationPolicy getAnimationPolicy() {
				return AnimationPolicy.RESET_ALL;
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
		return false;
	}


	/**
	 * The filled items.
	 */
	private static final Item[] WATER_CONTAINERS_FILLED = {
		new Item(1937), //Jug Of Water
		new Item(1921), //Bowl Of Water
		new Item(1929), //Bucket Of Water
		new Item(227), //Vial of water.
		new Item(1823), //Filled waterskin (4)
		new Item(1823), //Filled waterskin (4)
		new Item(1823), //Filled waterskin (4)
		new Item(1823), //Filled waterskin (4)
		new Item(4458), //Cup of water
		new Item(5340), //Waterin can (8)
		new Item(5340), //Waterin can (8)
		new Item(5340), //Waterin can (8)
		new Item(5340), //Waterin can (8)
		new Item(5340), //Waterin can (8)
		new Item(5340), //Waterin can (8)
		new Item(5340), //Waterin can (8)
		new Item(5340), //Waterin can (8)
	};

	/**
	 * The items before they are filled
	 */
	private static final Item[] WATER_CONTAINERS_UNFILLED = {
		new Item(1935), //Unfilled Jug
		new Item(1923), //Unfilled Bowl
		new Item(1925), //Unfilled Bucket
		new Item(229), //Empty vial.
		new Item(1825), //Filled waterskin (3)
		new Item(1827), //Filled waterskin (2)
		new Item(1829), //Filled waterskin (1)
		new Item(1831), //Empty waterskin 
		new Item(1980), //Empty Cup 
		new Item(5331), //Waterin can
		new Item(5333), //Waterin can (1)
		new Item(5334), //Waterin can (2)
		new Item(5335), //Waterin can (3)
		new Item(5336), //Waterin can (4)
		new Item(5337), //Waterin can (5)
		new Item(5338), //Waterin can (6)
		new Item(5339), //Waterin can (7)
	};

	
	/**
	 * All objects containing water ingame.
	 */
	//Well !?!??! Only for buckets I think, just ignore them.
	private static final int[] WATER_CONTAINING_OBJECTS = {
		153, //Fountain
		879, //Fountain
		880, //Fountain
		2654, //Sinclair family fountain
		2864, //Fountain
		6232, //Fountain
		10436, //Fountain
		10437, //Fountain
		11007, //Fountain
		11759, //Fountain
		13478, //Small fountain
		13479, //Large fountain
		13480, //Ornamental fountain
		21764, //Fountain
		24161, //Fountain
		24214, //Fountain
		24265, //Fountain
		
		873, //Sink
		874, //Sink
		4063, //Sink
		6151, //Sink
		8699, //Sink
		9143, //Sink
		9684, //Sink
		10175, //Sink		
		12279, //Sink		
		12974, //Sink		
		13563, //Sink
		13564, //Sink		
		14868, //Sink		
		14917, //Sink		
		15678, //Sink		
		16704, //Sink
		16705, //Sink		
		20358, //Sink		
		22715, //Sink	
		24112, //Sink
		24314, //Sink
		878, //well
		884, //well
		3264, //well
		3305, //well
		3359, //well
		3485, //well
		4004, //well
		4005, //well
		6097, //well
		6249, //well
		6549, //well
		8747, //well
		8927, //well
		11793, //well
		12201, //well
		12897, //well
		
		
		11661, //Waterpump in falador.
		
		/*
		 * In doubt of the following:
		 */
		4176, //Tap 
		4285, //Tap
		4482, //Tap
		
		//Cbf doing anymore, this is all fine ;)
	};

}
