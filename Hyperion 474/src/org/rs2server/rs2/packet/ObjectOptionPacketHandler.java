package org.rs2server.rs2.packet;


import org.rs2server.rs2.ScriptManager;
import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.action.Action.AnimationPolicy;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.Cannon;
import org.rs2server.rs2.model.DialogueManager;
import org.rs2server.rs2.model.Door;
import org.rs2server.rs2.model.Doors;
import org.rs2server.rs2.model.Entity;
import org.rs2server.rs2.model.GameObject;
import org.rs2server.rs2.model.GameObjectDefinition;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.ItemDefinition;
import org.rs2server.rs2.model.Ladders;
import org.rs2server.rs2.model.Location;
import org.rs2server.rs2.model.NPC;
import org.rs2server.rs2.model.Shop;
import org.rs2server.rs2.model.skills.Cooking;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.skills.Thieving;
import org.rs2server.rs2.content.Canoes;
import org.rs2server.rs2.content.EnvironmentHandler;
import org.rs2server.rs2.content.HAMHideout;
import org.rs2server.rs2.content.WaterFillables;
import org.rs2server.rs2.content.minigames.Barrows;
import org.rs2server.rs2.content.minigames.WarriorsGuild;
import org.rs2server.rs2.content.misc.*;
import org.rs2server.rs2.event.Event;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.model.Mob.InteractionMode;
import org.rs2server.rs2.model.container.Bank;
import org.rs2server.rs2.model.region.Region;
import org.rs2server.rs2.model.skills.Agility;
import org.rs2server.rs2.model.skills.Mining;
import org.rs2server.rs2.model.skills.Runecrafting;
import org.rs2server.rs2.model.skills.Smithing;
import org.rs2server.rs2.model.skills.Woodcutting;
import org.rs2server.rs2.model.skills.Agility.Obstacle;
import org.rs2server.rs2.model.skills.Mining.Rock;
import org.rs2server.rs2.model.skills.Smithing.Bar;
import org.rs2server.rs2.model.skills.Woodcutting.Tree;
import org.rs2server.rs2.net.Packet;
import org.rs2server.rs2.tickable.Tickable;
import org.rs2server.rs2.util.Misc;

/**
 * Object option packet handler.
 * @author Graham Edgecombe
 *
 */
@SuppressWarnings("unused")
public class ObjectOptionPacketHandler implements PacketHandler {

	private static final int OPTION_1 = 31, OPTION_2 = 203, OPTION_3 = 174, OPTION_4 = 4, ITEM_ON_OBJECT = 134;

	@Override
	public void handle(Player player, Packet packet) {
		if(player.getAttribute("busy") != null) {
			return;
		}
		player.getActionQueue().clearRemovableActions();
		switch(packet.getOpcode()) {
		case OPTION_1:
			handleOption1(player, packet);
			break;
		case OPTION_2:
			handleOption2(player, packet);
			break;
		case OPTION_3:
			handleOption3(player, packet);
			break;
		case OPTION_4:
			handleOption4(player, packet);
			break;
		case ITEM_ON_OBJECT:
			handleOptionItem(player, packet);
			break;
		}
	}

