package org.rs2server.rs2.content;

import org.rs2server.rs2.action.Action;
import org.rs2server.rs2.model.Animation;
import org.rs2server.rs2.model.GameObject;
import org.rs2server.rs2.model.Ladders;
import org.rs2server.rs2.model.Location;
import org.rs2server.rs2.model.NPC;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.model.UpdateFlags.UpdateFlag;
import org.rs2server.rs2.tickable.Tickable;
import org.rs2server.rs2.util.Misc;

public class HAMHideout {

	Location jail = Location.create(3184, 9610, 0);
	
	public static void actionOption1(final Player player, int id, final GameObject obj){
		switch(id){
		case 5492:
			player.getActionSender().sendMessage("You try to open the door");
			player.getActionSender().sendMessage("This trapdoor seems totally locked.");
			break;
			
		case 5491:
			player.playAnimation(Animation.create(828));
			player.setTeleportTarget(Location.create(3149, 9652, 0));
			player.getActionSender().sendMessage("You enter a dim-lighted area.");
			break;
			
		case 5493:
			player.playAnimation(Animation.create(828));
			player.setTeleportTarget(Location.create(3166, 3251, 0));
			break;
			
		case 5501:
			player.getActionSender().sendMessage("The door seems very secure.");
			
			break;
		
		}
	}
	
