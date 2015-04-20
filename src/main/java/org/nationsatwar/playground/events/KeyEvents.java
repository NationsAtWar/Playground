package org.nationsatwar.playground.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.proxy.ClientProxy;

public class KeyEvents {
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (ClientProxy.plotKey.isPressed()) {
			
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;			
			player.openGui(Playground.instance, 20, player.getEntityWorld(), 0, 0, 0);
		}
	}
}