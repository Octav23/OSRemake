package org.rs2server.rs2.model;
/* imports */
import org.rs2server.rs2.Constants;
import org.rs2server.rs2.content.minigames.WarriorsGuild;
import org.rs2server.rs2.content.misc.*;
import org.rs2server.rs2.model.Animation.FacialAnimation;
import org.rs2server.rs2.model.Player.Rights;
import org.rs2server.rs2.model.combat.impl.MagicCombatAction;
import org.rs2server.rs2.model.combat.impl.MagicCombatAction.Spell;
import org.rs2server.rs2.model.container.Bank;
import org.rs2server.rs2.model.container.Equipment;
import org.rs2server.rs2.net.ActionSender.DialogueType;
import org.rs2server.rs2.util.Misc;
/* Imports */

public class DialogueManager {
	

	public static void openDialogue(final Player player, int dialogueId) {
		if (dialogueId == -1) {
			return;
		}
		for (int i = 0; i < 5; i++) {
			player.getInterfaceState().setNextDialogueId(i, -1);
		}
		player.getInterfaceState().setOpenDialogueId(dialogueId);
		NPC npc = (NPC) player.getInteractingEntity();
		
		
		switch (dialogueId) {
		/*
		 *  BANKER
		 */
		case 0:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Good day. How may I help you?");
			player.getInterfaceState().setNextDialogueId(0, 1);
			break;
			
			
		case 1:
			player.getActionSender().sendDialogue("Select an Option",
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like to access my bank account, please.",
					"I'd like to set/change my PIN please.",
					"What is this place?");
			player.getInterfaceState().setNextDialogueId(0, 2);
			player.getInterfaceState().setNextDialogueId(1, 3);
			player.getInterfaceState().setNextDialogueId(2, 5);
			break;
			
			
		case 2:
			player.getActionSender().removeChatboxInterface();
			Bank.open(player);
			break;
			
			
		case 3:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"I'd like to set/change my PIN please.");
			player.getInterfaceState().setNextDialogueId(0, 4);
			break;
			
			
		case 4:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"We currently do not offer bank PINs, sorry.");
			break;
			
			
		case 5:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"What is this place?");
			player.getInterfaceState().setNextDialogueId(0, 6);
			break;
			
			
		case 6:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(),
					DialogueType.NPC,
					npc.getDefinition().getId(),
					FacialAnimation.DEFAULT,
					"This is a branch of the Bank of " + Constants.SERVER_NAME
							+ ". We have", "branches in many towns.");
			player.getInterfaceState().setNextDialogueId(0, 7);
			break;
			
			
		case 7:
			player.getActionSender().sendDialogue("Select an Option",
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"And what do you do?",
					"Didn't you used to be called the Bank of Varrock?");
			player.getInterfaceState().setNextDialogueId(0, 8);
			player.getInterfaceState().setNextDialogueId(1, 10);
			break;
			
			
		case 8:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"And what do you do?");
			player.getInterfaceState().setNextDialogueId(0, 9);
			break;
			
			
		case 9:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"We will look after your items and money for you.",
					"Leave your valuables with us if you want to keep them",
					"safe.");
			break;
			
			
		case 10:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Didn't you used to be called the Bank of Varrock?");
			player.getInterfaceState().setNextDialogueId(0, 11);
			break;
			
			
		case 11:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Yes we did, but people kept on coming into our",
					"branches outside of Varrock and telling us that our",
					"signs were wrong. They acted as if we didn't know",
					"what town we were in or something.");
			break;
			
			/*
			 * Spells dialogue
			 */
			
		case 12:
			player.getActionSender().sendDialogue("Choose spellbook:",
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Standard", "Ancient", "Lunar", "Cancel");
			player.getInterfaceState().setNextDialogueId(0, 13);
			player.getInterfaceState().setNextDialogueId(1, 14);
			player.getInterfaceState().setNextDialogueId(2, 15);
			player.getInterfaceState().setNextDialogueId(3, 16);
			break;
			
			
		case 13:
			player.getCombatState().setSpellbookSwap(true);
			MagicCombatAction.executeSpell(Spell.SPELLBOOK_SWAP, player,
					player, 0);
			player.getActionSender().removeAllInterfaces();
			break;
			
			
		case 14:
			player.getCombatState().setSpellbookSwap(true);
			MagicCombatAction.executeSpell(Spell.SPELLBOOK_SWAP, player,
					player, 1);
			player.getActionSender().removeAllInterfaces();
			break;
			
			
		case 15:
			/*player.getCombatState().setSpellbookSwap(true);
			MagicCombatAction.executeSpell(Spell.SPELLBOOK_SWAP, player,
					player, 2);
			player.getActionSender().removeAllInterfaces();
			break;*/
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Sorry, I have no knowledge of this spellbook.",
					"You might have better luck speaking to a member",
					"of the Etheral mages.");
			player.getInterfaceState().setNextDialogueId(1, 16);
			break;
			
/////////////////////////////////////////////////////////////////////////////////////
			/*
			 * ATTACK LEVEL UP
			 */
			
		case 76:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.ATTACK_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"  Congratulations, you have just advanced an Attack level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.ATTACK) + ".");
			if (player.getSkills().getLevelForExperience(Skills.ATTACK) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 109);
			}
			break;
			
			/*
			 * DEFENCE LEVEL UP
			 */
			
		case 77:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.DEFENCE_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							" Congratulations, you have just advanced a Defence level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.DEFENCE) + ".");
			if (player.getSkills().getLevelForExperience(Skills.DEFENCE) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 107);
			}
			break;
			
			/*
			 * STR LEVEL UP
			 */
			
		case 78:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.STRENGTH_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Strength level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.STRENGTH) + ".");
			if (player.getSkills().getLevelForExperience(Skills.STRENGTH) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 110);
			}
			break;
			
			/*
			 * HP LEVEL UP
			 */
			
		case 79:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.HITPOINT_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Hitpoints level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.HITPOINTS) + ".");
			if (player.getSkills().getLevelForExperience(Skills.HITPOINTS) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 114);
			}
			break;
			
			/*
			 * RANGE LEVEL UP
			 */
			
		case 80:
			player.getActionSender().sendDialogue(
					player.getName(),
					DialogueType.RANGING_LEVEL_UP,
					-1,
					FacialAnimation.DEFAULT,
					"Congratulations, you have just advanced a Ranged level!",
					"You have now reached level "
							+ player.getSkills().getLevelForExperience(
									Skills.RANGE) + ".");
			if (player.getSkills().getLevelForExperience(Skills.RANGE) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 108);
			}
			break;
			
			/*
			 * PRAYER LEVEL UP
			 */
			
		case 81:
			player.getActionSender().sendDialogue(
					player.getName(),
					DialogueType.PRAYER_LEVEL_UP,
					-1,
					FacialAnimation.DEFAULT,
					"Congratulations, you have just advanced a Prayer level!",
					"You have now reached level "
							+ player.getSkills().getLevelForExperience(
									Skills.PRAYER) + ".");
			if (player.getSkills().getLevelForExperience(Skills.PRAYER) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 111);
			}
			break;
			
			/*
			 * MAGIC LEVEL UP
			 */
			
		case 82:
			player.getActionSender().sendDialogue(
					player.getName(),
					DialogueType.MAGIC_LEVEL_UP,
					-1,
					FacialAnimation.DEFAULT,
					"Congratulations, you have just advanced a Magic level!",
					"You have now reached level "
							+ player.getSkills().getLevelForExperience(
									Skills.MAGIC) + ".");
			if (player.getSkills().getLevelForExperience(Skills.MAGIC) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 112);
			}
			break;
			
			/*
			 * COOKING LEVEL UP
			 */
			
		case 83:
			player.getActionSender().sendDialogue(
					player.getName(),
					DialogueType.COOKING_LEVEL_UP,
					-1,
					FacialAnimation.DEFAULT,
					"Congratulations, you have just advanced a Cooking level!",
					"You have now reached level "
							+ player.getSkills().getLevelForExperience(
									Skills.COOKING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.COOKING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 218);
			}
			break;
			
			/*
			 * WOODCUTTING LEVEL UP
			 */
			
		case 84:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.WOODCUTTING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Woodcutting level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.WOODCUTTING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.WOODCUTTING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 220);
			}
			break;
			
			/*
			 * FLETCHING LEVEL UP
			 */
			
		case 85:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.FLETCHING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"  Congratulations, you have just advanced a Fletching level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.FLETCHING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.FLETCHING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 119);
			}
			break;
			
			/*
			 * FISHING LEVEL UP
			 */
			
		case 86:
			player.getActionSender().sendDialogue(
					player.getName(),
					DialogueType.FISHING_LEVEL_UP,
					-1,
					FacialAnimation.DEFAULT,
					"Congratulations, you have just advanced a Fishing level!",
					"You have now reached level "
							+ player.getSkills().getLevelForExperience(
									Skills.FISHING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.FISHING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 123);
			}
			break;
			
			/*
			 * FIREMAKING LEVEL UP
			 */
			
		case 87:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.FIREMAKING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Firemaking level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.FIREMAKING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.FIREMAKING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 219);
			}
			break;
			
			/*
			 * CRAFTING LEVEL UP
			 */
			
		case 88:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.CRAFTING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Crafting level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.CRAFTING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.CRAFTING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 118);
			}
			break;
			
			/*
			 * SMITHING LEVEL UP
			 */
			
		case 89:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.SMITHING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"  Congratulations, you have just advanced a Smithing level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.SMITHING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.SMITHING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 122);
			}
			break;
			
			/*
			 * MINING LEVEL UP
			 */
			
		case 90:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MINING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"  Congratulations, you have just advanced a Mining level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.MINING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.MINING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 121);
			}
			break;
			
			/*
			 * HERBLORE LEVEL UP
			 */
			
		case 91:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.HERBLORE_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Herblore level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.HERBLORE) + ".");
			if (player.getSkills().getLevelForExperience(Skills.HERBLORE) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 116);
			}
			break;
			
			/*
			 * AGILITY LEVEL UP
			 */
		case 92:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.AGILITY_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced an Agility level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.AGILITY) + ".");
			if (player.getSkills().getLevelForExperience(Skills.AGILITY) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 115);
			}
			break;
			
			/*
			 * THIEVING LEVEL UP
			 */
			
		case 93:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.THIEVING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Thieving level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.THIEVING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.THIEVING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 117);
			}
			break;
			
			/*
			 * SLAYER LEVEL UP
			 */
			
		case 94:
			player.getActionSender().sendDialogue(
					player.getName(),
					DialogueType.SLAYER_LEVEL_UP,
					-1,
					FacialAnimation.DEFAULT,
					"Congratulations, you have just advanced a Slayer level!",
					"You have now reached level "
							+ player.getSkills().getLevelForExperience(
									Skills.SLAYER) + ".");
			if (player.getSkills().getLevelForExperience(Skills.SLAYER) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 120);
			}
			break;
			
			/*
			 * FARMING LEVEL UP
			 */
			
		case 95:
			player.getActionSender().sendDialogue(
					player.getName(),
					DialogueType.FARMING_LEVEL_UP,
					-1,
					FacialAnimation.DEFAULT,
					"Congratulations, you have just advanced a Farming level!",
					"You have now reached level "
							+ player.getSkills().getLevelForExperience(
									Skills.FARMING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.FARMING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 221);
			}
			break;
			
			/*
			 * RUNECRAFTING LEVEL UP
			 */
			
		case 96:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.RUNECRAFTING_LEVEL_UP,
							-1,
							FacialAnimation.DEFAULT,
							"Congratulations, you have just advanced a Runecrafting level!",
							"You have now reached level "
									+ player.getSkills().getLevelForExperience(
											Skills.RUNECRAFTING) + ".");
			if (player.getSkills().getLevelForExperience(Skills.RUNECRAFTING) > 98) {
				player.getInterfaceState().setNextDialogueId(0, 113);
			}
			break;
