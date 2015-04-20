package org.nationsatwar.playground.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import org.nationsatwar.playground.init.InitializeItems;

public class PacketHandlerGiveDeed implements IMessageHandler<PacketGiveDeed, IMessage> {

	@Override
	public IMessage onMessage(PacketGiveDeed message, MessageContext ctx) {
		
		EntityPlayer player = MinecraftServer.getServer().getConfigurationManager().getPlayerByUsername(message.plotOwner);
		World world = player.worldObj;
		
		if (!world.isRemote)
			player.inventory.addItemStackToInventory(new ItemStack(InitializeItems.plot_deed));
		
		return null;
	}
}