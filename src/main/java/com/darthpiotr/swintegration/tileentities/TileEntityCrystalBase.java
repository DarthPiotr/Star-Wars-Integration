package com.darthpiotr.swintegration.tileentities;

import com.darthpiotr.swintegration.network.MessageFacing;
import com.darthpiotr.swintegration.network.NetworkHandler;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityCrystalBase extends TileEntityRotateBase {
	public ItemStack[] crystals;
	
	public TileEntityCrystalBase(int crystalCount) {
		crystals = new ItemStack[crystalCount];
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setFacing(nbt.getShort("facing"));
		
		NBTTagList nbttaglist = nbt.getTagList("crystals", crystals.length);
        
		for(short i = 0; i < crystals.length; ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            crystals[i] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("facing", getFacing());
		
		NBTTagList nbttaglist = new NBTTagList();
		for(short i = 0; i < crystals.length; ++i) {
			if(crystals[i] != null) {
				NBTTagCompound nbtTmp = new NBTTagCompound();
				crystals[i].writeToNBT(nbtTmp);
				nbttaglist.appendTag(nbtTmp);
			}
		}
		nbt.setTag("crystals", nbttaglist);
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
	
	public ItemStack[] getCrystals() {
		return crystals;
	}
}
