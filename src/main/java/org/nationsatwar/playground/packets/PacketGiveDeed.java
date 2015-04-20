package org.nationsatwar.playground.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketGiveDeed implements IMessage {
	
	public String plotOwner;
	
	public PacketGiveDeed() {
		
	}
	
	public PacketGiveDeed(String name) {
		
		plotOwner = name;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		plotOwner = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
		ByteBufUtils.writeUTF8String(buf, plotOwner);
	}
}