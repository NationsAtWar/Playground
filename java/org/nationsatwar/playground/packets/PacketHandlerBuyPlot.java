package org.nationsatwar.playground.packets;

import java.util.HashMap;
import java.util.Map;

import org.nationsatwar.playground.configuration.ConfigurationHandler;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerBuyPlot implements IMessageHandler<PacketBuyPlot, IMessage> {

	@Override
	public IMessage onMessage(PacketBuyPlot message, MessageContext ctx) {
		
		ConfigurationHandler.addPlot(message.plotOwner, message.plotX, message.plotZ, true);
		
		return null;
	}
}