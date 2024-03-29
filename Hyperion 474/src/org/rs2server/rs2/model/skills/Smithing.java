package org.rs2server.rs2.model.skills;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.rs2server.rs2.action.impl.ProductionAction;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Graphic;
import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.Constants;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.container.Equipment;
import org.rs2server.rs2.model.Mob;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.Skills;


public class Smithing extends ProductionAction {

	public static final int SMELTING_INTERFACE = 311;
	private static final Random r = new Random();
/**
*
* Data about SMELTING
*/	
	
	public static class SmeltingAction extends Action {
		
		private static final Animation SMELTING_ANIMATION = Animation.create(899);

		public SmeltingAction(Player player, int mainOre, int secondaryOre, double exp, int resultBar, int resultBarAmount, int secondaryOreAmount, boolean isIron, boolean requiresTwoOres) {
			super(player, 0);
			this.mainOre = new Item(mainOre);
			this.secondaryOre = new Item(secondaryOre, secondaryOreAmount);
			this.exp = exp;
			this.resultBar = new Item(resultBar);
			this.resultBarAmount = resultBarAmount;
			this.isIron = isIron;
			this.requiresTwoOres = requiresTwoOres;
		}

		public void execute() {
			if(/*mob.isDead() || */resultBarAmount <= 0) {
				this.stop();
				return;
			}
			mob.getActionSender().removeChatboxInterface();

			/**
			 * If player is done with smithing all his bars, then we'll stop the event, AND return.
			 */
			if (!mob.getInventory().hasItem(secondaryOre) || !mob.getInventory().hasItem(mainOre)) {
				mob.getActionSender().sendMessage("You have run out of ores.");
				this.stop();
				return;
			}
			mob.playAnimation(SMELTING_ANIMATION);
			//mob.getActionSender().sendMessage("You smelt the " + mainOre.getDefinition().getName().toLowerCase() + " in the furnace.");
			World.getWorld().submit(new Event(0) {
				
				public void execute() {
					if (!mob.getInventory().hasItem(secondaryOre) || !mob.getInventory().hasItem(mainOre)) {
						mob.getActionSender().sendMessage("You've run out of ores."); //Message will come in the primary event.
						this.stop();
						return;
					}
						mob.getActionSender().sendMessage("You smelt a bar of " + resultBar.getDefinition().getName().toLowerCase().replace(" bar", "") + ".");
						mob.getInventory().remove(mainOre);
						if(requiresTwoOres) {
							mob.getInventory().remove(secondaryOre);
						}
						mob.getInventory().add(resultBar);
						mob.getSkills().addExperience(Skills.SMITHING, exp * Constants.MAGIC_EXP_MODIFIER - 75);
				try{
				Thread.sleep(3000);
				mob.playAnimation(SMELTING_ANIMATION);
				}catch(InterruptedException ie){
				ie.printStackTrace();
				}
					this.stop();
				}
			});
			if(--resultBarAmount <= 0){
				this.stop();
			}

		}
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

		private final Item mainOre;
		private final Item secondaryOre;
		private int resultBarAmount;
		private final Item resultBar;
		private final double exp;
		private final boolean isIron;
		private final boolean requiresTwoOres;

	}
	
	private static boolean hasRingOfForgin(Mob player) {
		Item ring = player.getEquipment().get(Equipment.SLOT_RING);
		return ring == null ? false : ring.getId() == 2568;
	}
	
		/**
	 * Returns true, if the players crafting level is high enough.
	 * */
   	private static boolean smithingLevel(Player player, int level) {
	    return (player.getSkills().getLevel(Skills.SMITHING) >= level);
	}

