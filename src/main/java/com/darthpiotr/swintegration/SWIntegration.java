package com.darthpiotr.swintegration;

import org.apache.logging.log4j.Logger;

import com.darthpiotr.swintegration.commands.CommandRender;
import com.darthpiotr.swintegration.config.ConfigurationHandler;
import com.darthpiotr.swintegration.gui.GuiKyberGenerator;
import com.darthpiotr.swintegration.handler.SWIGuiHandler;
import com.darthpiotr.swintegration.init.SWIBlocks;
import com.darthpiotr.swintegration.init.ModHandler;
import com.darthpiotr.swintegration.init.SWIItems;
import com.darthpiotr.swintegration.init.ModRecipes;
import com.darthpiotr.swintegration.init.ModValuableBlocks;
import com.darthpiotr.swintegration.proxy.CommonProxy;
import com.darthpiotr.swintegration.proxy.IProxy;
import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;
import com.parzivail.pswm.StarWarsItems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid = Reference.Mod.MOD_ID,
name = Reference.Mod.NAME,
version = Reference.Mod.VERSION,
dependencies = Reference.Mod.DEPENDENCIES,
guiFactory = Reference.Mod.CONFIG_GUIFACTORY)

public class SWIntegration {


	public static ModCreativeTab SWITab = new ModCreativeTab("Star Wars Integration");

	@SidedProxy(clientSide = Reference.Mod.CLIENT_PROXY, serverSide = Reference.Mod.SERVER_PROXY)
	public static CommonProxy proxy;

	@Mod.Instance(Reference.Mod.MOD_ID)
	public static SWIntegration instance;
/*
	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandRender());
	}
	*/
	//sth
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LogHelper.info("[SWI]: --------- PreInit ---------");
		
		LogHelper.info("[SWI]: Getting settings from config...");
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		LogHelper.info("[SWI]: Proxy preInit...");
		proxy.preInit();
		
		LogHelper.info("[SWI]: Registering handlers...");
		ModHandler.registerHandlers();
		
		LogHelper.info("[SWI]: PreInitializing recipes...");
		ModRecipes.preInit();
		
		LogHelper.info("[SWI]: --- PreInit successful! ---");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LogHelper.info("[SWI]: ---------- Init -----------");
		SWIBlocks.init();
		SWIItems.init();
		ModRecipes.init();
		ModValuableBlocks.init();
		LogHelper.info("[SWI]: ----- Init successful! ----");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
