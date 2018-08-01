package com.darthpiotr.swintegration.utils;

import java.util.ArrayList;
import java.util.List;

import com.parzivail.pswm.StarWarsItems;

import net.minecraft.item.ItemStack;

public class CrystalHelper {

	public class IDs {
		public static final int RED = 0;
		public static final int GREEN = 1;
		public static final int BLUE = 2;
		public static final int BLACK = 3;
		public static final int CYAN = 4;
		public static final int GRAY = 5;
		public static final int PINK = 6;
		public static final int PURPLE = 7;
		public static final int WHITE = 8;
		public static final int YELLOW = 9;
		public static final int PRISM = 10;
	}
	
	public static String getColorStringFromStack(ItemStack stack) {
		if (stack == null || stack.getItem() != StarWarsItems.lightsaberCrystal)
			throw new IllegalArgumentException("ItemStack does not contain lightsaberCrystal!");
		else {
			switch (stack.getItemDamage()) {
			case 0:
				return "red";
			case 1:
				return "green";
			case 2:
				return "blue";
			case 3:
				return "black";
			case 4:
				return "cyan";
			case 5:
				return "gray";
			case 6:
				return "pink";
			case 7:
				return "purple";
			case 8:
				return "white";
			case 9:
				return "yellow";
			case 10:
				return "prism";
			default:
				return null;
			}
		}
	}

	public static String getTextureFromItemStack(ItemStack stack) {
		if (stack == null || stack.getItem() != StarWarsItems.lightsaberCrystal)
			throw new IllegalArgumentException("ItemStack does not contain lightsaberCrystal!");
		else {
			switch (stack.getItemDamage()) {
			case 0:
				return Reference.Textures.Colors.RED;
			case 1:
				return Reference.Textures.Colors.GREEN;
			case 2:
				return Reference.Textures.Colors.BLUE;
			case 3:
				return Reference.Textures.Colors.BLACK;
			case 4:
				return Reference.Textures.Colors.CYAN;
			case 5:
				return Reference.Textures.Colors.GRAY;
			case 6:
				return Reference.Textures.Colors.PINK;
			case 7:
				return Reference.Textures.Colors.PURPLE;
			case 8:
				return Reference.Textures.Colors.WHITE;
			case 9:
				return Reference.Textures.Colors.YELLOW;
			case 10:
				return Reference.Textures.Colors.PRISM;
			default:
				return null;
			}
		}
	}

	public static boolean isCrystalValidForSlot(ItemStack stack, int slotID) {

		if (stack == null)
			return true;

		if (stack.getItem() != StarWarsItems.lightsaberCrystal)
			return false;

		if (stack.hasTagCompound())
			return false;
		
		if(stack.getItemDamage() == CrystalHelper.IDs.RED)
			return false;

		List<Integer> validCrystals = new ArrayList<Integer>();

		switch (slotID) {
		case 0:
			validCrystals.add(CrystalHelper.IDs.BLUE);
			validCrystals.add(CrystalHelper.IDs.GREEN);
			break;
		case 1:
			validCrystals.add(CrystalHelper.IDs.YELLOW);
			validCrystals.add(CrystalHelper.IDs.CYAN);
			break;
		case 2:
			validCrystals.add(CrystalHelper.IDs.PURPLE);
			validCrystals.add(CrystalHelper.IDs.PINK);
			break;
		case 3:
			validCrystals.add(CrystalHelper.IDs.BLACK);
			validCrystals.add(CrystalHelper.IDs.GRAY);
			validCrystals.add(CrystalHelper.IDs.WHITE);
			break;
		case 4:
			validCrystals.add(CrystalHelper.IDs.PRISM);
			break;
		}

		for (int i : validCrystals) {
			if (stack.getItemDamage() == i) {
				return true;
			}
		}
		return false;
	}
}