	public static void furnaceSmelting(Player player, int lvlReq, int mainOre1, int secondaryOre1, int secondaryOreAmount, int resultBar1, int resultBarAmount, double exp, boolean isIron1, boolean requiresTwoOres1) {
	//CASUES THE CLIENT TO CRASH..
		/*player.getActionSender().sendCloseInterface();
		*/String resultName = new Item(resultBar1).getDefinition().getName().toLowerCase();
		if(smithingLevel(player, lvlReq)) {
			if(player.getInventory().contains(mainOre1)) {
				if(player.getInventory().contains(secondaryOre1)) {
				//player.resetSkilling();
					//player.isSmelting = true;
					player.getActionQueue().addAction(new SmeltingAction(player, mainOre1, secondaryOre1, exp, resultBar1, resultBarAmount, secondaryOreAmount, isIron1, requiresTwoOres1));
					//appendFurnaceEngine(player, mainOre1, secondaryOre1, exp, resultBar1, resultBarAmount, secondaryOreAmount, isIron1, requiresTwoOres1);
				} else {
					player.getActionSender().removeChatboxInterface();
					player.getActionSender().sendMessage("You don't have enough " + new Item(secondaryOre1).getDefinition().getName().toLowerCase() + "'s in order to smelt a "+resultName+".");
				}
			} else {
				player.getActionSender().removeChatboxInterface();
				player.getActionSender().sendMessage("You don't have enough " + new Item(mainOre1).getDefinition().getName().toLowerCase() + "'s in order to smelt a "+resultName+".");
			}
		} else {
			player.getActionSender().removeChatboxInterface();
			player.getActionSender().sendMessage("You need a Smithing level of at least " + lvlReq +" in order to smelt " + resultName + "'s.");
		}
	}





	/**
	 * The smithing interface.
	 */
	public static final int INTERFACE = 312;
	
	/**
	 * The bar we are going to use.
	 */
	private Bar bar;

	/**
	 * The amount of items to produce.
	 */
	private int productionCount;

	/**
	 * The item we are making.
	 */
	private Item item;

	/**
	 * The column id of the item we are smithing.
	 */
	private int column;

	/**
	 * The row id of the item we are smithing.
	 */
	private int row;
	
	public Smithing(Mob mob, int productionCount, Item item, int column, int row, Bar bar) {
		super(mob);
		this.productionCount = productionCount;
		this.item = item;
		this.column = column;
		this.row = row;
		this.bar = bar;
		mob.getActionSender().removeAllInterfaces();
	}	
	
	
	/**
	 * The ids on the smithing interface for each item model to be displayed.
	 */
	private static int[] BAR_CHILD_IDS = new int[] { 146, 147, 148, 149, 150, 151 };
	
	/**
	 * The strings on the smithing interface to display the item's name.
	 */
	private static String[][] NAME_CHILD_STRINGS = new String[][] { 
														new String[] { "Dagger", "Sword", "Scimitar", "Long sword", "2 hand sword" },
														new String[] { "Axe", "Mace", "Warhammer", "Battle axe", "Claws" },
														new String[] { "Chain body", "Plate legs", "Plate skirt", "Plate body", "Boots" },
														new String[] { "Medium helm", "Full helm", "Square shield", "Kite shield", "Nails" },
														new String[] { "Dart tips", "Arrowtips", "Throwing knives", "Other", "Studs" },															
														new String[] { "Bolts", "Limbs", "Grapple tips" }
	};
	
	/**
	 * The ids on the smithing interface to display the item's name.
	 */
	private static int[][] NAME_CHILD_IDS = new int[][] { 
														new int[] { 121, 112, 114, 113, 115 }, 
														new int[] { 118, 120, 111, 119, 165 },
														new int[] { 125, 126, 128, 127, 171 },
														new int[] { 129, 130, 131, 132, 173 },
														new int[] { 134, 135, 133, 123, 162 },
														new int[] { 178, 175, 181 }
	};
	
