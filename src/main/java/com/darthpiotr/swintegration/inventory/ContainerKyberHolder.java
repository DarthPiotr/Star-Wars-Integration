package com.darthpiotr.swintegration.inventory;

import java.util.List;

import com.darthpiotr.swintegration.network.MessageKyberGenerator;
import com.darthpiotr.swintegration.network.NetworkHandler;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberHolder;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerKyberHolder extends Container {

	boolean lastHasCrystal;
	TileEntityKyberHolder te;
	
	public ContainerKyberHolder(InventoryPlayer invPlayer, TileEntityKyberHolder te) {
		this.te = te;
		
		// Kyber Slot
		addSlotToContainer(new SlotKyber(te, 0, 15, 35, 64));

		// Inventory
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		// Hotbar
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw)
    {
		ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotRaw);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotRaw < this.te.getSizeInventory())
            {
                if (!this.mergeItemStack(itemstack1, this.te.getSizeInventory(), this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.te.getSizeInventory(), false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return te.isUseableByPlayer(player);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (lastHasCrystal != te.hasCrystal()) {
			for (ICrafting ic : (List<ICrafting>) this.crafters) {
				if (ic instanceof EntityPlayer) {
					ic.sendProgressBarUpdate(this, Reference.GuiID.KYBER_HOLDER.ordinal(), (te.hasCrystal() ? 1 : 0));
				}
			}
			lastHasCrystal = te.hasCrystal();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
	}

}
