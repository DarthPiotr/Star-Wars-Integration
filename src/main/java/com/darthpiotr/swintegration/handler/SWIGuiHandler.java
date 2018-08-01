package com.darthpiotr.swintegration.handler;

import com.darthpiotr.swintegration.gui.GuiKyberGenerator;
import com.darthpiotr.swintegration.gui.GuiKyberHolder;
import com.darthpiotr.swintegration.inventory.ContainerKyberGenerator;
import com.darthpiotr.swintegration.inventory.ContainerKyberHolder;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberHolder;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SWIGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(Reference.GuiID.values()[ID]) {
		case KYBER_GENERATOR:
			return new ContainerKyberGenerator(player.inventory, (TileEntityKyberGenerator) world.getTileEntity(x, y, z));
		case KYBER_HOLDER:
			return new ContainerKyberHolder(player.inventory, (TileEntityKyberHolder) world.getTileEntity(x, y, z));
		}
		throw new IllegalArgumentException("Invalid GUI ID: " + ID);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(Reference.GuiID.values()[ID]) {
		case KYBER_GENERATOR:
			return new GuiKyberGenerator(player.inventory, (TileEntityKyberGenerator) world.getTileEntity(x, y, z));
		case KYBER_HOLDER:
			return new GuiKyberHolder(player.inventory, (TileEntityKyberHolder) world.getTileEntity(x, y, z));
		}
		throw new IllegalArgumentException("Invalid GUI ID: " + ID);
	}
}