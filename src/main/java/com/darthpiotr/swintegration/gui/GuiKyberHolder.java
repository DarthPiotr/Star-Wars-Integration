package com.darthpiotr.swintegration.gui;

import org.lwjgl.opengl.GL11;

import com.darthpiotr.swintegration.inventory.ContainerKyberHolder;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberHolder;
import com.darthpiotr.swintegration.utils.CrystalHelper;
import com.darthpiotr.swintegration.utils.Reference;
import com.parzivail.pswm.font.FontManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiKyberHolder extends GuiContainer {

	private ResourceLocation texture = new ResourceLocation(Reference.Gui.KYBER_HOLDER);
	private TileEntityKyberHolder te;
	private InventoryPlayer inventory;

	private final int fontColor = 0x0055d2;
	private final int fontImportant = 0x29caff;
	private final int maxWidth = 120;

	public GuiKyberHolder(InventoryPlayer invPlayer, TileEntityKyberHolder tile) {
		super(new ContainerKyberHolder(invPlayer, tile));
		te = tile;
		inventory = invPlayer;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

		FontManager.aurebesh.drawString("Crystal info", 55, 13, fontImportant, false);

		if (te.hasCrystal()) {
			ItemStack stack = te.getStackInSlot(0);
			FontManager.aurebesh.drawString("Color: " + CrystalHelper.getColorStringFromStack(stack), 42, 25, fontColor,
					false);

			if (stack.hasTagCompound()) {
				FontManager.aurebesh.drawSplitString("This crystal is synthetic.", 42, 35, maxWidth, fontImportant);
				FontManager.aurebesh.drawSplitString("It's not a good idea to use it.", 42, 55, maxWidth,  fontColor);
				return;
			}

			if (stack.getItemDamage() == 0) {
				FontManager.aurebesh.drawSplitString("The dark side is strong in this one.", 42, 35, maxWidth,
						fontColor);
				FontManager.aurebesh.drawSplitString("Provided energy might be unmanageable.", 42, 55, maxWidth,
						fontImportant);
			} else {

				FontManager.aurebesh.drawString("Natural kyber crystal", 42, 35, fontImportant, false);

				switch (stack.getItemDamage()) {
				case CrystalHelper.IDs.GREEN:
				case CrystalHelper.IDs.BLUE:
					FontManager.aurebesh.drawSplitString("The light side is strong in this one.", 42, 45, maxWidth,
							fontColor);
					break;
				case CrystalHelper.IDs.BLACK:
					FontManager.aurebesh.drawSplitString("It's great for the Dark Saber.", 42, 45, maxWidth, fontColor);
					break;

				case CrystalHelper.IDs.PRISM:
					FontManager.aurebesh.drawSplitString("This type of crystal is really hard to find", 42, 45,
							maxWidth, fontColor);
					break;

				case CrystalHelper.IDs.WHITE:
					FontManager.aurebesh.drawSplitString("The purest kyber that can be found.", 42, 45, maxWidth,
							fontColor);
					break;

				case CrystalHelper.IDs.CYAN:
				case CrystalHelper.IDs.YELLOW:
				case CrystalHelper.IDs.PINK:
				case CrystalHelper.IDs.PURPLE:
				case CrystalHelper.IDs.GRAY:
					FontManager.aurebesh.drawSplitString("This color is quite rare.", 42, 45, maxWidth, fontColor);
					break;
				}
			}

		} else {
			FontManager.aurebesh.drawString("No crystal detected.", 42, 25, fontColor, false);
			FontManager.aurebesh.drawSplitString("Please insert kyber to collect some info.", 42, 35, maxWidth,
					fontColor);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;

		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
