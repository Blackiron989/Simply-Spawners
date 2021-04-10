package com.simplyspawners.blocks;

import com.simplyspawners.Group;
import com.simplyspawners.Main;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockLoader {
	
	/** BLOCKS **/
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
	
	public static final RegistryObject<Block> EMPTY_SPAWNER = BLOCKS.register("emptyspawner", () -> new BlockEmptySpawner(Block.Properties.create(Material.ROCK).sound(SoundType.METAL).hardnessAndResistance(5.0F).notSolid()));
	
	/** BLOCK ITEMS **/
	public static final DeferredRegister<Item> BLOCK_ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
	
	static final Item.Properties properties = new Item.Properties().group(Group.SIMPLY_SPAWNERS);
	
	public static final RegistryObject<Item> EMPTY_SPAWNER_ITEM = BLOCK_ITEM.register("emptyspawner", () -> new BlockItem(EMPTY_SPAWNER.get(), properties));

}
