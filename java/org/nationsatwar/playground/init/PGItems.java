package org.nationsatwar.playground.init;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.items.PGItemPlotDeed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PGItems {
	
	public static ToolMaterial TUTORIAL = EnumHelper.addToolMaterial("TUTORIAL", 3, 59, 12.0F, 0.0F, 22);
	
	public static Item plot_deed;
	
	public static void init() {
		
		plot_deed = new PGItemPlotDeed(1, true);
	}
	
	public static void register() {
		
		GameRegistry.registerItem(plot_deed, "plot_deed");
	}
	
	public static void registerRenders() {
		
		registerRender(plot_deed);
	}
	
	public static void registerRender(Item item) {
		
		System.out.println(item.getUnlocalizedName());
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Playground.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}