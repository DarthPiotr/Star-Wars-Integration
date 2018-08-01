package com.darthpiotr.swintegration.items;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemGeneratorCircuit extends Item {
	public final String name = "generatorCircuit";

	public ItemGeneratorCircuit() {
		this.setMaxDamage(0);
		setCreativeTab(SWIntegration.SWITab);
	}
	
	public String getUnlocalizedName(ItemStack iStack)
    {
        return this.name;
    }
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iReg)
    {
            this.itemIcon = iReg.registerIcon(Reference.TexturesIcons.GENERATOR_CIRCUIT);
    }
}
