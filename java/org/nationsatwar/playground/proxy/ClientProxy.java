package org.nationsatwar.playground.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;
import org.nationsatwar.playground.init.InitializeItems;
import org.nationsatwar.playground.utility.KeyBindings;

public class ClientProxy extends CommonProxy {
	
	public static KeyBinding plotKey;
	
	@Override
	public void registerRenders() {
		
		InitializeItems.registerRenders();
	}
	
	@Override
	public void registerKeybindings() {
		
		plotKey = KeyBindings.registerKey(Keyboard.KEY_P, "plotKey");
	}
}