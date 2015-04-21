package org.nationsatwar.playground.configuration;

import java.io.File;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;

import org.nationsatwar.palette.ChatMessage;
import org.nationsatwar.playground.plots.PlotManager;

public class ConfigurationHandler {
	
	private static Configuration configuration;
	
	private static String database;
	private static String username;
	private static String password;
	
	public static void reloadConfig(File configFile) {
		
		// Create the configuration object from the given configuration file
		configuration = new Configuration(configFile);
		
		try {
			// Load the configuration file
			configuration.load();
			
			database = configuration.get("Settings", "Database", "mydb").getString();
			username = configuration.get("Settings", "Username", "username").getString();
			password = configuration.get("Settings", "Password", "password").getString();
		} catch(Exception e) {
			System.out.println("ERROR ERROR! " + e.getMessage());
		} finally {
			// Save the configuration file
			configuration.save();
		}
	}
	
	public static String getDatabaseUrl() {

		// "jdbc:mysql://localhost:3306/test?user=root&password=mountdew4";
		
		String databaseUrl = "jdbc:mysql://localhost:3306/";
		
		databaseUrl += database;
		databaseUrl += "?user=" + username;
		databaseUrl += "&password=" + password;
		
		System.out.println(databaseUrl);
		
		return databaseUrl;
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