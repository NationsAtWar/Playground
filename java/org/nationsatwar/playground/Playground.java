package org.nationsatwar.playground;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import org.nationsatwar.playground.configuration.ConfigurationHandler;
import org.nationsatwar.playground.init.TutorialBlocks;
import org.nationsatwar.playground.init.PGItems;
import org.nationsatwar.playground.proxy.CommonProxy;
 
@Mod(modid = Playground.MODID, name = Playground.MODNAME, version = Playground.MODVER)
public class Playground {

    @Instance(Playground.MODID)
    public static Playground instance;

	@SidedProxy(clientSide = Playground.CLIENT_PROXY_CLASS, serverSide = Playground.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final String MODID = "playground";
	public static final String MODNAME = "Playground";
	public static final String MODVER = "0.0.1";
	
	public static final String CLIENT_PROXY_CLASS = "org.nationsatwar.playground.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "org.nationsatwar.playground.proxy.CommonProxy";
	
	public static SimpleNetworkWrapper playgroundChannel;

	// <Key: plotOwner | Value: <Key: plotID | Value: int[0] = plotX, int[1] = plotZ>>
	public static Map<String, Map<Integer, int[]>> plotKeys = new HashMap<String, Map<Integer, int[]>>();
	
	TutorialEventHandler handler = new TutorialEventHandler();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		FMLCommonHandler.instance().bus().register(handler);
		MinecraftForge.EVENT_BUS.register(handler);
		
		TutorialBlocks.init();
		TutorialBlocks.register();
		PGItems.init();
		
		PGItems.register();
		
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerRenders();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
		playgroundChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Playground.MODID);
		playgroundChannel.registerMessage(TestPacketHandler.class, TestPacket.class, 1, Side.SERVER);
		
		proxy.registerKeybindings();
		FMLCommonHandler.instance().bus().register(new KeyEvents());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}