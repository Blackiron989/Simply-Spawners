package com.simplyspawners;

import com.simplyspawners.blocks.BlockLoader;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class Group extends ItemGroup {

	public static final ItemGroup SIMPLY_SPAWNERS = (new Group(ItemGroup.GROUPS.length, "simplyspawners"));
	
	public Group(int index, String label) {
		
		super(index, label);

	}
	
	@Override
	public ItemStack createIcon() {
		
		return new ItemStack(BlockLoader.EMPTY_SPAWNER_ITEM.get());
		
	}

}
