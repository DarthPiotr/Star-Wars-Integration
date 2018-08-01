package com.darthpiotr.swintegration.proxy;

import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy implements IProxy {
    public abstract void preInit();

    public abstract void init();

    public abstract void postInit();

    public abstract EntityPlayer getClientPlayer();
}
