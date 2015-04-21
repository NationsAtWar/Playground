package org.nationsatwar.playground.plots;

import java.util.ArrayList;
import java.util.List;

import org.nationsatwar.playground.database.DatabaseHandler;

public class PlotManager {
	
	private static List<PlotObject> plots = new ArrayList<PlotObject>();
	
	/**
	 * Adds plot to instance as well as database
	 * 
	 * @param owner
	 * @param plotX
	 * @param plotZ
	 */
	public static void addPlot(String owner, int plotX, int plotZ) {
		
		PlotObject newPlot = new PlotObject(owner, plotX, plotZ);
		plots.add(newPlot);
		
		DatabaseHandler.addtoDatabase(newPlot);
	}
	
	/**
	 * Loads all plots from the database to the mod instance
	 */
	public static void loadPlots() {
		
		plots = DatabaseHandler.loadFromDatabase();
	}
	
	/**
	 * Checks to see if the plot coordinates are owned by anyone
	 * 
	 * @param plotX
	 * @param plotZ
	 * @return true if plot is already taken, false otherwise
	 */
	public static boolean isPlotTaken(int plotX, int plotZ) {
		
		System.out.println("plotX = " + plotX);
		System.out.println("plotZ = " + plotZ);
		
		for (PlotObject plot : plots) {

			System.out.println(plot.getPlotX());
			System.out.println(plot.getPlotZ());
			
			if (plot.getPlotX() == plotX && plot.getPlotZ() == plotZ)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks to see if the plot coordinates are owned by the specified owner
	 * 
	 * @param owner
	 * @param plotX
	 * @param plotZ
	 * @return true if plot is already taken, false otherwise
	 */
	public static boolean isPlotTaken(String owner, int plotX, int plotZ) {

		for (PlotObject plot : plots)
			if (plot.getOwner() == owner && plot.getPlotX() == plotX && plot.getPlotZ() == plotZ)
				return true;
		
		return false;
	}
}