////////////////////////////////////////////////////////////////////////////////////////////
			/* DEFENCE MASTERY */
			
		case 107:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9753,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Defence. Why not visit the Melee combat tutor in Lumbridge? He has something special that is only available to the true masters of the Defence skill!");
			break;
			
		/* RANGE MASTERY */	
		case 108:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9756,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Ranging. Why not visit the Salesman in the Ranging Guild? He has something special that is only available to the true masters of the Ranging skill!");
			break;
		/* Attack Mastery */	
		case 109:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9747,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Attack." +
							" Why not visit Sajjat in the Warrior's Guild?" +
							" He has something special that is only available" +
							" to the true masters of the Attack skill!");
			break;
		/* Strength Mastery */	
		case 110:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9750,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Strength." +
							" Why not visit Sloane in the Warrior's Guild?" +
							" He has something special that is only available" +
							" to the true masters of the Strength skill!");
			break;
		/* Prayer Mastery */	
		case 111:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9759,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Prayer." +
							" Why not visit Brother Jered in the Prayer Guild?" +
							" He has something special that is only available" +
							" to the true masters of the Prayer skill!");
			break;
		/* Magic Mastery */	
		case 112:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9762,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Magic." +
							" Why not visit the Robe Store in the Magic Guild?" +
							" He has something special that is only available" +
							" to the true masters of the Magic skill!");
			break;
		/* Runecrafting Mastery */	
		case 113:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9765,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Runecraft." +
							" Why not visit Aubury in Varrock?" +
							" He has something special that is only available" +
							" to the true masters of the Runecraft. skill!");
			break;
		/* Hitpoints Mastery */	
		case 114:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9768,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Hitpoints." +
							" Why not visit Genral Tafani in the Duel Arena?" +
							" He has something special that is only available" +
							" to the true masters of the Hitpoints skill!");
			break;
		/* Agility Mastery */	
		case 115:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9771,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Agility." +
							" Why not visit Izzy No-Beard in the Agility Arena?" +
							" He has something special that is only available" +
							" to the true masters of the Agility skill!");
			break;
		/* Herblore Mastery */	
		case 116:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9774,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Herblore." +
							" Why not visit Kaqemeex in Taverley?" +
							" He has something special that is only available" +
							" to the true masters of the Herblore skill!");
			break;
		/* Thieving Mastery */	
		case 117:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9777,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Thieving." +
							" Why not visit Martin Thwait in the Rogues' Den?" +
							" He has something special that is only available" +
							" to the true masters of the Thieving skill!");
			break;
		/* Crafting Mastery */	
		case 118:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9780,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Crafting." +
							" Why not visit the Master in the Crafting Guild?" +
							" He has something special that is only available" +
							" to the true masters of the Crafting skill!");
			break;
		/* Fletching Mastery */	
		case 119:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9783,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Fletching." +
							" Why not visit Hickton in Catherby?" +
							" He has something special that is only available" +
							" to the true masters of the Fletching skill!");
			break;
		/* Slayer Mastery */	
		case 120:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9786,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Slayer." +
							" Why not visit Duradel in Shilo Village?" +
							" He has something special that is only available" +
							" to the true masters of the Slayer skill!");
			break;
		/* Mining Mastery */	
		case 121:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9792,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Mining." +
							" Why not visit the Dwarf in the Mining Guild?" +
							" He has something special that is only available" +
							" to the true masters of the Mining skill!");
			break;
		/* Smithing Mastery */	
		case 122:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9795,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Smithing." +
							" Why not visit Turgo at the Mudskipper Point?" +
							" He has something special that is only available" +
							" to the true masters of the Smithing skill!");
			break;
		/* Fishing Mastery */	
		case 123:
			player
					.getActionSender()
					.sendDialogue(
							player.getName(),
							DialogueType.MESSAGE_MODEL_LEFT,
							9798,
							FacialAnimation.DEFAULT,
							"Congratulations! You are now a master of Fishing." +
							" Why not visit the Master in the Fishing Guild?" +
							" He has something special that is only available" +
							" to the true masters of the Fishing skill!");
			break;
			
			
/////////////////////////////////////////////////////////////////////////////
			/* HAIRDRESSER */
		case 124:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Hello there! Up for a quick trim or a totally",
					"new look?");
			player.getInterfaceState().setNextDialogueId(0, 125);
			break;
			
			
		case 125:
			player.getActionSender().sendDialogue("Select an Option",
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Haircut.", "Shave.", "No thank you.");
			player.getInterfaceState().setNextDialogueId(0, 126);
			player.getInterfaceState().setNextDialogueId(1, 129);
			player.getInterfaceState().setNextDialogueId(2, 51);
			break;
			
			
		case 126:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"I want a haircut please.");
			player.getInterfaceState().setNextDialogueId(0, 127);
			break;
			
			
		case 127:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Certainly.");
			player.getInterfaceState().setNextDialogueId(0, 128);
			break;
			
			
		case 128:
			player.getActionSender().removeChatboxInterface()
					.sendConfig(261, 1).sendConfig(262, 1).sendInterface(
							204 - player.getAppearance().getGender(), true);
			player.setInterfaceAttribute("newHair", 0 + (player.getAppearance()
					.getGender() * 45));
			player.setInterfaceAttribute("newHairColour", 0);
			//Boothes.inBooth(player);
			break;
			
			
		case 129:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"I want a shave please.");
			player.getInterfaceState().setNextDialogueId(0,
					131 - player.getAppearance().getGender());
			break;
			
			
		case 130:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I'm sorry but women don't grow facial hair.");
			break;
			
			
		case 131:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Certainly.");
			player.getInterfaceState().setNextDialogueId(0, 132);
			break;
			
			
		case 132:
			player.getActionSender().removeChatboxInterface()
					.sendConfig(261, 1).sendConfig(262, 1).sendInterface(199,
							true);
			player.setInterfaceAttribute("newBeard", 11);
			player.setInterfaceAttribute("newHairColour", 0);
