package com.darthpiotr.swintegration.tileentities;

import com.darthpiotr.swintegration.energy.KyberGeneratorEnergySource;
import com.darthpiotr.swintegration.gui.GuiKyberGenerator;
import com.darthpiotr.swintegration.inventory.ContainerKyberGenerator;
import com.darthpiotr.swintegration.network.MessageExplosion;
import com.darthpiotr.swintegration.network.MessageKyberGenerator;
import com.darthpiotr.swintegration.network.NetworkHandler;
import com.darthpiotr.swintegration.utils.CrystalHelper;
import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;
import com.parzivail.pswm.StarWarsItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class TileEntityKyberGenerator extends TileEntityCrystalBase implements IInventory {

	public TileEntityKyberGenerator() {
		super(5);
	}

	private boolean alreadyRendered = false;

	private KyberGeneratorEnergySource energySource = new KyberGeneratorEnergySource(this, 40000000, 1);

	@Override
	public void invalidate() {
		energySource.invalidate(); // notify the energy source
		super.invalidate(); // this is important for mc!
	}

	@Override
	public void onChunkUnload() {
		energySource.onChunkUnload(); // notify the energy source
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		// LogHelper.info("readFromNBT: " + tag.toString());
		super.readFromNBT(tag);

		this.setFacing(tag.getShort("facing"));
		//LogHelper.info("ReadFromNBT: " + tag.toString());

		energySource.readFromNBT(tag.getCompoundTag("energy"));

		NBTTagCompound newNBT = new NBTTagCompound();
		for (int i = 0; i < crystals.length; ++i) {
			NBTTagCompound crystNbt = tag.getCompoundTag("crystals").getCompoundTag(String.valueOf(i));
			if (crystNbt != new NBTTagCompound()) {
				crystals[i] = ItemStack.loadItemStackFromNBT(crystNbt);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setShort("facing", getFacing());

		NBTTagCompound newNBT = new NBTTagCompound();
		for (int i = 0; i < crystals.length; ++i) {
			ItemStack stack = crystals[i];
			if (stack != null) {
				NBTTagCompound stackNbt = new NBTTagCompound();
				stack.writeToNBT(stackNbt);
				newNBT.setTag(String.valueOf(i), stackNbt);
			}
		}
		tag.setTag("crystals", newNBT);

		newNBT = new NBTTagCompound();
		energySource.writeToNBT(newNBT);
		tag.setTag("energy", newNBT);

		//LogHelper.info("WriteToNBT: " + tag.toString());
	}

	@Override
	public void updateEntity() {
		energySource.updateEntity(); // notify the energy source

		energySource.setTier(getPresentCrystalAmount());
		energySource.setCapacityByTier(energySource.getTier());

		if (getPresentCrystalAmount() > 0) {
			energySource.addEnergy(EnergyNet.instance.getPowerFromTier(energySource.getTier()) * 2);
		} else
			energySource.setEnergyStored(0);
	}
	
	@Override
	public void setFacing(short facing) {
		energySource.removeFromEnet();
		super.setFacing(facing);
		energySource.addToEnet();
	}

	public int getPresentCrystalAmount() {
		int counter = 0;
		// if(crystals != null) {
		for (int i = 0; i < crystals.length; ++i)
			if (crystals[i] != null) {
				if (crystals[i].stackSize > 0)
					++counter;
			}
		// }
		return counter;
	}

	public void updateEnergySource(NBTTagCompound tag) {
		energySource.readFromNBT(tag);
	}	

	public KyberGeneratorEnergySource getEnergySource() {
		return energySource;
	}

	@Override
	public String getInventoryName() {
		return "container.kyberGenerator";
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false
				: player.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public int getSizeInventory() {

		return crystals.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return crystals[slot];
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (crystals[slot] != null) {
			ItemStack itemstack = crystals[slot];
			crystals[slot] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		if (isItemValidForSlot(slot, stack)) {
			crystals[slot] = stack;
			if(stack != null)
				LogHelper.info("Added crystal " + stack.getItemDamage() + " for slot " + slot + " and is it valid?: " + CrystalHelper.isCrystalValidForSlot(stack, slot));
			if (stack != null && stack.stackSize > getInventoryStackLimit()) {
				stack.stackSize = getInventoryStackLimit();
			}
			markDirty();
		}
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (this.crystals[slot] != null) {
			ItemStack itemstack;

			if (this.crystals[slot].stackSize <= amount) {
				itemstack = this.crystals[slot];
				this.crystals[slot] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.crystals[slot].splitStack(amount);

				if (this.crystals[slot].stackSize == 0) {
					this.crystals[slot] = null;
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public boolean onRandomTick() {
		//LogHelper.info("Processing tick on " + (worldObj.isRemote ? "client" : "server"));
			for (int i = 0; i < crystals.length; ++i) {
				//LogHelper.info("i = " + i);
				if (!CrystalHelper.isCrystalValidForSlot(crystals[i], i)) {
					//LogHelper.info("INVALID found !");
					NetworkHandler.sendToServer(new MessageExplosion(xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, 2.0F));
					return true;
				}
			}
			return false;
	}

	public String getBasicInfo() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return nbt.toString();
	}
}
