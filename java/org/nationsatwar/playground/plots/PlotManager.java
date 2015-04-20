package org.nationsatwar.playground.plots;

import java.util.HashMap;
import java.util.Map;

public class PlotManager {
	
	// <Key: plotOwner | Value: <Key: plotID | Value: int[0] = plotX, int[1] = plotZ>>
	public static Map<String, Map<Integer, int[]>> plotKeys = new HashMap<String, Map<Integer, int[]>>();
	
	public static boolean isPlotTaken(int plotX, int plotZ) {

		// Check to see if the plot is already registered elsewhere
		for (Map<Integer, int[]> plots : plotKeys.values())
			for (int[] values : plots.values())
				if (values[0] == plotX && values[1] == plotZ)
					return true;
		
		return false;
	}
}