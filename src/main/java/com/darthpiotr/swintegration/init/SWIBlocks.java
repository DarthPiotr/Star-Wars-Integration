package com.darthpiotr.swintegration.init;

import com.darthpiotr.swintegration.blocks.BlockCompressedChromium;
import com.darthpiotr.swintegration.blocks.BlockCompressedTitanium;
import com.darthpiotr.swintegration.blocks.BlockKyberGenerator;
import com.darthpiotr.swintegration.blocks.BlockKyberHolder;
import com.darthpiotr.swintegration.tileentities.TileEntityRotateBase;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class SWIBlocks {
	public static Block compressed_titanium;
	public static Block compressed_chromium;
	public static Block kyber_generator;
	public static Block kyber_holder;
	
	public static void init()
    {
        compressed_titanium = new BlockCompressedTitanium();
        compressed_chromium = new BlockCompressedChromium();
        kyber_generator = new BlockKyberGenerator();
        kyber_holder = new BlockKyberHolder();
    }
	
}