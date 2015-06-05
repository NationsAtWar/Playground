package org.nationsatwar.playground.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PGBasicItem extends Item {
	
	public PGBasicItem(String unlocalizedName) {
		
		super();
		
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(64);
	}
}