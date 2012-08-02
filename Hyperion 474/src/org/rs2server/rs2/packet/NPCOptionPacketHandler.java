package org.rs2server.rs2.packet;

import org.rs2server.rs2.Constants;
import org.rs2server.rs2.ScriptManager;
import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.model.DialogueManager;
import org.rs2server.rs2.model.Item;
import org.rs2server.rs2.model.NPC;
import org.rs2server.rs2.model.NPCDefinition;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.content.misc.HasItemCheck;
import org.rs2server.rs2.content.misc.SheepShear;
import org.rs2server.rs2.content.misc.Tutors;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.model.Animation.FacialAnimation;
import org.rs2server.rs2.model.Mob.InteractionMode;
import org.rs2server.rs2.model.combat.impl.MagicCombatAction;
import org.rs2server.rs2.model.combat.impl.MagicCombatAction.Spell;
import org.rs2server.rs2.model.combat.impl.MagicCombatAction.SpellBook;
import org.rs2server.rs2.model.combat.impl.MagicCombatAction.SpellType;
import org.rs2server.rs2.model.container.Bank;
import org.rs2server.rs2.model.skills.Runecrafting;
import org.rs2server.rs2.model.skills.Thieving;
import org.rs2server.rs2.net.Packet;
import org.rs2server.rs2.net.ActionSender.DialogueType;

/**
 * Remove item options.
 * @author Graham Edgecombe
 *
 */
public class NPCOptionPacketHandler implements PacketHandler {

	private static final int OPTION_1 = 21, OPTION_2 = 160, OPTION_ATTACK = 196, OPTION_SPELL = 157, OPTION_EXAMINE = 178;

	@Override
	public void handle(Player player, Packet packet) {
		if(player.getInterfaceAttribute("fightPitOrbs") != null) {
			return;
		}
		switch(packet.getOpcode()) {
		case OPTION_1:
			handleOption1(player, packet);
			break;
		case OPTION_2:
			handleOption2(player, packet);
			break;
		case OPTION_ATTACK:
			handleOptionAttack(player, packet);
			break;
		case OPTION_SPELL:
			handleOptionSpell(player, packet);
			break;
		case OPTION_EXAMINE:
			handleOptionExamine(player, packet);
			break;
		}
	}
	
