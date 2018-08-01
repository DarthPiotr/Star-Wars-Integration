package com.darthpiotr.swintegration.network;

import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;

public class NetworkHandler {
	private static SimpleNetworkWrapper INSTANCE;
	
	public static void init() {
		LogHelper.info("[SWI] Network Handler init() called!!");
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.Mod.MOD_ID);
		
		INSTANCE.registerMessage(MessageFacing.class, MessageFacing.class, Reference.NetDiscriminator.MESSAGE_FACING_CLIENT.ordinal(), Side.CLIENT);
		INSTANCE.registerMessage(MessageExplosion.class, MessageExplosion.class, Reference.NetDiscriminator.MESSAGE_EXPLOSION_SERVER.ordinal(), Side.SERVER);
		INSTANCE.registerMessage(MessageKyberGenerator.class, MessageKyberGenerator.class, Reference.NetDiscriminator.MESSAGE_KYBERGENERATOR_SERVER.ordinal(), Side.SERVER);
		INSTANCE.registerMessage(MessageKyberGenerator.class, MessageKyberGenerator.class, Reference.NetDiscriminator.MESSAGE_KYBERGENERATOR_CLIENT.ordinal(), Side.CLIENT);
	}
	
    public static void sendToServer(IMessage message){
        INSTANCE.sendToServer(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player){
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAllAround(IMessage message, TargetPoint point){
        INSTANCE.sendToAllAround(message, point);
    }

    public static void sendToAll(IMessage message){
        INSTANCE.sendToAll(message);
    }

    public static void sendToDimension(IMessage message, int dimensionId){
        INSTANCE.sendToDimension(message, dimensionId);
    }
}
