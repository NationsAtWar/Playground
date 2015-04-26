package org.nationsatwar.playground.gui;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.packets.PacketBuyPlot;
import org.nationsatwar.playground.packets.PacketGiveDeed;
import org.nationsatwar.playground.plots.PlotManager;

public class PlotMenuGUI extends GuiScreen {
	
	private ResourceLocation backgroundimage = new ResourceLocation(Playground.MODID + ":" + 
			"textures/client/gui/GuiBackground.png");
	
	private EntityPlayer player;
	
	private int windowX, windowY, windowWidth, windowHeight;
	
	public static final int GUI_ID = 20;
	
	public PlotMenuGUI(EntityPlayer player, World world, int x, int y, int z) {
		
		this.player = player;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		
		windowWidth = 256;
		windowHeight = 192;
		windowX = (width - windowWidth) / 2;
		windowY = (height - windowHeight) / 2 - 20;
		
		buttonList.clear();
		
		GuiButton buyPlot = new GuiButton(0, windowX + 10, windowY + 30, 120, 20, "Buy Plot");
		buttonList.add(buyPlot);
		
		GuiButton getDeed = new GuiButton(1, windowX + 10, windowY + 55, 120, 20, "Get Deed");
		buttonList.add(getDeed);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		
		// Draws the background window
		this.mc.getTextureManager().bindTexture(backgroundimage);
		drawTexturedModalRect(windowX, windowY, 0, 0, windowWidth,  windowHeight);
		
		EntityPlayerSP playerSP = (EntityPlayerSP) player;
		
		int chunkX = playerSP.chunkCoordX;
		int chunkZ = playerSP.chunkCoordZ;
		
		int offsetX = 144;
		int offsetZ = 130;
		int gridSize = 13;
		int gridSpacing = 15;
		
		// Draws a grid map based on player location that alerts the player to which plots are owned
		for (int gridX = -3; gridX < 4; gridX++) {
			for (int gridZ = -3; gridZ < 4; gridZ++) {
				
				int gridColor = 0xFFFFFFFF;
				
				// Change color if plot is taken
				if (PlotManager.isPlotTaken(chunkX + gridX, chunkZ + gridZ))
					gridColor = 0xCC6666FF;
				
				// Increase transparency for player location
				if (chunkX == chunkX + gridX && chunkZ == chunkZ + gridZ)
					gridColor -= 0xAA000000;
				
				// Draws the grid
				drawRect(gridX * gridSpacing + offsetX, gridZ * gridSpacing + offsetZ, 
						gridX * gridSpacing + offsetX + gridSize, gridZ * gridSpacing + offsetZ + gridSize, gridColor);
			}
		}
		
		drawString(fontRendererObj, "Plot Management", windowX + 10, windowY + 10, 0xEE8888);
		
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		
		EntityPlayerSP playerSP = (EntityPlayerSP) player;
		
		// Buy Plot
		if (button.id == 0) {
			
			int chunkX = playerSP.chunkCoordX;
			int chunkZ = playerSP.chunkCoordZ;
			
			Playground.channel.sendToServer(new PacketBuyPlot(playerSP.getName(), chunkX, chunkZ));
		}
		
		// Gives Plot Deed
		if (button.id == 1)
			Playground.channel.sendToServer(new PacketGiveDeed(playerSP.getName()));
		
		player.closeScreen();
	}
}