	/**
	 * The ids on the smithing interface to display the bar amounts.
	 */
	private static int[][] BAR_AMOUNT_CHILD_IDS = new int[][] { 
														new int[] { 153, 152, 143, 116, 117 },
														new int[] { 154, 157, 145, 122, 164 },
														new int[] { 136, 137, 138, 139, 169 },
														new int[] { 155, 140, 141, 142, 172 },
														new int[] { 156, 158, 159, 160, 163 },
														new int[] { 176, 177, 182 }
	};
	
	/**
	 * Represents a bar of metal that can be smithed.
	 * @author Michael
	 *
	 */
	public static enum Bar {
		
		BRONZE(2349, 12.5, new Object[][] {
								new Item[] { 
									new Item(1205), new Item(1277), new Item(1321), new Item(1291), new Item(1307)
								},
								new Item[] { 
									new Item(1351), new Item(1422), new Item(1337), new Item(1375), new Item(4119)
								},
								new Item[] { 
									new Item(1103), new Item(1075), new Item(1087), new Item(1117), null
								},
								new Item[] { 
									new Item(1139), new Item(1155), new Item(1173), new Item(1189), new Item(4819, 15)
								},
								new Item[] { 
									new Item(819, 10), new Item(39, 15), new Item(864, 5), null, null,
								},
								new Item[] { 
									new Item(9236, 10), null, null
								}
							},
							new int[][] { 
								new int[] { 1, 1, 2, 2, 3 },
								new int[] { 1, 1, 3, 3, 2 },
								new int[] { 3, 3, 3, 5, -1},
								new int[] { 1, 2, 2, 3, 1 },
								new int[] { 1, 1, 1, -1, -1 },
								new int[] { 1, -1, -1 },
							},
							new int[][] { 
								new int[] { 1, 4, 5, 6, 14 },
								new int[] { 1, 2, 9, 10, 13 },
								new int[] { 11, 16, 16, 18, -1 },
								new int[] { 3, 7, 8, 12, 4 },
								new int[] { 4, 5, 7, -1, -1 },
								new int[] { 3, -1, -1 }
							}
		),
		
		IRON(2351, 25, new Object[][] {
								new Item[] { 
									new Item(1203), new Item(1279), new Item(1323), new Item(1293), new Item(1309)
								},
								new Item[] { 
									new Item(1349), new Item(1420), new Item(1335), new Item(1363), new Item(3096)
								},
								new Item[] { 
									new Item(1101), new Item(1067), new Item(1081), new Item(1115), new Item(4121)
								},
								new Item[] { 
									new Item(1137), new Item(1153), new Item(1175), new Item(1191), new Item(4820, 15)
								},
								new Item[] { 
									new Item(820, 10), new Item(40, 15), new Item(865, 5), new Item(7225), null
								},
								new Item[] { 
									new Item(9238, 10), null, null
								}
							},
							new int[][] { 
								new int[] { 1, 1, 2, 2, 3 },
								new int[] { 1, 1, 3, 3, 2 },
								new int[] { 3, 3, 3, 5, 1 },
								new int[] { 1, 2, 2, 3, 1 },
								new int[] { 1, 1, 1, 1, -1 },
								new int[] { 1, -1, -1 },
							},
							new int[][] { 
								new int[] { 15, 19, 20, 21, 29 },
								new int[] { 16, 17, 24, 25, 28 },
								new int[] { 26, 31, 31, 33, 26 },
								new int[] { 18, 22, 23, 27, 19 },
								new int[] { 19, 20, 22, 17, -1 },
								new int[] { 18, -1, -1 },
							}
		)
		
