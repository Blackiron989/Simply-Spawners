package com.simplyspawners.events;

import com.simplyspawners.loot.ChestLootModifier;

import net.minecraftforge.common.MinecraftForge;

public class EventHandler {
	
	public static void load() {
		
		MinecraftForge.EVENT_BUS.register(BreakEvents.class);
		MinecraftForge.EVENT_BUS.register(LivingDropsEvents.class);
		MinecraftForge.EVENT_BUS.register(ChestLootModifier.class);
		
	}

}
