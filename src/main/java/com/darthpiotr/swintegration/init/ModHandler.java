package com.darthpiotr.swintegration.init;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.config.ConfigurationHandler;
import com.darthpiotr.swintegration.handler.SWIGuiHandler;
import com.darthpiotr.swintegration.network.NetworkHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ModHandler {
	public static void registerHandlers() {
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler()); 
		NetworkRegistry.INSTANCE.registerGuiHandler(SWIntegration.instance, new SWIGuiHandler());
		NetworkHandler.init();
	}
}
