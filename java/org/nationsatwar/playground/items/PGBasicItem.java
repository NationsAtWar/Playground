package org.nationsatwar.playground.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.nationsatwar.playground.Playground;

public class PGBasicItem extends Item {
	
	public PGBasicItem(String unlocalizedName) {
		
		super();
		
		setUnlocalizedName(unlocalizedName);
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(64);
	}
	
	// Make item not disappear on item use on block
	// Make Utility API
	// Make GUI Confirmation Screen
}