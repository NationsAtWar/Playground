package org.nationsatwar.playground.configuration;

import java.io.File;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import org.nationsatwar.palette.chat.ChatMessage;
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
		
		String databaseUrl = "jdbc:mysql://localhost:3306/";
		
		databaseUrl += database;
		databaseUrl += "?user=" + username;
		databaseUrl += "&password=" + password;
		
		return databaseUrl;
	}
	
	public static void addPlot(String plotOwner, int plotX, int plotZ, boolean updateConfig) {
		
		EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(plotOwner);
		
		// Check to see if the plot is already registered by the player
		if (PlotManager.isPlotTaken(plotOwner, plotX, plotZ)) {
			
			if (Loader.isModLoaded("palette"))
				ChatMessage.sendMessage(player, "You already have this plot!");
			else
				System.out.println("lol");
			return;
		}

		// Check to see if the plot is already registered by anyone else
		if (PlotManager.isPlotTaken(plotX, plotZ)) {

			if (Loader.isModLoaded("palette"))
				ChatMessage.sendMessage(player, "This plot is already owned!");
			else
				System.out.println("lol");
			
			return;
		}
		
		// Plot is available, use up plot deed
		ChatMessage.sendMessage(player, "You have purchased this plot of land!");
		player.inventory.decrStackSize(player.inventory.currentItem, 1);
		
		PlotManager.addPlot(plotOwner, plotX, plotZ);
	}
}