package org.nationsatwar.playground.packets;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import org.nationsatwar.playground.database.ConfigurationHandler;

public class PacketHandlerBuyPlot implements IMessageHandler<PacketBuyPlot, IMessage> {

	@Override
	public IMessage onMessage(PacketBuyPlot message, MessageContext ctx) {
		
		ConfigurationHandler.addPlot(message.plotOwner, message.plotX, message.plotZ, true);
		
		return null;
	}
}