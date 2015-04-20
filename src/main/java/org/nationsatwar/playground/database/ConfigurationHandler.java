package org.nationsatwar.playground.database;

import java.io.File;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;

import org.nationsatwar.playground.plots.PlotManager;
import org.nationsatwar.playground.utility.ChatMessage;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	
	// <Key: plotOwner | Value: <Key: plotID | Value: int[0] = plotX, int[1] = plotZ>>
	
	public static void init(File configFile) {
		
		// Create the configuration object from the given configuration file
		configuration = new Configuration(configFile);
		
		try {
			// Load the configuration file
			configuration.load();
		} catch(Exception e) {
			System.out.println("ERROR ERROR! " + e.getMessage());
		} finally {
			// Save the configuration file
			configuration.save();
		}
	}
	
	public static void addPlot(String plotOwner, int plotX, int plotZ, boolean updateConfig) {
		
		EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerByUsername(plotOwner);
		
		// Check to see if the plot is already registered by the player
		if (PlotManager.isPlotTaken(plotOwner, plotX, plotZ)) {
			
			ChatMessage.sendMessage(player, "You already have this plot!");
			return;
		}

		// Check to see if the plot is already registered by anyone else
		if (PlotManager.isPlotTaken(plotX, plotZ)) {
			
			ChatMessage.sendMessage(player, "This plot is already owned!");
			return;
		}
		
		// Plot is available, use up plot deed
		ChatMessage.sendMessage(player, "You have purchased this plot of land!");
		player.inventory.decrStackSize(player.inventory.currentItem, 1);
		
		PlotManager.addPlot(plotOwner, plotX, plotZ);
	}
}