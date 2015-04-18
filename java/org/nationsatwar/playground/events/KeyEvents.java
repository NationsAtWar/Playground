package org.nationsatwar.playground.events;

import org.lwjgl.input.Keyboard;
import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.init.InitializeItems;
import org.nationsatwar.playground.proxy.ClientProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyEvents {
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (ClientProxy.plotKey.isPressed()) {
			
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;			
			player.openGui(Playground.instance, 20, player.getEntityWorld(), 0, 0, 0);
		}
	}
}