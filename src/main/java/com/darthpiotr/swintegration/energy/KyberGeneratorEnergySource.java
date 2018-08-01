package com.darthpiotr.swintegration.energy;

import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.utils.LogHelper;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.prefab.BasicSource;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

public class KyberGeneratorEnergySource extends BasicSource {

	TileEntityKyberGenerator parentTe;
	

	public KyberGeneratorEnergySource(TileEntityKyberGenerator parent1, double capacity1, int tier1) {
		super(parent1, capacity1, tier1);
		parentTe = parent1;
	}

	@Override
	public double getOfferedEnergy() {
		if(parentTe.getPresentCrystalAmount() > 0)		
			return Math.min(energyStored, power);
		
		return 0;
	}
	
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		LogHelper.info("emitsEnergyTo; facing: " +  parentTe.getFacing()); 
		return direction.ordinal() == parentTe.getFacing();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		NBTTagCompound data = tag.getCompoundTag("IC2BasicSource");
		tier = data.getInteger("Tier");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
			super.writeToNBT(tag);
			tag.getCompoundTag("IC2BasicSource").setInteger("Tier", this.tier);
	}
	
	public int getStorageFillScaled(int i) {
		return Math.min((int)(this.getEnergyStored() * i / this.getCapacity()), i);
	}
	
	public void removeFromEnet() {
		if (this.addedToEnet) 
		      MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	}
	
	public void addToEnet() {
		if (this.addedToEnet)
	    {
	      this.addedToEnet = false;
	      MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
	      this.addedToEnet = true;
	    }
	}
	
	public void setCapacityByTier(int tier) {
		switch(tier) {
		case 0:
		case 1:
			setCapacity(40000); // 40 000
			break;
		case 2:
			setCapacity(300000); // 300 000
			break;
		case 3:
			setCapacity(4000000); // 4 000 000
			break;
		case 4:
			setCapacity(40000000); // 40 000 000
			break;
		case 5:
			setCapacity(80000000); // 80 000 000
			break;
		default:
			throw new IllegalArgumentException("Your tier: " + tier + " is out of range!");
		}
	}
}