	public static void actionOption2(Player player, int id, final GameObject obj){
		switch(id){
		case 5491:
			player.playAnimation(Animation.create(832));
			player.getActionSender().sendMessage("You close the trapdoor.");
			World.getWorld().register(new GameObject(obj.getLocation(), 5492, 22, 1, false));
			break;
		
		}
	}
	
	
	public static void actionOption4(final Player player, int id, final GameObject obj){
		switch(id){
		case 5501:
			if(player.getLocation().getX() == 3182){
				player.getActionSender().sendMessage("The lock can only be opened from inside.");
				return;
			}
			if(player.getLocation().getX() == 3183 && player.getLocation().getY() != 9611){
				player.getWalkingQueue().reset();
				player.getWalkingQueue().addStep(3183, 9611);
				player.getWalkingQueue().finish();
				
			}
			player.getActionSender().sendMessage("You attempt to pick the lock on the door.");
			player.playAnimation(Animation.create(832));
						
						player.getActionQueue().addAction(new Action(player, 5) {
							@Override
							public void execute() {
								player.playAnimation(Animation.create(832));
								if(Misc.random(2) == 1){
									player.getActionSender().sendMessage("You pick the lock on the prison door.");
									SpecialDoors.forceWalkDoor(player, obj, 3182, 9611);
									this.stop();									
								} else {
									player.getActionSender().sendMessage("You attempt to pick the lock on the door.");
									
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
						
			break;
		case 5492:
			player.getActionSender().sendMessage("You try to open the trap door.");
			
			player.getActionQueue().addAction(new Action(player, 5) {
				@Override
				public void execute() {
					player.playAnimation(Animation.create(832));
					if(Misc.random(3) == 1){
						player.getActionSender().sendMessage("You pick the lock on the trapdoor.");
						World.getWorld().register(new GameObject(obj.getLocation(), 5491, 22, 1, false));	
						
						World.getWorld().submit(new Tickable(10) {
							public void execute() {
								World.getWorld().register(new GameObject(obj.getLocation(), 5492, 22, 1, false));	
							}
						});
						
						this.stop();						
					} else {
						player.getActionSender().sendMessage("You attempt to pick the lock on the trap door.");
						
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
			
			break;
		
		
		}		
	}
	
	

	public static int speechStage = 0;
	public static int speech = 0;
	
	public static void sittingHam(){
		for(NPC npc : World.getWorld().getNPCs()) {
			if(npc.getDefinition().getId() == 1717){
				switch(Misc.random(16)){
				case 0:
					npc.forceChat("I hear you brother...");
					break;
				case 1:
					npc.forceChat("Well said brother...");
					break;
				case 2:
					npc.forceChat("You speak the truth brother...");
					break;
				}
			}
			
		}
		
	}
	
		public static void hamSpeech(){
			for(NPC npc : World.getWorld().getNPCs()) {
				if(npc.getDefinition().getId() == 1713){
					if(Misc.random(1) == 1){
						npc.playAnimation(Animation.create(862));
					} else {
						npc.playAnimation(Animation.create(1670));						
					}
					speech += 1;
					switch(speech){
					case 0:
						npc.forceChat("And lo");
						break;
					case 1:
						npc.forceChat("we become the power");
						break;
					case 2:
						npc.forceChat("indeed the force to");
						break;
					case 3:
						npc.forceChat("stop these monsters");
						break;
					case 4:
						npc.forceChat("in their tracks.");
						sittingHam();
						break;
						
					case 5:
					case 6:
						npc.playAnimation(Animation.create(-1));	
						break;
						
					case 7:
						npc.forceChat("And let us smite");
						break;
					case 8:
						npc.forceChat("these monsters");
						break;	
					case 9:
						npc.forceChat("...unto their deaths.");
						sittingHam();
						break;
						
					case 10:
					case 11:
						npc.playAnimation(Animation.create(-1));	
						break;
						
					case 12:
						npc.forceChat("And let there be no cave");
						break;
					case 13:
						npc.forceChat("or shelter for their spawn");
						break;
					case 14:
						npc.forceChat("until the end of days");	
						sittingHam();
						break;
						
					case 15:
					case 16:
						npc.playAnimation(Animation.create(-1));	
						break;
						
					case 17:
						npc.forceChat("Let them not");	
						break;
					case 18:
						npc.forceChat("infest our ");
						break;	
					case 19:
						npc.forceChat("cities and towns...");	
						sittingHam();
						break;
						
					case 20:
					case 21:
						npc.playAnimation(Animation.create(-1));	
						break;
						
					case 22:
						npc.forceChat("We are the");	
						break;
					case 23:
						npc.forceChat("righteous ones in");	
						break;
					case 24:
						npc.forceChat("his eyes alone");	
						sittingHam();
						break;
						
					case 25:
					case 26:
						npc.playAnimation(Animation.create(-1));	
						break;
						
					case 27:
						npc.forceChat("For they are not");	
						break;
					case 28:
						npc.forceChat("the chosen ones");
						break;	
					case 29:
						npc.forceChat("in Saradomin's eyes");
						sittingHam();
						break;
						
					case 30:
					case 31:
						npc.playAnimation(Animation.create(-1));	
						break;

					case 32:
						npc.forceChat("If thine monsters visage");	
						break;
					case 33:
						npc.forceChat("does frighten thee then tear it off");
						break;
					case 34:
						npc.forceChat("I say tear it off!");
						sittingHam();	
						break;

					case 35:
					case 36:
						npc.playAnimation(Animation.create(-1));	
						break;
						
					case 37:
						npc.forceChat("Where do we go for safety");	
						break;
					case 38:
						npc.forceChat("from these monsters");	
						break;
					case 39:
						npc.forceChat("...here, my brethren!");
						sittingHam();	
						break;
						
					case 40:
					case 41:
						npc.playAnimation(Animation.create(-1));	
						break;
						
					case 42:
						npc.forceChat("For Saradomin will guide");	
						break;
					case 43:
						npc.forceChat("our sword arms and");	
						break;
					case 44:
						npc.forceChat("smash the enemies");	
						break;
					case 45:
						npc.forceChat("of humans till their");	
						break;
					case 46:
						npc.forceChat("bones becomes dust");
						sittingHam();	
						break;
					
					case 47:
						npc.playAnimation(Animation.create(-1));	
						break;
					case 48:
						npc.playAnimation(Animation.create(-1));	
						speech = 0;
						break;
					
					
						
					}
				}
			}
		}
}
