package com.darthpiotr.swintegration.init;

import org.apache.logging.log4j.Logger;

import com.darthpiotr.swintegration.config.ConfigurationHandler;
import com.parzivail.pswm.StarWarsMod;
import com.parzivail.pswm.blocks.BlockCrystalOre;
import com.parzivail.pswm.items.ItemCrystalOreBlock;

import cpw.mods.fml.common.FMLLog;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.core.IC2;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModValuableBlocks {

	public static Logger log = FMLLog.getLogger();

	public static void init() {
		int value = 1;

		if (ConfigurationHandler.enableCrystalScanner) {
			IC2.addValuableOre(StarWarsMod.blockFocusingCrystalOre, value);

			for (int i = 0; i < 10; i++) {
				IC2.addValuableOre(new RecipeInputItemStack(new ItemStack(StarWarsMod.blockCrystalOre, 1, i)), value);
			}
		}

		if (ConfigurationHandler.enableOresScanner) {
			IC2.addValuableOre(StarWarsMod.blockChromiumOre, value);
			IC2.addValuableOre(StarWarsMod.blockCortosisOre, value);
			IC2.addValuableOre(StarWarsMod.blockDiatiumOre, value);
			IC2.addValuableOre(StarWarsMod.blockExoniumOre, value);
			IC2.addValuableOre(StarWarsMod.blockHeliciteOre, value);
			IC2.addValuableOre(StarWarsMod.blockIoniteOre, value);
			IC2.addValuableOre(StarWarsMod.blockKeleriumOre, value);
			IC2.addValuableOre(StarWarsMod.blockRubindumOre, value);
			IC2.addValuableOre(StarWarsMod.blockThorolideOre, value);
			IC2.addValuableOre(StarWarsMod.blockTitaniumOre, value);
		}

	}

}