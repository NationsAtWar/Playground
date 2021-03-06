package org.nationsatwar.playground.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.items.PGItemPlotDeed;

public class InitializeItems {
	
	public static Item plot_deed;
	
	public static void init() {
		
		plot_deed = new PGItemPlotDeed();
	}
	
	public static void register() {
		
		GameRegistry.registerItem(plot_deed, "plot_deed");
	}
	
	public static void registerRenders() {
		
		registerRender(plot_deed);
	}
	
	public static void registerRender(Item item) {
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Playground.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}