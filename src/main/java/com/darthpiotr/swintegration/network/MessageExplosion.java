package com.darthpiotr.swintegration.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class MessageExplosion extends MessageBase<MessageExplosion>{

	float x, y, z, power;
	
	public MessageExplosion () {}
	
	public MessageExplosion (float x, float y, float z, float power) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.power = power;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		power = buf.readFloat();
		x = buf.readFloat();
		y = buf.readFloat();
		z = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(power);
		buf.writeFloat(x);
		buf.writeFloat(y);
		buf.writeFloat(z);
	}

	@Override
	public void handleClientSide(MessageExplosion message, EntityPlayer player) {}

	@Override
	public void handleServerSide(MessageExplosion message, EntityPlayer player) {
		player.worldObj.createExplosion(player, message.x,
				message.y, message.z, message.power, true);
	}
}
