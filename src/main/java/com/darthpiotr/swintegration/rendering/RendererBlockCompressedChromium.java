package com.darthpiotr.swintegration.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.darthpiotr.swintegration.proxy.ClientProxy;
import com.darthpiotr.swintegration.tileentities.TileEntityBlockCompressedTitanium;
import com.darthpiotr.swintegration.tileentities.TileEntityCompressedChromium;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RendererBlockCompressedChromium extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
	public static IModelCustom model = AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.Models.BLOCK_CHROMIUM));
	public static ResourceLocation texture = new ResourceLocation(Reference.Textures.BLOCK_CHROMIUM);

	public RendererBlockCompressedChromium() {
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		if (te instanceof TileEntityCompressedChromium) {
			renderTileEntityAt((TileEntityCompressedChromium) te, x, y, z, scale);
		}
	}

	public void renderTileEntityAt(TileEntityCompressedChromium te, double x, double y, double z, float scale) {

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		rotateByFacing(te.getFacing(), x, y, z);

		this.bindTexture(texture);
		this.model.renderAll();

		GL11.glPopMatrix();
	}

	private void rotateByFacing(short facing, double x, double y, double z) {
		switch (facing) {
		case 3:
			GL11.glTranslated(x, y, z + 1);
			GL11.glRotatef(90, 0, 1, 0);
			break;
		case 4:
			GL11.glTranslated(x, y, z);
			
			break;
		case 5:
			GL11.glTranslated(x + 1, y, z + 1);
			GL11.glRotatef(180, 0, 1, 0);
			break;
		case 2:
		default:
			GL11.glTranslated(x + 1, y, z);
			GL11.glRotatef(270, 0, 1, 0);
			break;
		}

	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslated(0, -1, 0);
		GL11.glRotatef(180, 0, 1, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
		this.model.renderAll();

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
		return ClientProxy.renderBlockCompressedChromiumID;
	}

}
