package com.simplyspawners.blocks;

import com.simplyspawners.Main;
import com.simplyspawners.SimplySpawnersTab;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockLoader {
	
	/** BLOCKS **/
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
	
	public static final RegistryObject<Block> EMPTY_SPAWNER = BLOCKS.register("emptyspawner", () -> new BlockEmptySpawner(Block.Properties.of(Material.STONE).sound(SoundType.METAL).strength(5.0F).noOcclusion()));
	
	/** BLOCK ITEMS **/
	public static final DeferredRegister<Item> BLOCK_ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
	
	static final Item.Properties properties = new Item.Properties().tab(SimplySpawnersTab.SIMPLY_SPAWNERS);
	
	public static final RegistryObject<Item> EMPTY_SPAWNER_ITEM = BLOCK_ITEM.register("emptyspawner", () -> new BlockItem(EMPTY_SPAWNER.get(), properties));

}
