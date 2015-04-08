package org.nationsatwar.playground;

import java.util.HashMap;
import java.util.Map;

import org.nationsatwar.playground.configuration.ConfigurationHandler;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TestPacketHandler implements IMessageHandler<TestPacket, IMessage> {

	@Override
	public IMessage onMessage(TestPacket message, MessageContext ctx) {
		
		ConfigurationHandler.addPlot(message.plotOwner, message.plotX, message.plotZ, true);
		
		//System.out.println("kekek " + Playground.plotKeys.get("player896").get(1)[0]);
		System.out.println("kekek " + ConfigurationHandler.configuration.getCategoryNames());
		
		return null;
	}
}