		,
				STEEL(2353, 30, new Object[][] {
								new Item[] { 
									new Item(1207), new Item(1281), new Item(1325), new Item(1295), new Item(1311)
								},
								new Item[] { 
									new Item(1353), new Item(1424), new Item(1339), new Item(1365), new Item(3097)
								},
								new Item[] { 
									new Item(1105), new Item(1069), new Item(1083), new Item(1119), new Item(4123)
								},
								new Item[] { 
									new Item(1141), new Item(1157), new Item(1177), new Item(1193), new Item(1539, 15)
								},
								new Item[] { 
									new Item(821, 10), new Item(41, 15), new Item(865, 5), null, null
								},
								new Item[] { 
									null, null, null
								}
							},
							new int[][] {//EXP Gained (CONSTANTS.EXP_RATE * MULTIPLIER) 
								new int[] { 2, 2, 3, 4, 5 },
								new int[] { 2, 2, 4, 4, 3 },
								new int[] { 3, 3, 3, 7, 1 },
								new int[] { 1, 2, 2, 3, 1 },
								new int[] { 1, 1, 1, 1, -1 },
								new int[] { 1, -1, -1 },
							},
							new int[][] { //Level REQ..
								new int[] { 30, 31, 32, 33, 33 },
								new int[] { 34, 34, 34, 35, 35 },
								new int[] { 36, 36, 36, 37, 37 },
								new int[] { 38, 38, 38, 39, 39 },
								new int[] { 40, 41, 41, 42 },
								new int[] {  },
							}
		),
						MITHRIL(2359, 35, new Object[][] {
								new Item[] { 
									new Item(1209), new Item(1285), new Item(1329), new Item(1299), new Item(1315)
								},
								new Item[] { 
									new Item(1355), new Item(1428), new Item(1343), new Item(1369), new Item(3099)
								},
								new Item[] { 
									new Item(1109), new Item(1071), new Item(1085), new Item(1121), new Item(4127)
								},
								new Item[] { 
									new Item(1143), new Item(1159), new Item(1181), new Item(1197), new Item(4822, 15)
								},
								new Item[] { 
									new Item(822, 10), new Item(42, 15), new Item(866, 5), null, null
								},
								new Item[] { 
									null, null, null
								}
							},
							new int[][] {//EXP Gained (CONSTANTS.EXP_RATE * MULTIPLIER) 
								new int[] { 1, 1, 2, 2, 3 },
								new int[] { 1, 1, 3, 3, 2 },
								new int[] { 3, 3, 3, 5, 1 },
								new int[] { 1, 2, 2, 3, 1 },
								new int[] { 1, 1, 1, 1, -1 },
								new int[] { 1, -1, -1 },
							},
							new int[][] { //Level REQ..
								new int[] { 50, 50, 50, 51, 51 },
								new int[] { 51, 51, 52, 52, 52 },
								new int[] { 53, 63, 60, 54, 55 },
								new int[] { 56, 56, 62, 62, 57 },
								new int[] { 57, 58, 59, 50 },
								new int[] {  },
							}
		);
		
		/**
		 * The item id.
		 */
		private int id;
		
		/**
		 * The experience granted per bar consumed.
		 */		
		private double experience;
		
		/**
		 * The array of items this bar can make.
		 */
		private Object[][] items;
		
		/**
		 * The amount of bars used in the making of an item.
		 */
		private int[][] consumedAmounts;
		
		/**
		 * The level required to make the item.
		 */
		private int[][] levelRequired;
		
		/**
		 * The map of item ids to bars.
		 */
		private static Map<Integer, Bar> bars = new HashMap<Integer, Bar>();

		/**
		 * Gets a bar by an item id.
		 * @param item The item id.
		 * @return The <code>Bar</code> or <code>null</code> if the item is not a bar.
		 */
		public static Bar forId(int item) {
			return bars.get(item);
		}

		/**
		 * Populates the bar map.
		 */
		static {
			for (Bar bar : Bar.values()) {
				bars.put(bar.id, bar);
			}
		}
		
		private Bar(int id, double experience, Object[][] items, int[][] consumedAmounts, int[][] levelRequired) {
			this.id = id;
			this.items = items;
			this.consumedAmounts = consumedAmounts;
			this.levelRequired = levelRequired;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @return the items
		 */
		public Object[] getItems() {
			return items;
		}

		/**
		 * @return the items
		 */
		public Item[] getItems(int i) {
			return (Item[]) items[i];
		}

		/**
		 * @return the consumedItems
		 */
		public int[][] getConsumedAmounts() {
			return consumedAmounts;
		}

		/**
		 * @return the levelRequired
		 */
		public int[][] getLevelRequired() {
			return levelRequired;
		}

		/**
		 * @return the experience
		 */
		public double getExperience() {
			return experience;
		}
	}
	
