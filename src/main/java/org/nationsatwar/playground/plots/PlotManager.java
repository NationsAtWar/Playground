package org.nationsatwar.playground.plots;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class PlotManager {
	
	private static List<PlotObject> plots = new ArrayList<PlotObject>();
	
	public static void addPlot(String owner, int plotX, int plotZ) {
		
		PlotObject newPlot = new PlotObject(plots.size(), owner, plotX, plotZ);
		plots.add(newPlot);
		
		try { addtoDatabase(newPlot); }
		catch (Exception e) { System.out.println(e.getMessage()); }
	}
	
	/**
	 * Checks to see if the plot coordinates are owned by anyone
	 * 
	 * @param plotX
	 * @param plotZ
	 * @return true if plot is already taken, false otherwise
	 */
	public static boolean isPlotTaken(int plotX, int plotZ) {
		
		for (PlotObject plot : plots)
			if (plot.getPlotX() == plotX && plot.getPlotZ() == plotZ)
				return true;
		
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
	
	private static void addtoDatabase(PlotObject newPlot) throws Exception {
		
		String databaseUrl = "jdbc:mysql://localhost:3306/test?user=root&password=mountdew4";
		
		// create a connection source to our database
		ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
		
		// instantiate the dao
		Dao<PlotObject, String> accountDao = DaoManager.createDao(connectionSource, PlotObject.class);
		
		// if you need to create the 'accounts' table make this call
		TableUtils.createTable(connectionSource, PlotObject.class);
		
		accountDao.create(newPlot);
		
		connectionSource.close();
    }
}