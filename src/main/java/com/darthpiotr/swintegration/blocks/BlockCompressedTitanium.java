package com.darthpiotr.swintegration.blocks;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.proxy.ClientProxy;
import com.darthpiotr.swintegration.tileentities.TileEntityBlockCompressedTitanium;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCompressedTitanium extends BlockContainer {

	TileEntityBlockCompressedTitanium tileEntity;

	private static final String name = "compressed_titanium";

	public BlockCompressedTitanium() {
		super(Material.rock);
		GameRegistry.registerBlock(this, name);
		GameRegistry.registerTileEntity(TileEntityBlockCompressedTitanium.class, "TileEntityBlockCompressedTitanium");
		setBlockName(name);
		setHardness(1.5F);
		setCreativeTab(SWIntegration.SWITab);
		this.setBlockBounds(0.03125F, 0, 0.03125F, 0.96875F, 1F, 0.96875F);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(Reference.TexturesIcons.BLOCK_TITANIUM);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		tileEntity = new TileEntityBlockCompressedTitanium();
		return tileEntity;
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
		return ClientProxy.renderBlockCompressedTitaniumID;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		TileEntityBlockCompressedTitanium te = (TileEntityBlockCompressedTitanium) world.getTileEntity(x, y, z);
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