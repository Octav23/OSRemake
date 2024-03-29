package org.rs2server.rs2.tickable.impl;

import org.rs2server.rs2.model.Mob;
import org.rs2server.rs2.model.Skills;
import org.rs2server.rs2.tickable.Tickable;
/**
 * A tickable which normalizes players skills.
 * @author Michael Bull
 *
 */
public class SkillsUpdateTick extends Tickable {

	/**
	 * The cycle time, in ticks.
	 */
	public static final int CYCLE_TIME = 100;
	
	/**
	 * The mob for who we are normalizing the levels.
	 */
	public Mob mob;
	
	/**
	 * Creates the event to cycle every 100 ticks (60 seconds).
	 */	
	public SkillsUpdateTick(Mob mob) {
		super(CYCLE_TIME);
		this.mob = mob;
	}

	@Override
	public void execute() {
		if(mob.getCombatState().isDead()) {
			return;
		}
		for(int i = 0; i < Skills.SKILL_COUNT; i++) {
			if(mob.getSkills().getLevel(i) != mob.getSkills().getLevelForExperience(i)) {
				mob.getSkills().normalizeLevel(i);
			}
		}
	}

}
