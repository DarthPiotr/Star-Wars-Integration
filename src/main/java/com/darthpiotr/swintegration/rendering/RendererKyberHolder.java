package com.darthpiotr.swintegration.rendering;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.darthpiotr.swintegration.config.ConfigurationHandler;
import com.darthpiotr.swintegration.proxy.ClientProxy;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberHolder;
import com.darthpiotr.swintegration.utils.CrystalHelper;
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

public class RendererKyberHolder extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {

	public static IModelCustom model = AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.Models.KYBER_HOLDER));
	public static IModelCustom modelKyber = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.Models.KYBER));

	public static ResourceLocation texture = new ResourceLocation(Reference.Textures.KYBER_HOLDER);

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		if(te instanceof TileEntityKyberHolder) {
			renderTileEntityAt((TileEntityKyberHolder)te, x, y, z, scale);
		}
	}
	
	private void renderTileEntityAt(TileEntityKyberHolder te, double x, double y, double z, float scale) {
		
		if(ConfigurationHandler.renderCrystalsInHolder && te.hasCrystal()) renderCrystal(te, x, y, z);
		
		GL11.glPushMatrix();

		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		//GL11.glRotated(270, 0, 1, 0);
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		model.renderAll();

		GL11.glPopMatrix();
	}
	
	private void renderCrystal(TileEntityKyberHolder te, double x, double y, double z) {
		GL11.glPushMatrix();
		
		GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
		GL11.glRotated(90, 1, 0, 0);
		
		if(ConfigurationHandler.animateCrystalsInHolder) {
			GL11.glTranslated(0, 0, te.getAnimationHeight());
			GL11.glRotated(te.getAnimationRotation(), 0, 0, 1);
			te.incrementAnimation();
		}
		
		ResourceLocation textureCrystal = new ResourceLocation(CrystalHelper.getTextureFromItemStack(te.getStackInSlot(0)));
		Minecraft.getMinecraft().getTextureManager().bindTexture(textureCrystal);
		modelKyber.renderAll();
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		GL11.glPushMatrix();

		//GL11.glRotated(270, 0, 1, 0);
		//GL11.glTranslated(0, 0, 0);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		model.renderAll();

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
		return ClientProxy.renderBlockKyberHolderID;
	}
}