//			Boothes.inBooth(player);
			break;
			
			
		/* MAKE-OVER MAGE */	
		case 133:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"*gulp* ... Hiya. Fancy changing your body for a",
					"small fee of 3,000 coins? It will reset all your",
					"clothes to their default, so bear that in mind!");
			player.getInterfaceState().setNextDialogueId(0, 134);
			break;
			
			
		case 134:
			player.getActionSender().sendDialogue("Select an Option",
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT, "Sure.",
					"No thank you.");
			player.getInterfaceState().setNextDialogueId(0, 135);
			player.getInterfaceState().setNextDialogueId(1, 51);
			break;
			
			
		case 135:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT, "Sure.");
			player.getInterfaceState().setNextDialogueId(0, 136);
			break;
			
			
		case 136:
			player.getActionSender().removeChatboxInterface()
					.sendConfig(261, 1).sendConfig(262, 1).sendInterface(205,
							true);
			player.setInterfaceAttribute("newGender", 0);
			player.setInterfaceAttribute("newSkinColour", 0);
			break;
			
			
		case 208:
		case 210:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"You want to access your bank account,",
					"JalYt-Ket-" + player.getName() + "?");
			player.getInterfaceState().setNextDialogueId(0, 211);
			break;
			
			
		case 211:
			player.getActionSender().sendDialogue("Select an Option",
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes please.", "No thank you.");
			player.getInterfaceState().setNextDialogueId(0, 2);
			player.getInterfaceState().setNextDialogueId(1, 51);
			break;
			
			
		/* TZHAAR-MEJ-KAH */	
		case 212:
			player.getActionSender().sendDialogue("TzHaar-Mej-Kah",
					DialogueType.NPC, 2618, FacialAnimation.DEFAULT,
					"Wait for my signal before fighting.");
			break;
			
			
		case 213:
			player.getActionSender().sendDialogue("TzHaar-Mej-Kah",
					DialogueType.NPC, 2618, FacialAnimation.DEFAULT, "FIGHT!");
			break;
			
		/* STAIR UP/DOWN */
		case 214:
			player.getActionSender().sendDialogue("Select an Option",
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Upstairs", "Downstairs");
			player.getInterfaceState().setNextDialogueId(0, 215);
			player.getInterfaceState().setNextDialogueId(1, 216);
			break;
			
			
		/* LADDER UP/DOWN */	
		case 215:
			Ladders.perform(player, "up", "ladder", 2);
			player.getActionSender().removeChatboxInterface();
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 216:
			Ladders.perform(player, "down", "ladder", 0);
			player.getActionSender().removeChatboxInterface();
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			/*
			 * Teleport to moderator room
			 */
			
		case 217:
			player.setTeleportTarget(Location.create(2845, 5221, 0));
			player.getActionSender().removeChatboxInterface();
			player.getInterfaceState().setNextDialogueId(0, 97);
			
			break;
			

			/* Cooking Mastery */	
			case 218:
				player
						.getActionSender()
						.sendDialogue(
								player.getName(),
								DialogueType.MESSAGE_MODEL_LEFT,
								9801,
								FacialAnimation.DEFAULT,
								"Congratulations! You are now a master of Cooking." +
								" Why not visit the head Chef in the Cooking Guild?" +
								" He has something special that is only available" +
								" to the true masters of the Cooking skill!");
				break;
			/* Firemaking Mastery */	
			case 219:
				player
						.getActionSender()
						.sendDialogue(
								player.getName(),
								DialogueType.MESSAGE_MODEL_LEFT,
								9804,
								FacialAnimation.DEFAULT,
								"Congratulations! You are now a master of Firemak." +
								" Why not visit Iganatius at Sorcerer's Tower?" +
								" He has something special that is only available" +
								" to the true masters of the Firemak. skill!");
				break;
			/* Woodcutting Mastery */	
			case 220:
				player
						.getActionSender()
						.sendDialogue(
								player.getName(),
								DialogueType.MESSAGE_MODEL_LEFT,
								9807,
								FacialAnimation.DEFAULT,
								"Congratulations! You are now a master of Woodcutting." +
								" Why not visit Wilfred, near Lumbridge, who has" +
								" something special that is only available to the true masters " +
								" of the Woodcutting skill!");
				break;
			/* Farming Mastery */	
			case 221:
				player
						.getActionSender()
						.sendDialogue(
								player.getName(),
								DialogueType.MESSAGE_MODEL_LEFT,
								9810,
								FacialAnimation.DEFAULT,
								"Congratulations! You are now a master of Farming." +
								" Why not visit Martin the Gardener in Draynor?" +
								" He has something special that is only available" +
								" to the true masters of the Farming skill!");
				break;
				
			/**
			*
			* Start of lots of Warrior Guild diag.
			*
			**/
		case 572:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Why hello there! I'm Kafreena. Like the look of my",
					"pets? I think they're eyeing you up.");
			player.getInterfaceState().setNextDialogueId(0, 573);
			break;
			
			
		case 573:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.LAUGH_4,
				"That was a really bad pun.");
			player.getInterfaceState().setNextDialogueId(0, 574);
			break;
			
			
		case 574:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.SAD,
					"Sorry... I don't get to see the rest of the guild much,",
					"stuck up here. The Cyclopes don't talk much you see.");
			player.getInterfaceState().setNextDialogueId(0, 575);
			break;
			
			
		case 575:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Shouldn't that be cyclopses?");
			player.getInterfaceState().setNextDialogueId(0, 576);
			break;
			
			
		case 576:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.LAUGH_1,
					"Nope! Cyclopes is the plural of cyclops. One cyclops,",
					"many cyclopes.");
			player.getInterfaceState().setNextDialogueId(0, 577);
			break;
			
			
		case 577:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
				"Oh, right, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 578);
			break;
			
			
		case 578:
			player.getActionSender().sendDialogue(
					"Select an Option", DialogueType.OPTION,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Where are they from?",
					"How did they get here?",
					"Why are they here?",
					"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 581);
			player.getInterfaceState().setNextDialogueId(1, 583);
			player.getInterfaceState().setNextDialogueId(2, 585);
			player.getInterfaceState().setNextDialogueId(3, 579);
			break;
			
			
		case 579:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
				"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 580);
			break;
			
			
		case 580:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"See you back here soon I hope!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 581:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Where are they from?");
			player.getInterfaceState().setNextDialogueId(0, 582);
			break;
			
			
		case 582:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"They're from the far east lands.");
			player.getInterfaceState().setNextDialogueId(0, 578);
			break;
			
			
		case 583:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"How did they get here?");
			player.getInterfaceState().setNextDialogueId(0, 584);
			break;
			
			
		case 584:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Ahh.. our guildmaster, Harrallak, went on an expedition",
					"there. He brought them back with him.");
			player.getInterfaceState().setNextDialogueId(0, 578);
			break;
			
			
		case 585:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Why are they here?");
			player.getInterfaceState().setNextDialogueId(0, 586);
			break;
			
			
		case 586:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"For the warriors to train on of course! They also drop",
					"a rather nice blade.");
			player.getInterfaceState().setNextDialogueId(0, 587);
			break;
			
			
		case 587:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Oh? What would that be?");
			player.getInterfaceState().setNextDialogueId(0, 588);
			break;
			
			
		case 588:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"Defenders.");
			player.getInterfaceState().setNextDialogueId(0, 589);
			break;
			
			
		case 589:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Err what are they?");
			player.getInterfaceState().setNextDialogueId(0, 590);
			break;
			
			
		case 590:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"It's a blade you can defend with using your shield",
					"hand, like I have.");
			player.getInterfaceState().setNextDialogueId(0, 591);
			break;
			
			
		case 591:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.ON_ONE_HAND,
				"Wow!");
			player.getInterfaceState().setNextDialogueId(0, 592);
			break;
			
			
		case 592:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"For every 10 tokens you collect around the guild, you",
					"can spend one minute in with my pets. As you get",
					"defenders you can show them to me to earn even",
					"better ones... but remember if you lose them you'll have");
			player.getInterfaceState().setNextDialogueId(0, 593);
			break;
			
			
		case 593:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"to start at bronze again. I'd advise keeping a spare in",
					"your bank.");
			player.getInterfaceState().setNextDialogueId(0, 594);
			break;
			
			
		case 594:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"Okay!");
			player.getInterfaceState().setNextDialogueId(0, 595);
			break;
			
			
		case 595:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Oh, by the way, you'll need to earn 100 tokens before",
					"I'll let you in!");
			player.getInterfaceState().setNextDialogueId(0, 596);
			break;
			
			
		case 596:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"Right, I'd better go play some games then!");
			player.getInterfaceState().setNextDialogueId(0, 578);
			break;
			
			
		case 597:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Greetings warrior, how may I help you?");
			player.getInterfaceState().setNextDialogueId(0, 598);
			break;
			
			
		case 598:
			player.getActionSender().sendDialogue(
					"What would you like to say?", DialogueType.OPTION,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I'd like to access my bank account, please.",
					"I'd like to check my PIN settings.",
					"How long have you worked here?",
					"In no way, sorry to bother.");
			player.getInterfaceState().setNextDialogueId(0, 2);
			player.getInterfaceState().setNextDialogueId(1, 4);
			player.getInterfaceState().setNextDialogueId(2, 600);
			player.getInterfaceState().setNextDialogueId(3, 599);
			break;
			
			
		case 599:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"In no way, sorry to bother.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 600:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"How long have you worked here?");
			player.getInterfaceState().setNextDialogueId(0, 601);
			break;
			
			
		case 601:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Oh ever since the Guild opened. I like it here.");
			player.getInterfaceState().setNextDialogueId(0, 602);
			break;
			
			
		case 602:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Why's that?");
			player.getInterfaceState().setNextDialogueId(0, 603);
			break;
			
			
		case 603:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Well.. with all these warriors around, there's not",
					"much chance of my bank being robbed, is there!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 604:
			player.getActionSender().sendDialogue(
					"Select an Option", DialogueType.OPTION,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"What do I do here?",
					"Where do my machines come from?",
					"May I claim my tokens please?",
					"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 607);
			player.getInterfaceState().setNextDialogueId(1, 618);
			player.getInterfaceState().setNextDialogueId(2, 624);
			player.getInterfaceState().setNextDialogueId(3, 605);
			break;
			
			
		case 605:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 606);
			break;
			
			
		case 606:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Health be with you travelling.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 607:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"What do I do here?");
			player.getInterfaceState().setNextDialogueId(0, 608);
			break;
			
			
		case 608:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"A spare suit of plate armour need you will. Full helm",
					"plate leggings and platebody yes? Placing it in the",
					"centre of the magical machines you will be doing. KA-",
					"POOF! The armour, it attacks most furiously as if");
			player.getInterfaceState().setNextDialogueId(0, 609);
			break;
			
			
		case 609:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"alive! Kill it you must, yes.");
			player.getInterfaceState().setNextDialogueId(0, 610);
			break;
			
			
		case 610:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"So I use a full set of plate armour on the centre plate",
				"of the machines and it will animate it? Then I have to",
				"kill my own armour... how bizarre!");
			player.getInterfaceState().setNextDialogueId(0, 611);
			break;
			
			
		case 611:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Yes. It is as you are saying. For this earn tokens you",
					"will. Also gain experience in combat you will. Trained",
					"long and hard here have I.");
			player.getInterfaceState().setNextDialogueId(0, 612);
			break;
			
			
		case 612:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
					"You're not from around here are you...?");
			player.getInterfaceState().setNextDialogueId(0, 613);
			break;
			
			
		case 613:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"It is as you say.");
			player.getInterfaceState().setNextDialogueId(0, 614);
			break;
			
			
		case 614:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"So I will lose my armour?");
			player.getInterfaceState().setNextDialogueId(0, 615);
			break;
			
			
		case 615:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Lose armour you will if damaged too much it becomes.",
					"Rare this is, but still possible. If kill you the armour",
					"does, also lose armour you will.");
			player.getInterfaceState().setNextDialogueId(0, 616);
			break;
			
			
		case 616:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"So, occasionally I might lose a bit because it's being",
				"bashed about and I'll obviously lose it if I die... that it?");
			player.getInterfaceState().setNextDialogueId(0, 617);
			break;
			
			
		case 617:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"It is as you say.");
			player.getInterfaceState().setNextDialogueId(0, 604);
			break;
			
			
		case 618:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
					"Where do the machines come from?");
			player.getInterfaceState().setNextDialogueId(0, 619);
			break;
			
			
		case 619:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Make them I did, with magics.");
			player.getInterfaceState().setNextDialogueId(0, 620);
			break;
			
			
		case 620:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Magic, in the Warrior's Guild?");
			player.getInterfaceState().setNextDialogueId(0, 621);
			break;
			
			
		case 621:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"A skilled warrior also am I. Harrallak mistakes does not",
					"make. Potential in my invention he sees and",
					"opportunity grasps.");
			player.getInterfaceState().setNextDialogueId(0, 622);
			break;
			
			
		case 622:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"I see, so you made the magical machines and Harrallak",
				"saw how they could be used in the guild to train",
				"warrior's combat... interesting. Harrallak certainly is an",
				"intelligent guy.");
			player.getInterfaceState().setNextDialogueId(0, 623);
			break;
			
			
		case 623:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"It is as you say.");
			player.getInterfaceState().setNextDialogueId(0, 604);
			break;
			
			
		case 624:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"May I claim my tokens please?");
			player.getInterfaceState().setNextDialogueId(0, 625);
			break;
			
			
		case 625:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.SAD,
					"No tokens earned have you. In training activites",
					"participate you must.");
			player.getInterfaceState().setNextDialogueId(0, 626);
			break;
			
			
		case 626:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
				"Ok, I'll see what I can find to do around here to",
				"earn some tokens.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 627:
			player.getActionSender().sendDialogue("", DialogueType.MESSAGE_MODEL_LEFT, 8851, FacialAnimation.DEFAULT,
					"You don't have enough Warrior Guild Tokens to enter the cyclopes enclosure yet, collect at least 100 then come back.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 628:
			String name = WarriorsGuild.create(player).getDefender().getDefinition().getName();
			String phrase = "a";
			String[] vowels = { "a", "e", "i", "o", "u" };
			for(String letter : vowels) {
				if(name.startsWith(letter)) {
					phrase = "an";
				}
			}
			player.getActionSender().sendDialogue(
					"", DialogueType.MESSAGE_MODEL_LEFT, WarriorsGuild.create(player).getDefender().getDefinition().getId(), FacialAnimation.DEFAULT,
					"You need to get " + phrase + " " + name);
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 629:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Ghommal welcome you to Warrior Guild!");
			player.getInterfaceState().setNextDialogueId(0, 630);
			break;
			
			
		case 630:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
					"Umm...thank you, I think.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 631:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
				"Welcome to my humble guild, " + player.getName() + ".");
			player.getInterfaceState().setNextDialogueId(0, 632);
			break;
			
			
		case 632:
			player.getActionSender().sendDialogue(
					"Select an Option", DialogueType.OPTION,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Quite a place you've got here.",
					"You any good with a sword?",
					"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 635);
			player.getInterfaceState().setNextDialogueId(1, 637);
			player.getInterfaceState().setNextDialogueId(2, 633);
			break;
			
			
		case 633:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 634);
			break;
			
			
		case 634:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Farewell, brave warrior, I do hope you enjoy my guild.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 635:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"Quite a place you've got here.");
			player.getInterfaceState().setNextDialogueId(0, 636);
			break;
			
			
		case 636:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
				"Indeed we do.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 637:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"You any good with a sword?");
			player.getInterfaceState().setNextDialogueId(0, 638);
			break;
			
			
		case 638:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ON_ONE_HAND,
				"Am I any good with a sword? Have you any clue who",
				"I am?");
			player.getInterfaceState().setNextDialogueId(0, 639);
			break;
			
			
		case 639:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"Not really, no.");
			player.getInterfaceState().setNextDialogueId(0, 640);
			break;
		case 640:
			
			
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Why I could best any person in a rapier duel!");
			player.getInterfaceState().setNextDialogueId(0, 641);
			break;
			
			
		case 641:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.EVIL,
				"Try me, then!");
			player.getInterfaceState().setNextDialogueId(0, 642);
			break;
			
			
		case 642:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
				"My dear man, I couldn't possibly duel you, I might",
				"hurt you and then would happen to my",
				"reputation! Besides, I have this wonderful guild to run.",
				"Why don't you take a look at the various activites we");
			player.getInterfaceState().setNextDialogueId(0, 643);
			break;
			
			
		case 643:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"have. You might even collect enough tokens to be",
					"allowed in to kill teh strange beasts from the far east!");
				player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
			case 655:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
				"Ahhh, hello there, " + player.getName() + ".");
			player.getInterfaceState().setNextDialogueId(0, 656);
			break;
			
			
		case 656:
			player.getActionSender().sendDialogue(
					"Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you tell me a bit about skillcapes?",
					"What can I do here?",
					"That's a big axe!",
					"May I claim my tokens, please?",
					"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 682);
			player.getInterfaceState().setNextDialogueId(1, 674);
			player.getInterfaceState().setNextDialogueId(2, 662);
			player.getInterfaceState().setNextDialogueId(3, 659);
			player.getInterfaceState().setNextDialogueId(4, 657);
			break;
			
			
		case 657:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"Bye!");
			player.getInterfaceState().setNextDialogueId(0, 658);
			break;
			
			
		case 658:
			npc.face(player.getLocation());
			npc.playAnimation(Animation.create(858));
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Be well, warrior " + player.getName() + ".");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 659:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"May I claim my tokens, please?");
			player.getInterfaceState().setNextDialogueId(0, 660);
			break;
			
			
		case 660:
			if(player.warriorTokens > 0) {
				player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Of course! Here you go, you've earned " + player.warriorTokens + " tokens!");
				player.getInterfaceState().setNextDialogueId(0, 661);
			} else {
				player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.SAD,
						"I'm afraid you have not earned any tokens yet. Try",
						"some of the activites around the guild to earn some.");
				player.getInterfaceState().setNextDialogueId(0, 97);
			}
			break;
			
			
		case 661:
			WarriorsGuild.create(player).rewardPlayer();
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
				"Thanks!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 662:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.BOWS_HEAD_WHILE_SAD,
				"That's a big axe!");
			player.getInterfaceState().setNextDialogueId(0, 663);
			break;
			
			
		case 663:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.LAUGH_2,
					"Yes, indeed, it is. Have to be mighty strong to wield it",
					"too.");
			player.getInterfaceState().setNextDialogueId(0, 664);
			break;
			
			
		case 664:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"But you don't look that strong!");
			player.getInterfaceState().setNextDialogueId(0, 665);
			break;
			
			
		case 665:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Maybe, maybe not, but I still had to beat a barbarian to",
					"get it. Mind you, usually they don't part with them.",
					"This was an unusual circumstance.");
			player.getInterfaceState().setNextDialogueId(0, 666);
			break;
			
			
		case 666:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Oh?");
			player.getInterfaceState().setNextDialogueId(0, 667);
			break;
			
			
		case 667:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.LAUGH_2,
					"I bet him he couldn't catch a squirrel while still holding",
					"his axe, but that I could...and that if I won I'd get his",
					"axe.");
			player.getInterfaceState().setNextDialogueId(0, 668);
			break;
			
			
		case 668:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"What happened?");
			player.getInterfaceState().setNextDialogueId(0, 669);
			break;
			
			
		case 669:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.LAUGH_2,
					"He went running up after thd squirrel...nearly caught it",
					"too, but it shot up a tree and he tried to climb it.",
					"Only he got a bit tangled up with his axe 'cause he",
					"couldn't hang on to it at the same time and... he fell out");
			player.getInterfaceState().setNextDialogueId(0, 670);
			break;
			
			
		case 670:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.LAUGH_2,
					"of the tree. Then it was my turn...");
			player.getInterfaceState().setNextDialogueId(0, 671);
			break;
			
			
		case 671:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"I simply went and chopped the tree down with his big",
					"old axe, still holding it. The squirrel was so petrified it",
					"simply jumped straight out of the tree onto me. I won",
					"the axe and let the poor creature go!");
			player.getInterfaceState().setNextDialogueId(0, 672);
			break;
			
			
		case 672:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"I sense a moral in there somewhere.");
			player.getInterfaceState().setNextDialogueId(0, 673);
			break;
			
			
		case 673:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Aye, indeed. Brawn isn't all you need to wield a big",
					"axe, brains are required too!");
			player.getInterfaceState().setNextDialogueId(0, 656);
			break;
			
			
		case 674:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"What can I do here?");
			player.getInterfaceState().setNextDialogueId(0, 675);
			break;
			
			
		case 675:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Ahh, the shot put is a great test of strength and can be",
					"quite rewarding. Mind you do it properly, though, you",
					"might want to dust your hands with some powdery",
					"substance first. I'll give better grip.");
			player.getInterfaceState().setNextDialogueId(0, 676);
			break;
			
			
		case 676:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"I'll remember that. What should I use?");
			player.getInterfaceState().setNextDialogueId(0, 677);
			break;
			
			
		case 677:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"I personally prefer ground ashes, it makes a nice fine",
					"powder and gives some really good grip.");
			player.getInterfaceState().setNextDialogueId(0, 678);
			break;
			
			
		case 678:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Okay, what else?");
			player.getInterfaceState().setNextDialogueId(0, 679);
			break;
			
			
		case 679:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Basically you'll need to go in there, make sure you've",
					"got nothing cluttering up your hands, pick up a shot",
					"and throw it. Depending upon your technique, you can",
					"get quite long throws.");
			player.getInterfaceState().setNextDialogueId(0, 680);
			break;
			
			
		case 680:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"What was your best?");
			player.getInterfaceState().setNextDialogueId(0, 681);
			break;
			
			
		case 681:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Ahh, that would be telling.");
			player.getInterfaceState().setNextDialogueId(0, 656);
			break;
			
			
		case 682:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
				"Can you tell me a bit about skillcapes?");
			player.getInterfaceState().setNextDialogueId(0, 683);
			break;
			
			
		case 683:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
				"Sure. You can buy a skillcape when you reach level 99",
				"in a skill. Apart from boosting your statistics in that skill,",
				"they can also be used to perform some pretty amazing",
				"emotes. Is there anything else you would like to know?");
			player.getInterfaceState().setNextDialogueId(0, 656);
			break;
		case 684:
			WarriorsGuild.create(player).throwShot(Animation.create(4151));
			break;
		case 685:
			WarriorsGuild.create(player).throwShot(Animation.create(4152));
			break;
		case 686:
			WarriorsGuild.create(player).throwShot(Animation.create(4153));
			break;

			
		/*
		 * SLAYER MASTER	
		 */
		case 900:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
				"Hello there.",
				"I am a master of Slayer, and as such, I'm",
				"able to give players a Slayer task.");
			player.getInterfaceState().setNextDialogueId(0, 901);
			break;
			
			
		case 901:
			player.getActionSender().sendDialogue("Select an Option", DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like a task.",
					"I'd like to reset my current task.",
					"Nevermind");
			player.getInterfaceState().setNextDialogueId(0, 902);
			player.getInterfaceState().setNextDialogueId(1, 904);
			player.getInterfaceState().setNextDialogueId(2, 97);
			break;
			
			
		case 902:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"I'd like a new task.");
			player.getInterfaceState().setNextDialogueId(0, 903);
			break;
			
			
		case 903:
			if(player.currentSlayerTask != -1){
				player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
				"You already have a task! You are to kill",
				" "+player.currentSlayerTaskAmount+" "+player.currentSlayerTaskName+". ");
			player.getInterfaceState().setNextDialogueId(0, 97);
			} 
			if(player.currentSlayerTask == -1){
			player.getSlayer().giveTask(player);
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I have given you a new task. You have to kill",
					" "+player.currentSlayerTaskAmount+" "+player.currentSlayerTaskName+". ");
			player.getInterfaceState().setNextDialogueId(0, 97);
			}
			break;
			
			
		case 904:
			player.getActionSender().sendDialogue(player.getName(), DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
				"I'd like to reset my current Slayer task.");
			player.getInterfaceState().setNextDialogueId(0, 905);
			break;
			
			
		case 905:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
				"I will reset your current task, but please remember",
				"that you get bonus experience for fully",
				"completing a task!");
			player.getInterfaceState().setNextDialogueId(0, 906);
			break;
			
			
		case 906:
			player.currentSlayerTaskAmount = -1;
			player.currentSlayerTaskName = "";
			player.currentSlayerTask = -1;
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Your task has been reset.");
				player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * MAN - PETE'S FIRST DIALOGUE :3
		 */
			
		case 907:
			Misc.random(4);
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Hello there.");
			player.getInterfaceState().setNextDialogueId(0, 908);
			break;
			
		case 908:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANNOYED,
					"Are you the one that keeps ringing the church bells?");
			player.getInterfaceState().setNextDialogueId(0, 909);
			break;
			
			
		case 909:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"No, I'm not.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * HANS	
		 */
			
		case 910:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"Hello. What are you doing here?");
			player.getInterfaceState().setNextDialogueId(0, 911);
			break;
			
			
		case 911:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I'm looking for whoever is in charge of this place.",
					"I have come to kill everyone in this castle!",
					"I don't know. I'm lost. Where am I?");
			player.getInterfaceState().setNextDialogueId(0, 912);
			player.getInterfaceState().setNextDialogueId(1, 915);
			player.getInterfaceState().setNextDialogueId(2, 916);
			break;
			
			
		case 912:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.ON_ONE_HAND,
					"I'm looking for whoever is in charge of this place.");
			player.getInterfaceState().setNextDialogueId(0, 913);
			break;
			
			
		case 913:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"Who, the Duke? He's in his study, on the first floor.");
			player.getInterfaceState().setNextDialogueId(0, 914);
			break;
			
			
		case 914:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I have come to kill everyone in this castle!",
					"I don't know. I'm lost. Where am I?");
			player.getInterfaceState().setNextDialogueId(0, 915);
			player.getInterfaceState().setNextDialogueId(1, 916);
			break;
			
			
		case 915:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.EVIL_LAUGH,
					"I have come to kill everyone in this castle!");
			npc.forceChat("Help! Help!");
			break;
			
			
		case 916:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"I don't know. I'm lost. Where am I?");
			player.getInterfaceState().setNextDialogueId(0, 917);
			break;
			
			
		case 917:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"You are at the Lumbridge Castle.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * LUMBRIDGE GUIDE
		 */
			
		case 918:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"Greetings, adventurer. How may I help you?");
			player.getInterfaceState().setNextDialogueId(0, 919);
			break;
			
			
		case 919:
			if(player.getRights() == Rights.ADMINISTRATOR || player.getRights() == Rights.MODERATOR){
				player.getActionSender().sendDialogue("Select an Option", 
						DialogueType.OPTION, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"Who are you?",
						"Tell me about the town of Lumbridge.",
						"I'm fine for now, thanks.",
						"Bring me to the Moderator Room");
				player.getInterfaceState().setNextDialogueId(0, 920);
				player.getInterfaceState().setNextDialogueId(1, 923);
				player.getInterfaceState().setNextDialogueId(2, 927);
				player.getInterfaceState().setNextDialogueId(3, 217);
			} else {
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Who are you?",
					"Tell me about the town of Lumbridge.",
					"I'm fine for now, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 920);
			player.getInterfaceState().setNextDialogueId(1, 923);
			player.getInterfaceState().setNextDialogueId(2, 927);
			}
			break;
			
		case 920:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Who are you?");
			player.getInterfaceState().setNextDialogueId(0, 921);
			break;
			
		case 921:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"I am Phileas, the Lumbridge Guide. In times past, people",
					"came from all around to ask me for advice. My renown",
					"seems to have diminished somewhat in recent years,",
					"though. Can I help you with anything?");
			player.getInterfaceState().setNextDialogueId(0, 922);
			break;
			
			
		case 922:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Tell me about the town of Lumbridge.",
					"I'm fine for now, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 923);
			player.getInterfaceState().setNextDialogueId(1, 927);
			break;
			
		case 923:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"Tell me about the town of Lumbridge.");
			player.getInterfaceState().setNextDialogueId(0, 924);
			break;
			
			
		case 924:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"Lumbridge is one of the older towns in the human-",
					"controlled kingdoms. It was founded over two hundred",
					"years ago twards the end of the Fourth age. It's called",
					"Lumbridge because of this bridge built over the River Lum");
			player.getInterfaceState().setNextDialogueId(0, 925);
			break;
			
			
		case 925:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"The town is governed by Duke Horacio, who is a good",
					"friend of our monarch, King Roald of Misthalin.");
			player.getInterfaceState().setNextDialogueId(0, 926);
			break;
			
			
		case 926:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Who are you?",
					"I'm fine for now, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 920);
			player.getInterfaceState().setNextDialogueId(1, 927);
			break;
			
			
		case 927:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"I'm fine for now, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 928);
			break;
			
			
		case 928:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"Good adventuring, Traveller.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * SHOPKEEPER
		 */
		case 929:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Can I help you at all?");
			player.getInterfaceState().setNextDialogueId(0, 930);
			break;
			
		case 930:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Yes please. What are you selling?",
					"How should I use your shop?",
					"No, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 931);
			player.getInterfaceState().setNextDialogueId(1, 932);
			player.getInterfaceState().setNextDialogueId(2, 937);
			break;
			
		case 931:
			Shop.open(player, 0, 1);
			player.getActionSender().removeChatboxInterface();
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 932:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"How should I use your shop?");
			player.getInterfaceState().setNextDialogueId(0, 933);
			break;
		
			/* CUSTOM DIALOGUE OPTION #2 */
		case 933:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I'm glad you ask! Our shop buys and sells items",
					"that adventurers bring in. right-click an item",
					"in the main window and select the quantity to buy.",
					"Click an item to see the price!");
			player.getInterfaceState().setNextDialogueId(0, 934);
			break;
			
			
		case 934:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"We have a limited stock, however if a player",
					"sells an item of that type, the shop will get",
					"a higher quantity of that item.");
			player.getInterfaceState().setNextDialogueId(0, 935);
			break;
			
			
		case 935:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Anything else?");
			player.getInterfaceState().setNextDialogueId(0, 936);
			break;
			
			
		case 936:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Yes please. What are you selling?",
					"No, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 931);
			player.getInterfaceState().setNextDialogueId(1, 937);
			break;
			
		case 937:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DEFAULT,
					"No, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;

			/*
			 * Combat tutor
			 */
			
		case 938:
			if(player.getSkills().getLevel(Skills.DEFENCE) < 99) {
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you tell me about different weapon types I can use?",
					"Please tell me about skillcapes.", "Bye.");
			player.getInterfaceState().setNextDialogueId(0, 939);
			player.getInterfaceState().setNextDialogueId(1, 949);
			player.getInterfaceState().setNextDialogueId(2, 97);
			} else {
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"Ah, but I can see you already a master in the fine art",
						"of Defence. Perhaps you have come to me to purchase",
						"a Skillcape of Defence, and thus join the elite few who",
						"have mastered this exacting skill?"
						);
				player.getInterfaceState().setNextDialogueId(0, 951);
				
			}
			break;
			
		case 939:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.CALM_1,
					"Can you tell me about different weapon types I can use?");
			player.getInterfaceState().setNextDialogueId(0, 940);
			break;
			
		case 940:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Well let me see now... There are stabbing type weapons",
					"such as daggers, then you have swords which are",
					"slashing, maces that have great crushing abilities, battle",
					"axes which are powerful and spears which can be good");
			player.getInterfaceState().setNextDialogueId(0, 941);
			break;
		case 941:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"for Defence and many forms of Attack.");
			player.getInterfaceState().setNextDialogueId(0, 942);
			break;
		case 942:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"It depends a lot on how you want to fight. Experiment",
					"and find out what is best for you. Never be scared to",
					"try out a new weapon; you never know, you might like",
					"it! Why, I tried all of them for a while and settled on");
			player.getInterfaceState().setNextDialogueId(0, 943);
			break;
		case 943:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"this rather good sword!");
			player.getInterfaceState().setNextDialogueId(0, 944);
			break;
		case 944:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like training sword and shield.",
					"Bye.");
			player.getInterfaceState().setNextDialogueId(0, 945);
			player.getInterfaceState().setNextDialogueId(1, 97);
			break;
			
		case 945:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"I'd like a training sword and shield.");
			player.getInterfaceState().setNextDialogueId(0, 946);
			break;
		case 946:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I think you're a bit big for those... but ok.");
			player.getInterfaceState().setNextDialogueId(0, 947);
			break;
		case 947:
		if(HasItemCheck.hasTrainingSword(player) == false){
			player.getActionSender().sendDialogue(player.getName(), DialogueType.MESSAGE_MODEL_LEFT, 9703, FacialAnimation.DEFAULT,
					"Harlan gives you a training sword.",
					"Click here to continue");
					player.getInventory().add(player.checkForSkillcape(new Item(9703, 1)));
			} else {
			player.getActionSender().sendDialogue(player.getName(), DialogueType.MESSAGE_MODEL_LEFT, 9703, FacialAnimation.DEFAULT,
					"You already have a training sword.",
					"Click here to continue");
			}
			player.getInterfaceState().setNextDialogueId(0, 948);
			break;
		case 948:
		if(HasItemCheck.hasTrainingShield(player) == false){
			player.getActionSender().sendDialogue(player.getName(), DialogueType.MESSAGE_MODEL_LEFT, 9704, FacialAnimation.DEFAULT,
					"Harlan gives you a training shield.",
					"Click here to continue");
					player.getInventory().add(new Item(9704, 1));
			} else {
			player.getActionSender().sendDialogue(player.getName(), DialogueType.MESSAGE_MODEL_LEFT, 9704, FacialAnimation.DEFAULT,
					"You already have a training shield.",
					"Click here to continue");
			}
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 949:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"Please tell me about skillcapes.");
			player.getInterfaceState().setNextDialogueId(0, 950);
			break;
		case 950:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Of course. Skillcapes are a symbol of achievement. Only",
					"people who have mastered a skill and reached level 99 can",
					"get their hands on them and gain the benefits they carry.");
			player.getInterfaceState().setNextDialogueId(0, -1);
			break;
		case 951:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Yes, please sell me a Skillcape of Defence",
					"Can you tell me about different weapon types I can use?");
			player.getInterfaceState().setNextDialogueId(0, 952);
			player.getInterfaceState().setNextDialogueId(1, 939);
			break;
		case 952:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"May I buy a Skillcape of Defence, please?");
			player.getInterfaceState().setNextDialogueId(0, 953);
			break;
		case 953:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"You wish to join the elite defenders of this world? I'm",
					"afraid such things do not come cheaply; in fact they",
					"cost 99000 coins to be precise!");
			player.getInterfaceState().setNextDialogueId(0, 954);
			break;
		case 954:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"99000 coins? That's much too expensive.",
					"I think I have the money right here, actually.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			player.getInterfaceState().setNextDialogueId(1, 955);
			break;
		case 955:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"I think I have the money right here, actually.");
			player.getInterfaceState().setNextDialogueId(0, 956);
			break;
		case 956:
			if(player.getInventory().freeSlots() < 2) {
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"I'm afraid you don't have enough inventory space.");
				player.getInterfaceState().setNextDialogueId(0, 97);
			return;
			}
			if(player.getInventory().getCount(995) >= 99000){
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"Excellent! Wear that cape with pride my friend.");
				
				player.getInventory().remove(new Item(995, 99000));
				player.getInventory().add(player.checkForSkillcape(new Item(9753, 1)));
				player.getInventory().add(player.checkForSkillcape(new Item(9755, 1)));
			} else {
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"I'm afraid you don't have enough money.");				
			}
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;

			/*
			 * Magic tutor
			 */
			
		case 957:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you teach me the basics please?",
					"Teach me about making runes.",
					"Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 958);
			player.getInterfaceState().setNextDialogueId(1, 965);
			player.getInterfaceState().setNextDialogueId(2, 97);
			break;
		case 958:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"Can you teach me the basics please?");
			player.getInterfaceState().setNextDialogueId(0, 959);
			break;
			
		case 959:
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"You can cast different spells according to what runes",
						"you have in your inventory. To start off with you'll",
						"need mind runes and air runes. These will allow you to",
						"cast Wind Strike like you did in the tutorial.");
				player.getInterfaceState().setNextDialogueId(0, 960);
			break;
		case 960:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Use the spell book icon in the top right of the control",
					"panel to see what spells you can cast. If you have the",
					"correct runes, the spell will light up.");
			player.getInterfaceState().setNextDialogueId(0, 961);
		break;
		case 961:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Nemari, the Ranged Combat tutor and I both give out",
					"items every 30 minutes, however you must choose",
					"wether you want runes or ranged equipment. To",
					"claim runes, right-click on me and choose Claim, to claim.");
			player.getInterfaceState().setNextDialogueId(0, 962);
		break;
		case 962:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"ranged equipment right-click on the Ranged Combat",
					"tutor and select Claim.");
			player.getInterfaceState().setNextDialogueId(0, 963);
		break;
		case 963:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"When you have the spell available, click on it once, then",
					"click on your target. A good target would be a monster",
					"that is below your combat level.");
			player.getInterfaceState().setNextDialogueId(0, 964);
		break;
		case 964:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Try rats in the castle or if you're felling brave, the",
					"goblins to the west of here have been causing a",
					"nuisance of themselves.");
			player.getInterfaceState().setNextDialogueId(0, 957);
		break;
		
		case 965:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.CALM_2,
					"Teach me about making runes.");
			player.getInterfaceState().setNextDialogueId(0, 966);
			break;
			
		case 966:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Runes which are used for casting spells can be",
					"made with the Runecrafting skill. To make a rune,",
					"you will need some rune essence, and a talisman.",
					"You can get talismans by killing certain monsters");
			player.getInterfaceState().setNextDialogueId(0, 967);
			break;
		
		case 967:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"And you can get rune essence inside a rune essence",
					"Mine.");
			player.getInterfaceState().setNextDialogueId(0, 968);
			break;
			
		case 968:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I've heard that duke Horaccio has one of",
					"those talismans. Perhaps you should talk to him.");
			player.getInterfaceState().setNextDialogueId(0, 957);
			break;
			

			/*Woodcutting Tutor*/
		case 969:
			if(player.getSkills().getLevel(Skills.WOODCUTTING) < 99) {
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you teach me the basics of Woodcutting and Firemaking, please?",
					"What is that cape you're wearing?.");
			player.getInterfaceState().setNextDialogueId(0, 970);
			player.getInterfaceState().setNextDialogueId(1, 980);
			} else {
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"Wow! It's not that often I meet somebody as ",
						"accomplished as you in Woodcutting! Perhaps you",
						" should become the tutor instead of me! See it as ",
						"you're skilled, maybe you are interested in buying"
						);
				player.getInterfaceState().setNextDialogueId(0, 982);
				
			}
			break;
			
		case 970:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.HAPPY,
					"Can you teach me the basics of Woodcutting and", "Firemaking, please?");
			player.getInterfaceState().setNextDialogueId(0, 971);
			break;
			
		case 971:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Of course... look for the tree icon on your mini map to",
					"find areas to find specific trees.");
			if(HasItemCheck.hasBronzeAxe(player) == false){
			player.getInterfaceState().setNextDialogueId(0, 972);
			} else {
			player.getInterfaceState().setNextDialogueId(0, 973);
			}
		break;
		
		case 972:
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"As you're already here, have an axe so that you can", "chop the trees around me.");
				player.getInventory().add(player.checkForSkillcape(new Item(1351, 1)));
				player.getInterfaceState().setNextDialogueId(0, 973);
		break;
		
		case 973:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"When you see a likely looking tree, appreciate its",
					"beauty, then simply left-click on it to chop it down.");
			player.getInterfaceState().setNextDialogueId(0, 974);
		break;
		
		case 974:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Sometimes the trees object to being cut down and",
					"damage your axe. Just be aware and pay attention",
					"when cutting trees! Bob south of here can fix your",
					"axes for a price");
			player.getInterfaceState().setNextDialogueId(0, 975);
		break;
		
		case 975:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"When you have a full inventory of logs, you have a", 
					"choice. You can take it to the bank, there's one on the",
					"roof of the castle in Lumbridge, or you can burn them");
			player.getInterfaceState().setNextDialogueId(0, 976);
		break;
		
		case 976:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.MESSAGE_MODEL_LEFT,
					5080, FacialAnimation.DEFAULT,
					"To find a bank, look for a dollar-signed symbol on"+
					"your minimap after climbing the stairs of the"+
					"Lumbridge Castle to the top. There are banks all over"+
					"RuneScape with this symbol.");
			if(HasItemCheck.hasTinderbox(player) == false){
				player.getInterfaceState().setNextDialogueId(0, 977);
			} else {
				player.getInterfaceState().setNextDialogueId(0, 978);
			}
		break;
		
		case 977:
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"Ah, you've lost your tinderbox have you?", "Have another!");
				player.getInventory().add(player.checkForSkillcape(new Item(590, 1)));
				player.getInterfaceState().setNextDialogueId(0, 978);
		break;
		
		case 978:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.MESSAGE_MODEL_LEFT,
					5080, FacialAnimation.DEFAULT,
					"To find a bank, look for a dollar-signed symbol on your"+
					"minimap after climbing the stairs of the Lumbridge"+
					"Castle to the top. There are banks all over RuneScape"+
					"with this symbol.");
			player.getInterfaceState().setNextDialogueId(0, 979);
		break;
		
		case 979:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Click on your tinderbox, then click on one of the logs in", 
					"your inventory, this will attempt to light a fire that you",
					"can use for cooking.");
			player.getInterfaceState().setNextDialogueId(0, 97);
		break;
		
		case 980:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"What is that cape you're wearing?");
			player.getInterfaceState().setNextDialogueId(0, 981);
			break;
			
		case 981:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"It's the woodcutting skillcape. It's only available to",
					"people who have mastered their woodcutting skill.");
			player.getInterfaceState().setNextDialogueId(0, -1);
			break;
			
		case 982:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"a Skillcape of Woodcutting?");
			player.getInterfaceState().setNextDialogueId(0, 983);
			break;
			
		case 983:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"No, thanks.",
					"Yes, please.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			player.getInterfaceState().setNextDialogueId(1, 984);
			break;
			
			

		case 984:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"Yes, please.");
			player.getInterfaceState().setNextDialogueId(0, 985);
			break;
		case 985:
			player.getActionSender().sendDialogue(
					npc.getDefinition().getName(), DialogueType.NPC,
					npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Anybody who has spent as much time cutting trees as",
					"you deserves the right to own one. That'll be 99000",
					"coins, please.");
			player.getInterfaceState().setNextDialogueId(0, 986);
			break;
			
		case 986:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"99000! That's too rich for me.",
					"No problem");
			player.getInterfaceState().setNextDialogueId(0, 97);
			player.getInterfaceState().setNextDialogueId(1, 987);
			break;
		case 987:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, 0, FacialAnimation.DEFAULT,
					"No problem.");
			player.getInterfaceState().setNextDialogueId(0, 988);
			break;
			
		case 988:
			if(player.getInventory().freeSlots() < 2) {
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"I'm afraid you don't have enough inventory space.");
				player.getInterfaceState().setNextDialogueId(0, 97);
			return;
			}
			if(player.getInventory().getCount(995) >= 99000){
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"Excellent! Wear that cape with pride my friend.");
				
				player.getInventory().remove(new Item(995, 99000));
				player.getInventory().add(player.checkForSkillcape(new Item(9807, 1)));
				player.getInventory().add(player.checkForSkillcape(new Item(9809, 1)));
			} else {
				player.getActionSender().sendDialogue(
						npc.getDefinition().getName(), DialogueType.NPC,
						npc.getDefinition().getId(), FacialAnimation.DEFAULT,
						"I'm afraid you don't have enough money.");				
			}
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			/*
			 * BOB AXE SELLER
			 */
			
		case 989:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'd like to trade.",
					"Can you repair my items for me?");
			player.getInterfaceState().setNextDialogueId(0, 990);
			player.getInterfaceState().setNextDialogueId(1, 992);
			break;
			
		
		case 990:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DISINTERESTED,
					"I'd like to trade.");
			player.getInterfaceState().setNextDialogueId(0, 991);
			break;
			
			
		case 991:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Great! I buy and sell pickaxes and hatchets.",
					"There are plenty to choose from.",
					"Take your pick... or hatchet.");
			player.getInterfaceState().setNextDialogueId(0, 1033);
			break;
			
			
		case 992:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.ALMOST_CRYING,
					"Can you repair my items for me?");
			player.getInterfaceState().setNextDialogueId(0, 993);
			break;
			
		case 993:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Of course I can, though the materials may cost you.",
					"Just hand me the item and I'll have a look.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			// TODO: Add item fixing to Bob
			break;
			
	    /*
	     * Father Aereck
	     */
			
		case 994:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Welcome back to the church of Saradomin, my",
					"friend. What can I do for you today?");
			player.getInterfaceState().setNextDialogueId(0, 995);
			break;
			
		case 995:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Who's Saradomin?",
					"Nice place you've got here."); //done first because it's shorter
			player.getInterfaceState().setNextDialogueId(0, 999);
			player.getInterfaceState().setNextDialogueId(1, 996);
			break;
			
		case 996:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.ON_ONE_HAND,
					"Nice place you've got here.");
			player.getInterfaceState().setNextDialogueId(0, 997);
			break;
			
		case 997:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"It is, isn't it? It was built over two centuries ago!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 998:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DRUNK_TO_RIGHT,
					"Who's Saradomin?");
			player.getInterfaceState().setNextDialogueId(0, 999);
			break;
			
		case 999:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANNOYED,
					"Surely you have heard of our god, Saradomin?");
			player.getInterfaceState().setNextDialogueId(0, 1000);
			break;
			
			
		case 1000:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANNOYED,
					"He who created the forces of goodness and purity in this",
					"world? I cannot believe your ignorance!");
			player.getInterfaceState().setNextDialogueId(0, 1001);
			break;
			
		case 1001:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANNOYED,
					"This is the god with more followers than any other...",
					"at least in this part of the world.");
			player.getInterfaceState().setNextDialogueId(0, 1002);
			break;
			
		case 1002:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANNOYED,
					"He who forged the world as we know it, along with his",
					"brothers Guthix and Zamorak?");
			player.getInterfaceState().setNextDialogueId(0, 1003);
			break;
			
		case 1003:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Oh. THAT Saradomin.",
					"Sorry. I'm not from this world.");
			player.getInterfaceState().setNextDialogueId(0, 1004);
			player.getInterfaceState().setNextDialogueId(1, 1007);
			break;
			
		case 1004:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Oh. THAT Saradomin");
			player.getInterfaceState().setNextDialogueId(0, 1005);
			break;
			
		case 1005:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"There is only one Saradomin.");
			player.getInterfaceState().setNextDialogueId(0, 1006);
			break;
			
		case 1006:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Yeah I, uh, thought you said something else.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		case 1007:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Sorry. I'm not from this world.");
			player.getInterfaceState().setNextDialogueId(0, 1008);
			break;
			
		case 1008:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.SAD,
					"...!");
			player.getInterfaceState().setNextDialogueId(0, 1009);
			break;
			
		case 1009:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"That's... strange...",
					"I thought things not from this world were all, you know,",
					"slime and tentacles.");
			player.getInterfaceState().setNextDialogueId(0, 1010);
			break;
			
		case 1010:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Not me.",
					"I am! Do you like my disguise?");
			player.getInterfaceState().setNextDialogueId(0, 1011);
			player.getInterfaceState().setNextDialogueId(1, 1014);
			break;
			
		case 1011:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Not me.");
			player.getInterfaceState().setNextDialogueId(0, 1012);
			break;
			
		case 1012:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Well, I can see that. Still, there's something special",
					"about you");
			player.getInterfaceState().setNextDialogueId(0, 1013);
			break;
			
		case 1013:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Thanks, I think.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1014:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"I am! Do you like my disguise?");
			player.getInterfaceState().setNextDialogueId(0, 1015);
			break;
			
		case 1015:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANGER_1,
					"Argh! Avaunt, foul creature from another dimension!",
					"Avaunt! Begone in the name of Saradomin!");
			player.getInterfaceState().setNextDialogueId(0, 1016);
			break;
			
		case 1016:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Okay, okay. I was only joking.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * The Lumbridge Cook
		 */
			
		case 1017:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Hello, friend. How's the adventuring going?");
			player.getInterfaceState().setNextDialogueId(0, 1018);
			break;
			
		case 1018:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'm getting strong and mighty.",
					"I keep on dying.",
					"Can I use your range?");
			player.getInterfaceState().setNextDialogueId(0, 1019);
			player.getInterfaceState().setNextDialogueId(1, 1021);
			player.getInterfaceState().setNextDialogueId(2, 1023);
			break;
			
		case 1019:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"I'm getting strong and mighty.");
			player.getInterfaceState().setNextDialogueId(0, 1020);
			break;
			
		case 1020:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Glad to hear it!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1021:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.SAD,
					"I keep on dying.");
			player.getInterfaceState().setNextDialogueId(0, 1022);
			break;
			
		case 1022:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Ah, well. At least you keep coming back to life too!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1023:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Can I use your range?");
			player.getInterfaceState().setNextDialogueId(0, 1024);
			break;
			
		case 1024:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Go ahead! It's a very good range; it's better than most",
					"other ranges!",
					"It's called the Cook-O-Matic 25 and it uses a combination",
					"of state-of-the-art temperature regulation and magic.");
			player.getInterfaceState().setNextDialogueId(0, 1025);
			break;
			
		case 1025:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Does this mean my food will burn less often?");
			player.getInterfaceState().setNextDialogueId(0, 1026);
			break;
			
		case 1026:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ALMOST_CRYING,
					"No. This is the cheap version..."); //custom one
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
			
		/*
		 * DUKE HORACCIO	
		 */
			
		case 1027:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Greetings. Welcome to my castle.");
			player.getInterfaceState().setNextDialogueId(0, 1028);
			break;
			
		case 1028:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Have you any quests for me?",
					"Where can I find money?");
			player.getInterfaceState().setNextDialogueId(0, 1029);
			player.getInterfaceState().setNextDialogueId(1, 1031);
			break;
			
		case 1029:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Have you any quests for me?");
			player.getInterfaceState().setNextDialogueId(0, 1030);
			break;
			
		case 1030:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Not at this moment, sorry."); //TODO: ADD QUEST AT THIS DIAG.
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1031:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Where can I find money?");
			player.getInterfaceState().setNextDialogueId(0, 1032);
			break;
			
		case 1032:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"I hear many of the local people earn money by learning a",
					"skill. Many people get by in life by becoming accomplished",
					"smiths, cooks, miners and woodcutters.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
		
		case 1033: //Bob - shop
			Shop.open(player,23, 1);
			player.getActionSender().removeChatboxInterface();
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
		
		/*
		 * Father Urhney
		 */
		case 1034: /* CUSTOM */
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Hello.");
			player.getInterfaceState().setNextDialogueId(0, 1035);
			break;
			
		case 1035:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANGER_2,
					"What are you doing in my holy sanctuary?!");
			player.getInterfaceState().setNextDialogueId(0, 1036);
			break;
			
		case 1036:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'm here to burn down your house!",
					"Nothing. I'm sorry to disturb you.");
			player.getInterfaceState().setNextDialogueId(0, 1037);
			player.getInterfaceState().setNextDialogueId(1, 1039);
			break;
			
		case 1037:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.EVIL,
					"I'm here to burn down your house!");
			player.getInterfaceState().setNextDialogueId(0, 1038);
			break;
			
		case 1038:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANGER_2,
					"No, you aren't. Leave my house at once!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1039:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"Nothing. I'm sorry to disturb you.");
			player.getInterfaceState().setNextDialogueId(0, 1040);
			break;
			
		case 1040:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANGER_2,
					"Get out.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1041:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"Good-day.");
			player.getInterfaceState().setNextDialogueId(0, 1042);
			break;
			
		case 1042:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANNOYED,
					"I'm sorry. I have no time to chat.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * HAM MEMBER - 1717
		 */
			
		case 1043:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"What are you people doing here?",
					"Who are you and what do you do here?",
					"What do you think you're going to achieve?",
					"Where do all you people come from?",
					"OK, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 1044);
			player.getInterfaceState().setNextDialogueId(1, 1046);
			player.getInterfaceState().setNextDialogueId(2, 1048);
			player.getInterfaceState().setNextDialogueId(3, 1052);
			player.getInterfaceState().setNextDialogueId(4, 1055);
			break;
			
		case 1044:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"What are you people doing here?");
			player.getInterfaceState().setNextDialogueId(0, 1045);
			break;
			
		case 1045:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Many of us disagree with the king about what freedoms",
					"the local monster population should have. We're taking a",
					"stand and mobilising our forces against the monstrous",
					"hordes.");
			player.getInterfaceState().setNextDialogueId(0, 1043);
			break;
			
		case 1046:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"Who are you and what do you do here?");
			player.getInterfaceState().setNextDialogueId(0, 1047);
			break;
			
		case 1047:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I'm a strong believer in the non-monsters policy...we",
					"should really get rid of them...and if that means I have to",
					"live in a cave like a monster, so be it!");
			player.getInterfaceState().setNextDialogueId(0, 1043);
			break;
			
		case 1048:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"What do you think you're going to achieve?");
			player.getInterfaceState().setNextDialogueId(0, 1049);
			break;
			
		case 1049:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"We want a world without monsters, to live in safety and",
					"withour fear of being attacked by these ferocious beasts.");
			player.getInterfaceState().setNextDialogueId(0, 1050);
			break;
			
		case 1050:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"But there aren't that many ferocious beasts in the towns",
					"and cities.");
			player.getInterfaceState().setNextDialogueId(0, 1051);
			break;
			
		case 1051:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"That's not enough, we want to get rid of them totally, we",
					"want to enjoy the surrounding lands and not worry about",
					"our children playing in caves and so on.");
			player.getInterfaceState().setNextDialogueId(0, 1043);
			break;
			
		case 1052:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"Where do all you people come from?");
			player.getInterfaceState().setNextDialogueId(0, 1053);
			break;
			
		case 1053:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Most of us came from small towns that had been attacked",
					"by monsters. We all got fed up with it and so decided to",
					"join this movement. We're hoping to return to the towns",
					"and cities when we've cleaned up the areas that these");
			player.getInterfaceState().setNextDialogueId(0, 1054);
			break;	
			
		case 1054:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"monsters live in. ");
			player.getInterfaceState().setNextDialogueId(0, 1043);
			break;	
			
		case 1055:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_1,
					"Ok, Thanks.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * Jimmy the Chisel
		 */
			
		case 1056:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"Hello mate!");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;	
			
			/*
			 * Johanus Ulsbrecht
			 */
			
		case 1057:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"What kind of organisation is this?",
					"What are you and what do you do here?",
					"Okay, thanks");
			player.getInterfaceState().setNextDialogueId(0, 1058);
			player.getInterfaceState().setNextDialogueId(1, 1063);
			player.getInterfaceState().setNextDialogueId(2, 1065);
			break;
			
		case 1058:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"What kind of organisation is this?");
			player.getInterfaceState().setNextDialogueId(0, 1059);
			break;
			
		case 1059:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"We're a proactive organisation working towards the",
					"cessation of monsters in normal civilised human society.",
					"There seems to be no backbone in this land so we're",
					"stepping up to the challenge.");
			player.getInterfaceState().setNextDialogueId(0, 1060);
			break;	
			
		case 1060:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Hmm, how do you propose to do that? I mean, I see people",
					"every day killing goblins around here!");
			player.getInterfaceState().setNextDialogueId(0, 1061);
			break;
			
		case 1061:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"We're mobilising people and we're starting our own",
					"society...cleaning out the caves once inhabited by these",
					"foul creatures and defending them so that they can never",
					"again shelter sub-human species.");
			player.getInterfaceState().setNextDialogueId(0, 1062);
			break;	
			
		case 1062:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"That sounds kind of strange, but, hey, it's your choice.");
			player.getInterfaceState().setNextDialogueId(0, 1057);
			break;
			
		case 1063:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Who are you and what do you do here?");
			player.getInterfaceState().setNextDialogueId(0, 1064);
			break;
			
		case 1064:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"My name is Johanhus and I lead these glorious people on a",
					"courageous mission called 'Humans Against Monsters'. We",
					"mean to make this land free of monsters, so that we can",
					"all live in peace.");
			player.getInterfaceState().setNextDialogueId(0, 1057);
			break;	
			
		case 1065:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Okay, thanks.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1066:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANGER_1,
					"What are you doing on my land? You're not the one",
					"who keeps leaving all my gates open and letting out all",
					"my sheep, are you?");
			player.getInterfaceState().setNextDialogueId(0, 1067);
			break;	
			
		case 1067:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I'm looking for a quest.",
					"I'm looking for something to kill.",
					"I'm lost.");
			player.getInterfaceState().setNextDialogueId(0, 1068);
			player.getInterfaceState().setNextDialogueId(1, 1069);
			player.getInterfaceState().setNextDialogueId(2, 1071);
			break;
			
		case 1068:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"I'm looking for a quest.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1069:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"I'm looking for something to kill.");
			player.getInterfaceState().setNextDialogueId(0, 1070);
			break;
			
		case 1070: /* CUSTOM */
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.ANGER_2,
					"Hey! Not on my land! If you want to kill something",
					"go east of here and kill some of those disgusting",
					"goblins.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;	
			
		case 1071:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"I'm lost.");
			player.getInterfaceState().setNextDialogueId(0, 1072);
			break;
			
		case 1072: /* SEMI_CUSTOM */
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"How can you be lost? Lumbridge is south of here",
					"and Draynor is to the west.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;	
			
		/*
		 * CRAFTING TUTOR
		 */
		case 1073:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Can you teach me the basics of crafting please?");
			player.getInterfaceState().setNextDialogueId(0, 1074);
			break;
			
		case 1074:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Firstly, you should know that not all places associated",
					"with crafting will be marked on your minimap. Some",
					"take quite a bit of hunting down to find, don't lose",
					"heart!");
			player.getInterfaceState().setNextDialogueId(0, 1075);
			break;	
			
		case 1075:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"I see... so where should I start?");
			player.getInterfaceState().setNextDialogueId(0, 1076);
			break;
			
		case 1076:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"I suggest you help Farmer Fred out with his sheep",
					"shearing, you can find him northwest of Lumbridge at",
					"his sheep farm, this will give you experience using the",
					"spinning wheel here in this room.");
			player.getInterfaceState().setNextDialogueId(0, 1077);
			break;	
			
		case 1077:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"It's fairly easy, simply gather your whool from sheep in",
					"the field near Lumbridge using the shears sold in",
					"general stores, then click on the spinning wheel and you",
					"will be given a choice of what to spin. Right click on");
			player.getInterfaceState().setNextDialogueId(0, 1078);
			break;	
			
		case 1078:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"the choices will allow you to make multiple spins and",
					"therefore save you time.");
			player.getInterfaceState().setNextDialogueId(0, 1079);
			break;	
			
		case 1079:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"When you have a full inventory, take it to the bank,",
					"you can find it on the roof of this very castle.");
			player.getInterfaceState().setNextDialogueId(0, 1080);
			break;	
			
		case 1080:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,5080,FacialAnimation.DEFAULT,
					"To find a bank look for this symbol on your mini map" +
					"after climbing the stairs of the Lumbridge Castle to the" +
					"top. There are banks all over Runescape with this " +
					"symbol.");
			player.getInterfaceState().setNextDialogueId(0, 97);
	        break;
			
	        
	    /*
	     * MINING TUTOR
	     */
	        
		case 1081:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Can you teach me the basics of mining please?",
					"Are there any mining related quests?",
					"Goodbye");
			player.getInterfaceState().setNextDialogueId(0, 1082);
			player.getInterfaceState().setNextDialogueId(1, 1089);
			player.getInterfaceState().setNextDialogueId(2, 1091);
			break;
			
		case 1082:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Can you teach me the basics of mining please?");
			player.getInterfaceState().setNextDialogueId(0, 1083);
			break;
			
		case 1083:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,5082,FacialAnimation.DEFAULT,
					"Look for this icon on your mini map to find mining" +
					"rocks.");
			player.getInterfaceState().setNextDialogueId(0, 1084);
	        break;
	        
		case 1084:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"As you're already here, have a pick so that you can",
					"mine the rocks around me.");
			player.getInventory().add(new Item(1265, 1));
			player.getInterfaceState().setNextDialogueId(0, 1085);
			break;	
			
		case 1085:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"If you want to know what ore's in a rock before you",
					"mine it, right-click the rock and select prospect from the",
					"menu. It will take a little time, but you'll find out what's",
					"in the rock before you mine.");
			player.getInterfaceState().setNextDialogueId(0, 1086);
			break;	
			
		case 1086:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"You can also tell the ore you'll get from the color of",
					"the rock.");
			player.getInterfaceState().setNextDialogueId(0, 1087);
			break;	
			
		case 1087:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"To mine, simply click on the rock to mine it, but make",
					"sure you have your pick with you.");
			player.getInterfaceState().setNextDialogueId(0, 1088);
			break;	
			
		case 1088:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"When you have a full inventory, take it to the bank,",
					"you can find it on the roof of the castle in Lumbridge.");
			player.getInterfaceState().setNextDialogueId(0, 1081);
			break;	
			
		case 1089:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Are there any mining related quests?");
			player.getInterfaceState().setNextDialogueId(0, 1090);
			break;
			
		case 1090:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"Certainly. These quests will reward you with mining",
					"experience and perhaps some useful equipment. I've",
					"heard that there's a dwarf north of Falador that",
					"needs some help collecting a bunch of materials.");
			player.getInterfaceState().setNextDialogueId(0, 1081);
			break;	
			
		case 1091:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		/*
		 * PRAYER TUTOR
		 */
			
		case 1092:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"I already know the basics of prayer, got any tips?",
					"Tell me about different bones",
					"Goodbye");
			player.getInterfaceState().setNextDialogueId(0, 1093);
			player.getInterfaceState().setNextDialogueId(1, 1097);
			player.getInterfaceState().setNextDialogueId(2, 99);
			break;
			
		case 1093:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"I already know the basics of prayer, got any typs?");
			player.getInterfaceState().setNextDialogueId(0, 1094);
			break;
			
		case 1094:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"For you "+player.getName()+"? Always. here are many advantages",
					"to using the protection prayers when fighting the more",
					"dangerous foes. You can protect yourself from magic,",
					"melee or ranged attacks with these useful prayers.");
			player.getInterfaceState().setNextDialogueId(0, 1095);
			break;	
			
		case 1095:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_2,
					"A good prayer to have when venturing into the",
					"wilderness is protect item. This will protect one of your",
					"items if you should die there.");
			player.getInterfaceState().setNextDialogueId(0, 1096);
			break;	
			
		case 1096:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Remember though that wandering into the wilderness is",
					"a risky business, store your items in a bank before",
					"you go there so that you don't lose them.");
			player.getInterfaceState().setNextDialogueId(0, 1092);
			break;	
			
		case 1097:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Tell me about different bones.");
			player.getInterfaceState().setNextDialogueId(0, 1099);
			break;
			
		case 1098:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;
			
		case 1099:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Basic bones",
					"Big bones",
					"Babydragon bones",
					"More",
					"Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 1100);
			player.getInterfaceState().setNextDialogueId(1, 1101);
			player.getInterfaceState().setNextDialogueId(2, 1102);
			player.getInterfaceState().setNextDialogueId(3, 1103);
			player.getInterfaceState().setNextDialogueId(4, 1098);
			break;
	
		case 1100:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,526,FacialAnimation.DEFAULT,
					"These bones are the most basic of all, and" +
					"they are dropped by monsters and humans"+
					"alike. They are the weakest type of bones.");
			player.getInterfaceState().setNextDialogueId(0, 1099);
	        break;
	        
		case 1101:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,532,FacialAnimation.DEFAULT,
					"Big bones are dropped by larger monsters such" +
					"as hill giants or moss giants. Burying these"+
					"bones will grant you more player experience"+
					"than using regular bones.");
			player.getInterfaceState().setNextDialogueId(0, 1099);
	        break;
	        
		case 1102:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,534,FacialAnimation.DEFAULT,
					"If you're feeling adventurous and up to tackling baby" +
					"dragons, you can get these Baby Dragon bones which"+
					"are even better than big bones.");
			player.getInterfaceState().setNextDialogueId(0, 1099);
	        break;
	        
		case 1103:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Wyvern bones",
					"Dragon bones",
					"Back",
					"Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 1104);
			player.getInterfaceState().setNextDialogueId(1, 1106);
			player.getInterfaceState().setNextDialogueId(2, 1099);
			player.getInterfaceState().setNextDialogueId(3, 1098);
			break;
			
		case 1104:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,6812,FacialAnimation.DEFAULT,
					"If you have the experience in the slayer skill you can" +
					"tackle these formidable beasts and get a substantial boost"+
					"to your prayer.");
			player.getInterfaceState().setNextDialogueId(0, 1105);
	        break;
	        
		case 1105:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,6812,FacialAnimation.DEFAULT,
					"People often mistake them for dragons, but dragons" +
					"have 4 legs,, wyverns have only two, or if they do have"+
					"4, the two front legs are so small they can't use them" +
					"to walk on... or so I've read in the histories.");
			player.getInterfaceState().setNextDialogueId(0, 1103);
	        break;
	        
		case 1106:
			player.getActionSender().sendDialogue(player.getName(),
					DialogueType.MESSAGE_MODEL_LEFT,536,FacialAnimation.DEFAULT,
					"Those bold enough to tackle the mighty dragons" +
					"of this world can obtain these powerful bones. These"+
					"are some of the strongest bones you can use.");
			player.getInterfaceState().setNextDialogueId(0, 1103);
	        break;
	        
	    /*
	     * Ranged tutor
	     */
	        
		case 1107:
			player.getActionSender().sendDialogue("Select an Option", 
					DialogueType.OPTION, -1, FacialAnimation.DEFAULT,
					"Any advice for an advanced user?",
					"What about fletching?",
					"Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 1108);
			player.getInterfaceState().setNextDialogueId(1, 1110);
			player.getInterfaceState().setNextDialogueId(2, 99);
			break;	
			
		case 1108:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"Any advice for an advanced user?");
			player.getInterfaceState().setNextDialogueId(0, 1109);
			break;	
			
		case 1109:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.CALM_1,
					"Visit the ranging guild over near Hemenster, there you",
					"can practice your archery and earn tickets for it at the",
					"same time");
			player.getInterfaceState().setNextDialogueId(0, 1107);
			break;	
			
		case 1110:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.HAPPY,
					"What about fletching?");
			player.getInterfaceState().setNextDialogueId(0, 1111);
			break;	
			
		case 1111:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.LAUGH_1,
					"Ahh the art of making your own bow and arrows. It's",
					"quite simple really. You'll need an axe to cut some logs",
					"from trees and a knife. Knives can be found in and",
					"around the Lumbridge castle and in the Varrock");
			player.getInterfaceState().setNextDialogueId(0, 1112);
			break;	
			
		case 1112:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.HAPPY,
					"General store upstairs.");
			player.getInterfaceState().setNextDialogueId(0, 1113);
			break;	
			
		case 1113:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"Use your knife on the logs, this will bring up a list of",
					"items you can make. Right-click on the item of your",
					"choice and choose the ammount fo fletch.");
			player.getInterfaceState().setNextDialogueId(0, 1114);
			break;	
			
		case 1114:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"For arrows, you will need to smith some arrow heads",
					"and kill some chickens for feathers. Add the feathers ",
					"and heads to the shafts to make arrows you can use.");
			player.getInterfaceState().setNextDialogueId(0, 1115);
			break;	
			
		case 1115:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"You'll need to find a flax field, there's one south of",
					"Seers' Village. Gather flax, then spin it on a spinning",
					"wheel, there's one in Seers' Village too. This makes bow",
					"strings which you can then use on unstrung bows.");
			player.getInterfaceState().setNextDialogueId(0, 1116);
			break;	
			
		case 1116:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.DEFAULT,
					"to make a working bow!");
			player.getInterfaceState().setNextDialogueId(0, 1117);
			break;	
			
		case 1117:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Brilliant. If I forget anything I'll come talk to you again.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;	
			
		/*
		 * Smithing Apprentice
		 */
			
		case 1118:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.DISTRESSED,
					"What do I do after smelting the ore?");
			player.getInterfaceState().setNextDialogueId(0, 1119);
			break;	
			
		case 1119:
			player.getActionSender().sendDialogue(npc.getDefinition().getName(), 
					DialogueType.NPC, npc.getDefinition().getId(), FacialAnimation.LAUGH_1,
					"Ahh you'll need to learn to smith. Go to Varrock...");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;	
			
		/*
		 * Barfy Bill
		 */
			
		case 99:
			player.getActionSender().sendDialogue(player.getName(), 
					DialogueType.PLAYER, -1, FacialAnimation.CALM_2,
					"Goodbye.");
			player.getInterfaceState().setNextDialogueId(0, 97);
			break;	
		case 97:
		case 98:
		default:
			player.getActionSender().removeChatboxInterface();
			break;
		}
	}
	
	public static void advanceDialogue(Player player, int index) {
		int dialogueId = player.getInterfaceState().getNextDialogueId(index);
		if (dialogueId == -1) {
			player.getActionSender().removeChatboxInterface();
			return;
		}
		openDialogue(player, dialogueId);
	}
	


	


}