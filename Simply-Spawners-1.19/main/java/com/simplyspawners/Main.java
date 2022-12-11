package com.simplyspawners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simplyspawners.blocks.BlockLoader;
import com.simplyspawners.config.Config;
import com.simplyspawners.enchantments.EnchantmentLoader;
import com.simplyspawners.events.EventHandler;
import com.simplyspawners.items.ItemLoader;
import com.simplyspawners.loot.ChestLootModifier;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Main.MODID)
public class Main {
	
	public static final String MODID = "simplyspawners";
	
	public static final Logger LOGGER = LogManager.getLogger();

    public Main() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        
        final IEventBus ieventbus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemLoader.ITEMS.register(ieventbus);
        BlockLoader.BLOCKS.register(ieventbus);
        BlockLoader.BLOCK_ITEM.register(ieventbus);
        EnchantmentLoader.ENCHANTMENT.register(ieventbus);
        EventHandler.load();
        ChestLootModifier.GLOBAL_LOOT_MODIFIER.register(ieventbus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    	
    	final ModLoadingContext modloadingcontext = ModLoadingContext.get();
    	
    	modloadingcontext.registerConfig(ModConfig.Type.COMMON, Config.SERVER_SPEC);

        Config.loadConfig(Config.SERVER_SPEC, FMLPaths.CONFIGDIR.get().resolve(MODID + ".toml"));
        
        Config.Holder.load();
    	
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    	
    	//ItemBlockRenderTypes.setRenderLayer(BlockLoader.EMPTY_SPAWNER.get(), RenderType.translucent());

    }

}
