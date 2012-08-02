package org.rs2server.rs2.model.skills;

import java.util.HashMap;
import java.util.Map;

import org.rs2server.rs2.action.impl.ProductionAction;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Graphic;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.ItemDefinition;
import org.rs2server.rs2.model.Mob;
import org.rs2server.rs2.model.Skills;


public class CraftingNew extends ProductionAction {

	/**
	 * The amount of items to produce.
	 */
	private int productionCount;
	
	/**
	 * The log index.
	 */
	private int logIndex;
	
	/**
	 * The log we are fletching.
	 */
	private Log1 log;
	
	public CraftingNew(Mob mob, int productionCount, int logIndex, Log1 log) {
		super(mob);
		this.productionCount = productionCount;
		this.logIndex = logIndex;
		this.log = log;
	}
	
	/**
	 * Represents a type of log that is able to be fletched.
	 * @author Michael
	 *
	 */
	public static enum Log1 {
		
		//ITEM LEVEL EXP
		GREEN(1745, new int[] { 1135, 1065, 1099 }, new int[] { 63, 57, 60}, new double[] { 20, 15, 18 }),
		
		BLUE(2505, new int[] { 2499, 2487, 2493 }, new int[] { 71, 66, 68}, new double[] { 25, 19, 21 }),
		
		RED(2507, new int[] { 2501, 2489, 2495 }, new int[] { 77, 73, 75 }, new double[] { 33, 21, 29 }),
		
		BLACK(2509, new int[] { 2503 , 2491, 2497  }, new int[] { 84, 79, 82 }, new double[] { 50, 26, 33 })
				
				;
		
		
		/**
		 * The id of the logs
		 */
		private int logId;
		
		/**
		 * @return the logId
		 */
		public int getLogId() {
			return logId;
		}

		/**
		 * The first item displayed on the fletching interface.
		 */
		private int[] item;

		/**
		 * The level required to fletch the first item on the fletching interface.
		 */
		private int[] level;

		/**
		 * The experience granted for the first item on the flteching interface.
		 */
		private double[] experience;

		/**
		 * A map of item ids to logs.
		 */
		private static Map<Integer, Log1> logs = new HashMap<Integer, Log1>();

		/**
		 * Gets a log by an item id.
		 * @param item The item id.
		 * @return The Log, or <code>null</code> if the object is not a log.
		 */
		public static Log1 forId(int item) {
			return logs.get(item);
		}

		/**
		 * Populates the log map.
		 */
		static {
			for (Log1 log : Log1.values()) {
				logs.put(log.logId, log);
			}
		}
		
		private Log1(int logId, int[] item, int[] level, double[] experience) {
			this.logId = logId;
			this.item = item;
			this.level = level;
			this.experience = experience;
		}

		/**
		 * @return the item
		 */
		public int[] getItem() {
			return item;
		}

		/**
		 * @return the level
		 */
		public int[] getLevel() {
			return level;
		}

		/**
		 * @return the experience
		 */
		public double[] getExperience() {
			return experience;
		}
	}
	
	

	@Override
	public boolean canProduce() {
		return true;
	}

	@Override
	public Animation getAnimation() {
		return Animation.create(1249);
	}

	@Override
	public Item[] getConsumedItems() {
		return new Item[] { new Item(log.getLogId()) };
	}

	@Override
	public int getCycleCount() {
		return 3;
	}

	@Override
	public double getExperience() {
		return log.getExperience()[logIndex];
	}

	@Override
	public Graphic getGraphic() {
		return null;
	}

	@Override
	public String getLevelTooLowMessage() {
		return "You need a Crafting level of " + getRequiredLevel() + " to make this.";
	}

	@Override
	public int getProductionCount() {
		return productionCount;
	}

	@Override
	public int getRequiredLevel() {
		return log.getLevel()[logIndex];
	}

	@Override
	public Item[] getRewards() {
		return new Item[] { new Item(log.getItem()[logIndex], log.getItem()[logIndex] == 53 ? 15 : 1) };
	}

	@Override
	public int getSkill() {
		return Skills.CRAFTING;
	}

	@Override
	public String getSuccessfulProductionMessage() {
		String prefix = "a";
		String suffix = "";
		char first = ItemDefinition.forId(log.getItem()[logIndex]).getName().toLowerCase().charAt(0);
		if(first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u') {
			prefix = "an";
		}
		if(log.getItem()[logIndex] == 52) {
			prefix = "some";
			suffix = "s";
		}
		return "You make " + prefix + " " + ItemDefinition.forId(log.getItem()[logIndex]).getName().toLowerCase() + "" + suffix + ".";
	}

}
