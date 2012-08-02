package org.rs2server.rs2;

/**
 * Holds global server constants.
 * @author Graham Edgecombe
 * @author Canownueasy
 */
public class Constants {
	
	/**
	*
	* Disabling Normal Player commands can stop things like NOCLIP.
	*
	*/
	public static boolean CAN_NORMAL_PLAYERS_USE_COMMANDS = false;
	
	/**
	* PORT to listen on.
	*/
	public static int PORT = 43594;
	
	/**
	* Instantly switch items?
	*/
	public static final boolean INSTANT_SWITCH = true;
	
	/**
	 * If the server is in debug mode.
	 *
	 * ANDREW: NOTE: This doesn't do much, except printLn some npc shit and inetface object shit
	 */
	public static final boolean DEBUGGING = true;
	

 	/**
	* X Y and Z of the home.
	*/
	public static int HOMEX = 3233;
	public static int HOMEY = 3230;
	public static int HOMEZ = 0; 
	
	
	/**
	* Extra login message
	*/
	public static String EXTRA_MESSAGE = "";

	/**
	* What do we call the server?
	*/
	public static String SERVER_NAME = "AytonCity";
	
	
	
	
	/**
	 * The exp modifier
	 * 
	 */
	public static int EXP_MODIFIER = 9;//6 seems to be good. 7 Faster eco, 5 too slow, Around 8 for whiners. 35 for pvp econ.
	
	public static int MAGIC_EXP_MODIFIER = 6;//Magic training was a little too fast compared to the other skills.
	
	public static int EXP_MODFIER = 9;//this one controls smithing only was too lazy to change it
	
	
	/**
	*
	* Modifier of the shop prices.
	* NOTE: BUYFROM should ALWAYS be lower than SELL TO..
	*
	*/
	//THIS IS ACTUALLY THE SELLING TO SHOP PRICE
	public static int BUY_FROM_SHOP_PRICE = 1;// REVERSE THIS
	
	//THIS IS ACTUALLY THE SELLING TO PLAYER PRICE..
	public static int SELL_TO_SHOP_PRICE = 2;// REVERSE THIS
	
	
	/**
	 * The media displayed on the message of the week.
	 * 16 = Moving cogs
	 * 17 = Question marks
	 * 18 = Drama faces
	 * 19 = Bank pin vaults
	 * 20 = Bank pin question marks
	 * 21 = Player scamming
	 * 22 = Bank pin vaults with moving key
	 * 23 = Christmas presents & Santa
	 * 24 = Killcount TODO: Useful in future
	 */
	public static final int MESSAGE_OF_THE_WEEK_SCREEN = 17;
	

	
	//TEXT NEAR THE KEY WILL NOT BE EDITED.
	public static String MESSAGE_BANK_PIN = "<col=ff9000>Latest content update</col> Runecrafting! Includes all altars and multiple runes.";//Text near the bank chest
	public static String MESSAGE_KEY = "<col=ff9000>Did you know</col> You can kill Yaks for the easiest experience available, however, they have no drops.";//Text near the key
	public static String MESSAGE_MEMBER = "<col=ff9000>Latest Bug Fix</col> Items you sell to the store will reward you with more money.";//Text near the coins
	public static String MESSAGE_LETTER = "<col=55ff00>Message from "+Constants.SERVER_NAME+" staff...</col>";//Text next to the letter.
	public static String LETTER_OTHER_TEXT = "The server is currently in BETA phase, which means you must report any bugs and glitches...you WILL be rewarded!";//Text under the above, next to the letter.

	/**
	 * The message of the week, as displayed on the login screen.
	 */
	public static String MESSAGE_OF_THE_WEEK = "Please don't be scammed. Double check the trade window!";
	public static String MESSAGE_OF_THE_WEEK2 = "If you are a member, you get LOADS of extra features!";
	public static String MESSAGE_OF_THE_WEEK3 = "Members have the ability to craft Nature and Death runes.";
	public static String MESSAGE_OF_THE_WEEK4 = "Staff will NEVER ask you for your password.";
	
	
	
	/**
	 * The server's website address.
	 */
	public static String WEBSITE = "not available";
	
	
	/**
	 * The owners.
	 */
	public static final String OWNERS[] = {
		"Ayton", "Octav"
	};
	
	/**
	 * The admins.
	 */
	public static final String ADMINS[] = {
		
	};
	
	/**
	 * The mods.
	 */
	public static final String MODS[] = {
		
	};
	
	
	
