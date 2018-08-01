package com.darthpiotr.swintegration.init;

import com.darthpiotr.swintegration.items.ItemGeneratorCircuit;
import com.darthpiotr.swintegration.items.ItemGeneratorCore;
import com.darthpiotr.swintegration.items.ItemGeneratorPlate;
import com.darthpiotr.swintegration.items.ItemKyberPlace;
import com.darthpiotr.swintegration.items.ItemLightsaberCrystalBlend;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class SWIItems {
	
	public static ItemLightsaberCrystalBlend lightsaber_crystal_blend;
	public static ItemKyberPlace kyber_place;
	public static ItemGeneratorPlate generator_plate;
	public static ItemGeneratorCircuit generator_circuit;
	public static ItemGeneratorCore generator_core;
	
    public static void init()
    {
    	lightsaber_crystal_blend = new ItemLightsaberCrystalBlend();
    	GameRegistry.registerItem(lightsaber_crystal_blend, lightsaber_crystal_blend.name);
    	
    	kyber_place = new ItemKyberPlace();
    	GameRegistry.registerItem(kyber_place, kyber_place.name);
    	
    	generator_plate = new ItemGeneratorPlate();
    	GameRegistry.registerItem(generator_plate, generator_plate.name);
    	
    	generator_circuit = new ItemGeneratorCircuit();
    	GameRegistry.registerItem(generator_circuit, generator_circuit.name);
    	
    	generator_core = new ItemGeneratorCore();
    	GameRegistry.registerItem(generator_core, generator_core.name);
    }
}
