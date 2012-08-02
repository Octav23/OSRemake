package org.rs2server.rs2.content;

import org.rs2server.rs2.model.GameObject;
import org.rs2server.rs2.model.Player;
import org.rs2server.rs2.model.World;
import org.rs2server.rs2.tickable.Tickable;

public class SpecialDoors {

	public static void forceWalkDoor(final Player player, final GameObject obj, final int destinationX, final int destinationY){
		
		World.getWorld().register(new GameObject(obj.getLocation(), obj.getId() + 2, 0, obj.getDirection()+1, false));
		

		
		World.getWorld().submit(new Tickable(1) {
			@Override
			public void execute() {
				player.getWalkingQueue().reset();
				player.getWalkingQueue().addStep(destinationX, destinationY);
				player.getWalkingQueue().finish();
				this.stop();
				World.getWorld().submit(new Tickable(2) {
					@Override
					public void execute() {
						World.getWorld().register(new GameObject(obj.getLocation(), obj.getId(), 0, obj.getDirection(), false));
						this.stop();
					}
				});
			}
		});
		
	}
	
}
