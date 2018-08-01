package com.darthpiotr.swintegration.network;

import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.utils.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class MessageKyberGeneratorCrystal extends MessageBase<MessageKyberGeneratorCrystal> {

	private int x, y, z;
	private NBTTagCompound nbtCrystal;

	public MessageKyberGeneratorCrystal() {
	}

	public MessageKyberGeneratorCrystal(TileEntityKyberGenerator te) {
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		//LogHelper.info("Creating crystal message.");
//		if(te.getCrystal() != null) {
//			nbtCrystal = new NBTTagCompound();
//			te.getCrystal().writeToNBT(nbtCrystal);
//		}
			
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
		if(buf.isReadable())
			nbtCrystal = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		
		
		if(nbtCrystal != null)
			ByteBufUtils.writeTag(buf, nbtCrystal);

	}

	@Override
	public void handleClientSide(MessageKyberGeneratorCrystal message, EntityPlayer player) {
//		//handleServerSide(message, player);
//		TileEntity te = player.worldObj.getTileEntity(message.x, message.y, message.z);
//		if (te instanceof TileEntityKyberGenerator) {
//			LogHelper.info("Updating crystal on Client. crystal:" + String.valueOf(message.nbtCrystal != null));
//			if(message.nbtCrystal != null)
//				((TileEntityKyberGenerator) te).setCrystal(ItemStack.loadItemStackFromNBT(message.nbtCrystal));
//		}
	}

	@Override
	public void handleServerSide(MessageKyberGeneratorCrystal message, EntityPlayer player) {
		/*TileEntity te = player.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEntityKyberGenerator) {
			((TileEntityKyberGenerator) te).setCrystal(ItemStack.loadItemStackFromNBT(nbtTag));
		}*/
	}
}
