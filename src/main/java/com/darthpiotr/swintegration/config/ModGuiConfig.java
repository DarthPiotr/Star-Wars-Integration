package com.darthpiotr.swintegration.config;

import java.util.ArrayList;
import java.util.List;

import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen guiScreen) {
		super(guiScreen,
				getConfigElements(),
				Reference.Mod.MOD_ID, false, true,
				"Star Wars Integration");
		this.titleLine2 = GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString());
	}

	private static List<IConfigElement> getConfigElements() {
		List<IConfigElement> list = new ArrayList();
		
		list.add(new ConfigElement(ConfigurationHandler.configuration.getCategory(Reference.Config.CATEGORY_CRAFTING)));
		list.add(new ConfigElement(ConfigurationHandler.configuration.getCategory(Reference.Config.CATEGORY_RENDER)));		
		list.add(new ConfigElement(ConfigurationHandler.configuration.getCategory(Reference.Config.CATEGORY_SCANNER)));		
		
		return list;
	}
}