package org.nationsatwar.playground.database;

import java.util.ArrayList;
import java.util.List;

import org.nationsatwar.playground.configuration.ConfigurationHandler;
import org.nationsatwar.playground.plots.PlotObject;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHandler {
	
	public static void addtoDatabase(PlotObject newPlot) {
		
		String databaseUrl = ConfigurationHandler.getDatabaseUrl();
		
		try {
			// create a connection source to our database
			ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
			
			// instantiate the dao
			Dao<PlotObject, String> accountDao = DaoManager.createDao(connectionSource, PlotObject.class);
			
			// if you need to create the 'accounts' table make this call
			TableUtils.createTableIfNotExists(connectionSource, PlotObject.class);
			
			accountDao.create(newPlot);
			
			connectionSource.close();
		} catch (Exception e) {
			System.out.println(e);
		}
    }
	
	public static List<PlotObject> loadFromDatabase() {
		
		String databaseUrl = ConfigurationHandler.getDatabaseUrl();
		
		List<PlotObject> plots = new ArrayList<PlotObject>();
		
		try {
			// create a connection source to our database
			ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
			
			// instantiate the dao
			Dao<PlotObject, String> accountDao = DaoManager.createDao(connectionSource, PlotObject.class);
			
			plots = accountDao.queryForAll();
			
			connectionSource.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return plots;
    }
}