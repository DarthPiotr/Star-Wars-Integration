package com.darthpiotr.swintegration.tileentities;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityKyberHolder extends TileEntity implements IInventory {

	private short rotation = 0;
	private double height = 0;
	private boolean up = true;
	
	private ItemStack crystal;
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		crystal = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("crystal"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		
		NBTTagCompound nbt = new NBTTagCompound();
		if(crystal != null)
			crystal.writeToNBT(nbt);
		tag.setTag("crystal", nbt);
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return crystal;
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		if (crystal != null)
        {
            ItemStack itemstack;

            if (crystal.stackSize <= size)
            {
                itemstack = crystal;
                crystal = null;
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = crystal.splitStack(size);

                if (crystal.stackSize == 0)
                {
                    crystal = null;
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return crystal;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		 crystal = stack;

	        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
	        {
	            stack.stackSize = this.getInventoryStackLimit();
	        }

	        this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "container.kyberHolder";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
	
	
	@Override
	public Packet getDescriptionPacket()
    {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
    	super.onDataPacket(net, pkt);
    	this.readFromNBT(pkt.func_148857_g());
    }
	
	public boolean hasCrystal() {
		return crystal != null && crystal.stackSize > 0;
	}
	
	public void incrementAnimation() {
		rotation++;
		if(rotation == 360) rotation = 0;
		
		height = Math.cos(rotation / 180D * Math.PI) * 0.09375D;
	}
	
	public double getAnimationHeight() {
		return height;
	}
	public short getAnimationRotation() {
		return rotation;
	}
}
