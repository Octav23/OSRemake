package org.rs2server.rs2.model.skills;

import org.rs2server.rs2.model.skills.Crafting.DragonLeatherType;

public class CraftingExtras {
	
	private boolean isCrafting = false;
	private DragonLeatherType leatherType;

	public void setCrafting(boolean isCrafting) {
		this.isCrafting = isCrafting;
	}

	public boolean isCrafting() {
		return isCrafting;
	}

	public void setLeatherType(DragonLeatherType leatherType) {
		this.leatherType = leatherType;
	}

	public DragonLeatherType getLeatherType() {
		return leatherType;
	}

}
