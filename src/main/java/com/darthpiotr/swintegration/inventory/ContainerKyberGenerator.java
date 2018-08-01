package com.darthpiotr.swintegration.inventory;

import java.util.List;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.network.MessageKyberGeneratorCrystal;
import com.darthpiotr.swintegration.network.MessageKyberGenerator;
import com.darthpiotr.swintegration.network.NetworkHandler;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class ContainerKyberGenerator extends Container {

	private int lastCapacity = -1;
	private ItemStack lastCrystal = null;
	private TileEntityKyberGenerator te;

	public ContainerKyberGenerator(InventoryPlayer invPlayer, TileEntityKyberGenerator te) {
		this.te = te;

		short slotID = 0;
		
		// Kyber Slots
		addSlotToContainer(new SlotKyber(te, slotID++, 24, 32));
		addSlotToContainer(new SlotKyber(te, slotID++, 10, 10));
		addSlotToContainer(new SlotKyber(te, slotID++, 38, 10));
		addSlotToContainer(new SlotKyber(te, slotID++, 10, 54));
		addSlotToContainer(new SlotKyber(te, slotID++, 38, 54));

		// Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 78 + i * 18));
			}
		}
		// Hotbar
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 136));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw) {
		ItemStack stack = null;
		Slot slot = (Slot) inventorySlots.get(slotRaw);

		if (slot != null && slot.getHasStack()) {
			ItemStack stackInSlot = slot.getStack();
			stack = stackInSlot.copy();

			if (slotRaw < 3 * 9) {
				if (!mergeItemStack(stackInSlot, 3 * 9, inventorySlots.size(), true)) {
					return null;
				}
			} else if (!mergeItemStack(stackInSlot, 0, 3 * 9, false)) {
				return null;
			}

			if (stackInSlot.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return te.isUseableByPlayer(player);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (lastCapacity != (int) te.getEnergySource().getEnergyStored()) {
			for (ICrafting ic : (List<ICrafting>) this.crafters) {
				if (ic instanceof EntityPlayer) {
					ic.sendProgressBarUpdate(this, Reference.GuiID.KYBER_GENERATOR.ordinal(),
							(int) te.getEnergySource().getEnergyStored());
					NetworkHandler.sendTo(new MessageKyberGenerator(te), (EntityPlayerMP) ic);
				}
			}
			lastCapacity = (int) te.getEnergySource().getEnergyStored();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
	}
}
