package org.nationsatwar.playground.plots;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "plots")
public class PlotObject {
	
	@DatabaseField(id = true)
	private int id;

	@DatabaseField
	private String owner;
	@DatabaseField
	private int plotX;
	@DatabaseField
	private int plotZ;
	
	public PlotObject() {}
	
	public PlotObject(int id, String owner, int plotX, int plotZ) {
		
		this.id = id;
		this.owner = owner;
		this.plotX = plotX;
		this.plotZ = plotZ;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public int getPlotX() {
		return plotX;
	}
	
	public void setPlotX(int plotX) {
		this.plotX = plotX;
	}
	
	public int getPlotZ() {
		return plotZ;
	}
	
	public void setPlotZ(int plotZ) {
		this.plotZ = plotZ;
	}
}