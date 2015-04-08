package org.nationsatwar.playground.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.blocks.BlockTest;

public class TutorialBlocks {
	
	public static Block test_block;
	
	public static void init() {
		
		test_block = new BlockTest(Material.cloth).setUnlocalizedName("test_block");
	}
	
	public static void register() {
		
		GameRegistry.registerBlock(test_block, test_block.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders() {
		
		registerRender(test_block);
	}
	
	public static void registerRender(Block block) {
		
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Playground.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}