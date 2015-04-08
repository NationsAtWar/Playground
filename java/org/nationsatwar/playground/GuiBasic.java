package org.nationsatwar.playground;

import org.nationsatwar.playground.init.PGItems;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GuiBasic extends GuiScreen {
	
	private ResourceLocation backgroundimage = new ResourceLocation(Playground.MODID + ":" + 
			"textures/client/gui/GuiBackground.png");
	
	private int x, y, z;
	private EntityPlayer player;
	private World world;
	
	private int windowX, windowY, windowWidth, windowHeight;
	
	public static final int GUI_ID = 20;
	
	public GuiBasic(EntityPlayer player, World world, int x, int y, int z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
		this.world = world;
	}
	
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
		
		this.mc.getTextureManager().bindTexture(backgroundimage);
		drawTexturedModalRect(windowX, windowY, 0, 0, windowWidth,  windowHeight);
		
		drawString(fontRendererObj, "Plot Management", windowX + 10, windowY + 10, 0xEE8888);
		
		super.drawScreen(mouseX, mouseY, renderPartialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
	
	@Override
	public void actionPerformed(GuiButton button) {
		
		if (button.id == 0) {
			
			EntityPlayerSP playerSP = (EntityPlayerSP) player;
			int chunkX = playerSP.chunkCoordX;
			int chunkZ = playerSP.chunkCoordZ;
			
			Playground.playgroundChannel.sendToServer(new TestPacket(playerSP.getName(), chunkX, chunkZ));
		}
		
		if (button.id == 1)
			player.inventory.setInventorySlotContents(1, new ItemStack(PGItems.plot_deed));
		
		player.closeScreen();
	}
}