package com.simplyspawners.config;

import java.nio.file.Path;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.simplyspawners.Main;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

	public static final ForgeConfigSpec SERVER_SPEC;

	static final ServerConfig SERVER;

	static {
		
		{
			
			final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
			SERVER = specPair.getLeft();
			SERVER_SPEC = specPair.getRight();
			
		}
		
	}
	
	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		
		final CommentedFileConfig configdata = CommentedFileConfig.builder(path)
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();

		configdata.load();
		spec.setConfig(configdata);
		
	}
	
	public static final class Holder {
		
		public static List<? extends String> blacklistEggification;
		public static double eggificationChance;
		public static boolean lootSpawn;
		
		public static void load() {
			
			blacklistEggification = SERVER.blacklistEggification.get();
			eggificationChance = SERVER.eggificationChance.get();
			lootSpawn = SERVER.lootSpawn.get();
			
			Main.LOGGER.info("Simply Spawners blacklist eggification enchant mob egg drops = " + blacklistEggification.toString());
			Main.LOGGER.info("Simply Spawners eggification chance = " + eggificationChance);
			Main.LOGGER.info("Simply Spawners load loot pools = " + lootSpawn);
			
		}
		
	}
	
}
