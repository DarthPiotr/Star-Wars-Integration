package com.darthpiotr.swintegration;

import com.darthpiotr.swintegration.blocks.BlockCompressedChromium;
import com.darthpiotr.swintegration.init.SWIBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModCreativeTab extends CreativeTabs{

	public ModCreativeTab(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return new ItemStack(SWIBlocks.compressed_chromium, 1, 0).getItem();
	}
}
