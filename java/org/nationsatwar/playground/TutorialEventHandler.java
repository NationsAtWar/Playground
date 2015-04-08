package org.nationsatwar.playground;

import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

import org.nationsatwar.playground.init.PGItems;

import akka.event.Logging.Debug;
import net.minecraft.entity.item.EntityItem;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TutorialEventHandler {
	
	@SubscribeEvent
	public void breakBlock(BlockEvent.BreakEvent event) {
		
		/*
		System.out.println("Breaker: " + event.getPlayer().getName());
		System.out.println("Block: " + event.state.getBlock().getUnlocalizedName() + "lol");
		System.out.println("LocationX: " + (event.pos.getX() / 16));
		System.out.println("LocationZ: " + (event.pos.getZ() / 16));
		*/
		
		int chunkX = event.pos.getX() / 16;
		int chunkZ = event.pos.getZ() / 16;
		
		for (Map<Integer, int[]> plotOwner : Playground.plotKeys.values()) {
			
			for (int[] plotKeys : plotOwner.values()) {
				
				if (plotKeys[0] == chunkX && plotKeys[1] == chunkZ)
					event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void placeBlock(BlockEvent.PlaceEvent event) {
		
		/*
		System.out.println("Placer: " + event.player.getName());
		System.out.println("Block: " + event.state.getBlock().getUnlocalizedName());
		System.out.println("LocationX: " + (event.pos.getX() / 16));
		System.out.println("LocationZ: " + (event.pos.getZ() / 16));
		*/
		
		int chunkX = event.pos.getX() / 16;
		int chunkZ = event.pos.getZ() / 16;
		
		for (Map<Integer, int[]> plotOwner : Playground.plotKeys.values()) {
			
			for (int[] plotKeys : plotOwner.values()) {
				
				if (plotKeys[0] == chunkX && plotKeys[1] == chunkZ)
					event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public void useDeed1(PlayerUseItemEvent.Tick event) {

		System.out.println(event.item.getDisplayName());
	}
	
	@SubscribeEvent
	public void useDeed2(PlayerUseItemEvent.Finish event) {

		System.out.println(event.item.getDisplayName());
	}
	
	@SubscribeEvent
	public void useDeed3(PlayerUseItemEvent.Start event) {
		
		System.out.println(event.item.getDisplayName());
		
		if (event.item.getDisplayName().equals("plot_deed"))
			return;
	}
	
	@SubscribeEvent
	public void useDeed4(PlayerUseItemEvent.Stop event) {
		
		System.out.println(event.item.getDisplayName());
		
		if (event.item.getDisplayName().equals("plot_deed"))
			return;
	}
}