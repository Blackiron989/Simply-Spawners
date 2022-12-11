package com.simplyspawners.config;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.simplyspawners.Main;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {
	
	final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistEggification;
	final ForgeConfigSpec.DoubleValue eggificationChance;
	final ForgeConfigSpec.BooleanValue lootSpawn;
	
	ServerConfig(final ForgeConfigSpec.Builder builder) {
		
		builder.push("general");
		
		blacklistEggification = builder
				.comment("Blacklist spawn egg drops using eggification enchant. (eg. bat_spawn_egg)")
				.translation(Main.MODID + "config.blacklistEggification")
				.defineList("blacklistEggification", ImmutableList.of(), obj -> true);
		eggificationChance = builder
				.comment("Chance for eggification enchant to occur.")
				.translation(Main.MODID + "config.eggificationChance")
				.defineInRange("eggificationChance", 0.1, 0, 1);
		lootSpawn = builder
				.comment("Remove enchanted books from loot pools?")
				.translation(Main.MODID + "config.lootSpawn")
				.define("lootSpawn", false);
		builder.pop();
		
	}
	
}
