package com.darthpiotr.swintegration.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.proxy.ClientProxy;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberHolder;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockKyberHolder extends BlockContainer {

	public static final String name = "kyber_holder";
	private TileEntityKyberHolder te;
	private Random rand = new Random();
	
	public BlockKyberHolder() {
		super(Material.iron);
		GameRegistry.registerTileEntity(TileEntityKyberHolder.class, "TileEntityKyberHolder");
		GameRegistry.registerBlock(this, name);
		setBlockName(name);
		setHardness(1.5F);
		setCreativeTab(SWIntegration.SWITab);
		this.setBlockBounds(0.21875F, 0, 0.21875F, 0.78125F, 0.25F, 0.781255F);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		te = new TileEntityKyberHolder();
		return te;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float lx, float ly,
			float lz) {
		if (world.isRemote)
			return true;
		if(player.isSneaking()) return false;
		
		TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileEntityKyberHolder) {
			player.openGui(SWIntegration.instance, Reference.GuiID.KYBER_HOLDER.ordinal(), world, x, y, z);
			return true;
		}
		return false;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		if (world.isRemote)
			return;

		ArrayList drops = new ArrayList();

		TileEntity teRaw = world.getTileEntity(x, y, z);

		if (teRaw != null && teRaw instanceof TileEntityKyberHolder) {
			TileEntityKyberHolder te = (TileEntityKyberHolder) teRaw;

				ItemStack stack = te.getStackInSlot(0);

				if (stack != null)
					drops.add(stack.copy());
			}


		if (drops.size() > 0) {
			EntityItem item = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, (ItemStack) drops.get(0));
			item.setVelocity((rand.nextDouble() - 0.5) * 0.25, rand.nextDouble() * 0.5 * 0.25,
					(rand.nextDouble() - 0.5) * 0.25);
			world.spawnEntityInWorld(item);
		}
		
		world.removeTileEntity(x, y, z);
		this.te = null;
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
		return ClientProxy.renderBlockKyberHolderID;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg)
    {
        this.blockIcon = reg.registerIcon(Reference.TexturesIcons.KYBER_HOLDER);
    }

}
