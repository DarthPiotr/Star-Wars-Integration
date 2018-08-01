package com.darthpiotr.swintegration.proxy;

import com.darthpiotr.swintegration.rendering.RendererBlockCompressedChromium;
import com.darthpiotr.swintegration.rendering.RendererBlockCompressedTitanium;
import com.darthpiotr.swintegration.rendering.RendererKyberGenerator;
import com.darthpiotr.swintegration.rendering.RendererKyberHolder;
import com.darthpiotr.swintegration.tileentities.TileEntityBlockCompressedTitanium;
import com.darthpiotr.swintegration.tileentities.TileEntityCompressedChromium;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberGenerator;
import com.darthpiotr.swintegration.tileentities.TileEntityKyberHolder;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy {
	   
	public static int renderBlockKyberGeneratorID;
	public static int renderBlockKyberHolderID;
	public static int renderBlockCompressedTitaniumID;
	public static int renderBlockCompressedChromiumID;
	
	@Override
	    public void preInit(){
		   registerRenderers();
	    }

	    @Override
	    public void init(){

	    }

	    @Override
	    public void postInit(){

	    }

	    @Override
	    public EntityPlayer getClientPlayer(){
	        return Minecraft.getMinecraft().thePlayer;
	    }
	    
	    public void registerRenderers() {
	    	TileEntitySpecialRenderer rendererKyberGenrator = new RendererKyberGenerator();
	    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKyberGenerator.class, rendererKyberGenrator);
	    	renderBlockKyberGeneratorID = RenderingRegistry.getNextAvailableRenderId();
	    	RenderingRegistry.registerBlockHandler(new RendererKyberGenerator());
	    	
	    	TileEntitySpecialRenderer rendererKyberHolder = new RendererKyberHolder();
	    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKyberHolder.class, rendererKyberHolder);
	    	renderBlockKyberHolderID = RenderingRegistry.getNextAvailableRenderId();
	    	RenderingRegistry.registerBlockHandler(new RendererKyberHolder());
	    	
	    	TileEntitySpecialRenderer rendererBlockCompressedTitanium = new RendererBlockCompressedTitanium();
	    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockCompressedTitanium.class, rendererBlockCompressedTitanium);
	    	renderBlockCompressedTitaniumID = RenderingRegistry.getNextAvailableRenderId();
	    	RenderingRegistry.registerBlockHandler(new RendererBlockCompressedTitanium());
	    	
	    	TileEntitySpecialRenderer rendererBlockCompressedChromium = new RendererBlockCompressedChromium();
	    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCompressedChromium.class, rendererBlockCompressedChromium);
	    	renderBlockCompressedChromiumID = RenderingRegistry.getNextAvailableRenderId();
	    	RenderingRegistry.registerBlockHandler(new RendererBlockCompressedChromium());
	    	
	    	
	    }

}
