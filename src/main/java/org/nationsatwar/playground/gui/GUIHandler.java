package org.nationsatwar.playground.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		
		if (ID == 20)
			return new PlotMenuGUI(player, world, x, y, z);
		
		if (ID == 21)
			return new ConfirmGUI(player, world, x, y, z);
		
		return null;
	}
}