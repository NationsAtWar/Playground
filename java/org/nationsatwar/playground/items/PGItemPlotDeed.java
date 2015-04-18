package org.nationsatwar.playground.items;

import java.util.List;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.packets.PacketBuyPlot;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PGItemPlotDeed extends PGBasicItem {

	public PGItemPlotDeed() {
		
		super("plot_deed");
		this.setUnlocalizedName("plot_deed");
        this.maxStackSize = 1;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		
		if (!worldIn.isRemote)
			return false;
			
		EntityPlayerSP playerSP = (EntityPlayerSP) playerIn;
		
		int chunkX = playerSP.chunkCoordX;
		int chunkZ = playerSP.chunkCoordZ;
		
		Playground.playgroundChannel.sendToServer(new PacketBuyPlot(playerSP.getName(), chunkX, chunkZ));
		
		return true;
	}
}