	/**
	 * Handles the option 1 packet.
	 * @param player The player.
	 * @param packet The packet.
	 */
	private void handleOption1(final Player player, Packet packet) {
		final int x = packet.getLEShortA();
		final int id = packet.getShort();
		final int y = packet.getShort();
		final int z = player.getLocation().getZ();
		final Location loc = Location.create(x, y, z);
		Region r = player.getRegion();
		System.out.println("Loc " + loc+"     obj "+id+"     reg "+r.getCoordinates().toString());
		final Door door = r.doorForLocation(loc, id);
		final GameObject obj = r.getGameObject(loc, id);
		

		if(player.showInfo == true){
			player.getActionSender().sendMessage("Loc " + loc+"     ID "+id);		
		}
		
		/**
		 * 
		 * 1 space to object
		 * 
		 */
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 1) {
					
					/**
					 * Door system
					 * @author Killamess
					 */
					if (Doors.getSingleton().handleDoor(id, x, y, z)) {
					}
					/**
					 * Trapdoor system
					 * @author Ayton
					 */
					Ladders.trapdoorsOption1(player, id, obj);

					/**
					 * Environmental objects
					 * @author Ayton
					 */
					EnvironmentHandler.actionOption1(player, id, obj);
					
					/**
					 * All objects in the HAM Hideout Dungeon
					 * @author Ayton
					 */
					HAMHideout.actionOption1(player, id, obj);
					
					switch (id){

					case 23271: //Wilderness Ditch
							Agility.tackleObstacle(player, Obstacle.WILDERNESS_DITCH, obj);
						break;
					case 12309: //Culinaromancer's Chest Bank
						Bank.open(player);
						break;
						
					case 8689: //Cow milking
					case 12111:
						MilkCow.milkCow(player, obj);
						break;
						
						
					case 6:
					case 7:
					case 8:
					case 9:
						if(player.getAttribute("cannon") != null) {
							Cannon cannon = (Cannon) player.getAttribute("cannon");
							if(cannon.getGameObject().getLocation().equals(loc)) {
								if(id == 6) {
									cannon.fire();
								} else {
									cannon.destroy();
								}
							} else {
								player.getActionSender().sendMessage("This is not your cannon.");									
							}
						} else {
							player.getActionSender().sendMessage("This is not your cannon.");
						}
						break;
					case 450:
					case 451:
					case 452:
					case 453:
						player.getActionSender().sendMessage("There is no ore currently available in this rock.");
						return;
						
					}
					
					this.stop();	
				}
			}
		});

		/**
		 * 
		 * 2 spaces to object
		 * 
		 */
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 2) {
					
					/**
					 * Ladder system
					 * @author Ayton
					 */
					Ladders.actionOption1(player, id, obj);
					
					this.stop();	
				}
			}
		});

		
		/**
		 * 
		 * 3 spaces to object
		 * 
		 */
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 3) {
					
					switch(id){
					
					case 12163:
					case 12164:
					case 12165:
					case 12166:
						player.getCanoes().handleObject(player, obj);
						break;
						
					
					case 26806: //Moderator room stairs
						player.setTeleportTarget(Location.create(3230, 3232, 0));
						break;
					}
					
					this.stop();	
				}
			}
		});
		
		if(obj == null && id != 23271) {
			return;
		}
		
		GameObjectDefinition def = GameObjectDefinition.forId(id);
		int width = 1;
		int height = 1;
		if(def != null) {
			width = def.getSizeX();
			height = def.getSizeY();
		}
		if(obj != null && id != 2302) {
			player.face(player.getLocation().oppositeTileOfEntity(obj));
			player.getActionSender().sendDebugPacket(packet.getOpcode(), "ObjOpt1", new Object[] { "ID: " + id, "Loc: " + loc, "X: " + obj.getWidth(), "Y: " + obj.getHeight(), "Direction: " + obj.getDirection(), "Type: " + obj.getType() });
		}

		int distance = 1;
		
		
		if(id == 2282) { //Ropeswing barb course
			distance = 5;
		}		
		if(id == 993) { //Stile Lumbridge
			distance = 2;
		}		
		
		Action action = null;
		Tree tree = Tree.forId(id);
		Rock rock = Rock.forId(id);
		final Obstacle obstacle = Obstacle.forLocation(loc);
		if(tree != null) {
			action = new Woodcutting(player, obj);
		} else if(rock != null) {
			action = new Mining(player, obj);
		} else if(obstacle != null) {
			action = new Action(player, 0) {
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
				@Override
				public void execute() {
					this.stop();
					Agility.tackleObstacle(player, obstacle, obj);
				}			
			};
		} else {
			action = new Action(player, 0) {
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
				@SuppressWarnings("null")
				@Override
				public void execute() {
					this.stop();
					if(Obelisks.handle(player, id, loc)) {
						return;
					}
					if(door != null) {
						int doorX = door.getObject().getLocation().getX();
						int doorY = door.getObject().getLocation().getY();
						
						door.open(true);
					} else {
						switch(id) {

						
							
						default:
							if(obj.getDefinition() != null) {
								if(obj.getDefinition().getName().toLowerCase().contains("bank")) {
									NPC closestBanker = null;
									int closestDist = 10;
									for (NPC banker : World.getWorld().getRegionManager().getLocalNpcs(player)) {
										if (banker.getDefinition().getName().toLowerCase().contains("banker")) {
											if (obj.getLocation().distanceToPoint(banker.getLocation()) < closestDist) {
												closestDist = obj.getLocation().distanceToPoint(banker.getLocation());
												closestBanker = banker;
											}
										}
									}
									if (closestBanker != null) {
										player.setInteractingEntity(InteractionMode.TALK, closestBanker);
										closestBanker.setInteractingEntity(InteractionMode.TALK, player);
										DialogueManager.openDialogue(player, 0);
									}
									return;
								}								
							}
							String scriptName = "objectOptionOne" + id;
							if(!ScriptManager.getScriptManager().invokeWithFailTest(scriptName, player, obj)) {
								System.out.println("[OPT1]: Loc " + loc+"     obj "+id);
							}
							break;
						}
					}
				}			
			};
		}
		if(action != null) {
			player.addCoordinateAction(player.getWidth(), player.getHeight(), loc, width, height, distance, action);
		}
	}
	

	/**
     * Handles the option 2 packet.
     * @param player The player.
     * @param packet The packet.
     */
    private void handleOption2(final Player player, Packet packet) {        
        final int x = packet.getShort() & 0xFFFF;
        final int id = packet.getLEShort() & 0xFFFF;
        final int y = packet.getShortA() & 0xFFFF;
		final int z = player.getLocation().getZ();
		final Location loc = Location.create(x, y, z);
        
		final GameObject obj = player.getRegion().getGameObject(loc, id);
		if(obj == null) {
			return;
		}
		player.face(player.getLocation().oppositeTileOfEntity(obj));

		player.getActionSender().sendDebugPacket(packet.getOpcode(), "ObjOpt2", new Object[] { "ID: " + id, "Loc: " + loc });
		//ItemPicking picker = new ItemPicking(player);
		final Animation PICKING = Animation.create(7270);

		/**
		 * 
		 * 
		 * 1 space to object
		 * 
		 */
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 1) {

					/**
					 * Environmental objects
					 * @author Ayton
					 */
					EnvironmentHandler.actionOption2(player, id, obj);

					/**
					 * All objects in the HAM Hideout Dungeon
					 * @author Ayton
					 */
					HAMHideout.actionOption2(player, id, obj);
					
					if(obj.getDefinition().getName().toLowerCase().contains("bank")) {
						Bank.open(player);
					}
					if(Thieving.handleObjectClick(player, obj)) {
						return;
					}
					if (Doors.getSingleton().handleDoor(id, x, y, z)) {
					}

					if(obj.getDefinition().getName().toLowerCase().contains("furnace")) {
						player.getActionSender().sendChatboxInterface(311);
						player.getActionSender().sendInterfaceModel(311, 4, 125, 2349);//Bronze bar.
						player.getActionSender().sendInterfaceModel(311, 5, 125, 9467);//Blurite bar.
						player.getActionSender().sendInterfaceModel(311, 6, 125, 2351);//Iron bar.
						player.getActionSender().sendInterfaceModel(311, 7, 125, 2355);//Silver
						player.getActionSender().sendInterfaceModel(311, 8, 125, 2353);//Steel bar.
						player.getActionSender().sendInterfaceModel(311, 9, 125, 2357);//Gold bar.
						player.getActionSender().sendInterfaceModel(311, 10, 125, 2359);//Mithril bar.
						player.getActionSender().sendInterfaceModel(311, 11, 125, 2361);//Adamant bar.
						player.getActionSender().sendInterfaceModel(311, 12, 125, 2363);//Rune bar.
					}
					
					switch (id){
					
					case 12309: //Culinaromancer's Chest
						Shop.open(player, 22, 1);
						break;
						
					}
				
					this.stop();	
				}
			}
		});
		
		/**
		 * 
		 * 2 spaces to object
		 * 
		 */
		
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 2) {
					
					/**
					 * Ladder system
					 * @author Ayton
					 */
					Ladders.actionOption2(player, id);
					
					this.stop();	
				}
			}
		});
		
		
		
		switch(id) {
			
		}
		
		Action action = null;
		Action prospectAction = null;
		final Rock rock = Rock.forId(id);
		if(rock != null || (id == 450 || id == 451 || id == 452 || id == 453)) {
			prospectAction = new Action(player, 4) {
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
				@Override
				public void execute() {
					if(id == 450 || id == 451 || id == 452 || id == 453) {
						player.getActionSender().sendMessage("This rock has no current ore available.");
					} else {
						player.getActionSender().sendMessage("This rock contains " + ItemDefinition.forId(rock.getOreId()).getName().toLowerCase() + ".");
					}
					this.stop();
				}				
			};
		}
		final Action finalProspectAction = prospectAction;
		action = new Action(player, 0) {
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
			@Override
			public void execute() {
				if(rock != null && finalProspectAction != null) {
					player.getActionSender().sendMessage("You examine the rock for ores...");
					player.getActionQueue().addAction(finalProspectAction);
				} else {
					
					switch(id) {
					case 6:
						if(player.getAttribute("cannon") != null) {
							Cannon cannon = (Cannon) player.getAttribute("cannon");
							if(loc.equals(cannon.getGameObject().getLocation())) {
								cannon.destroy();
							}
						}
						break;
					default:
						String scriptName = "objectOptionTwo" + id;
						if(!ScriptManager.getScriptManager().invokeWithFailTest(scriptName, player, obj)) {
							//player.getActionSender().sendMessage("Nothing interesting happens.");
							System.out.println("[OPT2]: Loc " + loc+"     obj "+id);
						}
						break;
					}
				}
				this.stop();
			}			
		};
		if(action != null) {
			player.addCoordinateAction(player.getWidth(), player.getHeight(), obj.getLocation(), obj.getWidth(), obj.getHeight(), 1, action);
		}
    }

	/**
     * Handles the option 3 packet.
     * @param player The player.
     * @param packet The packet.
     */
    private void handleOption3(final Player player, Packet packet) { 
        final int x = packet.getLEShortA() & 0xFFFF;   
        final int id = packet.getLEShort();    
        final int y = packet.getLEShort() & 0xFFFF;
        
		int z = player.getLocation().getZ();
		Region r = player.getRegion();
		final Location loc = Location.create(x, y, z);
		final GameObject obj = r.getGameObject(loc, id);
		
		player.getActionSender().sendDebugPacket(packet.getOpcode(), "ObjOpt3", new Object[] { "ID: " + id});
		


		/**
		 * 2 spaces to object
		 */
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 2) {
					
					/**
					 * Ladder system
					 * @author Ayton
					 */
					Ladders.actionOption3(player, id);
					
					this.stop();	
				}
			}
		});
		
		/**
		 * 1 space to object
		 */
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 2) {
					
					switch(id){
					case 12309:
						Shop.open(player, 21, 1);
						break;
					}
					
					this.stop();	
				}
			}
		});
		
		System.out.println("[OPT3]: Loc " + loc+"     obj "+id);
        
    }
	/**
     * Handles the option 4 packet.
     * @param player The player.
     * @param packet The packet.
     */
    private void handleOption4(final Player player, Packet packet) { 
        final int x = packet.getLEShortA() & 0xFFFF;   
        final int id = packet.getLEShort();    
        final int y = packet.getLEShort() & 0xFFFF;
        
		int z = player.getLocation().getZ();
		Region r = player.getRegion();
		final Location loc = Location.create(x, y, z);
		final GameObject obj = r.getGameObject(loc, id);
		
		player.getActionSender().sendDebugPacket(packet.getOpcode(), "ObjOpt4", new Object[] { "ID: " + id});

		
		
		/**
		 * 1 space to object
		 */
		World.getWorld().submit(new Tickable(2) {
			public void execute() {
				if(Misc.getDistance(player.getLocation(), loc) <= 1) {

					HAMHideout.actionOption4(player, id, obj);
					
					switch(id){
					}
					
					this.stop();	
				}
			}
		});

		player.face(player.getLocation().oppositeTileOfEntity(obj));
		System.out.println("[OPT4]: Loc " + loc+"     obj "+id);
        
    }
	/**
     * Handles the item on object packet.
     * @param player The player.
     * @param packet The packet.
     */
    private void handleOptionItem(final Player player, Packet packet) {
        final int x = packet.getShortA() & 0xFFFF;
        final int slot = packet.getShortA() & 0xFFFF;
        final int id = packet.getLEShort() & 0xFFFF;
        final int y = packet.getLEShortA() & 0xFFFF;
		int z = player.getLocation().getZ();
		if(player.getAttribute("temporaryHeight") != null) {
			//z = player.getAttribute("temporaryHeight");
		}
		final Location loc = Location.create(x, y, z);

        final Item item = player.getInventory().get(slot);
        if(item == null) {
        	return;
        }
        switch(id) {
        case 15621:
        	WarriorsGuild.create(player).armorOnPlatform();
        	break;
        }
		final GameObject obj = player.getRegion().getGameObject(loc, id);
		if (obj == null) {
			return;
		}
		System.out.println("Id: " + id + " Itemid: " + item.getDefinition().getId() + " Loc: " + loc.getX() + "," + loc.getY() + "," + loc.getZ());
		player.face(player.getLocation().oppositeTileOfEntity(obj));

		player.getActionSender().sendDebugPacket(packet.getOpcode(), "ItemOnObject", new Object[] { "ID: " + id, "Loc: " + loc });
		
		Action action = null;
		action = new Action(player, 0) {
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
			@Override
			public void execute() {
				WaterFillables.checkForWaterFillingItemAndObjects(player, item.getId(), slot, obj.getId());
				if(obj.getDefinition().getName().equalsIgnoreCase("Anvil")) {
					Bar bar = Bar.forId(item.getId());
					if(bar != null) {
						Smithing.openSmithingInterface(player, bar);
					}
				} else {
					
					switch(id) {
					case 114:
					case 21302:
					case 2714:
					case 1782:
					Cooking.checkForItemOnObject(player, item.getDefinition().getId(), slot, id);
					break;
					case 6:
						if(player.getAttribute("cannon") != null) {
							Cannon cannon = (Cannon) player.getAttribute("cannon");
							if(loc.equals(cannon.getGameObject().getLocation())) {
								if(item.getId() == 2) {
									int cannonBalls = cannon.getCannonBalls();
									if(cannonBalls >= 30) {
										player.getActionSender().sendMessage("Your cannon is already full.");
										return;
									}
									int newCannonBalls = item.getCount();
									if(newCannonBalls > 30) {
										newCannonBalls = 30;
									}
									if(newCannonBalls + cannonBalls > 30) {
										newCannonBalls = 30 - cannonBalls;
									}
									if(newCannonBalls < 1) {
										return;
									}
									player.getInventory().remove(new Item(2, newCannonBalls));
									cannon.addCannonBalls(newCannonBalls);
									player.getActionSender().sendMessage("You load " + newCannonBalls + " cannonball" + (newCannonBalls > 1 ? "s" : "") + " into your cannon.");									
								}
							}
						}
						break;
					case 7:
						if(player.getAttribute("cannon") != null) {
							Cannon cannon = (Cannon) player.getAttribute("cannon");
							if(loc.equals(cannon.getGameObject().getLocation())) {
								if(item.getId() == 8) {
									cannon.addPart(new Item(8, 1));
								}
							}
						}
						break;
					case 8:
						if(player.getAttribute("cannon") != null) {
							Cannon cannon = (Cannon) player.getAttribute("cannon");
							if(loc.equals(cannon.getGameObject().getLocation())) {
								if(item.getId() == 10) {
									cannon.addPart(new Item(10, 1));
								}
							}
						}
						break;
					case 9:
						if(player.getAttribute("cannon") != null) {
							Cannon cannon = (Cannon) player.getAttribute("cannon");
							if(loc.equals(cannon.getGameObject().getLocation())) {
								if(item.getId() == 12) {
									cannon.addPart(new Item(12, 1));
								}
							}
						}
						break;
					}
				}
				this.stop();
			}			
		};
		if(action != null) {
			player.addCoordinateAction(player.getWidth(), player.getHeight(), obj.getLocation(), obj.getWidth(), obj.getHeight(), 1, action);
		}
    }


}
