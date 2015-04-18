package org.nationsatwar.playground.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketBuyPlot implements IMessage {
	
	public String plotOwner;
	public int plotX;
	public int plotZ;
	
	public PacketBuyPlot() {
		
	}
	
	public PacketBuyPlot(String name, int chunkX, int chunkZ) {
		
		plotOwner = name;
		plotX = chunkX;
		plotZ = chunkZ;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		plotOwner = ByteBufUtils.readUTF8String(buf);
		plotX = buf.readInt();
		plotZ = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		
		ByteBufUtils.writeUTF8String(buf, plotOwner);
		buf.writeInt(plotX);
		buf.writeInt(plotZ);
	}
}