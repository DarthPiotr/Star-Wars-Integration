package com.darthpiotr.swintegration.init;

import com.parzivail.pswm.StarWarsItems;

import org.apache.logging.log4j.Logger;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.config.ConfigurationHandler;
import com.parzivail.pswm.*;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {
	public static Logger log = FMLLog.getLogger();
	
	public static void preInit() {
		GameRegistry.addShapedRecipe(new ItemStack(Items.saddle), "L  ", "LLL", " I ", 'L', Items.leather, 'I', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(Items.name_tag, 2), "  S", " P ", "P  ", 'S', Items.string, 'P', Items.paper);
	}
	
	//here goes every recipe that contains reference to StarWars*
	public static void init() {
		
		
		//  shapeless
		GameRegistry.addShapelessRecipe(new ItemStack(StarWarsMod.blockHothSandbag), Blocks.quartz_block, new ItemStack(StarWarsMod.blockDeathStarBlock, 1, 3));
		GameRegistry.addShapelessRecipe(new ItemStack(StarWarsMod.blockDeathStarLight, 1, 2), Blocks.quartz_block, Blocks.torch);
		
		//  shaped
		
		// with my items
		GameRegistry.addShapedRecipe(new ItemStack(SWIBlocks.compressed_titanium), "XXX", "XXX", "XXX", 'X', StarWarsItems.titaniumDust);
		GameRegistry.addShapelessRecipe(new ItemStack(StarWarsItems.titaniumDust, 9), SWIBlocks.compressed_titanium);
		
		GameRegistry.addShapedRecipe(new ItemStack(SWIBlocks.compressed_chromium), "XXX", "XXX", "XXX", 'X', StarWarsItems.chromiumDust);
		GameRegistry.addShapelessRecipe(new ItemStack(StarWarsItems.chromiumDust, 9), SWIBlocks.compressed_chromium);
		
		GameRegistry.addShapedRecipe(new ItemStack(SWIItems.kyber_place), "I I", "CIF", 'I', StarWarsItems.titaniumChromiumIngot, 'C', Ic2Items.electronicCircuit, 'F', StarWarsItems.focusingCrystal);
		GameRegistry.addShapedRecipe(new ItemStack(SWIItems.generator_plate), "PXP", "XPX", "PXP", 'P', SWIItems.kyber_place, 'X', Ic2Items.advancedAlloy);
		GameRegistry.addShapedRecipe(new ItemStack(SWIItems.generator_circuit), "IAI", "FMG", "IAI", 'I', Ic2Items.iridiumPlate, 'A', Ic2Items.advancedCircuit, 'G', StarWarsItems.energyGate, 'M', StarWarsItems.energyModulationCircuit, 'F', StarWarsItems.cyclingFieldEnergizer);
		GameRegistry.addShapedRecipe(new ItemStack(SWIItems.generator_core), "PCI", "TMX", "PGI", 'P', Ic2Items.iridiumPlate, 'G', Ic2Items.glassFiberCableItem, 'I', Ic2Items.casingiron, 'T', Ic2Items.evTransformer, 'C', SWIItems.generator_circuit, 'M', Ic2Items.mfsUnit, 'X', SWIItems.generator_plate);
		GameRegistry.addShapedRecipe(new ItemStack(SWIBlocks.kyber_generator), "PGP", "GCG", "PGP", 'P', Ic2Items.denseplateadviron, 'G', new ItemStack(Blocks.stained_glass_pane, 1, 15), 'C', SWIItems.generator_core);
		
		GameRegistry.addShapedRecipe(new ItemStack(SWIBlocks.kyber_holder), " P ", "ICI", 'I', Ic2Items.casingiron, 'C', Ic2Items.electronicCircuit, 'P', SWIItems.kyber_place);
		
		
		//items
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsItems.spawnSkyhopper), " S ", "CBC", "SRS", 'S', Ic2Items.ironrotorblade, 'C', Ic2Items.electronicCircuit, 'B', Ic2Items.machine, 'R', Items.redstone);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsItems.powerpack, 3), "GGG", "RRR", " C ", 'G', Items.gunpowder, 'R', Items.redstone, 'C', Ic2Items.electronicCircuit);
		
		//blocks
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockDeathStarDoor), "SSS", "P P", "SSS", 'S', Blocks.stone, 'P', Blocks.piston);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockDoorHoth), "QPQ", "Q Q", "QQQ", 'Q', Blocks.quartz_block, 'P', Blocks.piston);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockDeathStarGlass, 8), "GGG", "GCG", "GGG", 'G', Blocks.glass, 'C', Ic2Items.casingiron);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockDeathStarLightStairs), "B  ", "BB ", "BBB", 'B', new ItemStack(StarWarsMod.blockDeathStarBlock, 1, 4));
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockMedicalConsole), "ICI", " I ", " I ", 'I', Items.iron_ingot, 'C', Ic2Items.electronicCircuit);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockMedicalConsole2), "III", "ICI", "I I", 'I', Items.iron_ingot, 'C', Ic2Items.electronicCircuit);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockDeathStarLightFence, 4), "XIX", "XIX", 'X', new ItemStack(StarWarsMod.blockDeathStarBlock, 1, 4), 'I', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockGunRack), "III", "I I", "SSS", 'I', Items.iron_ingot, 'S', Blocks.stone_slab);
		
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockFloorLight), " T ", "III", 'T', Blocks.torch, 'I', Items.iron_ingot);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockFloorLight2), "TIT", "III", 'T', Blocks.torch, 'I', Items.iron_ingot);
		
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockHothCeilingLight), "IGI", 'I', Items.iron_ingot, 'G', Items.glowstone_dust);
		GameRegistry.addShapedRecipe(new ItemStack(StarWarsMod.blockHothCeilingLight2), " S ", "IGI", 'I', Items.iron_ingot, 'G', Items.glowstone_dust, 'S', Items.string);
		
		// with ore dictionary
		//items
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsItems.imperialCredit, 16), "XX", "XX", 'X', "ingotBronze"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsItems.spawnSpeederBike), " SD", "BAM", " RC", 'C', Ic2Items.electronicCircuit, 'B', Ic2Items.ironrotorblade, 'A', Items.saddle, 'D', "dyeBlack", 'S', Items.string, 'M', Ic2Items.machine, 'R', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsItems.spawnHothSpeederBike), " SD", "BAM", " RC", 'C', Ic2Items.electronicCircuit, 'B', Ic2Items.ironrotorblade, 'A', Items.saddle, 'D', "dyeWhite", 'S', Items.string, 'M', Ic2Items.machine, 'R', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsItems.spawnMouse), " B ", "IRI", 'B', "dyeBlack", 'I', Items.iron_ingot, 'R', Items.redstone));
		
		//blocks
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockHyperdrive), "qLq", "QCQ", "QQQ", 'Q', Blocks.quartz_block, 'q', Items.quartz, 'C', Ic2Items.electronicCircuit, 'L', "dyeBlue"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockLadder, 8), "LLL", "LYL", "LLL", 'L', Blocks.ladder, 'Y', "dyeYellow"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockFieldEmitter), "qLq", "qTq", "qQq", 'q', Items.quartz, 'T', Blocks.torch, 'Q', Blocks.quartz_block, 'L', "dyeBlue"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockBactaTank), "QqQ", "LWL", "QQQ", 'Q', Blocks.quartz_block, 'q', Items.quartz, 'L', "dyeBlue", 'W', StarWarsItems.waterDroplet));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockHoloTable), "DCD", "qMq", "QQQ", 'D', "dyeBlue", 'C', Ic2Items.electronicCircuit, 'q', Items.quartz, 'M', Ic2Items.machine, 'Q', Blocks.quartz_block));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockHoloTable, 1, 1), "DDD", "qHq", "QQQ", 'D', "dyeBlue", 'q', Items.quartz, 'H', new ItemStack(StarWarsMod.blockHoloTable), 'Q', Blocks.quartz_block));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockHoloTable, 1, 2), "DDD", "qHq", "QQQ", 'D', "dyeBlue", 'q', Items.quartz, 'H', new ItemStack(StarWarsMod.blockHoloTable, 1, 1), 'Q', Blocks.quartz_block));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockHoloTable, 1, 3), "DDD", "qHq", "QQQ", 'D', "dyeBlue", 'q', Items.quartz, 'H', new ItemStack(StarWarsMod.blockHoloTable, 1, 2), 'Q', Blocks.quartz_block));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockHolotableMass), "GWR", "qMq", "QQQ", 'G', "dyeGreen", 'W', "dyeWhite", 'R', "dyeRed", 'C', Ic2Items.electronicCircuit, 'q', Items.quartz, 'M', Ic2Items.machine, 'Q', Blocks.quartz_block));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockDeathStarBlock, 8, 3), "SSS", "SYS", "SSS", 'S', Blocks.stone, 'Y', "dyeYellow"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockDeathStarBlock, 8, 4), "SSS", "SDS", "SSS", 'S', Blocks.stone, 'D', "dyeLightGray"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockDeathStarBlock, 8, 5), "SSS", "SDS", "SSS", 'S', Blocks.stone, 'D', "dyeGray"));
	
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockDeathStarLight, 6, 0), "SLS", "SDS", "SLS", 'S', Blocks.stone, 'D', "dyeGray", 'L', new ItemStack(StarWarsMod.blockDeathStarLight, 1, 2)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockDeathStarLight, 6, 1), "SLS", "SDS", "SLS", 'S', Blocks.stone, 'D', "dyeLightGray", 'L', new ItemStack(StarWarsMod.blockDeathStarLight, 1, 2)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockDeathStarLight, 6, 3), "SSS", "LDL", "SSS", 'S', Blocks.stone, 'D', "dyeGray", 'L', new ItemStack(StarWarsMod.blockDeathStarLight, 1, 2)));
	
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockConsoleHoth1), "III", "DCG", "I I", 'I', Items.iron_ingot, 'D', "dyeGray", 'C', Ic2Items.electronicCircuit, 'G', Items.glowstone_dust));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockConsoleHoth2), "III", "G D", "I I", 'I', Items.iron_ingot, 'D', "dyeGray", 'G', Items.glowstone_dust));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(StarWarsMod.blockConsoleHoth3), "IGI", "D I", "I I", 'I', Items.iron_ingot, 'D', "dyeGray", 'G', Items.glowstone_dust));
	
		// test
		if(ConfigurationHandler.enableCrystalRecipes) {
			String[] dyes = {"Red", "Green", "Blue", "Black", "LightBlue", "Gray", "Pink", "Purple", "White", "Yellow"};
			Object[] crystalsItemStacks = new Object[9];
			
			for(int i = 0; i < dyes.length; ++i) {
				if(i != 0)crystalsItemStacks[i-1] = new ItemStack(SWIItems.lightsaber_crystal_blend, 1, i);
				log.info("Adding crafting recipe for " + dyes[i] + " crystal blend");
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SWIItems.lightsaber_crystal_blend, 1, i), "dGd", "GDG", "dGd", 'd', "dustDiamond", 'G', Blocks.glass, 'D', "dye"+dyes[i]));
				log.info("Adding compressor recipe for " + dyes[i] + " kyber crystal");
				
				ItemStack output = new ItemStack(StarWarsItems.lightsaberCrystal, 1, i);
				output.setTagCompound(new NBTTagCompound());
				output.stackTagCompound.setBoolean("synthetic", true);
				Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(SWIItems.lightsaber_crystal_blend, 1, i)), new NBTTagCompound(), output);
			}
			log.info("Adding crafting recipe for prism crystal blend");
			GameRegistry.addShapelessRecipe(new ItemStack(SWIItems.lightsaber_crystal_blend, 1, 10), crystalsItemStacks);
			log.info("Adding compressor recipe for prism kyber crystal");
			Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(SWIItems.lightsaber_crystal_blend, 1, 10)), new NBTTagCompound(), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 10));
		
		}
		
		//  smelting
		
		// focusing
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockFocusingCrystalOre), new ItemStack(StarWarsItems.focusingCrystal), 0.0F);
		
		//black
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 0), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 3), 0.0F);
		//blue
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 1), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 2), 0.0F);
		//cyan
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 2), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 4), 0.0F);
		//gray
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 3), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 5), 0.0F);
		//green
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 4), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 1), 0.0F);
		//pink
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 5), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 6), 0.0F);
		//purple
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 6), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 7), 0.0F);
		//white
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 7), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 8), 0.0F);
		//yellow
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 8), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 9), 0.0F);
		//prism
		GameRegistry.addSmelting(new ItemStack(StarWarsMod.blockCrystalOre, 1, 9), new ItemStack(StarWarsItems.lightsaberCrystal, 1, 10), 0.0F);
		
		
	}
}
