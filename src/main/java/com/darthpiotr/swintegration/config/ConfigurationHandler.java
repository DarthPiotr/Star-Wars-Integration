package com.darthpiotr.swintegration.config;

import java.io.File;

import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;
import com.google.common.io.Resources;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationHandler {

	static Configuration configuration;

	public static boolean enableCrystalRecipes = false;
	
	public static boolean renderCrystalsInGenerator = true;
	public static boolean renderCrystalsInHolder = true;
	public static boolean animateCrystalsInHolder = true;
	
	public static boolean enableCrystalScanner = false;
	public static boolean enableOresScanner = true;
	
	/*
	public static boolean useConfigRender = true;
	
	public static double rot = 0D;
	public static double rx = 0D;
	public static double ry = 0D;
	public static double rz = 1D;
	
	public static double tx = 0.5;
	public static double ty = 1.5;
	public static double tz = 0.5;
*/
	
	public static void init(File configFile) {

		if (configuration == null) {
			configuration = new Configuration(configFile);
			loadConfig();
		}	
	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {

		if (event.modID.equalsIgnoreCase(Reference.Mod.MOD_ID)) {
			loadConfig();
		}
	}

	public static void loadConfig() {
		
		// CRAFTING
		configuration.getCategory(Reference.Config.CATEGORY_CRAFTING).setRequiresMcRestart(true).setComment("Craftings options");
		enableCrystalRecipes = configuration
				.get(Reference.Config.CATEGORY_CRAFTING, "Enable Crystal Crafting", false, "Enables crafting recipes for kyber crystals")
				.setShowInGui(true).setRequiresMcRestart(true).getBoolean();
		
		
		// RENDERING
		configuration.getCategory(Reference.Config.CATEGORY_RENDER).setRequiresMcRestart(false).setComment("Rendering settings");
		
		renderCrystalsInGenerator = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Render crystals in kyber generator", true, "If crystals should be rendered inside kyber generator. Disable for performance purposes.")
				.setShowInGui(true).setRequiresMcRestart(false).getBoolean();
		
		renderCrystalsInHolder = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Render crystals in kyber holder", true, "If crystals should be rendered in kyber holder. Disable for performance purposes.")
				.setShowInGui(true).setRequiresMcRestart(false).getBoolean();
		
		animateCrystalsInHolder = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Animate crystals in kyber holder", true, "If crystals should be animated in kyber holder. Disable for performance purposes.")
				.setShowInGui(true).setRequiresMcRestart(false).getBoolean();
		
		// SCANNER
		configuration.getCategory(Reference.Config.CATEGORY_SCANNER).setRequiresMcRestart(true).setComment("Additional ores for IC2 OD & OV Scanner");
		
		enableOresScanner = configuration
				.get(Reference.Config.CATEGORY_SCANNER, "Enable PSWM ores", true, "Enable finding PSWM ores with OD/OV scanner")
				.setShowInGui(true).setRequiresMcRestart(true).getBoolean();
		
		enableCrystalScanner = configuration
				.get(Reference.Config.CATEGORY_SCANNER, "Enable kyber crystals", false, "Enable finding kyber crystals with OD/OV scanner")
				.setShowInGui(true).setRequiresMcRestart(true).getBoolean();
		
		/*
		useConfigRender = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Use Config Render Values", true, "Enable for testing purposes")
				.setShowInGui(true).setRequiresMcRestart(false).getBoolean();
		
		rot = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Rotation", 0.0, "Rotation")
				.setShowInGui(true).setRequiresMcRestart(false).getDouble();
		rx = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Rotation X", 0.1, "Rotation X")
				.setShowInGui(true).setRequiresMcRestart(false).getDouble();
		ry = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Rotation Y", 0.1, "Rotation Y")
				.setShowInGui(true).setRequiresMcRestart(false).getDouble();
		rz = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Rotation Z", 1.1, "Rotation Z")
				.setShowInGui(true).setRequiresMcRestart(false).getDouble();
		tx = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Translation X", 0.5, "Translation X")
				.setShowInGui(true).setRequiresMcRestart(false).getDouble();
		ty = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Translation Y", 1.5, "Translation Y")
				.setShowInGui(true).setRequiresMcRestart(false).getDouble();
		tz = configuration
				.get(Reference.Config.CATEGORY_RENDER, "Translation Z", 0.5, "Translation Z")
				.setShowInGui(true).setRequiresMcRestart(false).getDouble();
		*/

		if (configuration.hasChanged())
			configuration.save();
	}
}