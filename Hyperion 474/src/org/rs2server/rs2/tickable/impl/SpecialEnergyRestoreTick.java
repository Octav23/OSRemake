package org.rs2server.rs2.tickable.impl;

import org.rs2server.rs2.model.Mob;
import org.rs2server.rs2.tickable.Tickable;
/**
 * A tickable which increases a mob's special energy.
 * @author Michael Bull
 *
 */
public class SpecialEnergyRestoreTick extends Tickable {

	/**
	 * The cycle time, in ticks.
	 */
	public static final int CYCLE_TIME = 50;
	
	/**
	 * The mob for who we are increasing the special energy.
	 */
	public Mob mob;
	
	/**
	 * Creates the event to cycle every 30,000 milliseconds (30 seconds).
	 */	
	public SpecialEnergyRestoreTick(Mob mob) {
		super(CYCLE_TIME);
		this.mob = mob;
	}

	@Override
	public void execute() {
		mob.getCombatState().increaseSpecial(10);
		if(mob.getCombatState().getSpecialEnergy() >= 100) {
			mob.setSpecialUpdateTick(null);
			this.stop();
		}
	}

}
