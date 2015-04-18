package org.nationsatwar.playground.configuration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.utility.ChatMessage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	
	// <Key: plotOwner | Value: <Key: plotID | Value: int[0] = plotX, int[1] = plotZ>>
	
	public static void init(File configFile) {
		
		// Create the configuration object from the given configuration file
		configuration = new Configuration(configFile);
		
		try {
			// Load the configuration file
			configuration.load();
			
			for (String playerName : configuration.getCategoryNames()) {
			
				for (Property plotList : configuration.getCategory(playerName).getOrderedValues()) {
					
					int plotX = plotList.getIntList()[0];
					int plotZ = plotList.getIntList()[1];
					
					addPlot(playerName, plotX, plotZ, false);
				}
			}
		} catch(Exception e) {
			System.out.println("ERROR ERROR! " + e.getMessage());
		} finally {
			// Save the configuration file
			configuration.save();
		}
	}
	
	public static void addPlot(String plotOwner, int plotX, int plotZ, boolean updateConfig) {
		
		addPlotOwner(plotOwner);
		
		Map<Integer, int[]> plotList = Playground.plotKeys.get(plotOwner);

		int[] newPlot = {plotX, plotZ};
		
		EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().getPlayerByUsername(plotOwner);
		
		// Check to see if the plot is already registered elsewhere
		for (int[] values : plotList.values()) {
			
			if (values[0] == newPlot[0] && values[1] == newPlot[1]) {
				
				ChatMessage.sendMessage(player, "You already have this plot!");
				return;
			}	
		}
		
		ChatMessage.sendMessage(player, "You have purchased this plot of land!");
		player.inventory.decrStackSize(player.inventory.currentItem, 1);
		
		plotList.put(plotList.size(), newPlot);
		
		if (updateConfig)
			addPlayerPlot(plotOwner, plotList.size(), plotX, plotZ);
	}
	
	public static void addPlotOwner(String plotOwner) {
		
		// Add Player as Plot Owner if not already registered
		if (!Playground.plotKeys.containsKey(plotOwner)) {

			Map<Integer, int[]> plotList = new HashMap<Integer, int[]>();
			Playground.plotKeys.put(plotOwner, plotList);
		}
	}
	
	public static void addPlayerPlot(String playerName, int plotID, int plotX, int plotZ) {
		
		int[] configPlotKey = new int[]{plotX, plotZ};
		configuration.get(playerName, plotID + "", configPlotKey).set(configPlotKey);
		configuration.save();
	}
}