	public static void openSmithingInterface(Mob mob, Bar bar) {		
		for(int column = 0; column < bar.getItems().length; column++) {
			mob.getActionSender().sendUpdateItems(INTERFACE, BAR_CHILD_IDS[column], -1, bar.getItems(column));
			
			for(int row = 0; row < bar.getItems(column).length; row++) {
				if(bar.getItems(column)[row] == null) {
					mob.getActionSender().sendString(INTERFACE, NAME_CHILD_IDS[column][row], "")
										 .sendString(INTERFACE, BAR_AMOUNT_CHILD_IDS[column][row], "");
					
				} else {
					mob.getActionSender().sendString(INTERFACE, NAME_CHILD_IDS[column][row], (mob.getSkills().getLevelForExperience(Skills.SMITHING) >= bar.getLevelRequired()[column][row] ? "<col=FFFFFF>" : "") + NAME_CHILD_STRINGS[column][row])
					 					 .sendString(INTERFACE, BAR_AMOUNT_CHILD_IDS[column][row], (mob.getInventory().getCount(bar.getId()) >= bar.getConsumedAmounts()[column][row] ? "<col=00FF00>" : "") + bar.getConsumedAmounts()[column][row]+"bar" + (bar.getConsumedAmounts()[column][row] > 1 ? "s" : ""));
				}
			}
		}
		
		mob.getActionSender().sendInterface(INTERFACE, true);
		mob.setInterfaceAttribute("smithing_bar", bar);
	}

	@Override
	public boolean canProduce() {
		if(getMob().getInventory().getCount(2347) < 1) {
			getMob().getActionSender().sendMessage("You do not have a hammer.");
			return false;
		}
		return true;
	}

	@Override
	public Animation getAnimation() {
		return Animation.create(898);
	}

	@Override
	public Item[] getConsumedItems() {
		return new Item[] { new Item(bar.getId(), bar.getConsumedAmounts()[column][row]) };
	}

	@Override
	public int getCycleCount() {
		return 4;
	}

	@Override
	public double getExperience() {
		return bar.getExperience() * bar.getConsumedAmounts()[column][row] * Constants.EXP_MODIFIER - 50;
	}

	@Override
	public Graphic getGraphic() {
		return null;
	}

	@Override
	public String getLevelTooLowMessage() {
		return "You need a Smithing level of " + getRequiredLevel() + " to smith this.";
	}

	@Override
	public int getProductionCount() {
		return productionCount;
	}

	@Override
	public int getRequiredLevel() {
		return bar.getLevelRequired()[column][row];
	}

	@Override
	public Item[] getRewards() {
		return new Item[] { item };
	}

	@Override
	public int getSkill() {
		return Skills.SMITHING;
	}

	@Override
	public String getSuccessfulProductionMessage() {
		String name = item.getDefinition().getName().toLowerCase();
		String prefix = (name.startsWith("a") || name.startsWith("e") || name.startsWith("i") || name.startsWith("o") || name.startsWith("u")) ? "an " : "a ";
		String suffix = "";
		if(name.endsWith("s")) {
			prefix = "some ";
		} else if(name.endsWith("knife")) {
			name = name.substring(0, name.indexOf("knife") - 1);
			prefix = "some ";
			suffix = " knives";
		} else if(name.endsWith("dart tip")) {
			prefix = "some ";
			suffix = "s";
		} else if(name.endsWith("wire")) {
			prefix = "some ";
		} else if(name.contains("bolts")) {
			prefix = "some ";
		}
		return "You make " + prefix + name + suffix +".";
	}

}