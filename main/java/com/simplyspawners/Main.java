package com.simplyspawners;

import com.simplyspawners.blocks.BlockLoader;
import com.simplyspawners.enchantments.EnchantmentLoader;
import com.simplyspawners.items.ItemLoader;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public class Main {
	
	public static final String MODID = "simplyspawners";

    public Main() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        
        final IEventBus ieventbus = FMLJavaModLoadingContext.get().getModEventBus();
        
        ItemLoader.ITEMS.register(ieventbus);
        BlockLoader.BLOCKS.register(ieventbus);
        BlockLoader.BLOCK_ITEM.register(ieventbus);
        EnchantmentLoader.ENCHANTMENT.register(ieventbus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    	
    	RenderTypeLookup.setRenderLayer(BlockLoader.EMPTY_SPAWNER.get(), RenderType.getTranslucent());

    }
    
    /**
     * 	Notes:
     * 		Add particle effect when using empty egg
     * 		Play sound when using empty egg
     */

}
