package org.nationsatwar.playground.items;

import net.minecraft.item.ItemFood;

public class PGItemPlotDeed extends ItemFood {

	public PGItemPlotDeed(int amount, boolean isWolfFood) {
		
		super(amount, isWolfFood);
		this.setUnlocalizedName("plot_deed");
	}
}