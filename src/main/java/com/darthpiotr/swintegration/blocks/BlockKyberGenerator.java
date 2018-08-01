package com.darthpiotr.swintegration.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.proxy.ClientProxy;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.tileentities.TileEntityRotateBase;
import com.darthpiotr.swintegration.utils.LogHelper;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.item.tool.ItemToolWrench;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class BlockKyberGenerator extends BlockContainer {

	private static final String name = "kyber_generator";
	private TileEntityKyberGenerator tileEntity;
	private IIcon[] icons = new IIcon[3];

	private final Random rand = new Random();

	public BlockKyberGenerator() {
		super(Material.iron);
		GameRegistry.registerTileEntity(TileEntityKyberGenerator.class, "TileEntityKyberGenerator");
		GameRegistry.registerBlock(this, name);
		setBlockName(name);
		setHardness(1.5F);
		setCreativeTab(SWIntegration.SWITab);
		this.setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		return this.blockIcon;
    }
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		this.blockIcon = reg.registerIcon(Reference.Mod.MOD_ID + ":kyber_generator_icon");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		tileEntity = new TileEntityKyberGenerator();
		//tileEntity.syncClientWithServer(world);
		return tileEntity;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack stack) {
		
		//LogHelper.info("Block KyberGenerator placed!");
		
		TileEntityKyberGenerator te = (TileEntityKyberGenerator)world.getTileEntity(x, y, z);
		if (entityliving == null)
	    {
	      te.setFacing((short)1);
	    }
	    else //if(entityliving instanceof EntityPlayer)
	    {
	      int yaw = MathHelper.floor_double(entityliving.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
	      int pitch = Math.round(entityliving.rotationPitch);
	      if (pitch >= 65) {
	        te.setFacing((short)1);
	      } else if (pitch <= -65) {
	        te.setFacing((short)0);
	      } else {
	        switch (yaw)
	        {
	        case 0: 
	          te.setFacing((short)2);
	          break;
	        case 1: 
	          te.setFacing((short)5);
	          break;
	        case 2: 
	          te.setFacing((short)3);
	          break;
	        case 3: 
	          te.setFacing((short)4);
	        }
	      }
	    }
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float lx, float ly,
			float lz) {
		if (world.isRemote)
			return true;
		if(player.isSneaking()) return false;
		
		TileEntity te = world.getTileEntity(x, y, z);

		if (te != null && te instanceof TileEntityKyberGenerator) {
			player.openGui(SWIntegration.instance, Reference.GuiID.KYBER_GENERATOR.ordinal(), world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (world.isRemote)
			return;

		ArrayList drops = new ArrayList();

		TileEntity teRaw = world.getTileEntity(x, y, z);

		if (teRaw != null && teRaw instanceof TileEntityKyberGenerator) {
			TileEntityKyberGenerator te = (TileEntityKyberGenerator) teRaw;

			for (int i = 0; i < te.getSizeInventory(); i++) {
				ItemStack stack = te.getStackInSlot(i);

				if (stack != null)
					drops.add(stack.copy());
			}
		}

		for (int i = 0; i < drops.size(); i++) {
			EntityItem item = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, (ItemStack) drops.get(i));
			item.setVelocity((rand.nextDouble() - 0.5) * 0.25, rand.nextDouble() * 0.5 * 0.25,
					(rand.nextDouble() - 0.5) * 0.25);
			world.spawnEntityInWorld(item);
		}
		
		world.removeTileEntity(x, y, z);
		this.tileEntity = null;
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
		return ClientProxy.renderBlockKyberGeneratorID;
	}
	
	private boolean isProcessingRandomTick = false;
	@Override
	public void updateTick(World world, int x, int y, int z, Random ran)
    {
		//LogHelper.info("Recieved random tick! At " + x + " " + y + " " + z);
		if(!world.isRemote && !isProcessingRandomTick) {
			isProcessingRandomTick = true;
			if(tileEntity != null) {
				/*if(*/tileEntity.onRandomTick();/*)*/
					//breakBlock(world, x, y, z, this, 0);
					//LogHelper.info("Processing finished");
			}
			isProcessingRandomTick = false;
		}	
    }
	
	@Override
	public boolean canDropFromExplosion(Explosion ex)
    {
        return false;
    }
	
	@Override 
	public Item getItemDropped(int p1, Random random, int p2)
    {
        return Ic2Items.machine.getItem();
    }
}