	/**
	*
	* Everything below this line does not need to be edited.
	*
	**/	/**
	*
	* Everything below this line does not need to be edited.
	*
	**/	/**
	*
	* Everything below this line does not need to be edited.
	*
	**/	/**
	*
	* Everything below this line does not need to be edited.
	*
	**/

	
	
	
	/**
	 * The main screen window pane.
	 */
	public static final int MAIN_WINDOW = 548;
	
	/**
	 * The game window area.
	 */
	public static final int GAME_WINDOW = 75;
	
	/**
	 * The side tabs area.
	 */
	public static final int SIDE_TABS = 97;
	
	/**
	 * The chat box area.
	 */
	public static final int CHAT_BOX = 92;
	
	/**
	 * The login screen window pane.
	 */
	public static final int LOGIN_SCREEN = 549;
	
	/**
	 * The parameters for the equipment screen.
	 */
	public static final Object[] EQUIPMENT_PARAMETERS = new Object[] { "", "", "", "", "", "", "", "", "Wear", -1, 0, 7, 4, 98, 22020096 };

	/**
	 * The sting to send on the equipment interface run script.
	 */
	public final static String EQUIPMENT_TYPE_STRING = "IviiiIsssssssss";

	
	/**
	 * The interface sent in a run script for numerical input.
	 */
	public static final int NUMERICAL_INPUT_INTERFACE = 108;
	
	/**
	 * The interface sent in a run script for alphabetical & numerical input.
	 */
	public static final int ALPHA_NUMERICAL_INPUT_INTERFACE = 110;
	
	/**
	 * The interface sent to remove chat box interface input.
	 */
	public static final int REMOVE_INPUT_INTERFACE = 101;

	/**
	 * The first set of trade parameters.
	 */
	public final static Object[] TRADE_PARAMETERS_1 = new Object[] { -2, 0, 7, 4, 80, 21954610 };

	/**
	 * The offer parameters.
	 */
	public final static Object[] OFFER_PARAMETERS = new Object[] { "", "", "", "", "Offer-X", "Offer-All", "Offer-10", "Offer-5", "Offer", -1, 0, 7, 4, 82, 22020096 };

	/**
	 * The second set of trade parameters.
	 */
	public final static Object[] TRADE_PARAMETERS_2 = new Object[] { "", "", "", "", "Remove-X", "Remove-All", "Remove-10", "Remove-5", "Remove", -1, 0, 7, 4, 81, 21954608 };

	/**
	 * The sting to send on the trade interface run script.
	 */
	public final static String TRADE_TYPE_STRING = "IviiiIsssssssss";

	/**
	 * The set of sell parameters for shopping.
	 */
	public static final Object[] SELL_PARAMETERS = new Object[] { "", "", "", "", "Sell-X", "Sell-10", "Sell-5", "Sell-1", "Value", -1, 0, 7, 4, 93, 40697856 };

	/**
	 * The set of buy parameters for shopping.
	 */
	public static final Object[] BUY_PARAMETERS = new Object[] { "", "", "", "", "Buy-X", "Buy-10", "Buy-5", "Buy-1", "Value", -1, 0, 4, 10, 91, 40632344 };

	/**
	 * The sting to send on the shopping interface run script.
	 */
	public final static String MAIN_STOCK_TYPE_STRING = "vg";

	/**
	 * The sting to send on the shopping interface run script.
	 */
	public final static String MAIN_STOCK_OPTIONS_STRING = "IviiiIsssssssss";
	
	/**
	 * The examine option for run scripts.
	 */
	public static final int SCRIPT_OPTIONS_EXAMINE = 1278;
	
	
	/**
	 * Bonuses as displayed on the equipment screen.
	 */
	public static final String[] BONUSES = { "Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash", "Crush", "Magic", "Range", 
		"Strength", "Prayer", "Ranged Strength"
	};
	
	/**
	 * The directory for the engine scripts.
	 */
	public static final String SCRIPTS_DIRECTORY = "./data/scripts/";
	
	/**
	 * Difference in X coordinates for directions array.
	 */
	public static final byte[] DIRECTION_DELTA_X = new byte[] {-1, 0, 1, -1, 1, -1, 0, 1};
	
	/**
	 * Difference in Y coordinates for directions array.
	 */
	public static final byte[] DIRECTION_DELTA_Y = new byte[] {1, 1, 1, 0, 0, -1, -1, -1};
	
	/**
	 * Default sidebar interfaces array.
	 */
	public static final int SIDEBAR_INTERFACES[][] = new int[][] {
		new int[] {
			90, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112
		},
		new int[] {
			137, 92, 320, 274, 149, 387, 271, 192, 589, 550, 551, 182, 261, 464, 239
		}
	};
	
