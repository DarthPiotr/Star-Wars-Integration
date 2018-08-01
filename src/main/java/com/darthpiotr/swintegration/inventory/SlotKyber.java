package com.darthpiotr.swintegration.inventory;

import java.util.ArrayList;
import java.util.List;

import com.darthpiotr.swintegration.utils.CrystalHelper;
import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;
import com.parzivail.pswm.StarWarsItems;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SlotKyber extends Slot {

	private int stackLimit = 1;
	
	public SlotKyber(IInventory inv, int id, int xPos, int yPos) {
		super(inv, id, xPos, yPos);
	}
	
	public SlotKyber(IInventory inv, int id, int xPos, int yPos, int stackLimit) {
		super(inv, id, xPos, yPos);
		this.stackLimit = stackLimit;
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		if (itemstack != null) {
			if (itemstack.getItem() == StarWarsItems.lightsaberCrystal && itemstack.getItemDamage() < 11) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getSlotStackLimit() {
		return stackLimit;
	}
}
