package com.darthpiotr.swintegration.tileentities;

import com.darthpiotr.swintegration.init.SWIBlocks;
import com.darthpiotr.swintegration.network.MessageFacing;
import com.darthpiotr.swintegration.network.NetworkHandler;
import com.darthpiotr.swintegration.utils.LogHelper;

import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRotateBase extends TileEntity implements IWrenchable {
	private short facing = 2;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setFacing(nbt.getShort("facing"));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("facing", getFacing());
	}
	/*
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
	*/
    //@Override
	public short getFacing () {
		return facing;
	}
	
	//@Override
	public void setFacing(short face) {
		
		LogHelper.info("New facing set! Now it is: " + facing);
		//Thread.dumpStack();
		
		this.facing = face;
		markDirty();
		if(this.worldObj != null && !this.worldObj.isRemote)
			NetworkHandler.sendToAll(new MessageFacing(this));
	}

	@Override
	public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
		LogHelper.info("checking if can change facing");
		return side != getFacing();
	}

	@Override
	public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
		return true;
	}

	@Override
	public float getWrenchDropRate() {
		return 1;
	}

	@Override
	public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
		return new ItemStack(SWIBlocks.kyber_generator);
	}
}
