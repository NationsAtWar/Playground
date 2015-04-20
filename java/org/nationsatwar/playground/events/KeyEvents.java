package org.nationsatwar.playground.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import org.nationsatwar.playground.Playground;
import org.nationsatwar.playground.plots.PlotObject;
import org.nationsatwar.playground.proxy.ClientProxy;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class KeyEvents {
	
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		
		if (ClientProxy.plotKey.isPressed()) {
			
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;			
			player.openGui(Playground.instance, 20, player.getEntityWorld(), 0, 0, 0);

			
			try { connect(); }
			catch (Exception e) { System.out.println(e); }
		}
	}
	
	public void connect() throws Exception {
		
		String databaseUrl = "jdbc:mysql://localhost:3306/test?user=root&password=mountdew4";
		
		// create a connection source to our database
		ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
		
		// instantiate the dao
		Dao<PlotObject, String> accountDao = DaoManager.createDao(connectionSource, PlotObject.class);
		
		// if you need to create the 'accounts' table make this call
		TableUtils.createTable(connectionSource, PlotObject.class);
		
		connectionSource.close();
    }
}