	/**
	 * Incoming packet sizes array.
	 */
	public static final int[] PACKET_SIZES = new int[] {
	// 0-1---2---3---4---5---6---7---8---9
					-3, 8, -3, 6, -3, -3, -3, -3, -3, -3, // 0
					-3, -1, -3, -3, -3, -3, -3, -3, -3, -3, // 1
					-3, 2, 8, -3, -3, -3, -3, 13, -3, 6, // 2
					-3, -3, -3, -3, 1, 8, -3, -3, -3, -3, // 3
					-3, -3, -3, -3, -3, -3, -1, -3, -3, -3, // 4
					-3, -3, -1, -3, -3, 8, -3, -3, -3, -1, // 5
					-3, -3, -3, -3, 6, -3, -3, -3, -3, -3, // 6
					6, 8, -3, -3, -3, -3, -3, -3, -3, -3, // 7
					-3, -3, -3, -3, -3, -3, -3, 6, -1, 8, // 8
					-3, -3, -3, -3, -3, -3, 2, -3, -3, -3, // 9
					-3, -3, 8, -3, -3, 6, -3, 8, -3, -3, // 10
					-3, -3, -3, -3, -3, -3, -3, -3, -3, -3, // 11
					-3, -3, 8, -3, 8, -3, -3, -3, -3, -3, // 12
					-3, 8, -3, -3, -3, -3, -3, -3, -3, -3, // 13
					-3, -3, -3, -3, -3, -3, -3, -3, -3, -1, // 14
					-3, 0, 8, -3, -3, -3, 6, 8, -3, -3, // 15
					2, -3, -3, 8, -3, -3, 9, 4, -3, 4, // 16
					-3, -3, -3, -3, -3, -3, -3, -3, 2, -3, // 17
					-3, -3, -3, -3, -3, -3, 0, -3, -3, -3, // 18
					-3, -3, -3, -3, -3, -3, 2, -3, -3, -3, // 19
					-3, -3, -1, 6, -3, 8, -3, -3, -3, -3, // 20
					-3, -3, -3, -3, -3, 4, -3, -3, -3, -3, // 21
					-3, -3, -3, -3, -3, -3, -3, 2, -3, -3, // 22
					-3, -3, -3, -3, 6, -3, 1, -3, -3, -3, // 23
					-3, 8, -3, -3, -3, -3, -3, 2, -3, 4, // 24
					9, 3, -1, -3, -3, -3 // 25
					,
	};
	
	/**
	 * 474 Update Reference Keys
	 */
	public static final int[] UPDATE_KEYS = {                                    -1, 0, -1, 0, 0, 0, 0, -128, -2,
		-69, -92, 95, 0, 0, 0, 0, 43, 61, 92, -40, 0, 0, 0, 0, -7, -76, 26,
		-31, 0, 0, 0, -2, 92, -80, 107, -41, 0, 0, 0, 108, 90, 98, -32, 25,
		0, 0, 0, 20, -90, -124, 46, 119, 0, 0, 0, 84, 37, -107, -52, -39,
		0, 0, 0, 0, 103, -9, -101, 90, 0, 0, 0, 116, 42, 19, -99, -8, 0, 0,
		0, 25, -55, -93, 70, 58, 0, 0, 0, 3, 46, -53, -92, -83, 0, 0, 0, 0,
		30, 44, -35, 98, 0, 0, 0, 0, -127, -57, -52, -118, 0, 0, 0, 83, 7,
		-114, 106, 62, 0, 0, 0, 1, -85, 111, 40, -123, 0, 0, 0, -118, -72,
		-14, 77, 33, 0, 0, 0, 0,};

	/**
	 * The player cap.
	 */
	public static final int MAX_PLAYERS = 2000;
	
	/**
	 * The NPC cap.
	 */
	public static final int MAX_NPCS = 32000;
	
	/**
	 * An array of valid characters in a long username.
	 */
	public static final char VALID_CHARS[] = { '_', 'a', 'b', 'c', 'd',
		'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
		'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
		'4', '5', '6', '7', '8', '9', '!', '@', '#', '$', '%', '^', '&',
		'*', '(', ')', '-', '+', '=', ':', ';', '.', '>', '<', ',', '"',
		'[', ']', '|', '?', '/', '`' };
	
	/**
	 * Packed text translate table.
	 */
	public static final char XLATE_TABLE[] = { ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n',
		's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b',
		'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-',
		'&', '*', '\\', '\'', '@', '#', '+', '=', '\243', '$', '%', '"',
		'[', ']' };
	

	
	
	/**
	 * The maximum amount of items in a stack.
	 */
	public static final int MAX_ITEMS = Integer.MAX_VALUE;

}
