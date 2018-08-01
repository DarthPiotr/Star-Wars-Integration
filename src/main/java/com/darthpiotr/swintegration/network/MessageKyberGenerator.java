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

public class MessageKyberGenerator extends MessageBase<MessageKyberGenerator> {

	private int x, y, z;
	private NBTTagCompound nbtEnergy;

	public MessageKyberGenerator() {
	}

	public MessageKyberGenerator(TileEntityKyberGenerator te) {
		//LogHelper.info("MessageKyberGeneratorEnergy constructor");
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		nbtEnergy = new NBTTagCompound();
		te.getEnergySource().writeToNBT(nbtEnergy);
		//LogHelper.info("Creating energy message: " + nbtEnergy.toString());

	}

	@Override
	public void fromBytes(ByteBuf buf) {

		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		nbtEnergy = ByteBufUtils.readTag(buf);


		//LogHelper.info("fromBytes(): " + nbtEnergy.toString());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);

		//LogHelper.info("toBytes(): " + nbtEnergy.toString());
		ByteBufUtils.writeTag(buf, nbtEnergy);

	}

	@Override
	public void handleClientSide(MessageKyberGenerator message, EntityPlayer player) {
		// handleServerSide(message, player);
		TileEntity te = player.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TileEntityKyberGenerator) {
			//LogHelper.info("Updating energy source on Client: " + message.nbtEnergy.toString());
			((TileEntityKyberGenerator) te).updateEnergySource(message.nbtEnergy);
		}
	}

	@Override
	public void handleServerSide(MessageKyberGenerator message, EntityPlayer player) {
	}
}
