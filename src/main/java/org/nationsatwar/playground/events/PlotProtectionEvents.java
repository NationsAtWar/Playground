package org.nationsatwar.playground.events;

import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.nationsatwar.playground.plots.PlotManager;

public class PlotProtectionEvents {
	
	@SubscribeEvent
	public void breakBlock(BlockEvent.BreakEvent event) {
		
		String player = event.getPlayer().getName();
		
		int chunkX = event.pos.getX() / 16;
		int chunkZ = event.pos.getZ() / 16;
		
		if (PlotManager.isPlotTaken(player, chunkX, chunkZ))
			return;

		if (PlotManager.isPlotTaken(chunkX, chunkZ))
			event.setCanceled(true);
	}
	
	@SubscribeEvent
	public void placeBlock(BlockEvent.PlaceEvent event) {
		
		String player = event.player.getName();
		
		int chunkX = event.pos.getX() / 16;
		int chunkZ = event.pos.getZ() / 16;
		
		if (PlotManager.isPlotTaken(player, chunkX, chunkZ))
			return;

		if (PlotManager.isPlotTaken(chunkX, chunkZ))
			event.setCanceled(true);
	}
}