package com.darthpiotr.swintegration.network;

import com.darthpiotr.swintegration.tileentities.TileEntityRotateBase;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class MessageFacing extends MessageBase<MessageFacing> {

	short facing;
	int x, y, z;
	
	public MessageFacing() {}
	public MessageFacing(TileEntityRotateBase te) {
		facing = te.getFacing();
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		facing = buf.readShort();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeShort(facing);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	@Override
	public void handleClientSide(MessageFacing message, EntityPlayer player) {

		TileEntity te = player.worldObj.getTileEntity(message.x, message.y, message.z);
		if(te instanceof TileEntityRotateBase) {
			 ((TileEntityRotateBase)te).setFacing(message.facing);
		}
	}

	@Override
	public void handleServerSide(MessageFacing message, EntityPlayer player) {
	}

}
