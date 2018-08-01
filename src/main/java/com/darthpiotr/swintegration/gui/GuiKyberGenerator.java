package com.darthpiotr.swintegration.gui;

import org.lwjgl.opengl.GL11;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.inventory.ContainerKyberGenerator;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.utils.Reference;
import com.parzivail.pswm.font.FontManager;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiKyberGenerator extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation(Reference.Gui.KYBER_GENERATOR);
	private TileEntityKyberGenerator te;
	private InventoryPlayer inventory;

	public GuiKyberGenerator(InventoryPlayer invPlayer, TileEntityKyberGenerator tile) {
		super(new ContainerKyberGenerator(invPlayer, tile));
		this.te = tile;
		inventory = invPlayer;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

		fontRendererObj.drawString(I18n.format(te.getInventoryName()), 68, 9, 4210752, false);
		fontRendererObj.drawString("Output: " + (int)te.getEnergySource().getOfferedEnergy() + " EU/t", 68, 20, 4210752, false);

		
		fontRendererObj.drawString(" " + (long)te.getEnergySource().getEnergyStored(), 68, 51, 4210752, false);
		fontRendererObj.drawString("/" + (long)te.getEnergySource().getCapacity() + " EU", 68, 62, 4210752, false);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		int i1 = this.te.getEnergySource().getStorageFillScaled(24);
		drawTexturedModalRect(x + 76, y + 31, 176, 0, i1, 17);
	}
}