package org.nationsatwar.playground.gui;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.packets.PacketBuyPlot;

public class ConfirmGUI extends GuiScreen {
	
	private ResourceLocation backgroundimage = new ResourceLocation(Playground.MODID + ":" + 
			"textures/client/gui/GuiBackground.png");
	
	private EntityPlayer player;
	
	private int windowX, windowY, windowWidth, windowHeight;
	
	public static final int GUI_ID = 21;
	
	public ConfirmGUI(EntityPlayer player, World world, int x, int y, int z) {
		
		this.player = player;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		
		windowWidth = 150;
		windowHeight = 64;
		windowX = (width - windowWidth) / 2;
		windowY = (height - windowHeight) / 2 - 20;
		
		buttonList.clear();
		
		GuiButton buyPlot = new GuiButton(0, windowX + 10, windowY + 30, 60, 20, "Confirm");
		buttonList.add(buyPlot);
		
		GuiButton getDeed = new GuiButton(1, windowX + 80, windowY + 30, 60, 20, "Cancel");
		buttonList.add(getDeed);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
		
		this.mc.getTextureManager().bindTexture(backgroundimage);
		drawTexturedModalRect(windowX, windowY, 0, 0, windowWidth,  windowHeight);
		
		drawString(fontRendererObj, "Purchase this plot?", 
				windowX + 10, windowY + 10, 0xEE8888);
		
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		
		EntityPlayerSP playerSP = (EntityPlayerSP) player;
		
		// Confirm Button - Buys Plot
		if (button.id == 0) {
			
			int chunkX = playerSP.chunkCoordX;
			int chunkZ = playerSP.chunkCoordZ;
			
			Playground.channel.sendToServer(new PacketBuyPlot(playerSP.getName(), chunkX, chunkZ));
		}
		
		player.closeScreen();
	}
}