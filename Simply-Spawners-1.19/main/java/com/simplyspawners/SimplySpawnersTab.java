package com.simplyspawners;

import com.simplyspawners.blocks.BlockLoader;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SimplySpawnersTab extends CreativeModeTab {

	public static final CreativeModeTab SIMPLY_SPAWNERS = (new SimplySpawnersTab(CreativeModeTab.TABS.length, "simplyspawners"));
	
	public SimplySpawnersTab(int index, String label) {
		
		super(index, label);

	}

	@Override
	public ItemStack makeIcon() {
		
		return new ItemStack(BlockLoader.EMPTY_SPAWNER_ITEM.get());
		
	}

}
