package com.darthpiotr.swintegration.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.darthpiotr.swintegration.config.ConfigurationHandler;
import com.darthpiotr.swintegration.models.ModelKyberGenerator;
import com.darthpiotr.swintegration.proxy.ClientProxy;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.utils.CrystalHelper;
import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;

public class RendererKyberGenerator extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	public static IModelCustom model = AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.Models.KYBER_GENERATOR));
	public static IModelCustom modelInventory = AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.Models.BLOCK_BASE));
	public static IModelCustom modelKyber = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.Models.KYBER));

	public static ResourceLocation texture = new ResourceLocation(Reference.Textures.KYBER_GENERATOR);
	public static ResourceLocation textureInventory = new ResourceLocation(Reference.Textures.BLOCK_KYBER_GENERATOR);

	public RendererKyberGenerator() {
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale) {
		if (tileEntity instanceof TileEntityKyberGenerator) {
			renderTileEntityAt((TileEntityKyberGenerator) tileEntity, x, y, z, scale);
		}
	}

	public void renderTileEntityAt(TileEntityKyberGenerator tileEntity, double x, double y, double z, float scale) {

		if (ConfigurationHandler.renderCrystalsInGenerator) {
			if (tileEntity.getPresentCrystalAmount() > 0) {
				ItemStack[] crystals = tileEntity.getCrystals();

				for (short i = 0; i < 5; ++i) {
					if (crystals[i] != null && crystals[i].getItemDamage() != CrystalHelper.IDs.RED) {
						renderByFacingAndId(tileEntity.getFacing(), i, crystals[i], x, y, z);
					}
				}
			}
		}

		GL11.glPushMatrix();

		GL11.glDisable(GL11.GL_CULL_FACE);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glTranslated(x + 0.5F, y + 0.5F, z + 0.5F);
		rotateByFacing(tileEntity.getFacing());

		this.bindTexture(texture);
		this.model.renderAll();

		GL11.glPopMatrix();
	}

	private void renderByFacingAndId(short facing, short id, ItemStack stack, double x, double y, double z) {

		GL11.glPushMatrix();

		rotateAndTranslateByFacing(facing, id, x, y, z);

		ResourceLocation colorTexture = new ResourceLocation(CrystalHelper.getTextureFromItemStack(stack));
		bindTexture(colorTexture);
		modelKyber.renderAll();

		GL11.glPopMatrix();
	}

	private void rotateAndTranslateByFacing(short facing, short id, double x, double y, double z) {

		double newx = 0, newy = 0, newz = 0;

		switch (id) {
		case 0:
			newx = 0;
			newy = 0;
			newz = -0.125F;
			break;
		case 1:
			newx = -0.125F;
			newy = 0.125F;
			newz = -0.125F;
			break;
		case 2:
			newx = 0.125F;
			newy = 0.125F;
			newz = -0.125F;
			break;
		case 3:
			newx = -0.125F;
			newy = -0.125F;
			newz = -0.125F;
			break;
		case 4:
			newx = 0.125F;
			newy = -0.125F;
			newz = -0.125F;
			break;
		}

		double xc = newx, yc = newy, zc = newz;

		switch (facing) {
		case 0: // DOWN
			newy = -zc;
			newz = yc;
			GL11.glTranslated(x + newx + 0.5, y + newy + 0.5, z + newz + 0.5);
			GL11.glRotatef(90F, 1, 0, 0);
			break;
		case 1: // UP
			newy = zc;
			newz = -yc;
			GL11.glTranslated(x + newx + 0.5, y + newy + 0.5, z + newz + 0.5);
			GL11.glRotatef(270F, 1F, 0F, 0F);
			break;
		case 2: // NORTH
		default:
			newx = -xc;
			newz = -zc;
			GL11.glTranslated(x + newx + 0.5, y + newy + 0.5, z + newz + 0.5);
			GL11.glRotatef(180F, 0F, 1F, 0F);
			break;
		case 3: // SOUTH
			GL11.glTranslated(x + newx + 0.5, y + newy + 0.5, z + newz + 0.5);
			break;
		case 4: // WEST
			newx = -zc;
			newz = xc;
			GL11.glTranslated(x + newx + 0.5, y + newy + 0.5, z + newz + 0.5);
			GL11.glRotatef(270, 0, 1, 0);
			break;
		case 5: // EAST
			newx = zc;
			newz = xc;
			GL11.glTranslated(x + newx + 0.5, y + newy + 0.5, z + newz + 0.5);
			GL11.glRotatef(90, 0, 1, 0);
			break;
		}
	}

	private void rotateByFacing(short facing) {

		switch (facing) {
		case 0: // DOWN
			GL11.glRotatef(90F, 1, 0, 0);
			break;
		case 1: // UP
			GL11.glRotatef(270F, 1F, 0F, 0F);
			break;
		case 2: // NORTH
		default:
			GL11.glRotatef(180F, 0F, 1F, 0F);
			break;
		case 3: // SOUTH
			break;
		case 4: // WEST
			GL11.glRotatef(270, 0, 1, 0);
			break;
		case 5: // EAST
			GL11.glRotatef(90, 0, 1, 0);
			break;
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

		GL11.glPushMatrix();

		GL11.glRotated(270, 0, 1, 0);
		GL11.glTranslated(0, 0, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(textureInventory);
		modelInventory.renderAll();

		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return ClientProxy.renderBlockKyberGeneratorID;
	}
}