	/**
	 * Handles npc option 1.
	 * @param player The player.
	 * @param packet The packet.
	 */
	private void handleOption1(final Player player, Packet packet) {
		int id = packet.getShort() & 0xFFFF;
		if(id < 0 || id >= Constants.MAX_NPCS) {
			return;
		}
		if(player.getCombatState().isDead()) {
			return;
		}
		player.getActionQueue().clearRemovableActions();

		final NPC npc = (NPC) World.getWorld().getNPCs().get(id);

		player.getActionSender().sendDebugPacket(packet.getOpcode(), "NpcOpt1", new Object[] { "ID: " + npc.getDefinition().getId(), "Index: " + id });

		if(player.showInfo == true){
			player.getActionSender().sendMessage("ID "+npc.getDefinition().getId());		
		}
		
		if(npc != null) {
			player.setInteractingEntity(InteractionMode.TALK, npc);
			Action action = new Action(player, 0) {
				@Override
				public void execute() {
					if(player.getCombatState().isDead()) {
						this.stop();
						return;
					}
					if(npc.getDefinition().getName().equalsIgnoreCase("Sheep")) {
						SheepShear shearer = new SheepShear(player);
						shearer.shearSheep(npc);
					}
					if(npc.getDefinition().getId() == 1717) {
						DialogueManager.openDialogue(player, 1043);
					}
					if(npc.getDefinition().getInteractionMenu()[0].startsWith("Talk") && npc.getDefinition().getId() != 1717) {
						if(npc.getDefinition().getName().toLowerCase().contains("banker")) {
							DialogueManager.openDialogue(player, 0);
						} else {
							String scriptName = "talkTo" + npc.getDefinition().getId();
							if(!ScriptManager.getScriptManager().invokeWithFailTest(scriptName, player, npc)) {
								player.getActionSender().sendMessage(npc.getDefinedName() + " does not want to talk.");							
							}
						}
						npc.setInteractingEntity(InteractionMode.TALK, player);
					}
					this.stop();
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
			};
			int distance = 1;
			if(npc.getDefinition().getName().toLowerCase().contains("banker")
							|| npc.getDefinition().getName().toLowerCase().contains("emily")
							|| npc.getDefinition().getName().toLowerCase().contains("zambo")
							|| npc.getDefinition().getId() == 4296) {
				distance = 2;
			}
			player.addCoordinateAction(player.getWidth(), player.getHeight(), npc.getLocation(), npc.getWidth(), npc.getHeight(), distance, action);
		}
	}
	
	/**
	 * Handles npc option 2.
	 * @param player The player.
	 * @param packet The packet.
	 */
	private void handleOption2(final Player player, Packet packet) {
		int id = packet.getLEShort() & 0xFFFF;
		if(id < 0 || id >= Constants.MAX_NPCS) {
			return;
		}
		if(player.getCombatState().isDead()) {
			return;
		}
		player.getActionQueue().clearRemovableActions();

		final NPC npc = (NPC) World.getWorld().getNPCs().get(id);

		player.getActionSender().sendDebugPacket(packet.getOpcode(), "NpcOpt2", new Object[] { "ID: " + npc.getDefinition().getId(), "Index: " + id });


		if(player.showInfo == true){
			player.getActionSender().sendMessage("ID "+npc.getDefinition().getId());		
		}
		 
		if(npc != null) {
			player.setInteractingEntity(InteractionMode.TALK, npc);
			Action action = new Action(player, 0) {
				@Override
				public void execute() {
					if(player.getCombatState().isDead()) {
						this.stop();
						return;
					}
					String name = npc.getDefinition().getName();
					if(npc.getDefinition().getInteractionMenu()[2].startsWith("Bank")) {
						Bank.open(player);

					} else if(npc.getDefinition().getId() == 4290) {
						DialogueManager.openDialogue(player, 624);
					} else if (npc.getDefinition().getId() == 5477) {
						DialogueManager.openDialogue(player, 689);
					} else if(npc.getDefinition().getId() == 1861) {
						Tutors.getRangedEquipment(player);
					} else if(npc.getDefinition().getId() == 4707) {
						Tutors.getMagicEquipment(player);
					}  else {
						String scriptName = "tradeWith" + npc.getDefinition().getId();
						if(!ScriptManager.getScriptManager().invokeWithFailTest(scriptName, player, npc)) {
							
						} else {
							npc.setInteractingEntity(InteractionMode.TALK, player);
						}
					}
					this.stop();
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
			};
			int distance = 1;
			if(npc.getDefinition().getName().toLowerCase().contains("banker")
							|| npc.getDefinition().getName().toLowerCase().contains("emily")
							|| npc.getDefinition().getName().toLowerCase().contains("zambo")) {
				distance = 2;
			}
			player.addCoordinateAction(player.getWidth(), player.getHeight(), npc.getLocation(), npc.getWidth(), npc.getHeight(), distance, action);
		}
	}
	
	/**
	 * Handles npc attack option.
	 * @param player The player.
	 * @param packet The packet.
	 */
	private void handleOptionAttack(final Player player, Packet packet) {
		final int id = packet.getLEShort() & 0xFFFF;
		if(id < 0 || id >= Constants.MAX_NPCS) {
			return;
		}
		if(player.getCombatState().isDead()) {
			return;
		}
		player.getActionQueue().clearRemovableActions();

		final NPC npc = (NPC) World.getWorld().getNPCs().get(id);

		player.getActionSender().sendDebugPacket(packet.getOpcode(), "NpcAttack", new Object[] { "ID: " + npc.getDefinition().getId(), "Index: " + id });
		
		if(npc != null) {
			player.getCombatState().setQueuedSpell(null);
			player.getCombatState().startAttacking(npc, false);
		}
	}
	
	/**
	 * Handles npc spell option.
	 * @param player The player.
	 * @param packet The packet.
	 */
	private void handleOptionSpell(final Player player, Packet packet) {
		packet.getShort();
		int interfaceValue = packet.getLEInt();
//		int interfaceId = interfaceValue >> 16;
		final int childButton = interfaceValue & 0xFFFF;
		int id = packet.getLEShortA();
		if(id < 0 || id >= Constants.MAX_NPCS) {
			return;
		}
		if(player.getCombatState().isDead()) {
			return;
		}
		player.getActionQueue().clearRemovableActions();

		NPC npc = (NPC) World.getWorld().getNPCs().get(id);

		player.getActionSender().sendDebugPacket(packet.getOpcode(), "NpcSpell", new Object[] { "ID: " + npc.getDefinition().getId(), "Index: " + id, "Button: " + childButton });
		
		Spell spell = Spell.forId(childButton, SpellBook.forId(player.getCombatState().getSpellBook()));
		if(npc != null && spell != null) {
			if(spell.getSpellType() == SpellType.NON_COMBAT) {
				return;
			}
			MagicCombatAction.setAutocast(player, null, -1, false);
			player.getCombatState().setQueuedSpell(spell);
			player.getCombatState().startAttacking(npc, false);
		}
	}
	
	/**
	 * Handles npc option examine.
	 * @param player The player.
	 * @param packet The packet.
	 */
	private void handleOptionExamine(Player player, Packet packet) {
		int id = packet.getShortA() & 0xFFFF;
		if(id < 0 || id >= Constants.MAX_NPCS) {
			return;
		}
		if(player.getCombatState().isDead()) {
			return;
		}

		player.getActionSender().sendDebugPacket(packet.getOpcode(), "NpcExamine", new Object[] { "ID: " + id });
		
		NPCDefinition npcDef = NPCDefinition.forId(id);
		if(npcDef != null) {
			player.getActionSender().sendMessage(npcDef.getDescription());
		}
	}

}
