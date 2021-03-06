package org.nationsatwar.playground;

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
import org.nationsatwar.playground.events.KeyEvents;
import org.nationsatwar.playground.events.PlotProtectionEvents;
import org.nationsatwar.playground.gui.GUIHandler;
import org.nationsatwar.playground.init.InitializeItems;
import org.nationsatwar.playground.packets.PacketBuyPlot;
import org.nationsatwar.playground.packets.PacketGiveDeed;
import org.nationsatwar.playground.packets.PacketHandlerBuyPlot;
import org.nationsatwar.playground.packets.PacketHandlerGiveDeed;
import org.nationsatwar.playground.plots.PlotManager;
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
	
	public static SimpleNetworkWrapper channel;
	
	PlotProtectionEvents handler = new PlotProtectionEvents();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		FMLCommonHandler.instance().bus().register(handler);
		MinecraftForge.EVENT_BUS.register(handler);
		
		InitializeItems.init();
		InitializeItems.register();
		
		ConfigurationHandler.reloadConfig(event.getSuggestedConfigurationFile());
		
		// Packet Registration
		channel = NetworkRegistry.INSTANCE.newSimpleChannel(Playground.MODID);
		channel.registerMessage(PacketHandlerBuyPlot.class, PacketBuyPlot.class, 1, Side.SERVER);
		channel.registerMessage(PacketHandlerGiveDeed.class, PacketGiveDeed.class, 2, Side.SERVER);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		proxy.registerRenders();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
		
		proxy.registerKeybindings();
		FMLCommonHandler.instance().bus().register(new KeyEvents());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		PlotManager.loadPlots();
	}
}