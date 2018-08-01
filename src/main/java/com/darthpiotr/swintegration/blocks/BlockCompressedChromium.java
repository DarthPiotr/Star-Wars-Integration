package com.darthpiotr.swintegration.blocks;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.proxy.ClientProxy;
import com.darthpiotr.swintegration.tileentities.TileEntityBlockCompressedTitanium;
import com.darthpiotr.swintegration.tileentities.TileEntityCompressedChromium;
import com.darthpiotr.swintegration.tileentities.TileEntityRotateBase;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCompressedChromium extends BlockContainer{

	TileEntityCompressedChromium te;
	private static final String name = "compressed_chromium";
	
	public BlockCompressedChromium() {
		 super(Material.rock);
	        GameRegistry.registerBlock(this, name);
	        GameRegistry.registerTileEntity(TileEntityCompressedChromium.class, "TileEntityBlockCompressedChromium");
	        setBlockName(name);
	        setHardness(1.5F);
	        setBlockTextureName(Reference.Mod.MOD_ID + ":" + name);
	        setCreativeTab(SWIntegration.SWITab);
	        this.setBlockBounds(0.03125F, 0, 0.03125F, 0.96875F, 1F, 0.96875F);
	}
	public BlockCompressedChromium(boolean shouldRegister) {
		 super(Material.rock);
	        if(shouldRegister) {
	        	GameRegistry.registerBlock(this, name);
	        	setCreativeTab(SWIntegration.SWITab);
	        }
	        setBlockName(name);
	        setBlockTextureName(Reference.Mod.MOD_ID + ":" + name);
	}
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(Reference.TexturesIcons.BLOCK_CHROMIUM);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		te = new TileEntityCompressedChromium();
		return te;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return ClientProxy.renderBlockCompressedChromiumID;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileEntityCompressedChromium te = (TileEntityCompressedChromium) world.getTileEntity(x, y, z);
		if (entity == null) {
			te.setFacing((short) 1);
		} else // if(entityliving instanceof EntityPlayer)
		{
			int yaw = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
			switch (yaw) {
			case 0:
				te.setFacing((short) 2);
				break;
			case 1:
				te.setFacing((short) 5);
				break;
			case 2:
				te.setFacing((short) 3);
				break;
			case 3:
				te.setFacing((short) 4);
			}
		}
	}
}