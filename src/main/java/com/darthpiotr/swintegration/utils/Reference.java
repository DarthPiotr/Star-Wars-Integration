package com.darthpiotr.swintegration.utils;

public class Reference {

	public class Mod {
		public static final String NAME = "Star Wars Integration";
		public static final String MOD_ID = "swintegration";
		public static final String VERSION = "1.7.10-3.0.0";
		public static final String DEPENDENCIES = "required-after: IC2; required-after: starwarsmod@[1.3.3]";
		public static final String NETWORK_CHANNEL = MOD_ID + "netchannel";

		public static final String CLIENT_PROXY = "com.darthpiotr.swintegration.proxy.ClientProxy";
		public static final String SERVER_PROXY = "com.darthpiotr.swintegration.proxy.ServerProxy";
		public static final String CONFIG_GUIFACTORY = "com.darthpiotr.swintegration.config.GuiFactory";
	}
	
	public class Textures{
		public static final String test = Reference.Mod.MOD_ID + ":textures/blocks/test.png";
		public static final String DEFAULT = Reference.Mod.MOD_ID + ":textures/blocks/default.png";
		public static final String BLOCK_TITANIUM = Reference.Mod.MOD_ID + ":textures/blocks/compressed_titanium.png";
		public static final String BLOCK_CHROMIUM = Reference.Mod.MOD_ID + ":textures/blocks/compressed_chromium.png";
		public static final String BLOCK_KYBER_GENERATOR = Reference.Mod.MOD_ID + ":textures/blocks/kyber_generator_block.png";
		public static final String KYBER_GENERATOR = Reference.Mod.MOD_ID + ":textures/blocks/kyber_generator.png";
		public static final String KYBER_HOLDER = Reference.Mod.MOD_ID + ":textures/blocks/kyber_holder.png";
		
		public class Colors{
			public static final String RED = Reference.Mod.MOD_ID + ":textures/red.png";
			public static final String GREEN = Reference.Mod.MOD_ID + ":textures/green.png";
			public static final String BLUE = Reference.Mod.MOD_ID + ":textures/blue.png";
			public static final String BLACK = Reference.Mod.MOD_ID + ":textures/black.png";
			public static final String CYAN = Reference.Mod.MOD_ID + ":textures/cyan.png";
			public static final String GRAY = Reference.Mod.MOD_ID + ":textures/gray.png";
			public static final String PINK = Reference.Mod.MOD_ID + ":textures/pink.png";
			public static final String PURPLE = Reference.Mod.MOD_ID + ":textures/purple.png";
			public static final String WHITE = Reference.Mod.MOD_ID + ":textures/white.png";
			public static final String YELLOW = Reference.Mod.MOD_ID + ":textures/yellow.png";
			public static final String PRISM = Reference.Mod.MOD_ID + ":textures/prism.png";
		}
	}
	
	public class Gui{
		public static final String KYBER_GENERATOR = Reference.Mod.MOD_ID + ":textures/gui/kyber_generator.png";
		public static final String KYBER_HOLDER = Reference.Mod.MOD_ID + ":textures/gui/kyber_holder.png";
	}
	
	public class TexturesIcons{
		public static final String test = Reference.Mod.MOD_ID + ":test";
		public static final String BLOCK_TITANIUM = Reference.Mod.MOD_ID + ":compressed_titanium_icon";
		public static final String BLOCK_CHROMIUM = Reference.Mod.MOD_ID + ":compressed_chromium_icon";
		public static final String KYBER_GENERATOR = Reference.Mod.MOD_ID + ":kyber_generator_icon";
		public static final String KYBER_HOLDER = Reference.Mod.MOD_ID + ":kyber_holder_icon";
		
		public static final String KYBER_PLACE = Reference.Mod.MOD_ID + ":kyber_place";
		public static final String GENERATOR_PLATE= Reference.Mod.MOD_ID + ":generator_plate";
		public static final String GENERATOR_CIRCUIT= Reference.Mod.MOD_ID + ":generator_circuit";
		public static final String GENERATOR_CORE= Reference.Mod.MOD_ID + ":generator_core";
	}
	
	public class Models{
		public static final String test = Reference.Mod.MOD_ID + ":models/test.obj";
		public static final String BLOCK_BASE = Reference.Mod.MOD_ID + ":models/blockBase.obj";
		public static final String BLOCK_TITANIUM = Reference.Mod.MOD_ID + ":models/modelBlockTitanium.obj";
		public static final String BLOCK_CHROMIUM = Reference.Mod.MOD_ID + ":models/modelBlockChromium.obj";
		public static final String KYBER = Reference.Mod.MOD_ID + ":models/kyber.obj";
		public static final String KYBER_GENERATOR = Reference.Mod.MOD_ID + ":models/generator.obj";
		public static final String KYBER_HOLDER = Reference.Mod.MOD_ID + ":models/kyberHolder.obj";
	}

	public class Config{
		public static final String CATEGORY_CRAFTING = "craftings";
		public static final String CATEGORY_RENDER = "render";
		public static final String CATEGORY_SCANNER = "scanner";
	}
	
	public enum GuiID{
		KYBER_GENERATOR, KYBER_HOLDER;
	}
	
	public enum NetDiscriminator{
		MESSAGE_KYBERGENERATOR_CLIENT, MESSAGE_KYBERGENERATOR_SERVER, MESSAGE_FACING_CLIENT, MESSAGE_EXPLOSION_SERVER;
	}
}
