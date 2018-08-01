package com.darthpiotr.swintegration.items;

import java.util.List;

import com.darthpiotr.swintegration.SWIntegration;
import com.darthpiotr.swintegration.utils.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

public class ItemLightsaberCrystalBlend extends Item{
	public String name = "lightsaberCrystalBlend";
	public String[] colors = {"red", "green", "blue", "black", "cyan", "gray", "pink", "purple", "white", "yellow", "prism"};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public ItemLightsaberCrystalBlend(){
		setHasSubtypes(true);
		this.setMaxDamage(0);
		setCreativeTab(SWIntegration.SWITab);
	}
	 @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage)
    {
        int j = MathHelper.clamp_int(damage, 0, 15);
        return this.icons[j];
    }
	public String getUnlocalizedName(ItemStack iStack)
    {
        return this.name;
    }
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs cTab, List list)
    {
        for (int i = 0; i < colors.length; ++i)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iReg)
    {
        this.icons = new IIcon[colors.length];

        for (int i = 0; i < colors.length; ++i)
        {
            this.icons[i] = iReg.registerIcon(Reference.Mod.MOD_ID + ":" + name + "_" + colors[i]);
        }
    }
}
